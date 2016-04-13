/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.ArrayList;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateContinuousInterval;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparator;
import com.ankamagames.dofus.harvey.engine.generic.comparators.DefaultUncheckedContinuousComparableComparator;
import com.ankamagames.dofus.harvey.generic.sets.classes.EmptyContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IDegenerateContinuousGenericInterval;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseDegenerateContinuousGenericInterval<Data>
	extends AbstractDegenerateContinuousInterval<IContinuousGenericSet<Data>, BridgedContinuousGenericBound<Data, BaseDegenerateContinuousGenericInterval<Data>>>
	implements IDegenerateContinuousGenericInterval<Data>
{
	protected ContinuousComparator<? super Data>				_comparator;
	BridgedDegenerateContinuousGenericSetImplementation<Data>	_bridgedImplementation	= new BridgedDegenerateContinuousGenericSetImplementation<Data>(this);

	public static <Data> BaseDegenerateContinuousGenericInterval<Data> makeInterval(@Nullable final Data value, final ContinuousComparator<? super Data> comparator)
	{
		return new BaseDegenerateContinuousGenericInterval<Data>(new BridgedContinuousGenericBound<Data, BaseDegenerateContinuousGenericInterval<Data>>(value), comparator);
	}

	public static <Data> BaseDegenerateContinuousGenericInterval<Data> makeInterval(@Nullable final Data value)
	{
		return new BaseDegenerateContinuousGenericInterval<Data>(new BridgedContinuousGenericBound<Data, BaseDegenerateContinuousGenericInterval<Data>>(value),
			DefaultUncheckedContinuousComparableComparator.getInstance());
	}
	
	private BaseDegenerateContinuousGenericInterval(final BridgedContinuousGenericBound<Data, BaseDegenerateContinuousGenericInterval<Data>> bound, final ContinuousComparator<? super Data> comparator)
	{
		super(bound);
		_comparator = comparator;
	}

	@Override
	protected IContinuousGenericSet<Data> getThis()
	{
		return this;
	}

	@Override
	protected IContinuousGenericSet<Data> getEmpty()
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
	public @Nullable Data getValue()
	{
		return _bound.getValue();
	}

	@Override
	public @Nullable Data getLowerBound()
	{
		return _bound.getValue();
	}

	@Override
	public @Nullable Data getUpperBound()
	{
		return _bound.getValue();
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