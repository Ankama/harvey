/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.interfaces.ICommonIntegerSet;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.interfaces.IIIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIntegerSet
extends ISortedSet<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet>,
IIIntegerSet<IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet>,
ICommonIntegerSet<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet>
{
	@Override
	public IIntegerSet unite(final IIntegerSet set);
	@Override
	public IIntegerSet intersect(final IIntegerSet set);
	@Override
	ISimpleIntegerSet getSimpleSet();
}