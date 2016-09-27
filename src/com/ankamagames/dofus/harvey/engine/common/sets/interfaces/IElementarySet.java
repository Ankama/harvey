/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * An elementary set is a non-composite set
 * It can be a degenerate set or an interval for exemple
 * It is a set which holds all of its parts
 *
 * @author sgros
 *
 */
@NonNullByDefault
public interface IElementarySet
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>
>
extends ISimpleSet<Set, SimpleSet, ElementarySet>
{
	ElementarySet getAsElementarySet();
	
	@Override
	IElementarySet<Set, SimpleSet, ElementarySet> getSimpleSet();
}