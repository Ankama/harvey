/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.inetrfaces;

import com.ankamagames.dofus.harvey.generic.interfaces.IGenericInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IISimpleOrderedGenericRandomVariable<Data>
extends IGenericInterval<Data>
{
	int getProbabilityForLowerThan(@Nullable Data value);
	int getProbabilityForLowerThanOrEqualTo(@Nullable Data value);
	int getProbabilityForGreaterThan(@Nullable Data value);
	int getProbabilityForGreaterThanOrEqualTo(@Nullable Data value);
}