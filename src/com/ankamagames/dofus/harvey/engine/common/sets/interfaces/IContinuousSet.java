/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.common.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IContinuousSet<Set extends ISet<Set>>
	extends IOrderedSet<Set>
{
	boolean isLowerBoundClosed();
	boolean isUpperBoundClosed();
	
	@Override
	ICompositeContinuousSet<Set, ?> getMergedSet();
}