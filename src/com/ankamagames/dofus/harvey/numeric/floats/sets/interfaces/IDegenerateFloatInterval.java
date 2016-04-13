/**
 * 
 */
package com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateContinuousInterval;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDegenerateFloatInterval
	extends IDegenerateContinuousInterval<IFloatSet>,
	IBoundedFloatInterval,
	IDegenerateFloatSet
{}