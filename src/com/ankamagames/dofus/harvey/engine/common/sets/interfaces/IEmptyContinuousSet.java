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
public interface IEmptyContinuousSet
<
	Bound extends IContinuousBound<Bound>,
	Set extends IContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	Interval extends IContinuousInterval<Bound, Set, SimpleSet, ElementarySet, Interval>,
	EmptySet extends IEmptyContinuousSet<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet>
>
extends IEmptySortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet>, IContinuousInterval<Bound, Set, SimpleSet, ElementarySet, Interval>
{
	@Override
	Iterator<? extends IEmptyContinuousSet<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet>> iterator();

	@Override
	IEmptyContinuousSet<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet> intersect(Set set);

	@Override
	IEmptyContinuousSet<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet> getSimpleSet();
}