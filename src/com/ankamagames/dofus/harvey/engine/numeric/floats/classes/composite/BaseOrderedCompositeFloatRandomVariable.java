/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.sortedintervalset.MergeSortedIterator;
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
	ISortedFloatIntervalSet<ChildType> _defaultElements;
	ISortedFloatIntervalSet<ChildType> _otherElements;

	protected BaseOrderedCompositeFloatRandomVariable(final Collection<? extends ChildType> defaultElements, final Collection<? extends ChildType> otherElements)
	{
		_defaultElements = new FloatIntervalTreeSet<ChildType>(defaultElements);
		_otherElements = new FloatIntervalTreeSet<ChildType>(otherElements);
	}

	public BaseOrderedCompositeFloatRandomVariable()
	{
		_defaultElements = new FloatIntervalTreeSet<ChildType>();
		_otherElements = new FloatIntervalTreeSet<ChildType>();
	}

	@Override
	protected ISortedFloatIntervalSet<ChildType> getDefaultElements()
	{
		return _defaultElements;
	}

	@Override
	protected ISortedFloatIntervalSet<ChildType> getOtherElements()
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
	public float getLowerBound()
	{
		return childIterator().next().getLowerBound();
	}

	@Override
	public float getUpperBound()
	{
		return reverseIterator().next().getUpperBound();
	}

	@Override
	public boolean isLowerThan(final float value)
	{
		return reverseIterator().next().isLowerThan(value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final float value)
	{
		return reverseIterator().next().isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean isGreaterThan(final float value)
	{
		return childIterator().next().isGreaterThan(value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final float value)
	{
		return childIterator().next().isGreaterThanOrEqualTo(value);
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
		return childIterator().next().hasValueLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(final float value)
	{
		return childIterator().next().hasValueLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(final float value)
	{
		return reverseIterator().next().hasValueGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(final float value)
	{
		return reverseIterator().next().hasValueGreaterThanOrEqualTo(value);
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
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForLowerThan(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForLowerThan(value);
		return r;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final float value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForLowerThanOrEqualTo(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForLowerThanOrEqualTo(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThan(final float value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForGreaterThan(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForGreaterThan(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final float value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForGreaterThanOrEqualTo(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForGreaterThanOrEqualTo(value);
		return r;
	}

	@Override
	public @Nullable IOrderedFloatRandomVariable getLowerThan(final float value)
	{
		final ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper> defaultElements = new ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper>(getDefaultElements().size());
		for(final ChildType element:getDefaultElements())
		{
			final ReadOnlyOrderedFloatRandomVariableWrapper subElements = element.getLowerThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper> otherElements = new ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper>(getOtherElements().size());
		for(final ChildType element:getOtherElements())
		{
			final ReadOnlyOrderedFloatRandomVariableWrapper subElements = element.getLowerThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeFloatRandomVariable(this, defaultElements, otherElements);
		return null;
	}

	@Override
	public @Nullable IOrderedFloatRandomVariable getLowerThanOrEqualTo(final float value)
	{
		final ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper> defaultElements = new ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper>(getDefaultElements().size());
		for(final ChildType element:getDefaultElements())
		{
			final ReadOnlyOrderedFloatRandomVariableWrapper subElements = element.getLowerThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper> otherElements = new ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper>(getOtherElements().size());
		for(final ChildType element:getOtherElements())
		{
			final ReadOnlyOrderedFloatRandomVariableWrapper subElements = element.getLowerThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeFloatRandomVariable(this, defaultElements, otherElements);
		return null;
	}

	@Override
	public @Nullable IOrderedFloatRandomVariable getGreaterThan(final float value)
	{
		final ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper> defaultElements = new ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper>(getDefaultElements().size());
		Iterator<ChildType> it = getDefaultElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedFloatRandomVariableWrapper subElements = element.getGreaterThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper> otherElements = new ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper>(getOtherElements().size());
		it = getOtherElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedFloatRandomVariableWrapper subElements = element.getGreaterThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeFloatRandomVariable(this, defaultElements, otherElements);
		return null;
	}

	@Override
	public @Nullable IOrderedFloatRandomVariable getGreaterThanOrEqualTo(final float value)
	{
		final ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper> defaultElements = new ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper>(getDefaultElements().size());
		Iterator<ChildType> it = getDefaultElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedFloatRandomVariableWrapper subElements = element.getGreaterThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper> otherElements = new ArrayList<ReadOnlyOrderedFloatRandomVariableWrapper>(getOtherElements().size());
		it = getOtherElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedFloatRandomVariableWrapper subElements = element.getGreaterThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeFloatRandomVariable(this, defaultElements, otherElements);
		return null;
	}
}