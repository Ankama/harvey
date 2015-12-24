/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
	ISortedLongIntervalSet<ChildType> _elements;

	protected BaseOrderedCompositeLongRandomVariable(final Collection<? extends ChildType> elements)
	{
		_elements = new LongIntervalTreeSet<ChildType>(elements);
	}

	public BaseOrderedCompositeLongRandomVariable()
	{
		_elements = new LongIntervalTreeSet<ChildType>();
	}

	@Override
	protected ISortedLongIntervalSet<ChildType> getElements()
	{
		return _elements;
	}

	@Override
	public long getLowerBound()
	{
		return getElements().first().getLowerBound();
	}

	@Override
	public long getUpperBound()
	{
		return getElements().last().getUpperBound();
	}

	@Override
	public boolean isLowerThan(final long value)
	{
		return getElements().last().isLowerThan(value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final long value)
	{
		return getElements().last().isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean isGreaterThan(final long value)
	{
		return getElements().first().isGreaterThan(value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final long value)
	{
		return getElements().first().isGreaterThanOrEqualTo(value);
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
		return getElements().first().hasValueLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(final long value)
	{
		return getElements().first().hasValueLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(final long value)
	{
		return getElements().last().hasValueGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(final long value)
	{
		return getElements().last().hasValueGreaterThanOrEqualTo(value);
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
		for(final ChildType element:getElements())
			r+=element.getProbabilityForLowerThan(value);
		return r;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final long value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForLowerThanOrEqualTo(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThan(final long value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForGreaterThan(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final long value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForGreaterThanOrEqualTo(value);
		return r;
	}

	@Override
	public @Nullable IOrderedLongRandomVariable getLowerThan(final long value)
	{
		final ArrayList<ReadOnlyOrderedLongRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedLongRandomVariableWrapper>(getElements().size());
		for(final ChildType element:getElements())
		{
			final ReadOnlyOrderedLongRandomVariableWrapper subElements = element.getLowerThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				r.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeLongRandomVariable(this, r);
		return null;
	}

	@Override
	public @Nullable IOrderedLongRandomVariable getLowerThanOrEqualTo(final long value)
	{
		final ArrayList<ReadOnlyOrderedLongRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedLongRandomVariableWrapper>(getElements().size());
		for(final ChildType element:getElements())
		{
			final ReadOnlyOrderedLongRandomVariableWrapper subElements = element.getLowerThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				r.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeLongRandomVariable(this, r);
		return null;
	}

	@Override
	public @Nullable IOrderedLongRandomVariable getGreaterThan(final long value)
	{
		final ArrayList<ReadOnlyOrderedLongRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedLongRandomVariableWrapper>(getElements().size());
		final Iterator<ChildType> it = getElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedLongRandomVariableWrapper subElements = element.getGreaterThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				r.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeLongRandomVariable(this, r);
		return null;
	}

	@Override
	public @Nullable IOrderedLongRandomVariable getGreaterThanOrEqualTo(final long value)
	{
		final ArrayList<ReadOnlyOrderedLongRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedLongRandomVariableWrapper>(getElements().size());
		final Iterator<ChildType> it = getElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedLongRandomVariableWrapper subElements = element.getGreaterThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				r.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeLongRandomVariable(this, r);
		return null;
	}
}