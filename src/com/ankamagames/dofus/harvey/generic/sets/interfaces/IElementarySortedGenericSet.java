/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IElementarySortedGenericSet<Data>
	extends IElementarySortedSet<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>>,
	ISortedGenericSet<Data>,
	ISimpleSortedGenericSet<Data>
{
	@Override
	IElementarySortedGenericSet<Data> getSimpleSet();
}