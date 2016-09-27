/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.ByteBound;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.ByteIntervalBridge;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ByteInterval
extends AbstractInterval<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval>
implements IByteInterval
{
	private static final float MAX_SAMPLE = 10;
	protected ByteBound				_lowerBound;
	protected ByteBound				_upperBound;

	static public  ByteInterval makeInterval(final byte lowerBound, final byte upperBound)
	{
		return new ByteInterval(lowerBound, upperBound);
	}

	ByteIntervalBridge<ByteInterval> _bridge;

	AbstractIntervalBoundBridge<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval, ByteInterval> _boundBridge;

	private ByteInterval(final byte lowerBound, final byte upperBound)
	{
		_bridge =  new ByteIntervalBridge<ByteInterval>(this);
		_boundBridge = new AbstractIntervalBoundBridge<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval, ByteInterval>(this);
		_lowerBound = ByteBound.makeBound(lowerBound);
		_upperBound = ByteBound.makeBound(upperBound);
	}

	@Override
	protected ByteIntervalBridge<ByteInterval> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractIntervalBoundBridge<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval, ? extends AbstractInterval<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public IByteSet getAsSet()
	{
		return this;
	}

	@Override
	public ISimpleByteSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IElementaryByteSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public IByteInterval getAsInterval()
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
		final IByteBound lowerBound = getLowerBound();
		final IByteBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return 0;
		return (upperBound.getValue() - lowerBound.getValue()) + 1;
	}

	@Override
	public boolean isPreceding(final IByteSet set)
	{
		final IByteBound upperBound = getUpperBound();
		final IByteBound otherLowerBound = set.getLowerBound();
		if(upperBound == null || otherLowerBound == null)
			return false;
		final byte successor = (byte) (upperBound.getValue()+1);
		return successor == (otherLowerBound.getValue());
	}

	@Override
	public boolean isSucceeding(final IByteSet set)
	{
		final IByteBound lowerBound = getLowerBound();
		final IByteBound otherUpperBound = set.getUpperBound();
		if(lowerBound == null || otherUpperBound == null)
			return false;
		final byte predecessor = (byte) (lowerBound.getValue()-1);
		return predecessor == (otherUpperBound.getValue());
	}

	@Override
	public @Nullable IByteBound getLowerBound()
	{
		final ByteBound lowerBound = _lowerBound;

		if((lowerBound.getValue() - _upperBound.getValue())<=0)
			return lowerBound;
		return null;
	}

	@Override
	public @Nullable IByteBound getUpperBound()
	{
		final ByteBound upperBound = _upperBound;

		if((_lowerBound.getValue() - upperBound.getValue())<=0)
			return upperBound;
		return null;
	}

	@Override
	public boolean contains(final byte value)
	{
		final IByteBound lowerBound = getLowerBound();
		final IByteBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return false;
		return ((lowerBound.getValue() - value)<=0) && ((upperBound.getValue() - value)>=0);
	}

	@Override
	public List<? extends IByteSet> split(final byte[] values, final boolean[] isIntervalStart)
	{
		return getBridge().split(values, isIntervalStart);
	}

	@Override
	public List<? extends IByteSet> split(final byte... values)
	{
		final boolean[] inNextInterval = new boolean[values.length];
		Arrays.fill(inNextInterval, true);
		return split(values, inNextInterval);
	}

	@Override
	public IByteInterval getSimpleSet()
	{
		return this;
	}

	@Override
	public Iterator<Byte> getDataIterator()
	{
		final IByteBound lowerBound = getLowerBound();
		final IByteBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return EmptyIterator.getInstance();
		return new Iterator<Byte>()
		{
			byte nextValue = lowerBound.getValue();

			@Override
			public boolean hasNext()
			{
				return (nextValue - upperBound.getValue())<=0;
			}

			@Override
			public Byte next()
			{
				final byte r = nextValue;
				nextValue = (byte) (nextValue+1);
				return r;
			}
		};
	}

	@Override
	public Iterator<Byte> getReverseDataIterator()
	{
		final IByteBound lowerBound = getLowerBound();
		final IByteBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return EmptyIterator.getInstance();
		return new Iterator<Byte>()
		{
			byte nextValue = upperBound.getValue();

			@Override
			public boolean hasNext()
			{
				return (nextValue - lowerBound.getValue())>=0;
			}

			@Override
			public Byte next()
			{
				final byte r = nextValue;
				nextValue = (byte) (nextValue-1);
				return r;
			}
		};
	}

	@Override
	public String toString()
	{
		final IByteBound lowerBound = getLowerBound();
		final IByteBound upperBound = getUpperBound();
		if (lowerBound == null || upperBound == null)
			return "∅";
		if(isDegenerate())
			return "["+lowerBound.getValue()+"]";
		return "["+lowerBound.getValue()+";"+upperBound.getValue()+"]";
	}

	@Override
	public List<Byte> sample(int numberOfSample)
	{
		if(numberOfSample<=2)
			return sample();
		if(isEmpty())
			return Arrays.asList(null);
		final List<Byte> ret = new ArrayList<Byte>(numberOfSample);
		numberOfSample -= 2;
		final IByteBound lowerBound = getLowerBound();
		final IByteBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			throw new NullPointerException();
		ret.add(lowerBound.getValue());

		final int chunk = (int)(size()/(numberOfSample+1));
		final Iterator<Byte> it = getDataIterator();
		final byte next = it.next();
		for(int i = 1;i<=numberOfSample;i++)
		{
			ret.add((byte) (next + i*chunk));

		}
		ret.add(upperBound.getValue());
		return ret;
	}

	@Override
	public List<Byte> sample()
	{
		// the formula of the number of samples
		// I wanted it to grow quickly for little sets and as they goes bigger and bigger the number of samples will be stabilized at MAX_SAMPLE
		// I've stated from the Sigmoïde formula and then stretched it as I wanted. --> http://fooplot.com/plot/qej5p2su4q
		final float maxSample = MAX_SAMPLE-2.f; // because we always want 2 Sample fot lower and upper bound
		final int numberOfSample = (int) Math.min((1.f/(1.f+(Math.exp(-size()/(maxSample*4.f))))*maxSample*2.f-maxSample+2.f), MAX_SAMPLE);
		return sample(numberOfSample);
	}
}