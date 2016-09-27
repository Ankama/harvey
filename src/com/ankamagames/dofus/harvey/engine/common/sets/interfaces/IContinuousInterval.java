/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * Implies Continuous data
 *
 * @author sgros
 */
@NonNullByDefault
public interface IContinuousInterval
<
	Bound extends IContinuousBound<Bound>,
	Set extends IContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	Interval extends IContinuousInterval<Bound, Set, SimpleSet, ElementarySet, Interval>
>
extends IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet>, IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>
{
	@Override
	IContinuousInterval<Bound, Set, SimpleSet, ElementarySet, Interval> getSimpleSet();
}