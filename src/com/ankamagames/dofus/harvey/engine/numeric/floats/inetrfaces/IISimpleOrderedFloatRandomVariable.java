/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.inetrfaces;

import com.ankamagames.dofus.harvey.numeric.floats.interfaces.IFloatInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IISimpleOrderedFloatRandomVariable
extends IFloatInterval
{
	int getProbabilityForLowerThan(float value);
	int getProbabilityForLowerThanOrEqualTo(float value);
	int getProbabilityForGreaterThan(float value);
	int getProbabilityForGreaterThanOrEqualTo(float value);
}