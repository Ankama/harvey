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
public interface IDegenerateContinuousSet
<
	Bound extends IContinuousBound<Bound>,
	Set extends IContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	Interval extends IContinuousInterval<Bound, Set, SimpleSet, ElementarySet, Interval>,
	DegenerateSet extends IDegenerateContinuousSet<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet>
>
extends
IDegenerateSortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet>,
IContinuousInterval<Bound, Set, SimpleSet, ElementarySet, Interval>
{
	@Override
	Iterator<? extends IDegenerateContinuousSet<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet>> iterator();

	@Override
	IDegenerateContinuousSet<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet> getSimpleSet();
}