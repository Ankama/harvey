/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.inetrfaces;

import com.ankamagames.dofus.harvey.numeric.shorts.interfaces.IShortInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IISimpleOrderedShortRandomVariable
extends IShortInterval
{
	int getProbabilityForLowerThan(short value);
	int getProbabilityForLowerThanOrEqualTo(short value);
	int getProbabilityForGreaterThan(short value);
	int getProbabilityForGreaterThanOrEqualTo(short value);
}