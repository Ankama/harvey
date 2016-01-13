/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes.composite.sortedintervalset;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class MergeIterator<E>
implements Iterator<E>
{
	protected ArrayList<Iterator<? extends E>> _iterators;
	protected @Nullable Iterator<Iterator<? extends E>> _itIt;
	protected @Nullable Iterator<? extends E> _curIt;

	public MergeIterator()
    {
    	_iterators = new ArrayList<Iterator<? extends E>>(0);
    	_itIt = null;
    	_curIt = null;
    }

	public MergeIterator(final Iterator<? extends E> it)
	{
		_iterators = new ArrayList<Iterator<? extends E>>(1);
		if(it.hasNext())
		{
			_iterators.add(it);
			_curIt = it;
		}
    	_itIt = _iterators.iterator();
    }

    public MergeIterator(final Iterator<? extends E> ... iterators)
    {
    	_iterators = new ArrayList<Iterator<? extends E>>(iterators.length);
    	for(final Iterator<? extends E> iterator:iterators)
    	{
    		if(iterator.hasNext())
    			_iterators.add(iterator);
    	}
    	final Iterator<Iterator<? extends E>> itIt;
    	_itIt = itIt = _iterators.iterator();
		if(itIt.hasNext())
    		_curIt = itIt.next();
    }

	public MergeIterator(final Iterable<? extends E> ... iterables)
	{
    	_iterators = new ArrayList<Iterator<? extends E>>(iterables.length);
    	for(final Iterable<? extends E> iterable:iterables)
		{
			final Iterator<? extends E> iterator = iterable.iterator();
			if(iterator.hasNext())
				_iterators.add(iterator);
		}
    	final Iterator<Iterator<? extends E>> itIt;
    	_itIt = itIt = _iterators.iterator();
		if(itIt.hasNext())
    		_curIt = itIt.next();
    }

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext()
	{
		final Iterator<? extends E> curIt = _curIt;
		if(curIt==null)
			return false;
		if(curIt.hasNext())
			return true;
		final Iterator<Iterator<? extends E>> itIt = _itIt;
		if((itIt!=null)&&(itIt.hasNext()))
		{
			_curIt = itIt.next();
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@SuppressWarnings("null")
	@Override
	public E next()
	{
		return _curIt.next();
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@SuppressWarnings("null")
	@Override
	public void remove()
	{
		_curIt.remove();
	}
}