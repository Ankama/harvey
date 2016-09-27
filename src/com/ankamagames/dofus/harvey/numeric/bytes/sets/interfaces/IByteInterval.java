/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IByteInterval
extends
IInterval<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval>,
IByteSet,
IElementaryByteSet
{
	@Override
	IByteInterval getSimpleSet();
}