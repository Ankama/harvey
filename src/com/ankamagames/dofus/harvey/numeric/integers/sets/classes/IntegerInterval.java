/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.IntegerBound;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.IntegerIntervalBridge;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerBound;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class IntegerInterval
extends AbstractInterval<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval>
implements IIntegerInterval
{
	private static final float MAX_SAMPLE = 10;
	protected IntegerBound				_lowerBound;
	protected IntegerBound				_upperBound;

	static public  IntegerInterval makeInterval(final int lowerBound, final int upperBound)
	{
		return new IntegerInterval(lowerBound, upperBound);
	}

	IntegerIntervalBridge<IntegerInterval> _bridge;

	AbstractIntervalBoundBridge<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval, IntegerInterval> _boundBridge;

	private IntegerInterval(final int lowerBound, final int upperBound)
	{
		_bridge =  new IntegerIntervalBridge<IntegerInterval>(this);
		_boundBridge = new AbstractIntervalBoundBridge<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval, IntegerInterval>(this);
		_lowerBound = IntegerBound.makeBound(lowerBound);
		_upperBound = IntegerBound.makeBound(upperBound);
	}

	@Override
	protected IntegerIntervalBridge<IntegerInterval> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractIntervalBoundBridge<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval, ? extends AbstractInterval<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public IIntegerSet getAsSet()
	{
		return this;
	}

	@Override
	public ISimpleIntegerSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IElementaryIntegerSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public IIntegerInterval getAsInterval()
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
		final IIntegerBound lowerBound = getLowerBound();
		final IIntegerBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return 0;
		return (upperBound.getValue() - lowerBound.getValue()) + 1;
	}

	@Override
	public boolean isPreceding(final IIntegerSet set)
	{
		final IIntegerBound upperBound = getUpperBound();
		final IIntegerBound otherLowerBound = set.getLowerBound();
		if(upperBound == null || otherLowerBound == null)
			return false;
		final int successor = upperBound.getValue()+1;
		return successor == (otherLowerBound.getValue());
	}

	@Override
	public boolean isSucceeding(final IIntegerSet set)
	{
		final IIntegerBound lowerBound = getLowerBound();
		final IIntegerBound otherUpperBound = set.getUpperBound();
		if(lowerBound == null || otherUpperBound == null)
			return false;
		final int predecessor = lowerBound.getValue()-1;
		return predecessor == (otherUpperBound.getValue());
	}

	@Override
	public @Nullable IIntegerBound getLowerBound()
	{
		final IntegerBound lowerBound = _lowerBound;

		if((lowerBound.getValue() - _upperBound.getValue())<=0)
			return lowerBound;
		return null;
	}

	@Override
	public @Nullable IIntegerBound getUpperBound()
	{
		final IntegerBound upperBound = _upperBound;

		if((_lowerBound.getValue() - upperBound.getValue())<=0)
			return upperBound;
		return null;
	}

	@Override
	public boolean contains(final int value)
	{
		final IIntegerBound lowerBound = getLowerBound();
		final IIntegerBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return false;
		return ((lowerBound.getValue() - value)<=0) && ((upperBound.getValue() - value)>=0);
	}

	@Override
	public List<? extends IIntegerSet> split(final int[] values, final boolean[] isIntervalStart)
	{
		return getBridge().split(values, isIntervalStart);
	}

	@Override
	public List<? extends IIntegerSet> split(final int... values)
	{
		final boolean[] inNextInterval = new boolean[values.length];
		Arrays.fill(inNextInterval, true);
		return split(values, inNextInterval);
	}

	@Override
	public IIntegerInterval getSimpleSet()
	{
		return this;
	}

	@Override
	public Iterator<Integer> getDataIterator()
	{
		final IIntegerBound lowerBound = getLowerBound();
		final IIntegerBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return EmptyIterator.getInstance();
		return new Iterator<Integer>()
		{
			int nextValue = lowerBound.getValue();

			@Override
			public boolean hasNext()
			{
				return (nextValue - upperBound.getValue())<=0;
			}

			@Override
			public Integer next()
			{
				final int r = nextValue;
				nextValue = nextValue+1;
				return r;
			}
		};
	}

	@Override
	public Iterator<Integer> getReverseDataIterator()
	{
		final IIntegerBound lowerBound = getLowerBound();
		final IIntegerBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return EmptyIterator.getInstance();
		return new Iterator<Integer>()
		{
			int nextValue = upperBound.getValue();

			@Override
			public boolean hasNext()
			{
				return (nextValue - lowerBound.getValue())>=0;
			}

			@Override
			public Integer next()
			{
				final int r = nextValue;
				nextValue = nextValue-1;
				return r;
			}
		};
	}

	@Override
	public String toString()
	{
		final IIntegerBound lowerBound = getLowerBound();
		final IIntegerBound upperBound = getUpperBound();
		if (lowerBound == null || upperBound == null)
			return "∅";
		if(isDegenerate())
			return "["+lowerBound.getValue()+"]";
		return "["+lowerBound.getValue()+";"+upperBound.getValue()+"]";
	}

	@Override
	public List<Integer> sample(int numberOfSample)
	{
		if(numberOfSample<=2)
			return sample();
		if(isEmpty())
			return Arrays.asList(null);
		final List<Integer> ret = new ArrayList<Integer>(numberOfSample);
		numberOfSample -= 2;
		final IIntegerBound lowerBound = getLowerBound();
		final IIntegerBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			throw new NullPointerException();
		ret.add(lowerBound.getValue());

		final int chunk = (int)(size()/(numberOfSample+1));
		final Iterator<Integer> it = getDataIterator();
		final int next = it.next();
		for(int i = 1;i<=numberOfSample;i++)
		{
			ret.add(next + i*chunk);

		}
		ret.add(upperBound.getValue());
		return ret;
	}

	@Override
	public List<Integer> sample()
	{
		// the formula of the number of samples
		// I wanted it to grow quickly for little sets and as they goes bigger and bigger the number of samples will be stabilized at MAX_SAMPLE
		// I've stated from the Sigmoïde formula and then stretched it as I wanted. --> http://fooplot.com/plot/qej5p2su4q
		final float maxSample = MAX_SAMPLE-2.f; // because we always want 2 Sample fot lower and upper bound
		final int numberOfSample = (int) Math.min((1.f/(1.f+(Math.exp(-size()/(maxSample*4.f))))*maxSample*2.f-maxSample+2.f), MAX_SAMPLE);
		return sample(numberOfSample);
	}
}