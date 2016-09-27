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
public interface IDegenerateSet
<
	Set extends ISet<Set, SimpleSet, ElementarySet>, 
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>,
	DegenerateSet extends IDegenerateSet<Set, SimpleSet, ElementarySet, DegenerateSet>
>
extends IElementarySet<Set, SimpleSet, ElementarySet>
{
	DegenerateSet getAsDegenerateSet();
	
	@Override
	Iterator<? extends IDegenerateSet<Set, SimpleSet, ElementarySet, DegenerateSet>> iterator();

	@Override
	IDegenerateSet<Set, SimpleSet, ElementarySet, DegenerateSet> getSimpleSet();
}