/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.interfaces.IIContinuousLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IContinuousLongSet
extends IContinuousSet<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet>,
IIContinuousLongSet<IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet>
{
	@Override
	public IContinuousLongSet unite(IContinuousLongSet set);
	@Override
	public IContinuousLongSet intersect(IContinuousLongSet set);
	@Override
	ISimpleContinuousLongSet getSimpleSet();
}