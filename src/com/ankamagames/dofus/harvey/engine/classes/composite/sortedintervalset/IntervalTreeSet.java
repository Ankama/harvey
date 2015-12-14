/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite.sortedintervalset;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class IntervalTreeSet<Interval extends IInterval<E>, E>
implements ISortedIntervalSet<Interval, E>
{
	protected class IntervalLowerBoundComparatorWOComparator
	implements Comparator<Interval>
	{
		@SuppressWarnings("unchecked")
		@Override
		public int compare(@Nullable final Interval o1, @Nullable final Interval o2)
		{
			if(o1==null)
				if(o2==null)
					return 0;
				else
					return -1;
			if(o2==null)
				return 1;
			final E l1 = o1.getLowerBound();
			final E l2 = o2.getLowerBound();
			if(l1==null)
				if(l2==null)
					return 0;
				else
					return -((Comparable<E>)l2).compareTo(l1);
			return ((Comparable<E>)l1).compareTo(l2);
		}
	}

	protected class IntervalLowerBoundComparatorWComparator
	implements Comparator<Interval>
	{
		@SuppressWarnings("null")
		@Override
		public int compare(@Nullable final Interval o1, @Nullable final Interval o2)
		{
			if(o1==null)
				if(o2==null)
					return 0;
				else
					return -1;
			if(o2==null)
				return 1;
			return getElementComparator().compare(o1.getLowerBound(), o2.getLowerBound());
		}
	}

	protected class IntervalUpperBoundComparatorWOComparator
	implements Comparator<Interval>
	{
		@SuppressWarnings("unchecked")
		@Override
		public int compare(@Nullable final Interval o1, @Nullable final Interval o2)
		{
			if(o1==null)
				if(o2==null)
					return 0;
				else
					return 1;
			if(o2==null)
				return -1;
			final E u1 = o1.getUpperBound();
			final E u2 = o2.getUpperBound();
			if(u1==null)
				if(u2==null)
					return 0;
				else
					return ((Comparable<E>)u2).compareTo(u1);
			return -((Comparable<E>)u1).compareTo(u2);
		}
	}

	protected class IntervalUpperBoundComparatorWComparator
	implements Comparator<Interval>
	{
		@SuppressWarnings("null")
		@Override
		public int compare(@Nullable final Interval o1, @Nullable final Interval o2)
		{
			if(o1==null)
				if(o2==null)
					return 0;
				else
					return 1;
			if(o2==null)
				return -1;
			return -getElementComparator().compare(o1.getUpperBound(), o2.getUpperBound());
		}
	}

	protected @Nullable Comparator<? super E> _comparator;

	protected TreeSet<Interval> _byLowerBounds;
	protected TreeSet<Interval> _byUpperBounds;

    public IntervalTreeSet() {
    	_comparator = null;
    	_byLowerBounds = new TreeSet<Interval>(new IntervalLowerBoundComparatorWOComparator());
    	_byUpperBounds = new TreeSet<Interval>(new IntervalUpperBoundComparatorWOComparator());
    }

    public IntervalTreeSet(final @Nullable Comparator<? super E> comparator) {
    	_comparator = comparator;
    	if(comparator!=null)
    	{
    		_byLowerBounds = new TreeSet<Interval>(new IntervalLowerBoundComparatorWComparator());
    		_byUpperBounds = new TreeSet<Interval>(new IntervalUpperBoundComparatorWComparator());
    	}
    	else
    	{
        	_byLowerBounds = new TreeSet<Interval>(new IntervalLowerBoundComparatorWOComparator());
        	_byUpperBounds = new TreeSet<Interval>(new IntervalUpperBoundComparatorWOComparator());
    	}
    }

    public IntervalTreeSet(final Collection<? extends Interval> c) {
        this();
        addAll(c);
    }

    public IntervalTreeSet(final ISortedIntervalSet<Interval, E> s) {
    	this(s.getElementComparator());
        addAll(s);
    }

    @Override
	@Nullable
	public Comparator<? super E> getElementComparator()
    {
    	return _comparator;
    }

	@Override
	public Comparator<? super Interval> comparator()
	{
		return _byLowerBounds.comparator();
	}

	@Override
	public Interval first()
	{
		return _byLowerBounds.first();
	}

	@Override
	public Interval last()
	{
		return _byUpperBounds.first();
	}

	@Override
	public int size()
	{
		return _byLowerBounds.size();
	}

	@Override
	public boolean isEmpty()
	{
		return _byLowerBounds.isEmpty();
	}

	@Override
	public boolean contains(final @Nullable Object o)
	{
		return _byLowerBounds.contains(o);
	}

	@Override
	public Iterator<Interval> iterator()
	{
		return _byLowerBounds.iterator();
	}

	public Iterator<Interval> reverseIterator()
	{
		return _byUpperBounds.iterator();
	}

	@Override
	public Object[] toArray()
	{
		return _byLowerBounds.toArray();
	}

	@Override
	public <T> T[] toArray(final @Nullable T[] a)
	{
		return _byLowerBounds.toArray(a);
	}

	@Override
	public boolean add(final @Nullable Interval e)
	{
		return _byLowerBounds.add(e) && _byUpperBounds.add(e);
	}

	@Override
	public boolean remove(final @Nullable Object o)
	{
		return _byLowerBounds.remove(o) && _byUpperBounds.remove(o);
	}

	@Override
	public boolean containsAll(final @Nullable Collection<?> c)
	{
		return _byLowerBounds.containsAll(c);
	}

	@Override
	public boolean addAll(final @Nullable Collection<? extends Interval> c)
	{
		return _byLowerBounds.addAll(c) && _byUpperBounds.addAll(c);
	}

	@Override
	public boolean retainAll(final @Nullable Collection<?> c)
	{
		return _byLowerBounds.retainAll(c) && _byUpperBounds.retainAll(c);
	}

	@Override
	public boolean removeAll(final @Nullable Collection<?> c)
	{
		return _byLowerBounds.removeAll(c) && _byUpperBounds.removeAll(c);
	}

	@Override
	public void clear()
	{
		_byLowerBounds.clear();
		_byUpperBounds.clear();
	}

	@Override
	public ISortedIntervalSet<Interval, E> subSet(@Nullable final Interval fromElement, @Nullable final Interval toElement)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public ISortedIntervalSet<Interval, E> headSet(@Nullable final Interval toElement)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public ISortedIntervalSet<Interval, E> tailSet(@Nullable final Interval fromElement)
	{
		throw new UnsupportedOperationException();
	}
}