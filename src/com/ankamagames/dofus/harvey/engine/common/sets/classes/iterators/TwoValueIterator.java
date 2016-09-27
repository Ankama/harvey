/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class TwoValueIterator<E> implements Iterator<E> {
	protected E _firstValue;
	protected E _secondValue;
	protected int _index = 0;

	public TwoValueIterator(final E firstValue, final E secondValue)
	{
		_firstValue =  firstValue;
		_secondValue = secondValue;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext()
	{
		return _index<2;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public E next()
	{
		++_index;
		if(_index == 1)
			return _firstValue;
		if(_index == 2)
			return _secondValue;
		throw new NoSuchElementException();
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove()
	{
		throw new UnsupportedOperationException();
	}
}
