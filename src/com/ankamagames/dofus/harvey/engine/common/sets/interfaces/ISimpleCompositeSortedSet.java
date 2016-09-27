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
public interface ISimpleCompositeSortedSet
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	CompositeSet extends ICompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ?>,
	SimpleCompositeSet extends ISimpleCompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, SimpleCompositeSet, ?>,
	ChildType extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>
>
extends
ISimpleCompositeSet<Set, SimpleSet, ElementarySet, CompositeSet, ChildType>,
ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
ICompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType>
{}