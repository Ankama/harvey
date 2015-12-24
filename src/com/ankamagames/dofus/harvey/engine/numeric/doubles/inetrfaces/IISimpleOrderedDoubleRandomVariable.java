/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.inetrfaces;

import com.ankamagames.dofus.harvey.numeric.doubles.interfaces.IDoubleInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IISimpleOrderedDoubleRandomVariable
extends IDoubleInterval
{
	int getProbabilityForLowerThan(double value);
	int getProbabilityForLowerThanOrEqualTo(double value);
	int getProbabilityForGreaterThan(double value);
	int getProbabilityForGreaterThanOrEqualTo(double value);
}