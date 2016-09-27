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
public interface ISimpleCompositeContinuousSet
<
	Bound extends IContinuousBound<Bound>,
	Set extends IContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	CompositeSet extends ICompositeContinuousSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ?>,
	SimpleCompositeSet extends ISimpleCompositeContinuousSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, SimpleCompositeSet, ?>,
	ChildType extends IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet>
>
extends
ISimpleCompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, SimpleCompositeSet, ChildType>,
ICompositeContinuousSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType>,
ISimpleContinuousSet<Bound, Set, SimpleSet, ElementarySet>
{}