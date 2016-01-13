/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite.sortedintervalset;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

import com.ankamagames.dofus.harvey.numeric.floats.interfaces.IFloatInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISortedFloatIntervalSet<Interval extends IFloatInterval>
extends SortedSet<Interval>
{
	Comparator<? super Interval> reversecomparator();
	Iterator<Interval> reverseIterator();

	@Override
	ISortedFloatIntervalSet<Interval> subSet(final @Nullable Interval fromElement, final @Nullable Interval toElement);
	@Override
	ISortedFloatIntervalSet<Interval> headSet(final @Nullable Interval toElement);
	@Override
	ISortedFloatIntervalSet<Interval> tailSet(final @Nullable Interval fromElement);
}