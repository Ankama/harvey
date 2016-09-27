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
public interface ChainedValue<Data>
{
	@Nullable Data getSuccessor();
	@Nullable Data getSuccessor(int times);
	@Nullable Data getPredecessor();
}