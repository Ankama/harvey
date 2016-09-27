/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.interfaces.ICommonShortSet;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.interfaces.IIShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IShortSet
extends ISortedSet<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet>,
IIShortSet<IShortSet, ISimpleShortSet, IElementaryShortSet>,
ICommonShortSet<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet>
{
	@Override
	public IShortSet unite(final IShortSet set);
	@Override
	public IShortSet intersect(final IShortSet set);
	@Override
	ISimpleShortSet getSimpleSet();
}