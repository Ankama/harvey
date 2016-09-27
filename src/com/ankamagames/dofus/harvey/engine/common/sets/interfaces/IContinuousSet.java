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
public interface IContinuousSet
<
	Bound extends IContinuousBound<Bound>,
	Set extends IContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet>
>
extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>
{
	/**
	 * @return true is lowerbound is infinite
	 */
	boolean isLowerBoundInfinity();
	/**
	 * @return true is upperbound is infinite
	 */
	boolean isUpperBoundInfinity();
	/**
	 * @return true is lowerbound is closed
	 */
	boolean isLowerBoundClosed();
	/**
	 * @return true is upperbound is closed
	 */
	boolean isUpperBoundClosed();

	@Override
	Iterator<? extends IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet>> iterator();

	@Override
	IContinuousSet<Bound, Set, SimpleSet, ElementarySet> unite(Set set);

	@Override
	IContinuousSet<Bound, Set,SimpleSet,ElementarySet> intersect(Set set);

	@Override
	ISimpleContinuousSet<Bound, Set, SimpleSet, ElementarySet> getSimpleSet();
}