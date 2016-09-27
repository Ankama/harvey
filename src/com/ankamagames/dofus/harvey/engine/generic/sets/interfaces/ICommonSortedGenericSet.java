/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.interfaces;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICommonSortedGenericSet
<
	Data,
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>
>
extends ICommonGenericSet<Data, Set, SimpleSet, ElementarySet>,
ISortedSet<Bound, Set, SimpleSet, ElementarySet>
{
	@Override
	@Nullable Bound getLowerBound();
	@Override
	@Nullable Bound getUpperBound();
	/**
	 * Splits the set in values.length+1 parts, some empty if needed.
	 *
	 * @param values the values at which the set has to be split.
	 * @param isIntervalStart indicates if the value stored at the same index will be the start of a new chunk or the end of the current
	 * @return values.length+1 chunks
	 */
	List<? extends Set> split(Data[] values, boolean[] isIntervalStart);
	List<? extends Set> split(Data... values);
}