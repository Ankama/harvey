/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.interfaces;

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDegenerateSortedSet
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Interval extends IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>,
	DegenerateSet extends IDegenerateSortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet>
>
extends
IDegenerateSet<Set, SimpleSet, ElementarySet, DegenerateSet>,
IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>
{
	@Override
	@NonNull Bound getLowerBound();

	@Override
	@NonNull Bound getUpperBound();

	@Override
	Iterator<? extends IDegenerateSortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet>> iterator();

	@Override
	IDegenerateSortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet> getSimpleSet();
}