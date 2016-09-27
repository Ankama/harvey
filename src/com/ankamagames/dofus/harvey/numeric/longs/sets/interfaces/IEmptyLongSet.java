/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IEmptySortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEmptyLongSet
extends
IEmptySortedSet
<
	ILongBound,
	ILongSet,
	ISimpleLongSet,
	IElementaryLongSet,
	ILongInterval,
	IEmptyLongSet
>,
ILongInterval
{
	@Override
	Iterator<? extends IEmptyLongSet> iterator();

	@Override
	IEmptyLongSet intersect(ILongSet set);

	@Override
	IEmptyLongSet subtract(ILongSet set);

	@Override
	IEmptyLongSet getSimpleSet();
}