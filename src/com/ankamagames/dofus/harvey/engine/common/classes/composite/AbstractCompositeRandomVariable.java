/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes.composite;

import java.util.Collection;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.sortedintervalset.MergeIterator;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.classes.AbstractRandomVariable;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.engine.exceptions.ProbabilityOutOfBoundException;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

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
	abstract protected Collection<ChildType> getDefaultElements();
	abstract protected Collection<ChildType> getOtherElements();

	abstract protected boolean checkValues(ChildType firstElement, ChildType element);

	@SuppressWarnings("unchecked")
	protected Iterator<ChildType> childIterator()
	{
		return new MergeIterator<ChildType>(getDefaultElements(), getOtherElements());
	}

	public int size()
	{
		return getDefaultElements().size() + getOtherElements().size();
	}

	/**
	 *
	 * @return the first child containing the "only" value or null if there is not only one value. Becarful : it returns only the first child, there can be others.
	 */
	protected @Nullable ChildType _getOnlyValue()
	{
		final Iterator<ChildType> it = childIterator();
		boolean unknown = true;
		ChildType firstElement = null;
		while(it.hasNext())
		{
			firstElement = it.next();
			if(!firstElement.isUnknown())
			{
				unknown = false;
				if(!firstElement.hasOnlyOneValue())
					return null;
				break;
			}
		}
		if((firstElement==null)||(unknown))
			return null;

		while(it.hasNext())
		{
			final ChildType element = it.next();
			if((!element.isUnknown())&&
					((!element.hasOnlyOneValue())||(!checkValues(firstElement, element))))
				return null;
		}
		return firstElement;
	}

	@Override
	public boolean hasOnlyOneValue()
	{
		return _getOnlyValue()!=null;
	}

	@Override
	public int getKnownProbability()
	{
		long r = 0;
		for(final ChildType element:getDefaultElements())
		{
			r += element.getKnownProbability();
		}
		for(final ChildType element:getOtherElements())
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
		final Iterator<ChildType> it = childIterator();

		String r = it.next().toString();

		while(it.hasNext())
		{
			r += " ; " + it.next().toString();
		}

		return r;
	}
}