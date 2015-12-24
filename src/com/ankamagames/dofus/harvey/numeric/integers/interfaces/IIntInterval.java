/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * Generic type Type has to be Comparable or a comparator has to be provided
 *
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIntInterval
{
	int getLowerBound();
	int getUpperBound();
	boolean isLowerThan(int value);
	boolean isLowerThanOrEqualTo(int value);
	boolean isGreaterThan(int value);
	boolean isGreaterThanOrEqualTo(int value);
	boolean isBetween(int lowerBound, boolean lowerBoundIncluded, int upperBound, boolean upperBoundIncluded);
	boolean hasValueLowerThan(int value);
	boolean hasValueLowerThanOrEqualTo(int value);
	boolean hasValueGreaterThan(int value);
	boolean hasValueGreaterThanOrEqualTo(int value);
	boolean hasValueIn(int lowerBound, boolean lowerBoundIncluded, int upperBound, boolean upperBoundIncluded);
}