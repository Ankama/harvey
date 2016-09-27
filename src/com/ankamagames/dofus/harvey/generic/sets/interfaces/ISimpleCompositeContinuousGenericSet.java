/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleCompositeContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISimpleCompositeContinuousGenericSet<Data, ChildType extends IElementaryContinuousGenericSet<Data>>
extends ISimpleCompositeContinuousSet<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, ICompositeContinuousGenericSet<Data, ?>, ISimpleCompositeContinuousGenericSet<Data, ?>, ChildType>,
ICompositeContinuousGenericSet<Data, ChildType>,
ISimpleContinuousGenericSet<Data>
{}