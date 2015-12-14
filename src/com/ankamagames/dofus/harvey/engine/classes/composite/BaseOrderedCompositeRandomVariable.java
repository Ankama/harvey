/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.classes.composite.sortedintervalset.ISortedIntervalSet;
import com.ankamagames.dofus.harvey.engine.classes.composite.sortedintervalset.IntervalTreeSet;
import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedCompositeRandomVariable
<
	Data,
	ChildType extends BaseOrderedRandomVariableWrapper<Data, ?, ?, ?>
>
extends AbstractCompositeRandomVariable<Data, ChildType>
implements IOrderedRandomVariable<Data>
{
	protected @Nullable Comparator<? super Data> _comparator;
	ISortedIntervalSet<ChildType, Data> _elements;

	protected BaseOrderedCompositeRandomVariable(final Collection<? extends ChildType> elements, final@Nullable Comparator<? super Data> comparator)
	{
		_comparator = comparator;
		_elements = new IntervalTreeSet<ChildType, Data>(_comparator);
		_elements.addAll(elements);
	}

	protected BaseOrderedCompositeRandomVariable(final Collection<? extends ChildType> elements)
	{
		_elements = new IntervalTreeSet<ChildType, Data>(elements);
		_comparator = null;
	}

	public BaseOrderedCompositeRandomVariable(final@Nullable Comparator<? super Data> comparator)
	{
		_comparator = comparator;
		_elements = new IntervalTreeSet<ChildType, Data>(_comparator);
	}

	public BaseOrderedCompositeRandomVariable()
	{
		_comparator = null;
		_elements = new IntervalTreeSet<ChildType, Data>();
	}

	@Override
	protected ISortedIntervalSet<ChildType, Data> getElements()
	{
		return _elements;
	}

	@Override
	public @Nullable Comparator<? super Data> getComparator()
	{
		return _comparator;
	}

	@Override
	public @Nullable Data getLowerBound()
	{
		return getElements().first().getLowerBound();
	}

	@Override
	public @Nullable Data getUpperBound()
	{
		return getElements().last().getUpperBound();
	}

	@Override
	public boolean isLowerThan(@Nullable final Data value)
	{
		return getElements().last().isLowerThan(value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(@Nullable final Data value)
	{
		return getElements().last().isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean isGreaterThan(@Nullable final Data value)
	{
		return getElements().first().isGreaterThan(value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@Nullable final Data value)
	{
		return getElements().first().isGreaterThanOrEqualTo(value);
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
		return getElements().first().hasValueLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(@Nullable final Data value)
	{
		return getElements().first().hasValueLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(@Nullable final Data value)
	{
		return getElements().last().hasValueGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(@Nullable final Data value)
	{
		return getElements().last().hasValueGreaterThanOrEqualTo(value);
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
		for(final ChildType element:getElements())
			r+=element.getProbabilityForLowerThan(value);
		return r;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(@Nullable final Data value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForLowerThanOrEqualTo(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThan(@Nullable final Data value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForGreaterThan(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(@Nullable final Data value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForGreaterThanOrEqualTo(value);
		return r;
	}

	@Override
	public @Nullable IOrderedRandomVariable<Data> getLowerThan(@Nullable final Data value)
	{
		final ArrayList<ReadOnlyOrderedRandomVariableWrapper<Data>> r = new ArrayList<ReadOnlyOrderedRandomVariableWrapper<Data>>(getElements().size());
		for(final ChildType element:getElements())
		{
			final ReadOnlyOrderedRandomVariableWrapper<Data> subElements = element.getLowerThan(value);
			if((subElements!=null)&&(!subElements.isEmpty()))
				r.add(subElements);
			else
			{
				if(!element.isEmpty())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeRandomVariable<Data>(this, r);
		return null;
	}

	@Override
	public @Nullable IOrderedRandomVariable<Data> getLowerThanOrEqualTo(@Nullable final Data value)
	{
		final ArrayList<ReadOnlyOrderedRandomVariableWrapper<Data>> r = new ArrayList<ReadOnlyOrderedRandomVariableWrapper<Data>>(getElements().size());
		for(final ChildType element:getElements())
		{
			final ReadOnlyOrderedRandomVariableWrapper<Data> subElements = element.getLowerThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isEmpty()))
				r.add(subElements);
			else
			{
				if(!element.isEmpty())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeRandomVariable<Data>(this, r);
		return null;
	}

	@Override
	public @Nullable IOrderedRandomVariable<Data> getGreaterThan(@Nullable final Data value)
	{
		final ArrayList<ReadOnlyOrderedRandomVariableWrapper<Data>> r = new ArrayList<ReadOnlyOrderedRandomVariableWrapper<Data>>(getElements().size());
		final Iterator<ChildType> it = getElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedRandomVariableWrapper<Data> subElements = element.getGreaterThan(value);
			if((subElements!=null)&&(!subElements.isEmpty()))
				r.add(subElements);
			else
			{
				if(!element.isEmpty())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeRandomVariable<Data>(this, r);
		return null;
	}

	@Override
	public @Nullable IOrderedRandomVariable<Data> getGreaterThanOrEqualTo(@Nullable final Data value)
	{
		final ArrayList<ReadOnlyOrderedRandomVariableWrapper<Data>> r = new ArrayList<ReadOnlyOrderedRandomVariableWrapper<Data>>(getElements().size());
		final Iterator<ChildType> it = getElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedRandomVariableWrapper<Data> subElements = element.getGreaterThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isEmpty()))
				r.add(subElements);
			else
			{
				if(!element.isEmpty())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeRandomVariable<Data>(this, r);
		return null;
	}
}