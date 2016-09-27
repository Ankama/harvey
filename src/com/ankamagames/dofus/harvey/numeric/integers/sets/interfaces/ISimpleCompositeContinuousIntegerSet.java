/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleCompositeContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISimpleCompositeContinuousIntegerSet<ChildType extends IElementaryContinuousIntegerSet>
extends ISimpleCompositeContinuousSet<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, ICompositeContinuousIntegerSet<?>, ISimpleCompositeContinuousIntegerSet<?>, ChildType>,
ICompositeContinuousIntegerSet<ChildType>,
ISimpleContinuousIntegerSet
{}