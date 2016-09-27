/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.ShortBound;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.ShortIntervalBridge;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ShortInterval
extends AbstractInterval<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval>
implements IShortInterval
{
	private static final float MAX_SAMPLE = 10;
	protected ShortBound				_lowerBound;
	protected ShortBound				_upperBound;

	static public  ShortInterval makeInterval(final short lowerBound, final short upperBound)
	{
		return new ShortInterval(lowerBound, upperBound);
	}

	ShortIntervalBridge<ShortInterval> _bridge;

	AbstractIntervalBoundBridge<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval, ShortInterval> _boundBridge;

	private ShortInterval(final short lowerBound, final short upperBound)
	{
		_bridge =  new ShortIntervalBridge<ShortInterval>(this);
		_boundBridge = new AbstractIntervalBoundBridge<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval, ShortInterval>(this);
		_lowerBound = ShortBound.makeBound(lowerBound);
		_upperBound = ShortBound.makeBound(upperBound);
	}

	@Override
	protected ShortIntervalBridge<ShortInterval> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractIntervalBoundBridge<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval, ? extends AbstractInterval<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public IShortSet getAsSet()
	{
		return this;
	}

	@Override
	public ISimpleShortSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IElementaryShortSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public IShortInterval getAsInterval()
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
		final IShortBound lowerBound = getLowerBound();
		final IShortBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return 0;
		return (upperBound.getValue() - lowerBound.getValue()) + 1;
	}

	@Override
	public boolean isPreceding(final IShortSet set)
	{
		final IShortBound upperBound = getUpperBound();
		final IShortBound otherLowerBound = set.getLowerBound();
		if(upperBound == null || otherLowerBound == null)
			return false;
		final short successor = (short) (upperBound.getValue()+1);
		return successor == (otherLowerBound.getValue());
	}

	@Override
	public boolean isSucceeding(final IShortSet set)
	{
		final IShortBound lowerBound = getLowerBound();
		final IShortBound otherUpperBound = set.getUpperBound();
		if(lowerBound == null || otherUpperBound == null)
			return false;
		final short predecessor = (short) (lowerBound.getValue()-1);
		return predecessor == (otherUpperBound.getValue());
	}

	@Override
	public @Nullable IShortBound getLowerBound()
	{
		final ShortBound lowerBound = _lowerBound;

		if((lowerBound.getValue() - _upperBound.getValue())<=0)
			return lowerBound;
		return null;
	}

	@Override
	public @Nullable IShortBound getUpperBound()
	{
		final ShortBound upperBound = _upperBound;

		if((_lowerBound.getValue() - upperBound.getValue())<=0)
			return upperBound;
		return null;
	}

	@Override
	public boolean contains(final short value)
	{
		final IShortBound lowerBound = getLowerBound();
		final IShortBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return false;
		return ((lowerBound.getValue() - value)<=0) && ((upperBound.getValue() - value)>=0);
	}

	@Override
	public List<? extends IShortSet> split(final short[] values, final boolean[] isIntervalStart)
	{
		return getBridge().split(values, isIntervalStart);
	}

	@Override
	public List<? extends IShortSet> split(final short... values)
	{
		final boolean[] inNextInterval = new boolean[values.length];
		Arrays.fill(inNextInterval, true);
		return split(values, inNextInterval);
	}

	@Override
	public IShortInterval getSimpleSet()
	{
		return this;
	}

	@Override
	public Iterator<Short> getDataIterator()
	{
		final IShortBound lowerBound = getLowerBound();
		final IShortBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return EmptyIterator.getInstance();
		return new Iterator<Short>()
		{
			short nextValue = lowerBound.getValue();

			@Override
			public boolean hasNext()
			{
				return (nextValue - upperBound.getValue())<=0;
			}

			@Override
			public Short next()
			{
				final short r = nextValue;
				nextValue = (short) (nextValue+1);
				return r;
			}
		};
	}

	@Override
	public Iterator<Short> getReverseDataIterator()
	{
		final IShortBound lowerBound = getLowerBound();
		final IShortBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return EmptyIterator.getInstance();
		return new Iterator<Short>()
		{
			short nextValue = upperBound.getValue();

			@Override
			public boolean hasNext()
			{
				return (nextValue - lowerBound.getValue())>=0;
			}

			@Override
			public Short next()
			{
				final short r = nextValue;
				nextValue = (short) (nextValue-1);
				return r;
			}
		};
	}

	@Override
	public String toString()
	{
		final IShortBound lowerBound = getLowerBound();
		final IShortBound upperBound = getUpperBound();
		if (lowerBound == null || upperBound == null)
			return "∅";
		if(isDegenerate())
			return "["+lowerBound.getValue()+"]";
		return "["+lowerBound.getValue()+";"+upperBound.getValue()+"]";
	}

	@Override
	public List<Short> sample(int numberOfSample)
	{
		if(numberOfSample<=2)
			return sample();
		if(isEmpty())
			return Arrays.asList(null);
		final List<Short> ret = new ArrayList<Short>(numberOfSample);
		numberOfSample -= 2;
		final IShortBound lowerBound = getLowerBound();
		final IShortBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			throw new NullPointerException();
		ret.add(lowerBound.getValue());

		final int chunk = (int)(size()/(numberOfSample+1));
		final Iterator<Short> it = getDataIterator();
		final short next = it.next();
		for(int i = 1;i<=numberOfSample;i++)
		{
			ret.add((short) (next + i*chunk));

		}
		ret.add(upperBound.getValue());
		return ret;
	}

	@Override
	public List<Short> sample()
	{
		// the formula of the number of samples
		// I wanted it to grow quickly for little sets and as they goes bigger and bigger the number of samples will be stabilized at MAX_SAMPLE
		// I've stated from the Sigmoïde formula and then stretched it as I wanted. --> http://fooplot.com/plot/qej5p2su4q
		final float maxSample = MAX_SAMPLE-2.f; // because we always want 2 Sample fot lower and upper bound
		final int numberOfSample = (int) Math.min((1.f/(1.f+(Math.exp(-size()/(maxSample*4.f))))*maxSample*2.f-maxSample+2.f), MAX_SAMPLE);
		return sample(numberOfSample);
	}
}