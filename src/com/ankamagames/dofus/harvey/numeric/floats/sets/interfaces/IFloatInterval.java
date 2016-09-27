/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousInterval;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IFloatBound;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IFloatInterval
extends IFloatSet,
IContinuousInterval<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval>,
IElementaryFloatSet
{
	@Override
	IFloatInterval getSimpleSet();
}