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
public interface IBoundedInterval<Set extends IOrderedSet<Set>>
	extends ILeftBoundedInterval<Set>, IRightBoundedInterval<Set>
{}