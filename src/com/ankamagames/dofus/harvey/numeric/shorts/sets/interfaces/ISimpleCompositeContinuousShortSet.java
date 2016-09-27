/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleCompositeContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISimpleCompositeContinuousShortSet<ChildType extends IElementaryContinuousShortSet>
extends ISimpleCompositeContinuousSet<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, ICompositeContinuousShortSet<?>, ISimpleCompositeContinuousShortSet<?>, ChildType>,
ICompositeContinuousShortSet<ChildType>,
ISimpleContinuousShortSet
{}