/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.ArrayList;
import java.util.Comparator;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateOrderedSet;
import com.ankamagames.dofus.harvey.engine.generic.comparators.DefaultUncheckedComparableComparator;
import com.ankamagames.dofus.harvey.generic.sets.classes.EmptyOrderedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IDegenerateOrderedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IOrderedGenericSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseDegenerateOrderedGenericSet<Data>
	extends AbstractDegenerateOrderedSet<IOrderedGenericSet<Data>>
	implements IDegenerateOrderedGenericSet<Data>
{
	@Nullable Data _value;
	protected Comparator<? super Data> _comparator;
	BridgedDegenerateOrderedGenericSetImplementation<Data, BaseDegenerateOrderedGenericSet<Data>> _bridgedImplementation = new BridgedDegenerateOrderedGenericSetImplementation<Data, BaseDegenerateOrderedGenericSet<Data>>(this);

	public static <Data> BaseDegenerateOrderedGenericSet<Data> makeSet(@Nullable final Data value)
	{
		return makeSet(value, DefaultUncheckedComparableComparator.getInstance());
	}

	public static <Data> BaseDegenerateOrderedGenericSet<Data> makeSet(@Nullable final Data value, final Comparator<? super Data> comparator)
	{
		return new BaseDegenerateOrderedGenericSet<Data>(value, comparator);
	}
	
	private BaseDegenerateOrderedGenericSet(@Nullable final Data value, final Comparator<? super Data> comparator)
	{
		_value = value;
		_comparator = comparator;
	}

	@Override
	protected IOrderedGenericSet<Data> getThis()
	{
		return this;
	}

	@Override
	protected IOrderedGenericSet<Data> getEmpty()
	{
		return EmptyOrderedGenericSet.getInstance();
	}
	
	@Override
	public boolean equals(@Nullable final Object obj)
	{
		final Data value = getValue();
		if(obj instanceof IOrderedGenericSet)
		{
			final IOrderedGenericSet<?> set = (IOrderedGenericSet<?>)obj;
			if(set.isDegenerate())
			{
				if(value!=null)
					return value.equals(set.getLowerBound());
				return set.getLowerBound()==null;
			}
		}
		if(value==null)
			return obj==null;
		
		return value.equals(obj);
	}
	
	@Override
	public boolean contains(@Nullable final Data value)
	{
		return _bridgedImplementation.contains(value);
	}
	
	@Override
	public Comparator<? super Data> getComparator()
	{
		return _comparator;
	}
	
	@Override
	@Nullable
	public Data getValue()
	{
		return _value;
	}

	@Override
	public @Nullable Data getLowerBound()
	{
		return getValue();
	}

	@Override
	public @Nullable Data getUpperBound()
	{
		return getValue();
	}

	@Override
	public boolean is(final SetCoveringMask mask, final IOrderedGenericSet<Data> set)
	{
		return _bridgedImplementation.is(mask, set);
	}

	@Override
	public boolean isInRange(final IOrderedGenericSet<Data> set)
	{
		return _bridgedImplementation.isInRange(set);
	}

	@Override
	public boolean isGreaterThan(final IOrderedGenericSet<Data> set)
	{
		return _bridgedImplementation.isGreaterThan(set);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final IOrderedGenericSet<Data> set)
	{
		return _bridgedImplementation.isGreaterThanOrEqualTo(set);
	}

	@Override
	public boolean isLowerThan(final IOrderedGenericSet<Data> set)
	{
		return _bridgedImplementation.isLowerThan(set);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final IOrderedGenericSet<Data> set)
	{
		return _bridgedImplementation.isLowerThanOrEqualTo(set);
	}

	@Override
	public ArrayList<IOrderedGenericSet<Data>> split(final Data[] values, final boolean[] isIntervalStart)
	{
		return _bridgedImplementation.split(values, isIntervalStart);
	}

	@Override
	public ArrayList<IOrderedGenericSet<Data>> split(final Data... values)
	{
		return _bridgedImplementation.split(values);
	}
	
	@Override
	public BaseOrderedGenericSet<Data, ? extends IDegenerateOrderedGenericSet<Data>> getMergedSet()
	{
		return _bridgedImplementation.getMergedSet();
	}
	
	@Override
	public String toString()
	{
		final Data value = getValue();
		if(value==null)
			return "null";
		return value.toString();
	}
}