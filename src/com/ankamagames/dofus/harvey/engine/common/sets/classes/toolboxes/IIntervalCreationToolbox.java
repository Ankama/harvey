/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IEmptySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public interface IIntervalCreationToolbox
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Interval extends IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>,
	EmptySet extends IEmptySortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet>,
	Bridged extends IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>
>
extends ISortedSetCreationToolbox<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet, Bridged>
{
	List<? extends ElementarySet> split(int numberOfParts);

	List<? extends Set> splitOnRange(Set set);

	List<? extends Set> splitOnLowerBound(Set set);

	List<? extends Set> splitOnUpperBound(Set set);

	Interval makeIntervalFromLowerBounds(Set lowerBoundSet, Set upperBoundSet);

	Interval makeIntervalFromUpperBounds(Set lowerBoundSet, Set upperBoundSet);

	Interval makeInvertedInterval(Set lowerBoundSet, Set upperBoundSet);

	SimpleSet makeSimpleCompositeSet(ElementarySet... parts);
}