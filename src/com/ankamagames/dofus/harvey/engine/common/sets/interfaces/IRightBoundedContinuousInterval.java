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
public interface IRightBoundedContinuousInterval<Set extends IOrderedSet<Set>>
	extends IContinuousInterval<Set>, IRightBoundedInterval<Set>
{
	@Override
	boolean isUpperBoundClosed();
}