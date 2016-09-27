/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.interfaces.IIContinuousByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IContinuousByteSet
extends IContinuousSet<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet>,
IIContinuousByteSet<IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet>
{
	@Override
	public IContinuousByteSet unite(IContinuousByteSet set);
	@Override
	public IContinuousByteSet intersect(IContinuousByteSet set);
	@Override
	ISimpleContinuousByteSet getSimpleSet();
}