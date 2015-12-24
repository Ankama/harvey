/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite.sortedintervalset.ISortedIntIntervalSet;
import com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite.sortedintervalset.IntIntervalTreeSet;
import com.ankamagames.dofus.harvey.numeric.integers.interfaces.IOrderedIntRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedCompositeIntRandomVariable
<
	ChildType extends BaseOrderedIntRandomVariableWrapper<?, ?, ?>
>
extends AbstractCompositeIntRandomVariable<ChildType>
implements IOrderedIntRandomVariable
{
	ISortedIntIntervalSet<ChildType> _elements;

	protected BaseOrderedCompositeIntRandomVariable(final Collection<? extends ChildType> elements)
	{
		_elements = new IntIntervalTreeSet<ChildType>(elements);
	}

	public BaseOrderedCompositeIntRandomVariable()
	{
		_elements = new IntIntervalTreeSet<ChildType>();
	}

	@Override
	protected ISortedIntIntervalSet<ChildType> getElements()
	{
		return _elements;
	}

	@Override
	public int getLowerBound()
	{
		return getElements().first().getLowerBound();
	}

	@Override
	public int getUpperBound()
	{
		return getElements().last().getUpperBound();
	}

	@Override
	public boolean isLowerThan(final int value)
	{
		return getElements().last().isLowerThan(value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final int value)
	{
		return getElements().last().isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean isGreaterThan(final int value)
	{
		return getElements().first().isGreaterThan(value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final int value)
	{
		return getElements().first().isGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean isBetween(final int lowerBound, final boolean lowerBoundIncluded,
			final int upperBound, final boolean upperBoundIncluded)
	{
		return ((lowerBoundIncluded)?isGreaterThanOrEqualTo(lowerBound):isGreaterThan(lowerBound))&&((upperBoundIncluded)?isLowerThanOrEqualTo(upperBound):isLowerThan(upperBound));
	}

	@Override
	public boolean hasValueLowerThan(final int value)
	{
		return getElements().first().hasValueLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(final int value)
	{
		return getElements().first().hasValueLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(final int value)
	{
		return getElements().last().hasValueGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(final int value)
	{
		return getElements().last().hasValueGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueIn(final int lowerBound, final boolean lowerBoundIncluded,
			final int upperBound, final boolean upperBoundIncluded)
	{
		return ((lowerBoundIncluded)?hasValueGreaterThanOrEqualTo(lowerBound):hasValueGreaterThan(lowerBound))&&((upperBoundIncluded)?hasValueLowerThanOrEqualTo(upperBound):hasValueLowerThan(upperBound));
	}

	@Override
	public int getProbabilityForLowerThan(final int value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForLowerThan(value);
		return r;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final int value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForLowerThanOrEqualTo(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThan(final int value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForGreaterThan(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final int value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForGreaterThanOrEqualTo(value);
		return r;
	}

	@Override
	public @Nullable IOrderedIntRandomVariable getLowerThan(final int value)
	{
		final ArrayList<ReadOnlyOrderedIntRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedIntRandomVariableWrapper>(getElements().size());
		for(final ChildType element:getElements())
		{
			final ReadOnlyOrderedIntRandomVariableWrapper subElements = element.getLowerThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				r.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeIntRandomVariable(this, r);
		return null;
	}

	@Override
	public @Nullable IOrderedIntRandomVariable getLowerThanOrEqualTo(final int value)
	{
		final ArrayList<ReadOnlyOrderedIntRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedIntRandomVariableWrapper>(getElements().size());
		for(final ChildType element:getElements())
		{
			final ReadOnlyOrderedIntRandomVariableWrapper subElements = element.getLowerThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				r.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeIntRandomVariable(this, r);
		return null;
	}

	@Override
	public @Nullable IOrderedIntRandomVariable getGreaterThan(final int value)
	{
		final ArrayList<ReadOnlyOrderedIntRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedIntRandomVariableWrapper>(getElements().size());
		final Iterator<ChildType> it = getElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedIntRandomVariableWrapper subElements = element.getGreaterThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				r.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeIntRandomVariable(this, r);
		return null;
	}

	@Override
	public @Nullable IOrderedIntRandomVariable getGreaterThanOrEqualTo(final int value)
	{
		final ArrayList<ReadOnlyOrderedIntRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedIntRandomVariableWrapper>(getElements().size());
		final Iterator<ChildType> it = getElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedIntRandomVariableWrapper subElements = element.getGreaterThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				r.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeIntRandomVariable(this, r);
		return null;
	}
}