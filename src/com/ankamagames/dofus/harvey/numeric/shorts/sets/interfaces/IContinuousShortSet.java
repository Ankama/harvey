/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.interfaces.IIContinuousShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IContinuousShortSet
extends IContinuousSet<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet>,
IIContinuousShortSet<IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet>
{
	@Override
	public IContinuousShortSet unite(IContinuousShortSet set);
	@Override
	public IContinuousShortSet intersect(IContinuousShortSet set);
	@Override
	ISimpleContinuousShortSet getSimpleSet();
}