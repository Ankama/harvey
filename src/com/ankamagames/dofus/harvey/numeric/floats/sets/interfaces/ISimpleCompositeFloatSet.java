/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleCompositeContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IFloatBound;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISimpleCompositeFloatSet<ChildType extends IElementaryFloatSet>
extends ISimpleCompositeContinuousSet<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, ICompositeFloatSet<?>, ISimpleCompositeFloatSet<ChildType>, ChildType>,
ICompositeFloatSet<ChildType>,
ISimpleFloatSet
{}