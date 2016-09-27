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
public interface ISimpleSortedSet
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>
>
extends ISimpleSet<Set, SimpleSet, ElementarySet>, ISortedSet<Bound, Set, SimpleSet, ElementarySet>
{
	@Override
	Iterator<? extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>> iterator();
}