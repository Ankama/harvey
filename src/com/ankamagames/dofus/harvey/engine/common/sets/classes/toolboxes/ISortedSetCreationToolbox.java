/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IEmptySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISortedSetCreationToolbox
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Interval extends IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>,
	EmptySet extends IEmptySortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet>,
	Bridged extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>
>
extends ISetCreationToolbox<Set, SimpleSet, ElementarySet, EmptySet, Bridged>
{
	@Override
	EmptySet makeEmptySet();

	IDegenerateSortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, ?> makeDegenerate(Bound bound);

	Interval makeInterval(Bound lowerBound, Bound upperBound);
}