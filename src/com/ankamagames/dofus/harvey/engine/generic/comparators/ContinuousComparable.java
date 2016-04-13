/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.comparators;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ContinuousComparable<E>
{
	double compareTo(@Nullable E o);
}
