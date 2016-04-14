/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.ArrayList;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateContinuousSet;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparator;
import com.ankamagames.dofus.harvey.engine.generic.comparators.DefaultUncheckedContinuousComparableComparator;
import com.ankamagames.dofus.harvey.generic.sets.classes.EmptyContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IDegenerateContinuousGenericSet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseDegenerateContinuousGenericSet<Data>
extends AbstractDegenerateContinuousSet<IContinuousGenericSet<Data>>
implements IDegenerateContinuousGenericSet<Data>
{
	@Nullable Data _value;
	protected ContinuousComparator<? super Data> _comparator;
	BridgedDegenerateContinuousGenericSetImplementation<Data, BaseDegenerateContinuousGenericSet<Data>> _bridgedImplementation = new BridgedDegenerateContinuousGenericSetImplementation<Data, BaseDegenerateContinuousGenericSet<Data>>(this);

	public static <Data> BaseDegenerateContinuousGenericSet<Data> makeSet(@Nullable final Data value)
	{
		return makeSet(value, DefaultUncheckedContinuousComparableComparator.getInstance());
	}

	public static <Data> BaseDegenerateContinuousGenericSet<Data> makeSet(@Nullable final Data value, final ContinuousComparator<? super Data> comparator)
	{
		return new BaseDegenerateContinuousGenericSet<Data>(value, comparator);
	}
	
	private BaseDegenerateContinuousGenericSet(@Nullable final Data value, final ContinuousComparator<? super Data> comparator)
	{
		_value = value;
		_comparator = comparator;
	}

	@Override
	protected IContinuousGenericSet<Data> getThis()
	{
		return this;
	}

	@Override
	protected EmptyContinuousGenericSet<Data> getEmpty()
	{
		return EmptyContinuousGenericSet.getInstance();
	}
	
	@Override
	public boolean equals(@Nullable final Object obj)
	{
		final Data value = getValue();
		if(obj instanceof IContinuousGenericSet)
		{
			final IContinuousGenericSet<?> set = (IContinuousGenericSet<?>)obj;
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
	public ContinuousComparator<? super Data> getComparator()
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
	public boolean isLowerBoundClosed()
	{
		return true;
	}

	@Override
	public boolean isUpperBoundClosed()
	{
		return true;
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
	public boolean is(final SetCoveringMask mask, final IContinuousGenericSet<Data> set)
	{
		return _bridgedImplementation.is(mask, set);
	}

	@Override
	public boolean isInRange(final IContinuousGenericSet<Data> set)
	{
		return _bridgedImplementation.isInRange(set);
	}

	@Override
	public boolean isGreaterThan(final IContinuousGenericSet<Data> set)
	{
		return _bridgedImplementation.isGreaterThan(set);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final IContinuousGenericSet<Data> set)
	{
		return _bridgedImplementation.isGreaterThanOrEqualTo(set);
	}

	@Override
	public boolean isLowerThan(final IContinuousGenericSet<Data> set)
	{
		return _bridgedImplementation.isLowerThan(set);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final IContinuousGenericSet<Data> set)
	{
		return _bridgedImplementation.isLowerThanOrEqualTo(set);
	}

	@Override
	public ArrayList<IContinuousGenericSet<Data>> split(final Data[] values, final boolean[] isIntervalStart)
	{
		return _bridgedImplementation.split(values, isIntervalStart);
	}

	@Override
	public ArrayList<IContinuousGenericSet<Data>> split(final Data... values)
	{
		return _bridgedImplementation.split(values);
	}
	
	@Override
	public BaseContinuousGenericSet<Data, ?> getMergedSet()
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