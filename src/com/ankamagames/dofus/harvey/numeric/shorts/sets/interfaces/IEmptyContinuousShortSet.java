/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IEmptyContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEmptyContinuousShortSet
extends IEmptyContinuousSet<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval, IEmptyContinuousShortSet>,
IContinuousShortSet,
IContinuousShortInterval
{
	@Override
	Iterator<? extends IEmptyContinuousShortSet> iterator();
	@Override
	IEmptyContinuousShortSet intersect(IContinuousShortSet set);
	@Override
	IEmptyContinuousShortSet subtract(IContinuousShortSet set);
	@Override
	IEmptyContinuousShortSet getSimpleSet();
}