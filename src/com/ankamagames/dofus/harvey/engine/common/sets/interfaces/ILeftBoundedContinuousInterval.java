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
public interface ILeftBoundedContinuousInterval<Set extends IOrderedSet<Set>>
	extends IContinuousInterval<Set>, ILeftBoundedInterval<Set>
{
	@Override
	boolean isLowerBoundClosed();
}