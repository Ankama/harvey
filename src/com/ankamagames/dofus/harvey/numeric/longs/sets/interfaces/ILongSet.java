/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.interfaces.ICommonLongSet;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.interfaces.IILongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ILongSet
extends ISortedSet<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet>,
IILongSet<ILongSet, ISimpleLongSet, IElementaryLongSet>,
ICommonLongSet<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet>
{
	@Override
	public ILongSet unite(final ILongSet set);
	@Override
	public ILongSet intersect(final ILongSet set);
	@Override
	ISimpleLongSet getSimpleSet();
}