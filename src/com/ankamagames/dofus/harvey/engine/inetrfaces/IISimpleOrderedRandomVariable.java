/**
 *
 */
package com.ankamagames.dofus.harvey.engine.inetrfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IISimpleOrderedRandomVariable<Data>
{
	int getProbabilityForLowerThan(@Nullable Data value);
	int getProbabilityForLowerThanOrEqualTo(@Nullable Data value);
	int getProbabilityForGreaterThan(@Nullable Data value);
	int getProbabilityForGreaterThanOrEqualTo(@Nullable Data value);
}
