/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IEmptySortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEmptyIntegerSet
extends
IEmptySortedSet
<
	IIntegerBound,
	IIntegerSet,
	ISimpleIntegerSet,
	IElementaryIntegerSet,
	IIntegerInterval,
	IEmptyIntegerSet
>,
IIntegerInterval
{
	@Override
	Iterator<? extends IEmptyIntegerSet> iterator();

	@Override
	IEmptyIntegerSet intersect(IIntegerSet set);

	@Override
	IEmptyIntegerSet subtract(IIntegerSet set);

	@Override
	IEmptyIntegerSet getSimpleSet();
}