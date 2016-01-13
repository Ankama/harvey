/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite;

import java.util.Collection;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.AbstractCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.exceptions.MultipleValuesException;
import com.ankamagames.dofus.harvey.numeric.integers.interfaces.IIntRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractCompositeIntRandomVariable<ChildType extends BaseIntRandomVariableWrapper<?, ?, ?>>
extends AbstractCompositeRandomVariable<ChildType>
implements IIntRandomVariable
{
	@Override
	abstract protected Collection<ChildType> getDefaultElements();

	@Override
	abstract protected Collection<ChildType> getOtherElements();

	@Override
	protected Iterator<ChildType> iterator()
	{
		return super.iterator();
	}

	@Override
	public int getProbabilityOf(final int value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityOf(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityOf(value);
		return r;
	}

	@Override
	public boolean contains(final int value)
	{
		for(final ChildType element:getDefaultElements())
			if(element.contains(value))
				return true;
		for(final ChildType element:getOtherElements())
			if(element.contains(value))
				return true;
		return false;
	}

	@Override
	protected boolean checkValues(final ChildType firstElement, final ChildType element)
	{
		return (firstElement.getOnlyValue()==element.getOnlyValue());
	}

	@Override
	public boolean containsOnly(final int value)
	{
		boolean contains = false;
		final Iterator<ChildType> it = iterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			if(element.contains(value))
			{
				contains = true;
				if(!element.containsOnly(value))
					return false;
			}
			else if(!element.isUnknown())
				return false;
		}
		return contains;
	}

	@Override
	public int getOnlyValue()
	{
		final ChildType onlyValue = _getOnlyValue();
		if(onlyValue!=null)
			return onlyValue.getOnlyValue();
		throw new MultipleValuesException();
	}
}