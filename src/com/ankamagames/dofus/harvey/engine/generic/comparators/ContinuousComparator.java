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
public interface ContinuousComparator<E>
{
	double compare(@Nullable E o1, @Nullable E o2);
}
