/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousInterval;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.interfaces.IDoubleBound;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDoubleInterval
extends IDoubleSet,
IContinuousInterval<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval>,
IElementaryDoubleSet
{
	@Override
	IDoubleInterval getSimpleSet();
}