/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite.sortedintervalset;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IByteInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISortedByteIntervalSet<Interval extends IByteInterval>
extends SortedSet<Interval>
{
	Comparator<? super Interval> reversecomparator();
	Iterator<Interval> reverseIterator();

	@Override
	ISortedByteIntervalSet<Interval> subSet(final @Nullable Interval fromElement, final @Nullable Interval toElement);
	@Override
	ISortedByteIntervalSet<Interval> headSet(final @Nullable Interval toElement);
	@Override
	ISortedByteIntervalSet<Interval> tailSet(final @Nullable Interval fromElement);
}