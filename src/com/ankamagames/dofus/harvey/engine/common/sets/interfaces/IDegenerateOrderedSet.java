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
public interface IDegenerateOrderedSet<Set extends IOrderedSet<Set>>
	extends IOrderedSet<Set>, IDegenerateSet<Set>
{}