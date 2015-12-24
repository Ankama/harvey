/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.AbstractCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.exceptions.MultipleValuesException;
import com.ankamagames.dofus.harvey.numeric.longs.interfaces.ILongRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractCompositeLongRandomVariable<ChildType extends BaseLongRandomVariableWrapper<?, ?, ?>>
extends AbstractCompositeRandomVariable<ChildType>
implements ILongRandomVariable
{
	@Override
	abstract protected Collection<ChildType> getElements();

	@Override
	public int getProbabilityOf(final long value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityOf(value);
		return r;
	}

	@Override
	public boolean contains(final long value)
	{
		for(final ChildType element:getElements())
			if(element.contains(value))
				return true;
		return false;
	}

	@Override
	protected boolean checkValues(final ChildType firstElement, final ChildType element)
	{
		return firstElement.getOnlyValue()==element.getOnlyValue();
	}

	@Override
	public boolean containsOnly(final long value)
	{
		boolean contains = false;
		for(final ChildType element:getElements())
		{
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
	public long getOnlyValue() throws MultipleValuesException
	{
		if(isKnown())
			return getElements().iterator().next().getOnlyValue();
		throw new MultipleValuesException();
	}
}