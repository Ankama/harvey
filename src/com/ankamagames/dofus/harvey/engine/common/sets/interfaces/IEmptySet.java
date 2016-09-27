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
public interface IEmptySet
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>,
	EmptySet extends IEmptySet<Set, SimpleSet, ElementarySet, EmptySet>
>
extends IElementarySet<Set, SimpleSet, ElementarySet>
{
	EmptySet getAsEmptySet();

	@Override
	Set unite(Set set);

	@Override
	IEmptySet<Set, SimpleSet, ElementarySet, EmptySet> intersect(Set set);

	@Override
	IEmptySet<Set, SimpleSet, ElementarySet, EmptySet> subtract(Set set);

	@Override
	Set symmetricSubtract(Set set);

	@Override
	IEmptySet<Set, SimpleSet, ElementarySet, EmptySet> getSimpleSet();
}