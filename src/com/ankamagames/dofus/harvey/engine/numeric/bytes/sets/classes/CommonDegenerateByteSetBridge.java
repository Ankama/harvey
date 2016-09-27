/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes;

import java.util.ArrayList;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.SingleValueIterator;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.interfaces.ICommonByteSet;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.interfaces.IIByteBound;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.interfaces.IIByteBoundFactory;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CommonDegenerateByteSetBridge<Bound extends IBound<Bound>&IIByteBound, Type extends ICommonByteSet<Bound, ?, ?, ?>>
{

	protected byte _value;
	protected Bound						_lowerBound;
	protected Bound						_upperBound;
	protected Type						_emptySet;
	protected Type						_bridged;

	public CommonDegenerateByteSetBridge(final byte value, final IIByteBoundFactory<Bound> boundFactory, final Type emptySet, final Type bridged)
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

	public ArrayList<? extends Type> split(final byte[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<Type> r = new ArrayList<Type>(values.length+1);
		final byte lValue = getValue();
		int i;
		for(i = 0 ; i<values.length ; i++)
		{
			final byte value = values[i];
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

	public ArrayList<? extends Type> split(final byte... values)
	{
		final ArrayList<Type> r = new ArrayList<Type>(values.length+1);
		final byte lValue = getValue();
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

	public Iterator<Byte> getReverseDataIterator() {
		return getDataIterator();
	}

	public byte getValue()
	{
		return _value;
	}

	public boolean contains(final byte value)
	{
		final byte lValue = getValue();
		return lValue == (value);
	}

	public Iterator<Byte> getDataIterator()
	{
		return new SingleValueIterator<Byte>(getValue());
	}

	@Override
	public String toString()
	{
		final Byte value = getValue();
		return value.toString();
	}
}
