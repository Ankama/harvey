/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IContinuousLongInterval
extends IContinuousLongSet,
IContinuousInterval<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval>,
IElementaryContinuousLongSet
{
	@Override
	IContinuousLongInterval getSimpleSet();
}