/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite.sortedintervalset;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import com.ankamagames.dofus.harvey.numeric.floats.interfaces.IFloatInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FloatIntervalTreeSet<Interval extends IFloatInterval>
implements ISortedFloatIntervalSet<Interval>
{
	protected class IntervalLowerBoundComparator
	implements Comparator<Interval>
	{
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

			return Double.compare(o1.getLowerBound(), o2.getLowerBound());
		}
	}

	protected class IntervalUpperBoundComparator
	implements Comparator<Interval>
	{
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

			return - Double.compare(o1.getUpperBound(), o2.getUpperBound());
		}
	}

	protected TreeSet<Interval> _byLowerBounds;
	protected TreeSet<Interval> _byUpperBounds;

    public FloatIntervalTreeSet() {
    	_byLowerBounds = new TreeSet<Interval>(new IntervalLowerBoundComparator());
    	_byUpperBounds = new TreeSet<Interval>(new IntervalUpperBoundComparator());
    }

    public FloatIntervalTreeSet(final Collection<? extends Interval> c) {
        this();
        addAll(c);
    }

    public FloatIntervalTreeSet(final ISortedFloatIntervalSet<Interval> s) {
    	this();
        addAll(s);
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

	@Override
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
	public ISortedFloatIntervalSet<Interval> subSet(@Nullable final Interval fromElement, @Nullable final Interval toElement)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public ISortedFloatIntervalSet<Interval> headSet(@Nullable final Interval toElement)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public ISortedFloatIntervalSet<Interval> tailSet(@Nullable final Interval fromElement)
	{
		throw new UnsupportedOperationException();
	}
}