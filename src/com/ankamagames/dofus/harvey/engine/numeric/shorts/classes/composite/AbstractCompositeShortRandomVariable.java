/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.AbstractCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.exceptions.MultipleValuesException;
import com.ankamagames.dofus.harvey.numeric.shorts.interfaces.IShortRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractCompositeShortRandomVariable<ChildType extends BaseShortRandomVariableWrapper<?, ?, ?>>
extends AbstractCompositeRandomVariable<ChildType>
implements IShortRandomVariable
{
	@Override
	abstract protected Collection<ChildType> getElements();

	@Override
	public int getProbabilityOf(final short value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityOf(value);
		return r;
	}

	@Override
	public boolean contains(final short value)
	{
		for(final ChildType element:getElements())
			if(element.contains(value))
				return true;
		return false;
	}

	@Override
	protected boolean checkValues(final ChildType firstElement, final ChildType element)
	{
		return firstElement.getOnlyValue() == element.getOnlyValue();
	}
	@Override
	public boolean containsOnly(final short value)
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
	public short getOnlyValue() throws MultipleValuesException
	{
		if(isKnown())
			return getElements().iterator().next().getOnlyValue();
		throw new MultipleValuesException();
	}
}