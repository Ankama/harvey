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
public interface IDegenerateInterval<Set extends IOrderedSet<Set>>
	extends IBoundedInterval<Set>, IDegenerateOrderedSet<Set>
{}