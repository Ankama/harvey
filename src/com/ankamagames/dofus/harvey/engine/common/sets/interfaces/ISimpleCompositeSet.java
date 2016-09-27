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
public interface ISimpleCompositeSet
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>,
	CompositeSet extends ICompositeSet<Set, SimpleSet, ElementarySet, CompositeSet, ?>,
	ChildType extends IElementarySet<Set, SimpleSet, ElementarySet>
>
extends ISimpleSet<Set, SimpleSet, ElementarySet>, ICompositeSet<Set, SimpleSet, ElementarySet, CompositeSet, ChildType>
{
}