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
public interface IDegenerateContinuousInterval<Set extends IOrderedSet<Set>>
	extends IDegenerateInterval<Set>, IDegenerateContinuousSet<Set>
{}