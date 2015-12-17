/**
 *
 */
package com.ankamagames.dofus.harvey.generic.interfaces;

import java.util.Comparator;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Generic type Type has to be Comparable or a comparator has to be provided
 *
 * @author sgros
 *
 */
@NonNullByDefault
public interface IGenericInterval<E>
{
	@Nullable Comparator<? super E> getComparator();
	@Nullable E getLowerBound();
	@Nullable E getUpperBound();
	boolean isLowerThan(@Nullable E value);
	boolean isLowerThanOrEqualTo(@Nullable E value);
	boolean isGreaterThan(@Nullable E value);
	boolean isGreaterThanOrEqualTo(@Nullable E value);
	boolean isBetween(@Nullable E lowerBound, boolean lowerBoundIncluded, @Nullable E upperBound, boolean upperBoundIncluded);
	boolean hasValueLowerThan(@Nullable E value);
	boolean hasValueLowerThanOrEqualTo(@Nullable E value);
	boolean hasValueGreaterThan(@Nullable E value);
	boolean hasValueGreaterThanOrEqualTo(@Nullable E value);
	boolean hasValueIn(@Nullable E lowerBound, boolean lowerBoundIncluded, @Nullable E upperBound, boolean upperBoundIncluded);
}