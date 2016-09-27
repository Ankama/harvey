/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleCompositeContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISimpleCompositeContinuousByteSet<ChildType extends IElementaryContinuousByteSet>
extends ISimpleCompositeContinuousSet<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, ICompositeContinuousByteSet<?>, ISimpleCompositeContinuousByteSet<?>, ChildType>,
ICompositeContinuousByteSet<ChildType>,
ISimpleContinuousByteSet
{}