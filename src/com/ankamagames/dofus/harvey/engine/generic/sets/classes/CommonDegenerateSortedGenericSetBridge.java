/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.generic.sets.interfaces.ICommonSortedGenericSet;
import com.ankamagames.dofus.harvey.engine.generic.sets.interfaces.IIGenericBound;
import com.ankamagames.dofus.harvey.engine.generic.sets.interfaces.IIGenericBoundFactory;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CommonDegenerateSortedGenericSetBridge<Data, Bound extends IBound<Bound>&IIGenericBound<Data>, Type extends ICommonSortedGenericSet<Data, Bound, ?, ?, ?>>
extends CommonDegenerateGenericSetBridge<Data>
{
	protected Comparator<? super Data>	_comparator;
	protected Bound						_lowerBound;
	protected Bound						_upperBound;
	protected Type						_emptySet;
	protected Type						_bridged;

	public CommonDegenerateSortedGenericSetBridge(final @Nullable Data value, final IIGenericBoundFactory<Data, Bound> boundFactory,
		final Comparator<? super Data> comparator, final Type emptySet, final Type bridged)
	{
		super(value);
		_lowerBound = boundFactory.makeBound(true, value);
		_upperBound = boundFactory.makeBound(false, value);
		_comparator = comparator;
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

	public ArrayList<? extends Type> split(final Data[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<Type> r = new ArrayList<Type>(values.length+1);
		final Data lValue = getValue();
		int i;
		for(i = 0 ; i<values.length ; i++)
		{
			final Data value = values[i];
			final int compare = _comparator.compare(lValue, value);
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

	public ArrayList<? extends Type> split(final Data... values)
	{
		final ArrayList<Type> r = new ArrayList<Type>(values.length+1);
		final Data lValue = getValue();
		int i;
		for(i = 0 ; i<values.length ; i++)
		{
			final int compare = _comparator.compare(lValue, values[i]);
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

	public Iterator<Data> getReverseDataIterator() {
		return getDataIterator();
	}
}
