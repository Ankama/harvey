/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IEmptyContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEmptyContinuousByteSet
extends IEmptyContinuousSet<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IContinuousByteInterval, IEmptyContinuousByteSet>,
IContinuousByteSet,
IContinuousByteInterval
{
	@Override
	Iterator<? extends IEmptyContinuousByteSet> iterator();
	@Override
	IEmptyContinuousByteSet intersect(IContinuousByteSet set);
	@Override
	IEmptyContinuousByteSet subtract(IContinuousByteSet set);
	@Override
	IEmptyContinuousByteSet getSimpleSet();
}