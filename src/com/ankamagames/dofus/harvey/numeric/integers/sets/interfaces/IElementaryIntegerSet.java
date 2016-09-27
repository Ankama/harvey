/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IElementaryIntegerSet
	extends IElementarySortedSet<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet>,
	IIntegerSet,
	ISimpleIntegerSet
{
	@Override
	IElementaryIntegerSet getSimpleSet();
}