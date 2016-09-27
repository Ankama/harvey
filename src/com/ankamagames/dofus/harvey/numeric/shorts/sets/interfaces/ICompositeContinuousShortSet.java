/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICompositeContinuousShortSet<ChildType extends IContinuousShortSet>
extends ICompositeContinuousSet<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, ICompositeContinuousShortSet<?>, ChildType>, IContinuousShortSet
{
	public List<? extends IContinuousShortSet> splitInParts(int parts);
}