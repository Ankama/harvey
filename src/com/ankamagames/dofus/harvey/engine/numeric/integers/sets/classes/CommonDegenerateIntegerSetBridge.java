/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes;

import java.util.ArrayList;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.SingleValueIterator;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.interfaces.ICommonIntegerSet;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.interfaces.IIIntegerBound;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.interfaces.IIIntegerBoundFactory;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CommonDegenerateIntegerSetBridge<Bound extends IBound<Bound>&IIIntegerBound, Type extends ICommonIntegerSet<Bound, ?, ?, ?>>
{

	protected int _value;
	protected Bound						_lowerBound;
	protected Bound						_upperBound;
	protected Type						_emptySet;
	protected Type						_bridged;

	public CommonDegenerateIntegerSetBridge(final int value, final IIIntegerBoundFactory<Bound> boundFactory, final Type emptySet, final Type bridged)
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

	public ArrayList<? extends Type> split(final int[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<Type> r = new ArrayList<Type>(values.length+1);
		final int lValue = getValue();
		int i;
		for(i = 0 ; i<values.length ; i++)
		{
			final int value = values[i];
			final int compare = lValue - value;
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

	public ArrayList<? extends Type> split(final int... values)
	{
		final ArrayList<Type> r = new ArrayList<Type>(values.length+1);
		final int lValue = getValue();
		int i;
		for(i = 0 ; i<values.length ; i++)
		{
			final int compare = (lValue - values[i]);
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

	public Iterator<Integer> getReverseDataIterator() {
		return getDataIterator();
	}

	public int getValue()
	{
		return _value;
	}

	public boolean contains(final int value)
	{
		final int lValue = getValue();
		return lValue == (value);
	}

	public Iterator<Integer> getDataIterator()
	{
		return new SingleValueIterator<Integer>(getValue());
	}

	@Override
	public String toString()
	{
		final Integer value = getValue();
		return value.toString();
	}
}
