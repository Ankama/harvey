/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * Generic type Type has to be Comparable or a comparator has to be provided
 *
 * @author sgros
 *
 */
@NonNullByDefault
public interface IShortInterval
{
	short getLowerBound();
	short getUpperBound();
	boolean isLowerThan(short value);
	boolean isLowerThanOrEqualTo(short value);
	boolean isGreaterThan(short value);
	boolean isGreaterThanOrEqualTo(short value);
	boolean isBetween(short lowerBound, boolean lowerBoundIncluded, short upperBound, boolean upperBoundIncluded);
	boolean hasValueLowerThan(short value);
	boolean hasValueLowerThanOrEqualTo(short value);
	boolean hasValueGreaterThan(short value);
	boolean hasValueGreaterThanOrEqualTo(short value);
	boolean hasValueIn(short lowerBound, boolean lowerBoundIncluded, short upperBound, boolean upperBoundIncluded);
}