/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IFloatBound;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDegenerateFloatSet
extends IDegenerateContinuousSet<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, IDegenerateFloatSet>,
IFloatSet,
IElementaryFloatSet,
IFloatInterval
{
	float getValue();

	@Override
	public IDegenerateFloatSet getSimpleSet();
}