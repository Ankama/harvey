/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite;

import java.util.Collection;

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
	abstract protected Collection<ChildType> getElements();

	@Override
	public int getProbabilityOf(final int value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityOf(value);
		return r;
	}

	@Override
	public boolean contains(final int value)
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
	public boolean containsOnly(final int value)
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
	public int getOnlyValue() throws MultipleValuesException
	{
		if(isKnown())
			return getElements().iterator().next().getOnlyValue();
		throw new MultipleValuesException();
	}
}