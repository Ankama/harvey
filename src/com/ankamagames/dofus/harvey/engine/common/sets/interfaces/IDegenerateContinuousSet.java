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
public interface IDegenerateContinuousSet<Set extends IOrderedSet<Set>>
	extends IDegenerateOrderedSet<Set>, IContinuousSet<Set>
{}