/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.sortedintervalset.MergeSortedIterator;
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
	ISortedIntIntervalSet<ChildType> _defaultElements;
	ISortedIntIntervalSet<ChildType> _otherElements;

	protected BaseOrderedCompositeIntRandomVariable(final Collection<? extends ChildType> defaultElements, final Collection<? extends ChildType> otherElements)
	{
		_defaultElements = new IntIntervalTreeSet<ChildType>(defaultElements);
		_otherElements = new IntIntervalTreeSet<ChildType>(otherElements);
	}

	public BaseOrderedCompositeIntRandomVariable()
	{
		_defaultElements = new IntIntervalTreeSet<ChildType>();
		_otherElements = new IntIntervalTreeSet<ChildType>();
	}

	@Override
	protected ISortedIntIntervalSet<ChildType> getDefaultElements()
	{
		return _defaultElements;
	}

	@Override
	protected ISortedIntIntervalSet<ChildType> getOtherElements()
	{
		return _otherElements;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Iterator<ChildType> childIterator()
	{
		return new MergeSortedIterator<ChildType>(getDefaultElements(), getOtherElements());
	}

	@SuppressWarnings("unchecked")
	protected Iterator<ChildType> reverseIterator()
	{
		return new MergeSortedIterator<ChildType>(getDefaultElements().reversecomparator(), getDefaultElements().reverseIterator(), getOtherElements().reverseIterator());
	}

	@Override
	public int getLowerBound()
	{
		return childIterator().next().getLowerBound();
	}

	@Override
	public int getUpperBound()
	{
		return reverseIterator().next().getUpperBound();
	}

	@Override
	public boolean isLowerThan(final int value)
	{
		return reverseIterator().next().isLowerThan(value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final int value)
	{
		return reverseIterator().next().isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean isGreaterThan(final int value)
	{
		return childIterator().next().isGreaterThan(value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final int value)
	{
		return childIterator().next().isGreaterThanOrEqualTo(value);
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
		return childIterator().next().hasValueLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(final int value)
	{
		return childIterator().next().hasValueLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(final int value)
	{
		return reverseIterator().next().hasValueGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(final int value)
	{
		return reverseIterator().next().hasValueGreaterThanOrEqualTo(value);
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
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForLowerThan(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForLowerThan(value);
		return r;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final int value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForLowerThanOrEqualTo(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForLowerThanOrEqualTo(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThan(final int value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForGreaterThan(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForGreaterThan(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final int value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForGreaterThanOrEqualTo(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForGreaterThanOrEqualTo(value);
		return r;
	}

	@Override
	public @Nullable IOrderedIntRandomVariable getLowerThan(final int value)
	{
		final ArrayList<ReadOnlyOrderedIntRandomVariableWrapper> defaultElements = new ArrayList<ReadOnlyOrderedIntRandomVariableWrapper>(getDefaultElements().size());
		for(final ChildType element:getDefaultElements())
		{
			final ReadOnlyOrderedIntRandomVariableWrapper subElements = element.getLowerThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedIntRandomVariableWrapper> otherElements = new ArrayList<ReadOnlyOrderedIntRandomVariableWrapper>(getOtherElements().size());
		for(final ChildType element:getOtherElements())
		{
			final ReadOnlyOrderedIntRandomVariableWrapper subElements = element.getLowerThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeIntRandomVariable(this, defaultElements, otherElements);
		return null;
	}

	@Override
	public @Nullable IOrderedIntRandomVariable getLowerThanOrEqualTo(final int value)
	{
		final ArrayList<ReadOnlyOrderedIntRandomVariableWrapper> defaultElements = new ArrayList<ReadOnlyOrderedIntRandomVariableWrapper>(getDefaultElements().size());
		for(final ChildType element:getDefaultElements())
		{
			final ReadOnlyOrderedIntRandomVariableWrapper subElements = element.getLowerThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedIntRandomVariableWrapper> otherElements = new ArrayList<ReadOnlyOrderedIntRandomVariableWrapper>(getOtherElements().size());
		for(final ChildType element:getOtherElements())
		{
			final ReadOnlyOrderedIntRandomVariableWrapper subElements = element.getLowerThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeIntRandomVariable(this, defaultElements, otherElements);
		return null;
	}

	@Override
	public @Nullable IOrderedIntRandomVariable getGreaterThan(final int value)
	{
		final ArrayList<ReadOnlyOrderedIntRandomVariableWrapper> defaultElements = new ArrayList<ReadOnlyOrderedIntRandomVariableWrapper>(getDefaultElements().size());
		Iterator<ChildType> it = getDefaultElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedIntRandomVariableWrapper subElements = element.getGreaterThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedIntRandomVariableWrapper> otherElements = new ArrayList<ReadOnlyOrderedIntRandomVariableWrapper>(getOtherElements().size());
		it = getOtherElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedIntRandomVariableWrapper subElements = element.getGreaterThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeIntRandomVariable(this, defaultElements, otherElements);
		return null;
	}

	@Override
	public @Nullable IOrderedIntRandomVariable getGreaterThanOrEqualTo(final int value)
	{
		final ArrayList<ReadOnlyOrderedIntRandomVariableWrapper> defaultElements = new ArrayList<ReadOnlyOrderedIntRandomVariableWrapper>(getDefaultElements().size());
		Iterator<ChildType> it = getDefaultElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedIntRandomVariableWrapper subElements = element.getGreaterThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedIntRandomVariableWrapper> otherElements = new ArrayList<ReadOnlyOrderedIntRandomVariableWrapper>(getOtherElements().size());
		it = getOtherElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedIntRandomVariableWrapper subElements = element.getGreaterThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeIntRandomVariable(this, defaultElements, otherElements);
		return null;
	}
}