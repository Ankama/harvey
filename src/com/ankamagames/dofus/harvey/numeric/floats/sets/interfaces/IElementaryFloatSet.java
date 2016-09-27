/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementaryContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IFloatBound;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IElementaryFloatSet
extends IElementaryContinuousSet<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet>, IFloatSet, ISimpleFloatSet
{
	@Override
	IElementaryFloatSet getSimpleSet();
}