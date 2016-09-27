/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ILongInterval
extends
IInterval<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval>,
ILongSet,
IElementaryLongSet
{
	@Override
	ILongInterval getSimpleSet();
}