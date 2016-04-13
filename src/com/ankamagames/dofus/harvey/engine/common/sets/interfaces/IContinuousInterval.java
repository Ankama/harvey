/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * Implies ordered data
 * 
 * @author sgros
 */
@NonNullByDefault
public interface IContinuousInterval<Set extends IOrderedSet<Set>>
	extends IInterval<Set>, IContinuousSet<Set>
{}