/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite.sortedintervalset;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

import com.ankamagames.dofus.harvey.numeric.integers.interfaces.IIntInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISortedIntIntervalSet<Interval extends IIntInterval>
extends SortedSet<Interval>
{
	Comparator<? super Interval> reversecomparator();
	Iterator<Interval> reverseIterator();

	@Override
	ISortedIntIntervalSet<Interval> subSet(final @Nullable Interval fromElement, final @Nullable Interval toElement);
	@Override
	ISortedIntIntervalSet<Interval> headSet(final @Nullable Interval toElement);
	@Override
	ISortedIntIntervalSet<Interval> tailSet(final @Nullable Interval fromElement);
}