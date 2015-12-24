/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite.sortedintervalset.DoubleIntervalTreeSet;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite.sortedintervalset.ISortedDoubleIntervalSet;
import com.ankamagames.dofus.harvey.numeric.doubles.interfaces.IOrderedDoubleRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedCompositeDoubleRandomVariable
<
	ChildType extends BaseOrderedDoubleRandomVariableWrapper<?, ?, ?>
>
extends AbstractCompositeDoubleRandomVariable<ChildType>
implements IOrderedDoubleRandomVariable
{
	ISortedDoubleIntervalSet<ChildType> _elements;

	protected BaseOrderedCompositeDoubleRandomVariable(final Collection<? extends ChildType> elements)
	{
		_elements = new DoubleIntervalTreeSet<ChildType>(elements);
	}

	public BaseOrderedCompositeDoubleRandomVariable()
	{
		_elements = new DoubleIntervalTreeSet<ChildType>();
	}

	@Override
	protected ISortedDoubleIntervalSet<ChildType> getElements()
	{
		return _elements;
	}

	@Override
	public double getLowerBound()
	{
		return getElements().first().getLowerBound();
	}

	@Override
	public double getUpperBound()
	{
		return getElements().last().getUpperBound();
	}

	@Override
	public boolean isLowerThan(final double value)
	{
		return getElements().last().isLowerThan(value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final double value)
	{
		return getElements().last().isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean isGreaterThan(final double value)
	{
		return getElements().first().isGreaterThan(value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final double value)
	{
		return getElements().first().isGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean isBetween(final double lowerBound, final boolean lowerBoundIncluded,
			final double upperBound, final boolean upperBoundIncluded)
	{
		return ((lowerBoundIncluded)?isGreaterThanOrEqualTo(lowerBound):isGreaterThan(lowerBound))&&((upperBoundIncluded)?isLowerThanOrEqualTo(upperBound):isLowerThan(upperBound));
	}

	@Override
	public boolean hasValueLowerThan(final double value)
	{
		return getElements().first().hasValueLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(final double value)
	{
		return getElements().first().hasValueLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(final double value)
	{
		return getElements().last().hasValueGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(final double value)
	{
		return getElements().last().hasValueGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueIn(final double lowerBound, final boolean lowerBoundIncluded,
			final double upperBound, final boolean upperBoundIncluded)
	{
		return ((lowerBoundIncluded)?hasValueGreaterThanOrEqualTo(lowerBound):hasValueGreaterThan(lowerBound))&&((upperBoundIncluded)?hasValueLowerThanOrEqualTo(upperBound):hasValueLowerThan(upperBound));
	}

	@Override
	public int getProbabilityForLowerThan(final double value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForLowerThan(value);
		return r;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final double value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForLowerThanOrEqualTo(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThan(final double value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForGreaterThan(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final double value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForGreaterThanOrEqualTo(value);
		return r;
	}

	@Override
	public @Nullable IOrderedDoubleRandomVariable getLowerThan(final double value)
	{
		final ArrayList<ReadOnlyOrderedDoubleRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedDoubleRandomVariableWrapper>(getElements().size());
		for(final ChildType element:getElements())
		{
			final ReadOnlyOrderedDoubleRandomVariableWrapper subElements = element.getLowerThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				r.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeDoubleRandomVariable(this, r);
		return null;
	}

	@Override
	public @Nullable IOrderedDoubleRandomVariable getLowerThanOrEqualTo(final double value)
	{
		final ArrayList<ReadOnlyOrderedDoubleRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedDoubleRandomVariableWrapper>(getElements().size());
		for(final ChildType element:getElements())
		{
			final ReadOnlyOrderedDoubleRandomVariableWrapper subElements = element.getLowerThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				r.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeDoubleRandomVariable(this, r);
		return null;
	}

	@Override
	public @Nullable IOrderedDoubleRandomVariable getGreaterThan(final double value)
	{
		final ArrayList<ReadOnlyOrderedDoubleRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedDoubleRandomVariableWrapper>(getElements().size());
		final Iterator<ChildType> it = getElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedDoubleRandomVariableWrapper subElements = element.getGreaterThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				r.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeDoubleRandomVariable(this, r);
		return null;
	}

	@Override
	public @Nullable IOrderedDoubleRandomVariable getGreaterThanOrEqualTo(final double value)
	{
		final ArrayList<ReadOnlyOrderedDoubleRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedDoubleRandomVariableWrapper>(getElements().size());
		final Iterator<ChildType> it = getElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedDoubleRandomVariableWrapper subElements = element.getGreaterThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				r.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeDoubleRandomVariable(this, r);
		return null;
	}
}