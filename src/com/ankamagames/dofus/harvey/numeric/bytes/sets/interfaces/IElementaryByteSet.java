/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IElementaryByteSet
	extends IElementarySortedSet<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet>,
	IByteSet,
	ISimpleByteSet
{
	@Override
	IElementaryByteSet getSimpleSet();
}