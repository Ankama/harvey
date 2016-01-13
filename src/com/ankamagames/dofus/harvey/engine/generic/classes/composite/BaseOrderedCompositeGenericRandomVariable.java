/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.sortedintervalset.MergeSortedIterator;
import com.ankamagames.dofus.harvey.engine.generic.classes.composite.sortedintervalset.GenericIntervalTreeSet;
import com.ankamagames.dofus.harvey.engine.generic.classes.composite.sortedintervalset.ISortedGenericIntervalSet;
import com.ankamagames.dofus.harvey.generic.interfaces.IOrderedGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedCompositeGenericRandomVariable
<
	Data,
	ChildType extends BaseOrderedGenericRandomVariableWrapper<Data, ?, ?, ?>
>
extends AbstractCompositeGenericRandomVariable<Data, ChildType>
implements IOrderedGenericRandomVariable<Data>
{
	protected @Nullable Comparator<? super Data> _comparator;
	ISortedGenericIntervalSet<ChildType, Data> _defaultElements;
	ISortedGenericIntervalSet<ChildType, Data> _otherElements;

	protected BaseOrderedCompositeGenericRandomVariable(final Collection<? extends ChildType> defaultElements, final Collection<? extends ChildType> otherElements, final@Nullable Comparator<? super Data> comparator)
	{
		_comparator = comparator;
		if(comparator!=null)
		{
			_defaultElements = new GenericIntervalTreeSet<ChildType, Data>(_comparator);
			_defaultElements.addAll(defaultElements);
			_otherElements = new GenericIntervalTreeSet<ChildType, Data>(_comparator);
			_otherElements.addAll(otherElements);
		}
		else
		{
			_defaultElements = new GenericIntervalTreeSet<ChildType, Data>(defaultElements);
			_otherElements = new GenericIntervalTreeSet<ChildType, Data>(otherElements);
		}
	}

	protected BaseOrderedCompositeGenericRandomVariable(final Collection<? extends ChildType> defaultElements, final Collection<? extends ChildType> otherElements)
	{
		_defaultElements = new GenericIntervalTreeSet<ChildType, Data>(defaultElements);
		_otherElements = new GenericIntervalTreeSet<ChildType, Data>(otherElements);
		_comparator = null;
	}

	public BaseOrderedCompositeGenericRandomVariable(final@Nullable Comparator<? super Data> comparator)
	{
		_comparator = comparator;
		_defaultElements = new GenericIntervalTreeSet<ChildType, Data>();
		_otherElements = new GenericIntervalTreeSet<ChildType, Data>();
	}

	public BaseOrderedCompositeGenericRandomVariable()
	{
		_comparator = null;
		_defaultElements = new GenericIntervalTreeSet<ChildType, Data>();
		_otherElements = new GenericIntervalTreeSet<ChildType, Data>();
	}

	@Override
	protected ISortedGenericIntervalSet<ChildType, Data> getDefaultElements()
	{
		return _defaultElements;
	}

	@Override
	protected ISortedGenericIntervalSet<ChildType, Data> getOtherElements()
	{
		return _otherElements;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Iterator<ChildType> iterator()
	{
		return new MergeSortedIterator<ChildType>(getDefaultElements(), getOtherElements());
	}

	@SuppressWarnings("unchecked")
	protected Iterator<ChildType> reverseIterator()
	{
		return new MergeSortedIterator<ChildType>(getDefaultElements().reversecomparator(), getDefaultElements().reverseIterator(), getOtherElements().reverseIterator());
	}

	@Override
	public @Nullable Comparator<? super Data> getComparator()
	{
		return _comparator;
	}

	@Override
	public @Nullable Data getLowerBound()
	{
		return iterator().next().getLowerBound();
	}

	@Override
	public @Nullable Data getUpperBound()
	{
		return reverseIterator().next().getUpperBound();
	}

	@Override
	public boolean isLowerThan(@Nullable final Data value)
	{
		return reverseIterator().next().isLowerThan(value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(@Nullable final Data value)
	{
		return reverseIterator().next().isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean isGreaterThan(@Nullable final Data value)
	{
		return iterator().next().isGreaterThan(value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@Nullable final Data value)
	{
		return iterator().next().isGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean isBetween(@Nullable final Data lowerBound, final boolean lowerBoundIncluded,
			@Nullable final Data upperBound, final boolean upperBoundIncluded)
	{
		return ((lowerBoundIncluded)?isGreaterThanOrEqualTo(lowerBound):isGreaterThan(lowerBound))&&((upperBoundIncluded)?isLowerThanOrEqualTo(upperBound):isLowerThan(upperBound));
	}

	@Override
	public boolean hasValueLowerThan(@Nullable final Data value)
	{
		return iterator().next().hasValueLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(@Nullable final Data value)
	{
		return iterator().next().hasValueLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(@Nullable final Data value)
	{
		return reverseIterator().next().hasValueGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(@Nullable final Data value)
	{
		return reverseIterator().next().hasValueGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueIn(@Nullable final Data lowerBound, final boolean lowerBoundIncluded,
			@Nullable final Data upperBound, final boolean upperBoundIncluded)
	{
		return ((lowerBoundIncluded)?hasValueGreaterThanOrEqualTo(lowerBound):hasValueGreaterThan(lowerBound))&&((upperBoundIncluded)?hasValueLowerThanOrEqualTo(upperBound):hasValueLowerThan(upperBound));
	}

	@Override
	public int getProbabilityForLowerThan(@Nullable final Data value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForLowerThan(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForLowerThan(value);
		return r;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(@Nullable final Data value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForLowerThanOrEqualTo(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForLowerThanOrEqualTo(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThan(@Nullable final Data value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForGreaterThan(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForGreaterThan(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(@Nullable final Data value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForGreaterThanOrEqualTo(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForGreaterThanOrEqualTo(value);
		return r;
	}

	@Override
	public @Nullable IOrderedGenericRandomVariable<Data> getLowerThan(@Nullable final Data value)
	{
		final ArrayList<ReadOnlyOrderedGenericRandomVariableWrapper<Data>> defaultElements = new ArrayList<ReadOnlyOrderedGenericRandomVariableWrapper<Data>>(getDefaultElements().size());
		for(final ChildType element:getDefaultElements())
		{
			final ReadOnlyOrderedGenericRandomVariableWrapper<Data> subElements = element.getLowerThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedGenericRandomVariableWrapper<Data>> otherElements = new ArrayList<ReadOnlyOrderedGenericRandomVariableWrapper<Data>>(getOtherElements().size());
		for(final ChildType element:getOtherElements())
		{
			final ReadOnlyOrderedGenericRandomVariableWrapper<Data> subElements = element.getLowerThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeGenericRandomVariable<Data>(this, defaultElements, otherElements);
		return null;
	}

	@Override
	public @Nullable IOrderedGenericRandomVariable<Data> getLowerThanOrEqualTo(@Nullable final Data value)
	{
		final ArrayList<ReadOnlyOrderedGenericRandomVariableWrapper<Data>> defaultElements = new ArrayList<ReadOnlyOrderedGenericRandomVariableWrapper<Data>>(getDefaultElements().size());
		for(final ChildType element:getDefaultElements())
		{
			final ReadOnlyOrderedGenericRandomVariableWrapper<Data> subElements = element.getLowerThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedGenericRandomVariableWrapper<Data>> otherElements = new ArrayList<ReadOnlyOrderedGenericRandomVariableWrapper<Data>>(getOtherElements().size());
		for(final ChildType element:getOtherElements())
		{
			final ReadOnlyOrderedGenericRandomVariableWrapper<Data> subElements = element.getLowerThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeGenericRandomVariable<Data>(this, defaultElements, otherElements);
		return null;
	}

	@Override
	public @Nullable IOrderedGenericRandomVariable<Data> getGreaterThan(@Nullable final Data value)
	{
		final ArrayList<ReadOnlyOrderedGenericRandomVariableWrapper<Data>> defaultElements = new ArrayList<ReadOnlyOrderedGenericRandomVariableWrapper<Data>>(getDefaultElements().size());
		Iterator<ChildType> it = getDefaultElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedGenericRandomVariableWrapper<Data> subElements = element.getGreaterThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedGenericRandomVariableWrapper<Data>> otherElements = new ArrayList<ReadOnlyOrderedGenericRandomVariableWrapper<Data>>(getOtherElements().size());
		it = getOtherElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedGenericRandomVariableWrapper<Data> subElements = element.getGreaterThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeGenericRandomVariable<Data>(this, defaultElements, otherElements);
		return null;
	}

	@Override
	public @Nullable IOrderedGenericRandomVariable<Data> getGreaterThanOrEqualTo(@Nullable final Data value)
	{
		final ArrayList<ReadOnlyOrderedGenericRandomVariableWrapper<Data>> defaultElements = new ArrayList<ReadOnlyOrderedGenericRandomVariableWrapper<Data>>(getDefaultElements().size());
		Iterator<ChildType> it = getDefaultElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedGenericRandomVariableWrapper<Data> subElements = element.getGreaterThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedGenericRandomVariableWrapper<Data>> otherElements = new ArrayList<ReadOnlyOrderedGenericRandomVariableWrapper<Data>>(getOtherElements().size());
		it = getOtherElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedGenericRandomVariableWrapper<Data> subElements = element.getGreaterThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeGenericRandomVariable<Data>(this, defaultElements, otherElements);
		return null;
	}
}