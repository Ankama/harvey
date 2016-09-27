/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDegenerateContinuousShortSet
extends IDegenerateContinuousSet<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval, IDegenerateContinuousShortSet>,
IContinuousShortInterval
{
	short getValue();

	@Override
	Iterator<? extends IDegenerateContinuousShortSet> iterator();

	@Override
	IDegenerateContinuousShortSet getSimpleSet();
}