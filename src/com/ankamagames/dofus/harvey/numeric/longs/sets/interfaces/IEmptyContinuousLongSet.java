/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IEmptyContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEmptyContinuousLongSet
extends IEmptyContinuousSet<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval, IEmptyContinuousLongSet>,
IContinuousLongSet,
IContinuousLongInterval
{
	@Override
	Iterator<? extends IEmptyContinuousLongSet> iterator();
	@Override
	IEmptyContinuousLongSet intersect(IContinuousLongSet set);
	@Override
	IEmptyContinuousLongSet subtract(IContinuousLongSet set);
	@Override
	IEmptyContinuousLongSet getSimpleSet();
}