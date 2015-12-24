/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.inetrfaces;

import com.ankamagames.dofus.harvey.numeric.integers.interfaces.IIntInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IISimpleOrderedIntRandomVariable
extends IIntInterval
{
	int getProbabilityForLowerThan(int value);
	int getProbabilityForLowerThanOrEqualTo(int value);
	int getProbabilityForGreaterThan(int value);
	int getProbabilityForGreaterThanOrEqualTo(int value);
}