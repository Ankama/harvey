/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite.sortedintervalset;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

import com.ankamagames.dofus.harvey.numeric.doubles.interfaces.IDoubleInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISortedDoubleIntervalSet<Interval extends IDoubleInterval>
extends SortedSet<Interval>
{
	Comparator<? super Interval> reversecomparator();
	Iterator<Interval> reverseIterator();

	@Override
	ISortedDoubleIntervalSet<Interval> subSet(final @Nullable Interval fromElement, final @Nullable Interval toElement);
	@Override
	ISortedDoubleIntervalSet<Interval> headSet(final @Nullable Interval toElement);
	@Override
	ISortedDoubleIntervalSet<Interval> tailSet(final @Nullable Interval fromElement);
}