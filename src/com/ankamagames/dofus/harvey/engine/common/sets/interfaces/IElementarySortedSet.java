/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IElementarySortedSet
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>
>
extends IElementarySet<Set, SimpleSet, ElementarySet>, ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>
{
	@Override
	IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet> getSimpleSet();
}