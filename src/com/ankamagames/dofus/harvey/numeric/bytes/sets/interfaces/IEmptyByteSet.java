/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IEmptySortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEmptyByteSet
extends
IEmptySortedSet
<
	IByteBound,
	IByteSet,
	ISimpleByteSet,
	IElementaryByteSet,
	IByteInterval,
	IEmptyByteSet
>,
IByteInterval
{
	@Override
	Iterator<? extends IEmptyByteSet> iterator();

	@Override
	IEmptyByteSet intersect(IByteSet set);

	@Override
	IEmptyByteSet subtract(IByteSet set);

	@Override
	IEmptyByteSet getSimpleSet();
}