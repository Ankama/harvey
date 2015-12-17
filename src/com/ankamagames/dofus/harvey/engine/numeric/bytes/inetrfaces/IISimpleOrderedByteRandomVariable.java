/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.inetrfaces;

import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IByteInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IISimpleOrderedByteRandomVariable
extends IByteInterval
{
	int getProbabilityForLowerThan(byte value);
	int getProbabilityForLowerThanOrEqualTo(byte value);
	int getProbabilityForGreaterThan(byte value);
	int getProbabilityForGreaterThanOrEqualTo(byte value);
}