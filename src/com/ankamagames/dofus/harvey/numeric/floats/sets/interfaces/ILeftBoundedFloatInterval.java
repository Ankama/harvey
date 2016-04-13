/**
 * 
 */
package com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ILeftBoundedContinuousInterval;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ILeftBoundedFloatInterval
	extends IFloatInterval, ILeftBoundedContinuousInterval<IFloatSet>
{}