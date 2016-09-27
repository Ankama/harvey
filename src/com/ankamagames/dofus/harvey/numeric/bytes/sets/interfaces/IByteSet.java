/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.interfaces.ICommonByteSet;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.interfaces.IIByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IByteSet
extends ISortedSet<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet>,
IIByteSet<IByteSet, ISimpleByteSet, IElementaryByteSet>,
ICommonByteSet<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet>
{
	@Override
	public IByteSet unite(final IByteSet set);
	@Override
	public IByteSet intersect(final IByteSet set);
	@Override
	ISimpleByteSet getSimpleSet();
}