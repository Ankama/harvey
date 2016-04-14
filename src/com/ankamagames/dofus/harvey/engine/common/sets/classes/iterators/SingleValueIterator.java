/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author sgros
 *
 */
public class SingleValueIterator<E> implements Iterator<E>
{
	protected E _value;
	
	public SingleValueIterator(E value)
	{
		_value =  value;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext()
	{
		return _value != null;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public E next()
	{
		if(_value != null)
		{
			E tmp = _value;
			_value = null;
			return tmp;
		}
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
