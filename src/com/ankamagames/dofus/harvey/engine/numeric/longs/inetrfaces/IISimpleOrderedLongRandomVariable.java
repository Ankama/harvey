/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.inetrfaces;

import com.ankamagames.dofus.harvey.numeric.longs.interfaces.ILongInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IISimpleOrderedLongRandomVariable
extends ILongInterval
{
	int getProbabilityForLowerThan(long value);
	int getProbabilityForLowerThanOrEqualTo(long value);
	int getProbabilityForGreaterThan(long value);
	int getProbabilityForGreaterThanOrEqualTo(long value);
}