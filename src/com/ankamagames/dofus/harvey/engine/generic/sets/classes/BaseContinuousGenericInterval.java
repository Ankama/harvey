/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.BridgedOrderedSetIsAndHasImplementation;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparable;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparator;
import com.ankamagames.dofus.harvey.engine.generic.comparators.DefaultUncheckedContinuousComparableComparator;
import com.ankamagames.dofus.harvey.generic.sets.classes.EmptyContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseContinuousGenericInterval<Data>
	extends AbstractSet<IContinuousGenericSet<Data>>
	implements IContinuousGenericInterval<Data>
{
	protected BridgedOrderedSetIsAndHasImplementation<IContinuousGenericSet<Data>, IContinuousGenericInterval<Data>>	_bridgedIsAndHasImplementation	=
			new BridgedOrderedSetIsAndHasImplementation<IContinuousGenericSet<Data>, IContinuousGenericInterval<Data>>(this);

	protected ContinuousComparator<? super Data>																		_comparator;
	protected BridgedContinuousGenericBound<Data, BaseContinuousGenericInterval<Data>>									_lowerBound;
	protected BridgedContinuousGenericBound<Data, BaseContinuousGenericInterval<Data>>									_upperBound;

	static public <Data extends ContinuousComparable<? super Data>> BaseContinuousGenericInterval<Data> makeInterval(@Nullable final Data lowerBound,
		@Nullable final Data upperBound)
	{
		return makeInterval(lowerBound, upperBound, DefaultUncheckedContinuousComparableComparator.getInstance());
	}

	static public <Data> BaseContinuousGenericInterval<Data> makeInterval(@Nullable final Data lowerBound, @Nullable final Data upperBound,
		final ContinuousComparator<? super Data> comparator)
	{
		return makeInterval(lowerBound, true, upperBound, true, comparator);
	}

	static public <Data extends ContinuousComparable<? super Data>> BaseContinuousGenericInterval<Data> makeInterval(@Nullable final Data lowerBound,
		final boolean lowerBoundClosed, @Nullable final Data upperBound, final boolean upperBoundClosed)
	{
		return makeInterval(lowerBound, lowerBoundClosed, upperBound, upperBoundClosed, DefaultUncheckedContinuousComparableComparator.getInstance());
	}

	static public <Data> BaseContinuousGenericInterval<Data> makeInterval(@Nullable final Data lowerBound, final boolean lowerBoundClosed, @Nullable final Data upperBound,
		final boolean upperBoundClosed, final ContinuousComparator<? super Data> comparator)
	{
		return new BaseContinuousGenericInterval<Data>(lowerBound, lowerBoundClosed, upperBound, upperBoundClosed, comparator);
	}

	private BaseContinuousGenericInterval(@Nullable final Data lowerBound, final boolean lowerBoundClosed, @Nullable final Data upperBound, final boolean upperBoundClosed,
		final ContinuousComparator<? super Data> comparator)
	{
		_comparator = comparator;
		_lowerBound = new BridgedContinuousGenericBound<Data, BaseContinuousGenericInterval<Data>>(lowerBound, lowerBoundClosed);
		_upperBound = new BridgedContinuousGenericBound<Data, BaseContinuousGenericInterval<Data>>(upperBound, upperBoundClosed);
	}

	@Override
	protected IContinuousGenericSet<Data> getThis()
	{
		return this;
	}
	
	@Override
	public boolean equals(@Nullable final Object obj)
	{
		if(obj instanceof IContinuousGenericSet)
		{
			@SuppressWarnings("unchecked")
			final IContinuousGenericSet<Data> set = (IContinuousGenericSet<Data>)obj;
			if(set != null)
			{
				try
				{
					if(set.isInterval())
						return ((getComparator().compare(set.getLowerBound(), getLowerBound()) == 0) &&
								(getComparator().compare(set.getUpperBound(), getUpperBound()) == 0) &&
								(set.isLowerBoundClosed() == isLowerBoundClosed()) &&
								(set.isUpperBoundClosed() == isUpperBoundClosed()));
				}
				catch(final Throwable t)
				{
					return false;
				}
			}
		}
		if(isDegenerate())
		{
			final Data lowerBound = getLowerBound();
			if(lowerBound==null)
				return obj==null;
			return lowerBound.equals(obj);
		}
		return false;
	}

	@Override
	public ContinuousComparator<? super Data> getComparator()
	{
		return _comparator;
	}

	@Override
	public @Nullable Data getLowerBound()
	{
		return _lowerBound.getValue();
	}

	@Override
	public @Nullable Data getUpperBound()
	{
		return _upperBound.getValue();
	}

	@Override
	public boolean isLowerBoundClosed()
	{
		return _lowerBound.isClosed();
	}

	@Override
	public boolean isUpperBoundClosed()
	{
		return _upperBound.isClosed();
	}

	@Override
	public boolean isEmpty()
	{
		return (_comparator.compare(getLowerBound(), getUpperBound()) > 0) ||
				((_comparator.compare(getLowerBound(), getUpperBound()) == 0) && (!isLowerBoundClosed() || !isUpperBoundClosed()));
	}
	
	@Override
	public double size()
	{
		return getComparator().compare(getUpperBound(), getLowerBound());
	}

	@Override
	public boolean isDegenerate()
	{
		return (isLowerBoundClosed() && isUpperBoundClosed() && (_comparator.compare(getLowerBound(), getUpperBound()) == 0));
	}
	
	@Override
	public boolean isInterval()
	{
		return true;
	}
	
	@Override
	public boolean containsAllValuesInRange(final IContinuousGenericSet<Data> set)
	{
		return contains(set);
	}

	@Override
	public boolean contains(final IContinuousGenericSet<Data> set)
	{
		if(set.isEmpty())
			return true;
		return ((contains(set.getLowerBound()) || (!set.isLowerBoundClosed() && !isLowerBoundClosed() && (getComparator().compare(set.getLowerBound(), getLowerBound()) == 0)))
			&& (contains(set.getUpperBound()) || (!set.isUpperBoundClosed() && !isUpperBoundClosed() && (getComparator().compare(set.getUpperBound(), getUpperBound()) == 0))));
	}

	@Override
	public boolean isContainedBy(final IContinuousGenericSet<Data> set)
	{
		if(isEmpty())
			return true;
		if(set.isEmpty())
			return false;
		if(set.isDegenerate())
			return isDegenerate() && (_comparator.compare(getLowerBound(), set.getLowerBound()) == 0);
		if(set.isInterval())
		{
			final double compare1 = _comparator.compare(getLowerBound(), set.getLowerBound());
			final double compare2 = _comparator.compare(getUpperBound(), set.getUpperBound());
			return (((compare1 > 0) || ((compare1 == 0) && (set.isLowerBoundClosed() || !isLowerBoundClosed())))
				&& ((compare2 < 0) || ((compare2 == 0) && (set.isUpperBoundClosed() || !isUpperBoundClosed()))));
		}
		
		return set.contains(this);
	}
	
	@Override
	public boolean contains(@Nullable final Data value)
	{
		final double compare1 = _comparator.compare(value, getLowerBound());
		final double compare2 = _comparator.compare(value, getUpperBound());
		return ((compare1 > 0) || ((isLowerBoundClosed()) && (compare1 == 0))) &&
				((compare2 < 0) || ((isUpperBoundClosed()) && (compare2 == 0)));
	}

	@Override
	public boolean is(final SetCoveringMask mask, final IContinuousGenericSet<Data> set)
	{
		return _bridgedIsAndHasImplementation.is(mask, set);
	}

	@Override
	public boolean has(final SetCoveringMask mask, final IContinuousGenericSet<Data> set)
	{
		return _bridgedIsAndHasImplementation.has(mask, set);
	}

	@Override
	public boolean intersects(final IContinuousGenericSet<Data> set)
	{
		if((set.isEmpty()) || isEmpty())
			return false;
		
		if(contains(set.getLowerBound()))
			if((set.isLowerBoundClosed()) || (getComparator().compare(set.getLowerBound(), getUpperBound()) != 0))
				return true;

		if(set.isDegenerate())
			return false;
		
		if(contains(set.getUpperBound()))
			if((set.isUpperBoundClosed()) || (getComparator().compare(set.getUpperBound(), getLowerBound()) != 0))
				return true;

		if(set.isInterval())
		{
			return ((_comparator.compare(set.getLowerBound(), getLowerBound()) < 0) && (_comparator.compare(set.getUpperBound(), getUpperBound()) > 0));
		}

		return set.intersects(this);
	}

	@Override
	public boolean isInRange(final IContinuousGenericSet<Data> set)
	{
		if(set.isEmpty())
			return isEmpty();
		if(isEmpty())
			return false;
		final double compare1 = _comparator.compare(getLowerBound(), set.getLowerBound());
		final double compare2 = _comparator.compare(getUpperBound(), set.getUpperBound());
		return ((compare1 > 0) || ((isLowerBoundClosed() == set.isLowerBoundClosed()) && (compare1 == 0))) &&
				((compare2 <= 0) || ((isUpperBoundClosed() == set.isUpperBoundClosed()) && (compare2 == 0)));
	}

	@Override
	public boolean hasValueInRange(final IContinuousGenericSet<Data> set)
	{
		if(set.isEmpty())
			return isEmpty();
		if(isEmpty())
			return false;
		final double compare1 = _comparator.compare(getLowerBound(), set.getUpperBound());
		final double compare2 = _comparator.compare(getUpperBound(), set.getLowerBound());
		return ((compare1 < 0) || (((compare1 == 0) && isLowerBoundClosed() && set.isUpperBoundClosed()) &&
				((compare2 > 0) || ((compare2 == 0) && isUpperBoundClosed() && set.isLowerBoundClosed()))));
	}

	@Override
	public boolean isGreaterThan(final IContinuousGenericSet<Data> set)
	{
		if(set.isEmpty() || isEmpty())
			return false;
		final double compare = _comparator.compare(getLowerBound(), set.getUpperBound());
		return ((compare > 0) || (((compare == 0)) && (!isLowerBoundClosed() || !set.isUpperBoundClosed())));
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final IContinuousGenericSet<Data> set)
	{
		if(set.isEmpty())
			return isEmpty();
		if(isEmpty())
			return false;
		return (_comparator.compare(getLowerBound(), set.getUpperBound()) >= 0);
	}

	@Override
	public boolean hasValueLowerThan(final IContinuousGenericSet<Data> set)
	{
		if(set.isEmpty() || isEmpty())
			return false;
		final double compare = _comparator.compare(getLowerBound(), set.getLowerBound());
		return ((compare < 0) || ((compare == 0)  && (!set.isLowerBoundClosed() && isLowerBoundClosed())));
	}

	@Override
	public boolean isLowerThan(final IContinuousGenericSet<Data> set)
	{
		if(set.isEmpty() || isEmpty())
			return false;
		final double compare = _comparator.compare(getUpperBound(), set.getLowerBound());
		return ((compare < 0) || ((compare == 0) && (!isUpperBoundClosed() || !set.isLowerBoundClosed())));
	}

	@Override
	public boolean isLowerThanOrEqualTo(final IContinuousGenericSet<Data> set)
	{
		if(set.isEmpty())
			return isEmpty();
		if(isEmpty())
			return false;
		return (_comparator.compare(getUpperBound(), set.getLowerBound()) <= 0);
	}

	@Override
	public boolean hasValueGreaterThan(final IContinuousGenericSet<Data> set)
	{
		if(set.isEmpty() || isEmpty())
			return false;
		final double compare = _comparator.compare(getUpperBound(), set.getUpperBound());
		return ((compare > 0) || ((compare == 0) && (!set.isUpperBoundClosed() && isUpperBoundClosed())));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<? extends IContinuousGenericSet<Data>> splitOnRange(final IContinuousGenericSet<Data> set)
	{
		return split((Data[])new Object[]{set.getLowerBound(), set.getUpperBound()}, new boolean[]{set.isLowerBoundClosed(), !set.isUpperBoundClosed()});
	}

	@Override
	public ArrayList<IContinuousGenericSet<Data>> split(final Data[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IContinuousGenericSet<Data>> r = new ArrayList<IContinuousGenericSet<Data>>(
			values.length + 1);

		if(values.length == 0)
		{
			r.add(0, this);
			return r;
		}

		final EmptyContinuousGenericSet<Data> empty = EmptyContinuousGenericSet.getInstance();
		int i = 0;
		Data value = values[0];
		boolean intervalStart = isIntervalStart[0];
		double compare = getComparator().compare(value, getLowerBound());
		if((compare < 0) || ((compare == 0) && (intervalStart || !isLowerBoundClosed())))
		{
			r.add(0, empty);
			Data formerValue;
			boolean formerIntervalStart;
			for(i++ ; i < values.length ; i++)
			{
				formerValue = value;
				formerIntervalStart = intervalStart;
				value = values[i];
				intervalStart = isIntervalStart[i];
				compare = getComparator().compare(value, getLowerBound());
				if((compare > 0) || ((compare == 0) && (!intervalStart) && isLowerBoundClosed()))
					break;
				compare = getComparator().compare(formerValue, value);
				if((compare < 0) || ((compare == 0) && (formerIntervalStart) && (!intervalStart)))
					r.add(i, empty);
				else
					throw new UnsupportedOperationException("The values given to split must be sorted");
			}
		}

		if(i == (values.length))
		{
			r.add(values.length, this);
			return r;
		}

		Data startValue = getLowerBound();
		boolean closedStart = isLowerBoundClosed();
		final int formerI = i;
		for(; i < values.length ; i++)
		{
			final Data intervalEnd = values[i];
			final boolean closedEnd = !isIntervalStart[i];
			compare = getComparator().compare(intervalEnd, getUpperBound());
			if((compare > 0) || ((compare == 0) && (closedEnd)))
				break;

			compare = getComparator().compare(startValue, intervalEnd);

			if((compare > 0) || ((compare == 0) && ((!closedEnd) || (!closedStart))))
				throw new UnsupportedOperationException("The values given to split must be sorted");

			if(compare == 0)
				r.add(i, BaseDegenerateContinuousGenericInterval.makeInterval(startValue, getComparator()));
			else
				r.add(i, BaseContinuousGenericInterval.makeInterval(startValue, closedStart, intervalEnd, closedEnd, _comparator));

			startValue = intervalEnd;
			closedStart = !closedEnd;
		}
		if(i == formerI)
		{
			r.add(i, this);
		}
		else
		{
			compare = getComparator().compare(startValue, getUpperBound());
			if(compare == 0)
				if(isUpperBoundClosed())
					r.add(i, BaseDegenerateContinuousGenericInterval.makeInterval(getUpperBound(), getComparator()));
				else
					r.add(i, empty);
			else if(compare < 0)
				r.add(i, BaseContinuousGenericInterval.makeInterval(startValue, closedStart, getUpperBound(), isUpperBoundClosed(), _comparator));
		}

		final Data formerValue = getUpperBound();
		while((i++) < values.length)
		{
			compare = getComparator().compare(formerValue, values[i - 1]);
			if((compare < 0) || ((compare == 0) && (!isIntervalStart[i - 1])))
				r.add(i, empty);
			else
				throw new UnsupportedOperationException("The values given to split must be sorted");
		}

		return r;
	}
	
	@Override
	public ArrayList<IContinuousGenericSet<Data>> split(final Data... values)
	{
		final boolean[] inNextInterval = new boolean[values.length];
		Arrays.fill(inNextInterval, true);
		return split(values, inNextInterval);
	}
	
	@Override
	public BaseContinuousGenericSet<Data, BaseContinuousGenericInterval<Data>> getMergedSet()
	{
		return BaseContinuousGenericSet.makeSet(this, getComparator());
	}
	
	@Override
	public String toString()
	{
		if(isEmpty())
			return "∅";
		final Data lowerBound = getLowerBound();
		if(isDegenerate())
			return lowerBound==null?"null":lowerBound.toString();
		final Data upperBound = getUpperBound();
		String r;
		if(isLowerBoundClosed())
			r = "[";
		else
			r = "(";
		r += (lowerBound==null?"null":lowerBound.toString()) + " ; " + (upperBound==null?"null":upperBound.toString());
		if(isUpperBoundClosed())
			r += "]";
		else
			r += ")";
		return r;
	}
}