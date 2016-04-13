/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite;

import java.util.Collection;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.AbstractCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.exceptions.MultipleValuesException;
import com.ankamagames.dofus.harvey.numeric.floats.interfaces.IFloatRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractCompositeFloatRandomVariable<ChildType extends BaseFloatRandomVariableWrapper<?, ?, ?>>
extends AbstractCompositeRandomVariable<ChildType>
implements IFloatRandomVariable
{
	@Override
	abstract protected Collection<ChildType> getDefaultElements();

	@Override
	abstract protected Collection<ChildType> getOtherElements();

	@Override
	protected Iterator<ChildType> childIterator()
	{
		return super.childIterator();
	}

	@Override
	public int getProbabilityOf(final float value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityOf(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityOf(value);
		return r;
	}

	@Override
	public boolean contains(final float value)
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
	public boolean containsOnly(final float value)
	{
		boolean contains = false;
		final Iterator<ChildType> it = childIterator();
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
	public float getOnlyValue()
	{
		final ChildType onlyValue = _getOnlyValue();
		if(onlyValue!=null)
			return onlyValue.getOnlyValue();
		throw new MultipleValuesException();
	}
}