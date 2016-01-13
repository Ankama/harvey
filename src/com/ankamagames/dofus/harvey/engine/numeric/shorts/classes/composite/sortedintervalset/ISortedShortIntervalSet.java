/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite.sortedintervalset;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

import com.ankamagames.dofus.harvey.numeric.shorts.interfaces.IShortInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISortedShortIntervalSet<Interval extends IShortInterval>
extends SortedSet<Interval>
{
	Comparator<? super Interval> reversecomparator();
	Iterator<Interval> reverseIterator();

	@Override
	ISortedShortIntervalSet<Interval> subSet(final @Nullable Interval fromElement, final @Nullable Interval toElement);
	@Override
	ISortedShortIntervalSet<Interval> headSet(final @Nullable Interval toElement);
	@Override
	ISortedShortIntervalSet<Interval> tailSet(final @Nullable Interval fromElement);
}