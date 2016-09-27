/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes;

import java.util.ArrayList;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.SingleValueIterator;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.interfaces.ICommonLongSet;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.interfaces.IILongBound;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.interfaces.IILongBoundFactory;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CommonDegenerateLongSetBridge<Bound extends IBound<Bound>&IILongBound, Type extends ICommonLongSet<Bound, ?, ?, ?>>
{

	protected long _value;
	protected Bound						_lowerBound;
	protected Bound						_upperBound;
	protected Type						_emptySet;
	protected Type						_bridged;

	public CommonDegenerateLongSetBridge(final long value, final IILongBoundFactory<Bound> boundFactory, final Type emptySet, final Type bridged)
	{
		_value = value;
		_lowerBound = boundFactory.makeBound(true, value);
		_upperBound = boundFactory.makeBound(false, value);
		_emptySet = emptySet;
		_bridged = bridged;
	}

	public Bound getLowerBound()
	{
		return _lowerBound;
	}

	public Bound getUpperBound()
	{
		return _upperBound;
	}

	public ArrayList<? extends Type> split(final long[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<Type> r = new ArrayList<Type>(values.length+1);
		final long lValue = getValue();
		int i;
		for(i = 0 ; i<values.length ; i++)
		{
			final long value = values[i];
			final int compare = (int) Math.signum(lValue - value);
			if((compare<0)||((compare==0)&&(!isIntervalStart[i])))
				break;
			r.add(_emptySet);
		}

		r.add(_bridged);

		for( i++ ; i<values.length ; i++)
		{
			r.add(_emptySet);
		}
		return r;
	}

	public ArrayList<? extends Type> split(final long... values)
	{
		final ArrayList<Type> r = new ArrayList<Type>(values.length+1);
		final long lValue = getValue();
		int i;
		for(i = 0 ; i<values.length ; i++)
		{
			final int compare = (int) Math.signum(lValue - values[i]);
			if(compare<0)
				break;
			r.add(_emptySet);
		}

		r.add(_bridged);

		for( i++ ; i<values.length ; i++)
		{
			r.add(_emptySet);
		}
		return r;
	}

	public Iterator<Long> getReverseDataIterator() {
		return getDataIterator();
	}

	public long getValue()
	{
		return _value;
	}

	public boolean contains(final long value)
	{
		final long lValue = getValue();
		return lValue == (value);
	}

	public Iterator<Long> getDataIterator()
	{
		return new SingleValueIterator<Long>(getValue());
	}

	@Override
	public String toString()
	{
		final Long value = getValue();
		return value.toString();
	}
}
