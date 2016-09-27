/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.comparators;

import java.util.Comparator;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ContinuousComparator<E>
extends Comparator<E>
{
	@Override
	int compare(@Nullable E o1, @Nullable E o2);

	double compareContinuous(@Nullable E o1, @Nullable E o2);
}
