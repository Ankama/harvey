/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IElementaryShortSet
	extends IElementarySortedSet<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet>,
	IShortSet,
	ISimpleShortSet
{
	@Override
	IElementaryShortSet getSimpleSet();
}