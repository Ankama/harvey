/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IContinuousShortInterval
extends IContinuousShortSet,
IContinuousInterval<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval>,
IElementaryContinuousShortSet
{
	@Override
	IContinuousShortInterval getSimpleSet();
}