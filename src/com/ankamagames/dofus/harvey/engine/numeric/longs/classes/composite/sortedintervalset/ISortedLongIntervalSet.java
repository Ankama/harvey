/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite.sortedintervalset;

import java.util.Iterator;
import java.util.SortedSet;

import com.ankamagames.dofus.harvey.numeric.longs.interfaces.ILongInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISortedLongIntervalSet<Interval extends ILongInterval>
extends SortedSet<Interval>
{
	Iterator<Interval> reverseIterator();

	@Override
	ISortedLongIntervalSet<Interval> subSet(final @Nullable Interval fromElement, final @Nullable Interval toElement);
	@Override
	ISortedLongIntervalSet<Interval> headSet(final @Nullable Interval toElement);
	@Override
	ISortedLongIntervalSet<Interval> tailSet(final @Nullable Interval fromElement);
}