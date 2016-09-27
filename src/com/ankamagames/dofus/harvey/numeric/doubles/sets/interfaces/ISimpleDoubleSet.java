/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.interfaces.IDoubleBound;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISimpleDoubleSet
extends ISimpleContinuousSet<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet>, IDoubleSet
{}