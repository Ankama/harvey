/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes;

import java.util.ArrayList;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.SingleValueIterator;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.interfaces.ICommonShortSet;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.interfaces.IIShortBound;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.interfaces.IIShortBoundFactory;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CommonDegenerateShortSetBridge<Bound extends IBound<Bound>&IIShortBound, Type extends ICommonShortSet<Bound, ?, ?, ?>>
{

	protected short _value;
	protected Bound						_lowerBound;
	protected Bound						_upperBound;
	protected Type						_emptySet;
	protected Type						_bridged;

	public CommonDegenerateShortSetBridge(final short value, final IIShortBoundFactory<Bound> boundFactory, final Type emptySet, final Type bridged)
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

	public ArrayList<? extends Type> split(final short[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<Type> r = new ArrayList<Type>(values.length+1);
		final short lValue = getValue();
		int i;
		for(i = 0 ; i<values.length ; i++)
		{
			final short value = values[i];
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

	public ArrayList<? extends Type> split(final short... values)
	{
		final ArrayList<Type> r = new ArrayList<Type>(values.length+1);
		final short lValue = getValue();
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

	public Iterator<Short> getReverseDataIterator() {
		return getDataIterator();
	}

	public short getValue()
	{
		return _value;
	}

	public boolean contains(final short value)
	{
		final short lValue = getValue();
		return lValue == (value);
	}

	public Iterator<Short> getDataIterator()
	{
		return new SingleValueIterator<Short>(getValue());
	}

	@Override
	public String toString()
	{
		final Short value = getValue();
		return value.toString();
	}
}
