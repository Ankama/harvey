/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.doubles.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * Generic type Type has to be Comparable or a comparator has to be provided
 *
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDoubleInterval
{
	double getLowerBound();
	double getUpperBound();
	boolean isLowerThan(double value);
	boolean isLowerThanOrEqualTo(double value);
	boolean isGreaterThan(double value);
	boolean isGreaterThanOrEqualTo(double value);
	boolean isBetween(double lowerBound, boolean lowerBoundIncluded, double upperBound, boolean upperBoundIncluded);
	boolean hasValueLowerThan(double value);
	boolean hasValueLowerThanOrEqualTo(double value);
	boolean hasValueGreaterThan(double value);
	boolean hasValueGreaterThanOrEqualTo(double value);
	boolean hasValueIn(double lowerBound, boolean lowerBoundIncluded, double upperBound, boolean upperBoundIncluded);
}