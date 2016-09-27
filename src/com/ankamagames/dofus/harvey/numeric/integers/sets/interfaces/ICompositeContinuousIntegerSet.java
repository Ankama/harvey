/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICompositeContinuousIntegerSet<ChildType extends IContinuousIntegerSet>
extends ICompositeContinuousSet<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, ICompositeContinuousIntegerSet<?>, ChildType>, IContinuousIntegerSet
{
	public List<? extends IContinuousIntegerSet> splitInParts(int parts);
}