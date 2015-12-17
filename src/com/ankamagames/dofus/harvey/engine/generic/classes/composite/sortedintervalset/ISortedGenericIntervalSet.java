/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.composite.sortedintervalset;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

import com.ankamagames.dofus.harvey.generic.interfaces.IGenericInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISortedGenericIntervalSet<Interval extends IGenericInterval<E>, E>
extends SortedSet<Interval>
{
	@Nullable Comparator<? super E> getElementComparator();

	Iterator<Interval> reverseIterator();

	@Override
	ISortedGenericIntervalSet<Interval, E> subSet(final @Nullable Interval fromElement, final @Nullable Interval toElement);
	@Override
	ISortedGenericIntervalSet<Interval, E> headSet(final @Nullable Interval toElement);
	@Override
	ISortedGenericIntervalSet<Interval, E> tailSet(final @Nullable Interval fromElement);
}