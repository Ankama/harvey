/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.interfaces.IIContinuousIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IContinuousIntegerSet
extends IContinuousSet<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet>,
IIContinuousIntegerSet<IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet>
{
	@Override
	public IContinuousIntegerSet unite(IContinuousIntegerSet set);
	@Override
	public IContinuousIntegerSet intersect(IContinuousIntegerSet set);
	@Override
	ISimpleContinuousIntegerSet getSimpleSet();
}