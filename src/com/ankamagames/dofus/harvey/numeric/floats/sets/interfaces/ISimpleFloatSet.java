/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IFloatBound;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISimpleFloatSet
extends ISimpleContinuousSet<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet>, IFloatSet
{}