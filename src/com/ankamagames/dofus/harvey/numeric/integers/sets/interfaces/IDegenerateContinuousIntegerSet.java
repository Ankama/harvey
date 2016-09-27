/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDegenerateContinuousIntegerSet
extends IDegenerateContinuousSet<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, IContinuousIntegerInterval, IDegenerateContinuousIntegerSet>,
IContinuousIntegerInterval
{
	int getValue();

	@Override
	Iterator<? extends IDegenerateContinuousIntegerSet> iterator();

	@Override
	IDegenerateContinuousIntegerSet getSimpleSet();
}