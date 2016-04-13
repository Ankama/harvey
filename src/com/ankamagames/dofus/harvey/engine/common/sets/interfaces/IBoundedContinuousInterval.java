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
public interface IBoundedContinuousInterval<Set extends IOrderedSet<Set>>
	extends ILeftBoundedContinuousInterval<Set>, IRightBoundedContinuousInterval<Set>, IBoundedInterval<Set>
{}