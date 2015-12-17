/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * Generic type Type has to be Comparable or a comparator has to be provided
 *
 * @author sgros
 *
 */
@NonNullByDefault
public interface IByteInterval
{
	byte getLowerBound();
	byte getUpperBound();
	boolean isLowerThan(byte value);
	boolean isLowerThanOrEqualTo(byte value);
	boolean isGreaterThan(byte value);
	boolean isGreaterThanOrEqualTo(byte value);
	boolean isBetween(byte lowerBound, boolean lowerBoundIncluded, byte upperBound, boolean upperBoundIncluded);
	boolean hasValueLowerThan(byte value);
	boolean hasValueLowerThanOrEqualTo(byte value);
	boolean hasValueGreaterThan(byte value);
	boolean hasValueGreaterThanOrEqualTo(byte value);
	boolean hasValueIn(byte lowerBound, boolean lowerBoundIncluded, byte upperBound, boolean upperBoundIncluded);
}