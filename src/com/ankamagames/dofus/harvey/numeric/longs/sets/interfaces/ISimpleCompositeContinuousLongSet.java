/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleCompositeContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISimpleCompositeContinuousLongSet<ChildType extends IElementaryContinuousLongSet>
extends ISimpleCompositeContinuousSet<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, ICompositeContinuousLongSet<?>, ISimpleCompositeContinuousLongSet<?>, ChildType>,
ICompositeContinuousLongSet<ChildType>,
ISimpleContinuousLongSet
{}