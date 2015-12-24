/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite.sortedintervalset.FloatIntervalTreeSet;
import com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite.sortedintervalset.ISortedFloatIntervalSet;
import com.ankamagames.dofus.harvey.numeric.floats.interfaces.IOrderedFloatRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedCompositeFloatRandomVariable
<
	ChildType extends BaseOrderedFloatRandomVariableWrapper<?, ?, ?>
>
extends AbstractCompositeFloatRandomVariable<ChildType>
implements IOrderedFloatRandomVariable
{
	ISortedFloatIntervalSet<ChildType> _elements;

	protected BaseOrderedCompositeFloatRandomVariable(final Collection<? extends ChildType> elements)
	{
		_elements = new FloatIntervalTreeSet<ChildType>(elements);
	}

	public BaseOrderedCompositeFloatRandomVariable()
	{
		_elements = new FloatIntervalTreeSet<ChildType>();
	}

	@Override
	protected ISortedFloatIntervalSet<ChildType> getElements()
	{
		return _elements;
	}

	@Override
	public float getLowerBound()
	{
		return getElements().first().getLowerBound();
	}

	@Override
	public float getUpperBound()
	{
		return getElements().last().getUpperBound();
	}

	@Override
	public boolean isLowerThan(final float value)
	{
		return getElements().last().isLowerThan(value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final float value)
	{
		return getElements().last().isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean isGreaterThan(final float value)
	{
		return getElements().first().isGreaterThan(value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final float value)
	{
		return getElements().first().isGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean isBetween(final float lowerBound, final boolean lowerBoundIncluded,
			final float upperBound, final boolean upperBoundIncluded)
	{
		return ((lowerBoundIncluded)?isGreaterThanOrEqualTo(lowerBound):isGreaterThan(lowerBound))&&((upperBoundIncluded)?isLowerThanOrEqualTo(upperBound):isLowerThan(upperBound));
	}

	@Override
	public boolean hasValueLowerThan(final float value)
	{
		return getElements().first().hasValueLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(final float value)
	{
		return getElements().first().hasValueLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(final float value)
	{
		return getElements().last().hasValueGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(final float value)
	{
		return getElements().last().hasValueGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueIn(final float lowerBound, final boolean lowerBoundIncluded,
			final float upperBound, final boolean upperBoundIncluded)
	{
		return ((lowerBoundIncluded)?hasValueGreaterThanOrEqualTo(lowerBound):hasValueGreaterThan(lowerBound))&&((upperBoundIncluded)?hasValueLowerThanOrEqualTo(upperBound):hasValueLowerThan(upperBound));
	}

	@Override
	public int getProbabilityForLowerThan(final float value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForLowerThan(value);
		return r;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final float value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForLowerThanOrEqualTo(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThan(final float value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForGreaterThan(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final float value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForGreaterThanOrEqualTo(value);
		return r;
	}

	@Override
	public @Nullable IOrderedFloatRandomVariable getLowerThan(final float value)
	{
		final ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper>(getElements().size());
		for(final ChildType element:getElements())
		{
			final ReadOnlyOrderedFloatRandomVariableWrapper subElements = element.getLowerThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				r.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeFloatRandomVariable(this, r);
		return null;
	}

	@Override
	public @Nullable IOrderedFloatRandomVariable getLowerThanOrEqualTo(final float value)
	{
		final ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper>(getElements().size());
		for(final ChildType element:getElements())
		{
			final ReadOnlyOrderedFloatRandomVariableWrapper subElements = element.getLowerThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				r.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeFloatRandomVariable(this, r);
		return null;
	}

	@Override
	public @Nullable IOrderedFloatRandomVariable getGreaterThan(final float value)
	{
		final ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper>(getElements().size());
		final Iterator<ChildType> it = getElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedFloatRandomVariableWrapper subElements = element.getGreaterThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				r.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeFloatRandomVariable(this, r);
		return null;
	}

	@Override
	public @Nullable IOrderedFloatRandomVariable getGreaterThanOrEqualTo(final float value)
	{
		final ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper>(getElements().size());
		final Iterator<ChildType> it = getElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedFloatRandomVariableWrapper subElements = element.getGreaterThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				r.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeFloatRandomVariable(this, r);
		return null;
	}
}