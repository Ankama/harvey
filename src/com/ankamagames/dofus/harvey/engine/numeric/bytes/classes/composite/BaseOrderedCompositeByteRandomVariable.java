/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.sortedintervalset.MergeSortedIterator;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite.sortedintervalset.ByteIntervalTreeSet;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite.sortedintervalset.ISortedByteIntervalSet;
import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IOrderedByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedCompositeByteRandomVariable
<
	ChildType extends BaseOrderedByteRandomVariableWrapper<?, ?, ?>
>
extends AbstractCompositeByteRandomVariable<ChildType>
implements IOrderedByteRandomVariable
{
	ISortedByteIntervalSet<ChildType> _defaultElements;
	ISortedByteIntervalSet<ChildType> _otherElements;

	protected BaseOrderedCompositeByteRandomVariable(final Collection<? extends ChildType> defaultElements, final Collection<? extends ChildType> otherElements)
	{
		_defaultElements = new ByteIntervalTreeSet<ChildType>(defaultElements);
		_otherElements = new ByteIntervalTreeSet<ChildType>(otherElements);
	}

	public BaseOrderedCompositeByteRandomVariable()
	{
		_defaultElements = new ByteIntervalTreeSet<ChildType>();
		_otherElements = new ByteIntervalTreeSet<ChildType>();
	}

	@Override
	protected ISortedByteIntervalSet<ChildType> getDefaultElements()
	{
		return _defaultElements;
	}

	@Override
	protected ISortedByteIntervalSet<ChildType> getOtherElements()
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
	public byte getLowerBound()
	{
		return childIterator().next().getLowerBound();
	}

	@Override
	public byte getUpperBound()
	{
		return reverseIterator().next().getUpperBound();
	}

	@Override
	public boolean isLowerThan(final byte value)
	{
		return reverseIterator().next().isLowerThan(value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final byte value)
	{
		return reverseIterator().next().isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean isGreaterThan(final byte value)
	{
		return childIterator().next().isGreaterThan(value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final byte value)
	{
		return childIterator().next().isGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean isBetween(final byte lowerBound, final boolean lowerBoundIncluded,
			final byte upperBound, final boolean upperBoundIncluded)
	{
		return ((lowerBoundIncluded)?isGreaterThanOrEqualTo(lowerBound):isGreaterThan(lowerBound))&&((upperBoundIncluded)?isLowerThanOrEqualTo(upperBound):isLowerThan(upperBound));
	}

	@Override
	public boolean hasValueLowerThan(final byte value)
	{
		return childIterator().next().hasValueLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(final byte value)
	{
		return childIterator().next().hasValueLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(final byte value)
	{
		return reverseIterator().next().hasValueGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(final byte value)
	{
		return reverseIterator().next().hasValueGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueIn(final byte lowerBound, final boolean lowerBoundIncluded,
			final byte upperBound, final boolean upperBoundIncluded)
	{
		return ((lowerBoundIncluded)?hasValueGreaterThanOrEqualTo(lowerBound):hasValueGreaterThan(lowerBound))&&((upperBoundIncluded)?hasValueLowerThanOrEqualTo(upperBound):hasValueLowerThan(upperBound));
	}

	@Override
	public int getProbabilityForLowerThan(final byte value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForLowerThan(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForLowerThan(value);
		return r;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final byte value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForLowerThanOrEqualTo(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForLowerThanOrEqualTo(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThan(final byte value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForGreaterThan(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForGreaterThan(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final byte value)
	{
		int r = 0;
		for(final ChildType element:getDefaultElements())
			r+=element.getProbabilityForGreaterThanOrEqualTo(value);
		for(final ChildType element:getOtherElements())
			r+=element.getProbabilityForGreaterThanOrEqualTo(value);
		return r;
	}

	@Override
	public @Nullable IOrderedByteRandomVariable getLowerThan(final byte value)
	{
		final ArrayList<ReadOnlyOrderedByteRandomVariableWrapper> defaultElements = new ArrayList<ReadOnlyOrderedByteRandomVariableWrapper>(getDefaultElements().size());
		for(final ChildType element:getDefaultElements())
		{
			final ReadOnlyOrderedByteRandomVariableWrapper subElements = element.getLowerThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedByteRandomVariableWrapper> otherElements = new ArrayList<ReadOnlyOrderedByteRandomVariableWrapper>(getOtherElements().size());
		for(final ChildType element:getOtherElements())
		{
			final ReadOnlyOrderedByteRandomVariableWrapper subElements = element.getLowerThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeByteRandomVariable(this, defaultElements, otherElements);
		return null;
	}

	@Override
	public @Nullable IOrderedByteRandomVariable getLowerThanOrEqualTo(final byte value)
	{
		final ArrayList<ReadOnlyOrderedByteRandomVariableWrapper> defaultElements = new ArrayList<ReadOnlyOrderedByteRandomVariableWrapper>(getDefaultElements().size());
		for(final ChildType element:getDefaultElements())
		{
			final ReadOnlyOrderedByteRandomVariableWrapper subElements = element.getLowerThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedByteRandomVariableWrapper> otherElements = new ArrayList<ReadOnlyOrderedByteRandomVariableWrapper>(getOtherElements().size());
		for(final ChildType element:getOtherElements())
		{
			final ReadOnlyOrderedByteRandomVariableWrapper subElements = element.getLowerThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeByteRandomVariable(this, defaultElements, otherElements);
		return null;
	}

	@Override
	public @Nullable IOrderedByteRandomVariable getGreaterThan(final byte value)
	{
		final ArrayList<ReadOnlyOrderedByteRandomVariableWrapper> defaultElements = new ArrayList<ReadOnlyOrderedByteRandomVariableWrapper>(getDefaultElements().size());
		Iterator<ChildType> it = getDefaultElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedByteRandomVariableWrapper subElements = element.getGreaterThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedByteRandomVariableWrapper> otherElements = new ArrayList<ReadOnlyOrderedByteRandomVariableWrapper>(getOtherElements().size());
		it = getOtherElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedByteRandomVariableWrapper subElements = element.getGreaterThan(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeByteRandomVariable(this, defaultElements, otherElements);
		return null;
	}

	@Override
	public @Nullable IOrderedByteRandomVariable getGreaterThanOrEqualTo(final byte value)
	{
		final ArrayList<ReadOnlyOrderedByteRandomVariableWrapper> defaultElements = new ArrayList<ReadOnlyOrderedByteRandomVariableWrapper>(getDefaultElements().size());
		Iterator<ChildType> it = getDefaultElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedByteRandomVariableWrapper subElements = element.getGreaterThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				defaultElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		final ArrayList<ReadOnlyOrderedByteRandomVariableWrapper> otherElements = new ArrayList<ReadOnlyOrderedByteRandomVariableWrapper>(getOtherElements().size());
		it = getOtherElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedByteRandomVariableWrapper subElements = element.getGreaterThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isUnknown()))
				otherElements.add(subElements);
			else
			{
				if(!element.isUnknown())
					break;
			}
		}

		if((!defaultElements.isEmpty())||(!otherElements.isEmpty()))
			return new ReadOnlyOrderedCompositeByteRandomVariable(this, defaultElements, otherElements);
		return null;
	}
}