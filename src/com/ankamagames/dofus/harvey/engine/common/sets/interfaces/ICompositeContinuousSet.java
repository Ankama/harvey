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
public interface ICompositeContinuousSet
<
	Bound extends IContinuousBound<Bound>,
	Set extends IContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	CompositeSet extends ICompositeContinuousSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ?>,
	ChildType extends IContinuousSet<Bound, Set, SimpleSet, ElementarySet>
>
extends ICompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType>, IContinuousSet<Bound, Set, SimpleSet, ElementarySet>
{}