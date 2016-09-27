/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.interfaces;

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEmptySortedSet
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Interval extends IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>,
	EmptySet extends IEmptySortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet>
>
extends IEmptySet<Set, SimpleSet, ElementarySet, EmptySet>, IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>
{
	@Override
	IEmptySortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet> intersect(Set set);

	@Override
	Iterator<? extends IEmptySortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet>> iterator();

	@Override
	IEmptySortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet> getSimpleSet();
}