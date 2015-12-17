/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
	ISortedByteIntervalSet<ChildType> _elements;

	protected BaseOrderedCompositeByteRandomVariable(final Collection<? extends ChildType> elements)
	{
		_elements = new ByteIntervalTreeSet<ChildType>(elements);
	}

	public BaseOrderedCompositeByteRandomVariable()
	{
		_elements = new ByteIntervalTreeSet<ChildType>();
	}

	@Override
	protected ISortedByteIntervalSet<ChildType> getElements()
	{
		return _elements;
	}

	@Override
	public byte getLowerBound()
	{
		return getElements().first().getLowerBound();
	}

	@Override
	public byte getUpperBound()
	{
		return getElements().last().getUpperBound();
	}

	@Override
	public boolean isLowerThan(final byte value)
	{
		return getElements().last().isLowerThan(value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final byte value)
	{
		return getElements().last().isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean isGreaterThan(final byte value)
	{
		return getElements().first().isGreaterThan(value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final byte value)
	{
		return getElements().first().isGreaterThanOrEqualTo(value);
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
		return getElements().first().hasValueLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(final byte value)
	{
		return getElements().first().hasValueLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(final byte value)
	{
		return getElements().last().hasValueGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(final byte value)
	{
		return getElements().last().hasValueGreaterThanOrEqualTo(value);
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
		for(final ChildType element:getElements())
			r+=element.getProbabilityForLowerThan(value);
		return r;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final byte value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForLowerThanOrEqualTo(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThan(final byte value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForGreaterThan(value);
		return r;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final byte value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityForGreaterThanOrEqualTo(value);
		return r;
	}

	@Override
	public @Nullable IOrderedByteRandomVariable getLowerThan(final byte value)
	{
		final ArrayList<ReadOnlyOrderedByteRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedByteRandomVariableWrapper>(getElements().size());
		for(final ChildType element:getElements())
		{
			final ReadOnlyOrderedByteRandomVariableWrapper subElements = element.getLowerThan(value);
			if((subElements!=null)&&(!subElements.isEmpty()))
				r.add(subElements);
			else
			{
				if(!element.isEmpty())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeByteRandomVariable(this, r);
		return null;
	}

	@Override
	public @Nullable IOrderedByteRandomVariable getLowerThanOrEqualTo(final byte value)
	{
		final ArrayList<ReadOnlyOrderedByteRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedByteRandomVariableWrapper>(getElements().size());
		for(final ChildType element:getElements())
		{
			final ReadOnlyOrderedByteRandomVariableWrapper subElements = element.getLowerThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isEmpty()))
				r.add(subElements);
			else
			{
				if(!element.isEmpty())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeByteRandomVariable(this, r);
		return null;
	}

	@Override
	public @Nullable IOrderedByteRandomVariable getGreaterThan(final byte value)
	{
		final ArrayList<ReadOnlyOrderedByteRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedByteRandomVariableWrapper>(getElements().size());
		final Iterator<ChildType> it = getElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedByteRandomVariableWrapper subElements = element.getGreaterThan(value);
			if((subElements!=null)&&(!subElements.isEmpty()))
				r.add(subElements);
			else
			{
				if(!element.isEmpty())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeByteRandomVariable(this, r);
		return null;
	}

	@Override
	public @Nullable IOrderedByteRandomVariable getGreaterThanOrEqualTo(final byte value)
	{
		final ArrayList<ReadOnlyOrderedByteRandomVariableWrapper> r = new ArrayList<ReadOnlyOrderedByteRandomVariableWrapper>(getElements().size());
		final Iterator<ChildType> it = getElements().reverseIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			final ReadOnlyOrderedByteRandomVariableWrapper subElements = element.getGreaterThanOrEqualTo(value);
			if((subElements!=null)&&(!subElements.isEmpty()))
				r.add(subElements);
			else
			{
				if(!element.isEmpty())
					break;
			}
		}
		if(!r.isEmpty())
			return new ReadOnlyOrderedCompositeByteRandomVariable(this, r);
		return null;
	}
}