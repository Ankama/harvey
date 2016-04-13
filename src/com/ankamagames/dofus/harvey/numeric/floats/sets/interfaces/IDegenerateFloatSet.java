/**
 * 
 */
package com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateContinuousSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDegenerateFloatSet
	extends IFloatSet, IDegenerateContinuousSet<IFloatSet>
{
	float getValue();
}