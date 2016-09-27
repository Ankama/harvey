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
public interface IElementaryContinuousSet
<
	Bound extends IContinuousBound<Bound>,
	Set extends IContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet>
>
extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>, ISimpleContinuousSet<Bound, Set, SimpleSet, ElementarySet>
{
	@Override
	Iterator<? extends IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet>> iterator();

	@Override
	IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet> getSimpleSet();
}