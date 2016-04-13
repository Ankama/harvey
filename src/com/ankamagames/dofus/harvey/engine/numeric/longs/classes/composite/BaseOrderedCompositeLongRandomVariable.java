/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.sortedintervalset.MergeSortedIterator;
import com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite.sortedintervalset.ISortedLongIntervalSet;
import com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite.sortedintervalset.LongIntervalTreeSet;
import com.ankamagames.dofus.harvey.numeric.longs.interfaces.IOrderedLongRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedCompositeLongRandomVariable
<
	ChildType extends BaseOrderedLongRandomVariableWrapper<?, ?, ?>
>
extends AbstractCompositeLongRandomVariable<ChildType>
implements IOrderedLongRandomVariable
{
	ISortedLongIntervalSet<ChildType> _defaultElements;
	ISortedLongIntervalSet<ChildType> _otherElements;

	protected BaseOrderedCompositeLongRandomVariable(final Collection<? extends ChildType> defaultElements, final Collection<? extends ChildType> otherElements)
	{
		_defaultElements = new LongIntervalTreeSet<ChildType>(defaultElements);
		_otherElements = new LongIntervalTreeSet<ChildType>(otherElements);
	}

	public BaseOrderedCompositeLongRandomVariable()
	{
		_defaultElements = new LongIntervalTreeSet<ChildType>();
		_otherElements = new LongIntervalTreeSet<ChildType>();
	}

	@Override
	protected ISortedLongIntervalSet<ChildType> getDefaultElements()
	{
		return _defaultElements;
	}

	@Override
	protected ISortedLongIntervalSet<ChildType> getOtherElements()
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
	public long getLowerBound()
	{
		return childIterator().next().getLowerBound();
	}

	@Override
	public long getUpperBound()
	{
		return reverseIterator().next().getUpperBound();
	}

	@Override
	public boolean isLowerThan(final long value)
	{
		return reverseIterator().next().isLowerThan(value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final long value)
	{
		return reverseIterator().next().isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean isGreaterThan(final long value)
	{
		return childIterator().next().isGreaterThan(value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final long value)
	{
		return childIterator().next().isGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean isBetween(final long lowerBound, final boolean lowerBoundIncluded,
			final long upperBound, final boolean upperBoundIncluded)
	{
		return ((lowerBoundIncluded)?isGreaterThanOrEqualTo(lowerBound):isGreaterThan(lowerBound))&&((upperBoundIncluded)?isLowerThanOrEqualTo(upperBound):isLowerThan(upperBound));
	}

	@Override
	public boolean hasValueLowerThan(final long value)
	{
		return childIterator().next().hasValueLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(final long value)
	{
		return childIterator().next().hasValueLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(final long value)
	{
		return reverseIterator().next().hasValueGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(final long value)
	{
		return reverseIterator().next().hasValueGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueIn(final long lowerBound, final boolean lowerBoundIncluded,
			final long upperBound, final boolean upperBoundIncluded)
	{
		return ((lowerBoundIncluded)?hasValueGreaterThanOrEqualTo(lowerBound):hasValueGreaterThan(lowerBound))&&((upperBoundIncluded)?hasValueLowerThanOrEqualTo(upperBound):hasValueLowerThan(upperBound));
	}

	@Override
	public int getProbabilityForLowerThan(final long value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForLowerThan(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForLowerThan(value);
		return r;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final long value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForLowerThanOrEqualTo(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForLowerThanOrEqualTo(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThan(final long value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForGreaterThan(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForGreaterThan(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final long value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForGreaterThanOrEqualTo(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForGreaterThanOrEqualTo(value);
		return r;
	}

	@Override
	public @Nullable IOrderedLongRandomVariable getLowerThan(final long value)
	{
		final ArrayList<ReadOnlyOrderedLongRandomVariableWrapper> defaultElements = new ArrayList<ReadOnlyOrderedLongRandomVariableWrapper>(getDefaultElements().size());
		for(final ChildType element:getDefaultElements())
		{
			final ReadOnlyOrderedLongRandomVariableWrapper subElements = element.getLowerThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedLongRandomVariableWrapper> otherElements = new ArrayList<ReadOnlyOrderedLongRandomVariableWrapper>(getOtherElements().size());
		for(final ChildType element:getOtherElements())
		{
			final ReadOnlyOrderedLongRandomVariableWrapper subElements = element.getLowerThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeLongRandomVariable(this, defaultElements, otherElements);
		return null;
	}

	@Override
	public @Nullable IOrderedLongRandomVariable getLowerThanOrEqualTo(final long value)
	{
		final ArrayList<ReadOnlyOrderedLongRandomVariableWrapper> defaultElements = new ArrayList<ReadOnlyOrderedLongRandomVariableWrapper>(getDefaultElements().size());
		for(final ChildType element:getDefaultElements())
		{
			final ReadOnlyOrderedLongRandomVariableWrapper subElements = element.getLowerThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedLongRandomVariableWrapper> otherElements = new ArrayList<ReadOnlyOrderedLongRandomVariableWrapper>(getOtherElements().size());
		for(final ChildType element:getOtherElements())
		{
			final ReadOnlyOrderedLongRandomVariableWrapper subElements = element.getLowerThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeLongRandomVariable(this, defaultElements, otherElements);
		return null;
	}

	@Override
	public @Nullable IOrderedLongRandomVariable getGreaterThan(final long value)
	{
		final ArrayList<ReadOnlyOrderedLongRandomVariableWrapper> defaultElements = new ArrayList<ReadOnlyOrderedLongRandomVariableWrapper>(getDefaultElements().size());
		Iterator<ChildType> it = getDefaultElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedLongRandomVariableWrapper subElements = element.getGreaterThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedLongRandomVariableWrapper> otherElements = new ArrayList<ReadOnlyOrderedLongRandomVariableWrapper>(getOtherElements().size());
		it = getOtherElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedLongRandomVariableWrapper subElements = element.getGreaterThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeLongRandomVariable(this, defaultElements, otherElements);
		return null;
	}

	@Override
	public @Nullable IOrderedLongRandomVariable getGreaterThanOrEqualTo(final long value)
	{
		final ArrayList<ReadOnlyOrderedLongRandomVariableWrapper> defaultElements = new ArrayList<ReadOnlyOrderedLongRandomVariableWrapper>(getDefaultElements().size());
		Iterator<ChildType> it = getDefaultElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedLongRandomVariableWrapper subElements = element.getGreaterThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedLongRandomVariableWrapper> otherElements = new ArrayList<ReadOnlyOrderedLongRandomVariableWrapper>(getOtherElements().size());
		it = getOtherElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedLongRandomVariableWrapper subElements = element.getGreaterThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeLongRandomVariable(this, defaultElements, otherElements);
		return null;
	}
}