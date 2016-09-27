/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IElementaryLongSet
	extends IElementarySortedSet<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet>,
	ILongSet,
	ISimpleLongSet
{
	@Override
	IElementaryLongSet getSimpleSet();
}