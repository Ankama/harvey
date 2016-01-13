/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes.composite.sortedintervalset;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class MergeSortedIterator<E>
implements Iterator<E>
{
	public class IteratorComparator
	implements Comparator<Iterator<? extends E>>
	{
		@Nullable Comparator<? super E> _comparator;

		public IteratorComparator()
		{
			_comparator = null;
		}

		public IteratorComparator(@Nullable final Comparator<? super E> comparator)
		{
			_comparator = comparator;
		}

		@Override
		public int compare(@Nullable final Iterator<? extends E> o1, @Nullable final Iterator<? extends E> o2)
		{
			if(o1 == null)
				if(o2==null)
					return 0;
				else
					return -1;
			if(o2==null)
				return 1;
			E e1 = _iteratorValueMap.get(o1);
			if(e1==null)
			{
				if(o1.hasNext())
				{
					e1 = o1.next();
					_iteratorValueMap.put(o1, e1);
				}
				else
				{
					E e2 = _iteratorValueMap.get(o2);
					if(e2==null)
					{
						if(o2.hasNext())
						{
							e2 = o2.next();
							_iteratorValueMap.put(o2, e2);
							return -1;
						}
						else
							return 0;
					}
					return -1;
				}
			}

			E e2 = _iteratorValueMap.get(o2);
			if(e2==null)
			{
				if(o2.hasNext())
				{
					e2 = o2.next();
					_iteratorValueMap.put(o2, e2);
				}
				else
					return 1;
			}

			final Comparator<? super E> comparator = _comparator;
			if(comparator == null)
			{
				@SuppressWarnings("unchecked")
				final int comparision = ((Comparable<E>)e1).compareTo(e2);
				return comparision;
			}

			return comparator.compare(e1, e2);
		}
	}

	protected PriorityQueue<Iterator<? extends E>> _iterators;
	protected HashMap<Iterator<? extends E>, E> _iteratorValueMap;
	protected IteratorComparator _comparator;
	protected @Nullable Iterator<? extends E> _curIt;

	public MergeSortedIterator(@Nullable final Comparator<? super E> comparator)
    {
		_comparator = new IteratorComparator(comparator);
    	_iterators = new PriorityQueue<Iterator<? extends E>>(0, _comparator);
    	_iteratorValueMap = new HashMap<Iterator<? extends E>, E>(0);
    	_curIt = null;
    }

	public MergeSortedIterator()
    {
		this((Comparator<? super E>)null);
    }

	public MergeSortedIterator(@Nullable final Comparator<? super E> comparator, final Iterator<? extends E> it)
	{
		_comparator = new IteratorComparator(comparator);
		_iterators = new PriorityQueue<Iterator<? extends E>>(1, _comparator);
		_iteratorValueMap = new HashMap<Iterator<? extends E>, E>(1);
		if(it.hasNext())
			_iterators.add(it);
    	_curIt = null;
    }

	public MergeSortedIterator(final Iterator<? extends E> it)
	{
		this(null, it);
    }

    public MergeSortedIterator(@Nullable final Comparator<? super E> comparator, final Iterator<? extends E> ... iterators)
    {
    	_comparator = new IteratorComparator(comparator);
    	_iterators = new PriorityQueue<Iterator<? extends E>>(iterators.length, _comparator);
    	_iteratorValueMap = new HashMap<Iterator<? extends E>, E>(iterators.length);
    	for(final Iterator<? extends E> iterator:iterators)
    	{
    		if(iterator.hasNext())
    			_iterators.add(iterator);
    	}
    	_curIt = null;
    }

    public MergeSortedIterator(final Iterator<? extends E> ... iterators)
    {
    	this(null, iterators);
    }

	public MergeSortedIterator(@Nullable final Comparator<? super E> comparator, final Iterable<? extends E> ... iterables)
	{
    	_comparator = new IteratorComparator(comparator);
    	_iterators = new PriorityQueue<Iterator<? extends E>>(iterables.length, _comparator);
    	_iteratorValueMap = new HashMap<Iterator<? extends E>, E>(iterables.length);
    	for(final Iterable<? extends E> iterable:iterables)
		{
			final Iterator<? extends E> iterator = iterable.iterator();
			if(iterator.hasNext())
				_iterators.add(iterator);
		}
    	_curIt = null;
    }

	public MergeSortedIterator(final Iterable<? extends E> ... iterables)
	{
		this(null, iterables);
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext()
	{
		return !_iterators.isEmpty();
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public E next()
	{
		final Iterator<? extends E> curIt;
		_curIt = curIt = _iterators.poll();
		E r = _iteratorValueMap.get(curIt);
		if(r!=null)
		{
			_iteratorValueMap.remove(curIt);
			if(curIt.hasNext())
				_iterators.add(curIt);
		}
		else
		{
			r = curIt.next();
		}
		return r;
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