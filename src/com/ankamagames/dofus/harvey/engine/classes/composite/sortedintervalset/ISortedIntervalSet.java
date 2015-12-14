/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite.sortedintervalset;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISortedIntervalSet<Interval extends IInterval<E>, E>
extends SortedSet<Interval>
{
	@Nullable Comparator<? super E> getElementComparator();

	Iterator<Interval> reverseIterator();

	@Override
	ISortedIntervalSet<Interval, E> subSet(final @Nullable Interval fromElement, final @Nullable Interval toElement);
	@Override
	ISortedIntervalSet<Interval, E> headSet(final @Nullable Interval toElement);
	@Override
	ISortedIntervalSet<Interval, E> tailSet(final @Nullable Interval fromElement);
}