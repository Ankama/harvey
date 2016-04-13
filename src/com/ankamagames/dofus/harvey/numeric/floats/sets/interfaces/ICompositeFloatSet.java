/**
 * 
 */
package com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeContinuousSet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICompositeFloatSet<ChildType extends IFloatSet>
	extends IFloatSet, ICompositeContinuousSet<IFloatSet, ChildType>
{}