/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IEmptyContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEmptyContinuousIntegerSet
extends IEmptyContinuousSet<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, IContinuousIntegerInterval, IEmptyContinuousIntegerSet>,
IContinuousIntegerSet,
IContinuousIntegerInterval
{
	@Override
	Iterator<? extends IEmptyContinuousIntegerSet> iterator();
	@Override
	IEmptyContinuousIntegerSet intersect(IContinuousIntegerSet set);
	@Override
	IEmptyContinuousIntegerSet subtract(IContinuousIntegerSet set);
	@Override
	IEmptyContinuousIntegerSet getSimpleSet();
}