/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.floats.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * Generic type Type has to be Comparable or a comparator has to be provided
 *
 * @author sgros
 *
 */
@NonNullByDefault
public interface IFloatInterval
{
	float getLowerBound();
	float getUpperBound();
	boolean isLowerThan(float value);
	boolean isLowerThanOrEqualTo(float value);
	boolean isGreaterThan(float value);
	boolean isGreaterThanOrEqualTo(float value);
	boolean isBetween(float lowerBound, boolean lowerBoundIncluded, float upperBound, boolean upperBoundIncluded);
	boolean hasValueLowerThan(float value);
	boolean hasValueLowerThanOrEqualTo(float value);
	boolean hasValueGreaterThan(float value);
	boolean hasValueGreaterThanOrEqualTo(float value);
	boolean hasValueIn(float lowerBound, boolean lowerBoundIncluded, float upperBound, boolean upperBoundIncluded);
}