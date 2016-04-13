/**
 * 
 */
package com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IRightBoundedContinuousInterval;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IRightBoundedFloatInterval
	extends IFloatInterval, IRightBoundedContinuousInterval<IFloatSet>
{}