/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IShortInterval
extends
IInterval<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval>,
IShortSet,
IElementaryShortSet
{
	@Override
	IShortInterval getSimpleSet();
}