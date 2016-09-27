/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IEmptySortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEmptyShortSet
extends
IEmptySortedSet
<
	IShortBound,
	IShortSet,
	ISimpleShortSet,
	IElementaryShortSet,
	IShortInterval,
	IEmptyShortSet
>,
IShortInterval
{
	@Override
	Iterator<? extends IEmptyShortSet> iterator();

	@Override
	IEmptyShortSet intersect(IShortSet set);

	@Override
	IEmptyShortSet subtract(IShortSet set);

	@Override
	IEmptyShortSet getSimpleSet();
}