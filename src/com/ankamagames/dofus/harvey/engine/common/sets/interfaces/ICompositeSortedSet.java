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
public interface ICompositeSortedSet
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	CompositeSet extends ICompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ?>,
	ChildType extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>
>
extends ICompositeSet<Set, SimpleSet, ElementarySet, CompositeSet, ChildType>, ISortedSet<Bound, Set, SimpleSet, ElementarySet>
{
	public Iterable<? extends ChildType> getChildrenDescending();
}