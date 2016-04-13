/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IInterval;

/**
 * Generic type Type has to be Comparable or a comparator has to be provided
 *
 * @author sgros
 *
 */
@NonNullByDefault
public interface IByteInterval<Elements extends IByteSet<?>>
	extends IByteSet<Elements>, IInterval<IByteSet<?>, Elements>
{
	boolean isBetween(byte lowerBound, boolean lowerBoundIncluded, byte upperBound, boolean upperBoundIncluded);
	boolean hasValueIn(byte lowerBound, boolean lowerBoundIncluded, byte upperBound, boolean upperBoundIncluded);
}