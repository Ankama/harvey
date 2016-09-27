/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * A "simple" set is a set that is not composite of composite sets
 * It is either an elementary set, or a composite of elementary set
 *
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISimpleSet
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>
>
extends ISet<Set, SimpleSet, ElementarySet>
{
	SimpleSet getAsSimpleSet();
}