/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;
import com.ankamagames.dofus.harvey.engine.generic.sets.interfaces.ICommonSortedGenericSet;
import com.ankamagames.dofus.harvey.engine.generic.sets.interfaces.IIGenericSet;
import com.ankamagames.dofus.harvey.engine.generic.sets.interfaces.IISortedGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISortedGenericSet<Data>
extends ISortedSet<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>>,
IIGenericSet<Data, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>>,
IISortedGenericSet<Data, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>>,
ICommonSortedGenericSet<Data, IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>>
{
	@Override
	public ISortedGenericSet<Data> unite(final ISortedGenericSet<Data> set);
	@Override
	public ISortedGenericSet<Data> intersect(final ISortedGenericSet<Data> set);
	@Override
	ISimpleSortedGenericSet<Data> getSimpleSet();
}