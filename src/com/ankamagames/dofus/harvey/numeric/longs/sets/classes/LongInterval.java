/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.LongBound;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.LongIntervalBridge;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongBound;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class LongInterval
extends AbstractInterval<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval>
implements ILongInterval
{
	private static final float MAX_SAMPLE = 10;
	protected LongBound				_lowerBound;
	protected LongBound				_upperBound;

	static public  LongInterval makeInterval(final long lowerBound, final long upperBound)
	{
		return new LongInterval(lowerBound, upperBound);
	}

	LongIntervalBridge<LongInterval> _bridge;

	AbstractIntervalBoundBridge<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval, LongInterval> _boundBridge;

	private LongInterval(final long lowerBound, final long upperBound)
	{
		_bridge =  new LongIntervalBridge<LongInterval>(this);
		_boundBridge = new AbstractIntervalBoundBridge<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval, LongInterval>(this);
		_lowerBound = LongBound.makeBound(lowerBound);
		_upperBound = LongBound.makeBound(upperBound);
	}

	@Override
	protected LongIntervalBridge<LongInterval> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractIntervalBoundBridge<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval, ? extends AbstractInterval<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public ILongSet getAsSet()
	{
		return this;
	}

	@Override
	public ISimpleLongSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IElementaryLongSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public ILongInterval getAsInterval()
	{
		return this;
	}

	@Override
	public boolean isEmpty()
	{
		return getBridge().isEmpty();
	}

	@Override
	public double size()
	{
		final ILongBound lowerBound = getLowerBound();
		final ILongBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return 0;
		return (upperBound.getValue() - lowerBound.getValue()) + 1;
	}

	@Override
	public boolean isPreceding(final ILongSet set)
	{
		final ILongBound upperBound = getUpperBound();
		final ILongBound otherLowerBound = set.getLowerBound();
		if(upperBound == null || otherLowerBound == null)
			return false;
		final long successor = (long) (upperBound.getValue()+1);
		return successor == (otherLowerBound.getValue());
	}

	@Override
	public boolean isSucceeding(final ILongSet set)
	{
		final ILongBound lowerBound = getLowerBound();
		final ILongBound otherUpperBound = set.getUpperBound();
		if(lowerBound == null || otherUpperBound == null)
			return false;
		final long predecessor = (long) (lowerBound.getValue()-1);
		return predecessor == (otherUpperBound.getValue());
	}

	@Override
	public @Nullable ILongBound getLowerBound()
	{
		final LongBound lowerBound = _lowerBound;

		if((lowerBound.getValue() - _upperBound.getValue())<=0)
			return lowerBound;
		return null;
	}

	@Override
	public @Nullable ILongBound getUpperBound()
	{
		final LongBound upperBound = _upperBound;

		if((_lowerBound.getValue() - upperBound.getValue())<=0)
			return upperBound;
		return null;
	}

	@Override
	public boolean contains(final long value)
	{
		final ILongBound lowerBound = getLowerBound();
		final ILongBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return false;
		return ((lowerBound.getValue() - value)<=0) && ((upperBound.getValue() - value)>=0);
	}

	@Override
	public List<? extends ILongSet> split(final long[] values, final boolean[] isIntervalStart)
	{
		return getBridge().split(values, isIntervalStart);
	}

	@Override
	public List<? extends ILongSet> split(final long... values)
	{
		final boolean[] inNextInterval = new boolean[values.length];
		Arrays.fill(inNextInterval, true);
		return split(values, inNextInterval);
	}

	@Override
	public ILongInterval getSimpleSet()
	{
		return this;
	}

	@Override
	public Iterator<Long> getDataIterator()
	{
		final ILongBound lowerBound = getLowerBound();
		final ILongBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return EmptyIterator.getInstance();
		return new Iterator<Long>()
		{
			long nextValue = lowerBound.getValue();

			@Override
			public boolean hasNext()
			{
				return (nextValue - upperBound.getValue())<=0;
			}

			@Override
			public Long next()
			{
				final long r = nextValue;
				nextValue = (long) (nextValue+1);
				return r;
			}
		};
	}

	@Override
	public Iterator<Long> getReverseDataIterator()
	{
		final ILongBound lowerBound = getLowerBound();
		final ILongBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return EmptyIterator.getInstance();
		return new Iterator<Long>()
		{
			long nextValue = upperBound.getValue();

			@Override
			public boolean hasNext()
			{
				return (nextValue - lowerBound.getValue())>=0;
			}

			@Override
			public Long next()
			{
				final long r = nextValue;
				nextValue = (long) (nextValue-1);
				return r;
			}
		};
	}

	@Override
	public String toString()
	{
		final ILongBound lowerBound = getLowerBound();
		final ILongBound upperBound = getUpperBound();
		if (lowerBound == null || upperBound == null)
			return "∅";
		if(isDegenerate())
			return "["+lowerBound.getValue()+"]";
		return "["+lowerBound.getValue()+";"+upperBound.getValue()+"]";
	}

	@Override
	public List<Long> sample(int numberOfSample)
	{
		if(numberOfSample<=2)
			return sample();
		if(isEmpty())
			return Arrays.asList(null);
		final List<Long> ret = new ArrayList<Long>(numberOfSample);
		numberOfSample -= 2;
		final ILongBound lowerBound = getLowerBound();
		final ILongBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			throw new NullPointerException();
		ret.add(lowerBound.getValue());

		final int chunk = (int)(size()/(numberOfSample+1));
		final Iterator<Long> it = getDataIterator();
		final long next = it.next();
		for(int i = 1;i<=numberOfSample;i++)
		{
			ret.add((long) (next + i*chunk));

		}
		ret.add(upperBound.getValue());
		return ret;
	}

	@Override
	public List<Long> sample()
	{
		// the formula of the number of samples
		// I wanted it to grow quickly for little sets and as they goes bigger and bigger the number of samples will be stabilized at MAX_SAMPLE
		// I've stated from the Sigmoïde formula and then stretched it as I wanted. --> http://fooplot.com/plot/qej5p2su4q
		final float maxSample = MAX_SAMPLE-2.f; // because we always want 2 Sample fot lower and upper bound
		final int numberOfSample = (int) Math.min((1.f/(1.f+(Math.exp(-size()/(maxSample*4.f))))*maxSample*2.f-maxSample+2.f), MAX_SAMPLE);
		return sample(numberOfSample);
	}
}