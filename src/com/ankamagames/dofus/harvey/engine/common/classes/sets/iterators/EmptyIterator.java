/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.common.classes.sets.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class EmptyIterator<E> implements Iterator<E>
{
	@SuppressWarnings("rawtypes")
	protected static EmptyIterator _untypedEmptyIterator = new EmptyIterator();
	
	@SuppressWarnings("unchecked")
	public static <T> EmptyIterator<T> getInstance()
	{
		return _untypedEmptyIterator;
	}
	
	protected EmptyIterator()
	{	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext()
	{
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public E next()
	{
		throw new NoSuchElementException();
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove()
	{
		throw new NoSuchElementException();
	}
}
