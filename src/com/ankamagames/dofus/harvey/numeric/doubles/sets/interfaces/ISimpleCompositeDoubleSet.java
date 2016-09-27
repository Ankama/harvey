/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleCompositeContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.interfaces.IDoubleBound;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISimpleCompositeDoubleSet<ChildType extends IElementaryDoubleSet>
extends ISimpleCompositeContinuousSet<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, ICompositeDoubleSet<?>, ISimpleCompositeDoubleSet<ChildType>, ChildType>,
ICompositeDoubleSet<ChildType>,
ISimpleDoubleSet
{}