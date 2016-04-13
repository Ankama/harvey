/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.sortedintervalset.MergeSortedIterator;
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
	ISortedShortIntervalSet<ChildType> _defaultElements;
	ISortedShortIntervalSet<ChildType> _otherElements;

	protected BaseOrderedCompositeShortRandomVariable(final Collection<? extends ChildType> defaultElements, final Collection<? extends ChildType> otherElements)
	{
		_defaultElements = new ShortIntervalTreeSet<ChildType>(defaultElements);
		_otherElements = new ShortIntervalTreeSet<ChildType>(otherElements);
	}

	public BaseOrderedCompositeShortRandomVariable()
	{
		_defaultElements = new ShortIntervalTreeSet<ChildType>();
		_otherElements = new ShortIntervalTreeSet<ChildType>();
	}

	@Override
	protected ISortedShortIntervalSet<ChildType> getDefaultElements()
	{
		return _defaultElements;
	}

	@Override
	protected ISortedShortIntervalSet<ChildType> getOtherElements()
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
	public short getLowerBound()
	{
		return childIterator().next().getLowerBound();
	}

	@Override
	public short getUpperBound()
	{
		return reverseIterator().next().getUpperBound();
	}

	@Override
	public boolean isLowerThan(final short value)
	{
		return reverseIterator().next().isLowerThan(value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final short value)
	{
		return reverseIterator().next().isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean isGreaterThan(final short value)
	{
		return childIterator().next().isGreaterThan(value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final short value)
	{
		return childIterator().next().isGreaterThanOrEqualTo(value);
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
		return childIterator().next().hasValueLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(final short value)
	{
		return childIterator().next().hasValueLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(final short value)
	{
		return reverseIterator().next().hasValueGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(final short value)
	{
		return reverseIterator().next().hasValueGreaterThanOrEqualTo(value);
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
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForLowerThan(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForLowerThan(value);
		return r;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final short value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForLowerThanOrEqualTo(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForLowerThanOrEqualTo(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThan(final short value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForGreaterThan(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForGreaterThan(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final short value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForGreaterThanOrEqualTo(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForGreaterThanOrEqualTo(value);
		return r;
	}

	@Override
	public @Nullable IOrderedShortRandomVariable getLowerThan(final short value)
	{
		final ArrayList<ReadOnlyOrderedShortRandomVariableWrapper> defaultElements = new ArrayList<ReadOnlyOrderedShortRandomVariableWrapper>(getDefaultElements().size());
		for(final ChildType element:getDefaultElements())
		{
			final ReadOnlyOrderedShortRandomVariableWrapper subElements = element.getLowerThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedShortRandomVariableWrapper> otherElements = new ArrayList<ReadOnlyOrderedShortRandomVariableWrapper>(getOtherElements().size());
		for(final ChildType element:getOtherElements())
		{
			final ReadOnlyOrderedShortRandomVariableWrapper subElements = element.getLowerThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeShortRandomVariable(this, defaultElements, otherElements);
		return null;
	}

	@Override
	public @Nullable IOrderedShortRandomVariable getLowerThanOrEqualTo(final short value)
	{
		final ArrayList<ReadOnlyOrderedShortRandomVariableWrapper> defaultElements = new ArrayList<ReadOnlyOrderedShortRandomVariableWrapper>(getDefaultElements().size());
		for(final ChildType element:getDefaultElements())
		{
			final ReadOnlyOrderedShortRandomVariableWrapper subElements = element.getLowerThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedShortRandomVariableWrapper> otherElements = new ArrayList<ReadOnlyOrderedShortRandomVariableWrapper>(getOtherElements().size());
		for(final ChildType element:getOtherElements())
		{
			final ReadOnlyOrderedShortRandomVariableWrapper subElements = element.getLowerThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeShortRandomVariable(this, defaultElements, otherElements);
		return null;
	}

	@Override
	public @Nullable IOrderedShortRandomVariable getGreaterThan(final short value)
	{
		final ArrayList<ReadOnlyOrderedShortRandomVariableWrapper> defaultElements = new ArrayList<ReadOnlyOrderedShortRandomVariableWrapper>(getDefaultElements().size());
		Iterator<ChildType> it = getDefaultElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedShortRandomVariableWrapper subElements = element.getGreaterThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedShortRandomVariableWrapper> otherElements = new ArrayList<ReadOnlyOrderedShortRandomVariableWrapper>(getOtherElements().size());
		it = getOtherElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedShortRandomVariableWrapper subElements = element.getGreaterThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeShortRandomVariable(this, defaultElements, otherElements);
		return null;
	}

	@Override
	public @Nullable IOrderedShortRandomVariable getGreaterThanOrEqualTo(final short value)
	{
		final ArrayList<ReadOnlyOrderedShortRandomVariableWrapper> defaultElements = new ArrayList<ReadOnlyOrderedShortRandomVariableWrapper>(getDefaultElements().size());
		Iterator<ChildType> it = getDefaultElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedShortRandomVariableWrapper subElements = element.getGreaterThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedShortRandomVariableWrapper> otherElements = new ArrayList<ReadOnlyOrderedShortRandomVariableWrapper>(getOtherElements().size());
		it = getOtherElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedShortRandomVariableWrapper subElements = element.getGreaterThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeShortRandomVariable(this, defaultElements, otherElements);
		return null;
	}
}