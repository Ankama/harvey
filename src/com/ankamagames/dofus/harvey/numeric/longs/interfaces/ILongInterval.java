/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * Generic type Type has to be Comparable or a comparator has to be provided
 *
 * @author sgros
 *
 */
@NonNullByDefault
public interface ILongInterval
{
	long getLowerBound();
	long getUpperBound();
	boolean isLowerThan(long value);
	boolean isLowerThanOrEqualTo(long value);
	boolean isGreaterThan(long value);
	boolean isGreaterThanOrEqualTo(long value);
	boolean isBetween(long lowerBound, boolean lowerBoundIncluded, long upperBound, boolean upperBoundIncluded);
	boolean hasValueLowerThan(long value);
	boolean hasValueLowerThanOrEqualTo(long value);
	boolean hasValueGreaterThan(long value);
	boolean hasValueGreaterThanOrEqualTo(long value);
	boolean hasValueIn(long lowerBound, boolean lowerBoundIncluded, long upperBound, boolean upperBoundIncluded);
}