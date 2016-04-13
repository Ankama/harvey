/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.incrementors;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ContinuousIncrementor<Data>
{
	@Nullable Data getNext(@Nullable Data value, double steps);
}