/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite.sortedintervalset.ISortedShortIntervalSet;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite.sortedintervalset.ShortIntervalTreeSet;
import com.ankamagames.dofus.harvey.numeric.shorts.interfaces.IOrderedShortRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedCompositeShortRandomVariable
<
	ChildType extends BaseOrderedShortRandomVariableWrapper<?, ?, ?>
>
extends AbstractCompositeShortRandomVariable<ChildType>
implements IOrderedShortRandomVariable
{
	ISortedShortIntervalSet<ChildType> _elements;

	protected BaseOrderedCompositeShortRandomVariable(final Collection<? extends ChildType> elements)
	{
		_elements = new ShortIntervalTreeSet<ChildType>(elements);
	}

	public BaseOrderedCompositeShortRandomVariable()
	{
		_elements = new ShortIntervalTreeSet<ChildType>();
	}

	@Override
	protected ISortedShortIntervalSet<ChildType> getElements()
	{
		return _elements;
	}

	@Override
	public short getLowerBound()
	{
		return getElements().first().getLowerBound();
	}

	@Override
	public short getUpperBound()
	{
		return getElements().last().getUpperBound();
	}

	@Override
	public boolean isLowerThan(final short value)
	{
		return getElements().last().isLowerThan(value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final short value)
	{
		return getElements().last().isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean isGreaterThan(final short value)
	{
		return getElements().first().isGreaterThan(value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final short value)
	{
		return getElements().first().isGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean isBetween(final short lowerBound, final boolean lowerBoundIncluded,
			final short upperBound, final boolean upperBoundIncluded)
	{
		return ((lowerBoundIncluded)?isGreaterThanOrEqualTo(lowerBound):isGreaterThan(lowerBound))&&((upperBoundIncluded)?isLowerThanOrEqualTo(upperBound):isLowerThan(upperBound));
	}

	@Override
	public boolean hasValueLowerThan(final short value)
	{
		return getElements().first().hasValueLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(final short value)
	{
		return getElements().first().hasValueLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(final short value)
	{
		return getElements().last().hasValueGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(final short value)
	{
		return getElements().last().hasValueGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueIn(final short lowerBound, final boolean lowerBoundIncluded,
			final short upperBound, final boolean upperBoundIncluded)
	{
		return ((lowerBoundIncluded)?hasValueGreaterThanOrEqualTo(lowerBound):hasValueGreaterThan(lowerBound))&&((upperBoundIncluded)?hasValueLowerThanOrEqualTo(upperBound):hasValueLowerThan(upperBound));
	}

	@Override
	public int getProbabilityForLowerThan(final short value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForLowerThan(value);
		return r;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final short value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForLowerThanOrEqualTo(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThan(final short value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForGreaterThan(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final short value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForGreaterThanOrEqualTo(value);
		return r;
	}

	@Override
	public @Nullable IOrderedShortRandomVariable getLowerThan(final short value)
	{
		final ArrayList<ReadOnlyOrderedShortRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedShortRandomVariableWrapper>(getElements().size());
		for(final ChildType element:getElements())
		{
			final ReadOnlyOrderedShortRandomVariableWrapper subElements = element.getLowerThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				r.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeShortRandomVariable(this, r);
		return null;
	}

	@Override
	public @Nullable IOrderedShortRandomVariable getLowerThanOrEqualTo(final short value)
	{
		final ArrayList<ReadOnlyOrderedShortRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedShortRandomVariableWrapper>(getElements().size());
		for(final ChildType element:getElements())
		{
			final ReadOnlyOrderedShortRandomVariableWrapper subElements = element.getLowerThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				r.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeShortRandomVariable(this, r);
		return null;
	}

	@Override
	public @Nullable IOrderedShortRandomVariable getGreaterThan(final short value)
	{
		final ArrayList<ReadOnlyOrderedShortRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedShortRandomVariableWrapper>(getElements().size());
		final Iterator<ChildType> it = getElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedShortRandomVariableWrapper subElements = element.getGreaterThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				r.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeShortRandomVariable(this, r);
		return null;
	}

	@Override
	public @Nullable IOrderedShortRandomVariable getGreaterThanOrEqualTo(final short value)
	{
		final ArrayList<ReadOnlyOrderedShortRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedShortRandomVariableWrapper>(getElements().size());
		final Iterator<ChildType> it = getElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedShortRandomVariableWrapper subElements = element.getGreaterThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				r.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeShortRandomVariable(this, r);
		return null;
	}
}