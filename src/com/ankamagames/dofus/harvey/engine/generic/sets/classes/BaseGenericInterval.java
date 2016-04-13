/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.BridgedOrderedSetIsAndHasImplementation;
import com.ankamagames.dofus.harvey.engine.generic.comparators.DefaultUncheckedComparableComparator;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.DefaultIncrementableIncrementor;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.Incrementable;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.Incrementor;
import com.ankamagames.dofus.harvey.generic.sets.classes.EmptyOrderedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IOrderedGenericSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseGenericInterval<Data>
	extends AbstractSet<IOrderedGenericSet<Data>>
	implements IGenericInterval<Data>
{
	protected BridgedOrderedSetIsAndHasImplementation<IOrderedGenericSet<Data>, IGenericInterval<Data>>	_bridgedIsAndHasImplementation	=
			new BridgedOrderedSetIsAndHasImplementation<IOrderedGenericSet<Data>, IGenericInterval<Data>>(this);

	protected Comparator<? super Data>																	_comparator;
	protected Incrementor<Data>																			_incrementor;
	protected int																						_defaultStep;
	protected BridgedGenericBound<Data, BaseGenericInterval<Data>>										_lowerBound;
	protected BridgedGenericBound<Data, BaseGenericInterval<Data>>										_upperBound;
	
	static public <Data extends Incrementable<Data>&Comparable<? super Data>> BaseGenericInterval<Data> makeInterval(@Nullable final Data lowerBound, @Nullable final Data upperBound)
	{
		final DefaultIncrementableIncrementor<Data> incrementor = DefaultIncrementableIncrementor.getInstance();
		return makeInterval(lowerBound, upperBound, incrementor);
	}

	static public <Data extends Comparable<? super Data>> BaseGenericInterval<Data> makeInterval(@Nullable final Data lowerBound, @Nullable final Data upperBound, final Incrementor<Data> incrementor)
	{
		return makeInterval(lowerBound, upperBound, DefaultUncheckedComparableComparator.getInstance(), incrementor, 1);
	}
	
	static public <Data extends Incrementable<Data>&Comparable<? super Data>> BaseGenericInterval<Data> makeInterval(@Nullable final Data lowerBound, @Nullable final Data upperBound, final int defaultStep)
	{
		final DefaultIncrementableIncrementor<Data> incrementor = DefaultIncrementableIncrementor.getInstance();
		return makeInterval(lowerBound, upperBound, DefaultUncheckedComparableComparator.getInstance(), incrementor, defaultStep);
	}
	
	static public <Data extends Comparable<? super Data>> BaseGenericInterval<Data> makeInterval(@Nullable final Data lowerBound, @Nullable final Data upperBound, final Incrementor<Data> incrementor, final int defaultStep)
	{
		return makeInterval(lowerBound, upperBound, DefaultUncheckedComparableComparator.getInstance(), incrementor, defaultStep);
	}
	
	static public <Data extends Incrementable<Data>> BaseGenericInterval<Data> makeInterval(@Nullable final Data lowerBound, @Nullable final Data upperBound, final Comparator<? super Data> comparator)
	{
		final DefaultIncrementableIncrementor<Data> incrementor = DefaultIncrementableIncrementor.getInstance();
		return makeInterval(lowerBound, upperBound, comparator, incrementor, 1);
	}
	
	static public <Data> BaseGenericInterval<Data> makeInterval(@Nullable final Data lowerBound, @Nullable final Data upperBound, final Comparator<? super Data> comparator, final Incrementor<Data> incrementor)
	{
		return makeInterval(lowerBound, upperBound, comparator, incrementor, 1);
	}

	static public <Data extends Incrementable<Data>> BaseGenericInterval<Data> makeInterval(@Nullable final Data lowerBound, @Nullable final Data upperBound, final Comparator<? super Data> comparator, final int defaultStep)
	{
		final DefaultIncrementableIncrementor<Data> incrementor = DefaultIncrementableIncrementor.getInstance();
		return new BaseGenericInterval<Data>(lowerBound, upperBound, comparator, incrementor, defaultStep);
	}

	static public <Data> BaseGenericInterval<Data> makeInterval(@Nullable final Data lowerBound, @Nullable final Data upperBound, final Comparator<? super Data> comparator, final Incrementor<Data> incrementor, final int defaultStep)
	{
		return new BaseGenericInterval<Data>(lowerBound, upperBound, comparator, incrementor, defaultStep);
	}

	private BaseGenericInterval(@Nullable final Data lowerBound, @Nullable final Data upperBound, final Comparator<? super Data> comparator, final Incrementor<Data> incrementor, final int defaultStep)
	{
		_comparator = comparator;
		_incrementor = incrementor;
		_defaultStep = defaultStep;
		_lowerBound = new BridgedGenericBound<Data, BaseGenericInterval<Data>>(lowerBound);
		_upperBound = new BridgedGenericBound<Data, BaseGenericInterval<Data>>(upperBound);
	}

	@Override
	protected IOrderedGenericSet<Data> getThis()
	{
		return this;
	}
	
	@Override
	public boolean equals(@Nullable final Object obj)
	{
		if(obj instanceof IOrderedGenericSet)
		{
			@SuppressWarnings("unchecked")
			final IOrderedGenericSet<Data> set = (IOrderedGenericSet<Data>)obj;
			if(set != null)
			{
				try
				{
					if(set.isInterval())
						return ((getComparator().compare(set.getLowerBound(), getLowerBound()) == 0) &&
								(getComparator().compare(set.getUpperBound(), getUpperBound()) == 0));
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
	public Comparator<? super Data> getComparator()
	{
		return _comparator;
	}

	public int getDefaultStep()
	{
		return _defaultStep;
	}

	public void setDefaultStep(final int step)
	{
		_defaultStep = step;
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
	public boolean isEmpty()
	{
		return _comparator.compare(getLowerBound(), getUpperBound()) > 0;
	}
	
	@Override
	public double size()
	{
		return getComparator().compare(getUpperBound(), getLowerBound());
	}

	@Override
	public boolean isDegenerate()
	{
		return _comparator.compare(getLowerBound(), getUpperBound()) == 0;
	}
	
	@Override
	public boolean isInterval()
	{
		return true;
	}
	
	@Override
	public boolean contains(@Nullable final Data value)
	{
		return (_comparator.compare(value, getLowerBound()) >= 0) && (_comparator.compare(value, getUpperBound()) <= 0);
	}

	@Override
	public boolean contains(final IOrderedGenericSet<Data> set)
	{
		if(set.isEmpty())
			return true;
		return contains(set.getLowerBound())&&contains(set.getUpperBound());
	}

	@Override
	public boolean isContainedBy(final IOrderedGenericSet<Data> set)
	{
		if(set.isEmpty())
			return false;
		if(set.isDegenerate())
			return isDegenerate() && (_comparator.compare(getLowerBound(), set.getLowerBound()) == 0);
		if(set.isInterval())
		{
			return ((_comparator.compare(getLowerBound(), set.getLowerBound()) >= 0) && (_comparator.compare(getUpperBound(), set.getUpperBound()) <= 0));
		}
		
		return set.contains(this);
	}
	
	@Override
	public boolean containsAllValuesInRange(final IOrderedGenericSet<Data> set)
	{
		return set.isInRange(this);
	}

	@Override
	public boolean is(final SetCoveringMask mask, final IOrderedGenericSet<Data> set)
	{
		return _bridgedIsAndHasImplementation.is(mask, set);
	}

	@Override
	public boolean has(final SetCoveringMask mask, final IOrderedGenericSet<Data> set)
	{
		return _bridgedIsAndHasImplementation.has(mask, set);
	}

	@Override
	public boolean intersects(final IOrderedGenericSet<Data> set)
	{
		if((set.isEmpty()) || isEmpty())
			return false;
		
		if(contains(set.getLowerBound()))
			return true;

		if(set.isDegenerate())
			return false;
		
		if(contains(set.getUpperBound()))
			return true;

		if(set.isInterval())
		{
			return ((_comparator.compare(set.getLowerBound(), getLowerBound()) < 0) && (_comparator.compare(set.getUpperBound(), getUpperBound()) > 0));
		}

		return set.intersects(this);
	}

	@Override
	public boolean isInRange(final IOrderedGenericSet<Data> set)
	{
		if(set.isEmpty())
			return isEmpty();
		if(isEmpty())
			return false;
		return (_comparator.compare(getLowerBound(), set.getLowerBound()) >= 0) && (_comparator.compare(getUpperBound(), set.getUpperBound()) <= 0);
	}

	@Override
	public boolean hasValueInRange(final IOrderedGenericSet<Data> set)
	{
		if(set.isEmpty())
			return isEmpty();
		return !isEmpty() && (_comparator.compare(getLowerBound(), set.getUpperBound()) <= 0) && (_comparator.compare(getUpperBound(), set.getLowerBound()) >= 0);
	}

	@Override
	public boolean isGreaterThan(final IOrderedGenericSet<Data> set)
	{
		if(set.isEmpty())
			return false;
		return !isEmpty() && (_comparator.compare(getLowerBound(), set.getUpperBound()) > 0);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final IOrderedGenericSet<Data> set)
	{
		if(set.isEmpty())
			return isEmpty();
		return !isEmpty() && (_comparator.compare(getLowerBound(), set.getUpperBound()) >= 0);
	}

	@Override
	public boolean hasValueLowerThan(final IOrderedGenericSet<Data> set)
	{
		if(set.isEmpty())
			return false;
		return !isEmpty() && (_comparator.compare(getLowerBound(), set.getLowerBound()) < 0);
	}

	@Override
	public boolean isLowerThan(final IOrderedGenericSet<Data> set)
	{
		if(set.isEmpty())
			return false;
		return !isEmpty() && (_comparator.compare(getUpperBound(), set.getLowerBound()) < 0);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final IOrderedGenericSet<Data> set)
	{
		if(set.isEmpty())
			return isEmpty();
		return !isEmpty() && (_comparator.compare(getUpperBound(), set.getLowerBound()) <= 0);
	}

	@Override
	public boolean hasValueGreaterThan(final IOrderedGenericSet<Data> set)
	{
		if(set.isEmpty())
			return false;
		return !isEmpty() && (_comparator.compare(getUpperBound(), set.getUpperBound()) > 0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<? extends IOrderedGenericSet<Data>> splitOnRange(final IOrderedGenericSet<Data> set)
	{
		return split((Data[])new Object[]{set.getLowerBound(), set.getUpperBound()}, new boolean[]{true, false});
	}

	@Override
	public ArrayList<IOrderedGenericSet<Data>> split(final Data[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IOrderedGenericSet<Data>> r = new ArrayList<IOrderedGenericSet<Data>>(
			values.length + 1);

		if(values.length == 0)
		{
			r.add(0, this);
			return r;
		}

		final EmptyOrderedGenericSet<Data> empty = EmptyOrderedGenericSet.getInstance();
		int i = 0;
		Data value = values[0];
		boolean intervalStart = isIntervalStart[0];
		int compare = getComparator().compare(value, getLowerBound());
		if((compare < 0) || ((compare == 0) && (intervalStart)))
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
				if((compare > 0) || ((compare == 0) && (!intervalStart)))
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
		Data nextValue;
		final int formerI = i;
		for(; i < values.length ; i++)
		{
			Data intervalEnd = values[i];
			compare = getComparator().compare(intervalEnd, getUpperBound());
			if((compare > 0) || ((compare == 0) && (!isIntervalStart[i])))
				break;

			compare = getComparator().compare(startValue, intervalEnd);

			if(isIntervalStart[i])
			{
				nextValue = intervalEnd;
				intervalEnd = _incrementor.getNext(intervalEnd, -1);
			}
			else
				nextValue = _incrementor.getNext(intervalEnd, 1);

			if((compare > 0) || ((compare == 0) && (isIntervalStart[i])))
				throw new UnsupportedOperationException("The values given to split must be sorted");

			if(compare == 0)
				r.add(i, BaseDegenerateGenericInterval.makeInterval(startValue, getComparator()));
			else
				r.add(i, BaseGenericInterval.makeInterval(startValue, intervalEnd, _comparator, _incrementor, _defaultStep));

			startValue = nextValue;
		}
		if(i == formerI)
		{
			r.add(i, this);
		}
		else
		{
			compare = getComparator().compare(startValue, getUpperBound());
			if(compare == 0)
				r.add(i, BaseDegenerateGenericInterval.makeInterval(getUpperBound(), getComparator()));
			else if(compare < 0)
				r.add(i, BaseGenericInterval.makeInterval(startValue, getUpperBound(), _comparator, _incrementor, _defaultStep));
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
	public ArrayList<IOrderedGenericSet<Data>> split(final Data... values)
	{
		final boolean[] inNextInterval = new boolean[values.length];
		Arrays.fill(inNextInterval, true);
		return split(values, inNextInterval);
	}
	
	@Override
	public BaseOrderedGenericSet<Data, BaseGenericInterval<Data>> getMergedSet()
	{
		return BaseOrderedGenericSet.makeSet(this, getComparator(), _incrementor);
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
		return "[" + (lowerBound==null?"null":lowerBound.toString()) + " ; " + (upperBound==null?"null":upperBound.toString()) + "]";
	}
}