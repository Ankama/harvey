/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementaryContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IElementaryContinuousIntegerSet
extends IElementaryContinuousSet<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet>, IContinuousIntegerSet, ISimpleContinuousIntegerSet
{
	@Override
	IElementaryContinuousIntegerSet getSimpleSet();
}