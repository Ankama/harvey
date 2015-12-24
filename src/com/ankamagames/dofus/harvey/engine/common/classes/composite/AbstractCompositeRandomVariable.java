/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes.composite;

import java.util.Collection;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.classes.AbstractRandomVariable;
import com.ankamagames.dofus.harvey.engine.common.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.engine.exceptions.ProbabilityOutOfBoundException;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractCompositeRandomVariable
<
	ChildType extends RandomVariableWrapper<?, ?, ?>
>
extends AbstractRandomVariable
implements IRandomVariable
{
	abstract protected Collection<ChildType> getElements();

	abstract protected boolean checkValues(ChildType firstElement, ChildType element);

	protected Iterator<ChildType> iterator()
	{
		return getElements().iterator();
	}

	public int size()
	{
		return getElements().size();
	}

	@Override
	public boolean hasOnlyOneValue()
	{
		final Iterator<ChildType> it = getElements().iterator();
		boolean unknown = true;
		ChildType firstElement = null;
		while(it.hasNext())
		{
			firstElement = it.next();
			if(!firstElement.isUnknown())
			{
				unknown = false;
				if(!firstElement.hasOnlyOneValue())
					return false;
				break;
			}
		}
		if((firstElement==null)||(unknown))
			return false;

		while(it.hasNext())
		{
			final ChildType element = it.next();
			if(!element.hasOnlyOneValue())
			{
				if(!checkValues(firstElement, element))
					return false;
			}
		}
		return true;
	}

	@Override
	public int getKnownProbability()
	{
		long r = 0;
		for(final ChildType element:getElements())
		{
			r += element.getKnownProbability();
		}
		if((r<=Integer.MAX_VALUE)&&(r>Integer.MIN_VALUE))
			return (int) r;
		throw new ProbabilityOutOfBoundException();
	}

	@Override
	protected String toStringValues()
	{
		final Iterator<ChildType> it = getElements().iterator();

		String r = it.next().toString();

		while(it.hasNext())
		{
			r += " ; " + it.next().toString();
		}

		return r;
	}
}