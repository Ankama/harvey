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
public interface SurroundingValuesProvider<Data>
{
	@Nullable Data getSuccessor(@Nullable Data value);
	@Nullable Data getSuccessor(@Nullable Data value, int times);
	@Nullable Data getPredecessor(@Nullable Data value);
}