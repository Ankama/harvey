/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;

/**
 * @author sgros
 *
 */
public interface IElementaryEvent
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>,
	DegenerateSet extends IDegenerateSet<Set, SimpleSet, ElementarySet, DegenerateSet>,
	RandomVariable extends IRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, ?, ?>,
	IterableType extends IElementaryEvent<Set, SimpleSet, ElementarySet, DegenerateSet, RandomVariable, ?, ?>,
	ElementType extends IDegenerateSet<Set, SimpleSet, ElementarySet, DegenerateSet>
>
extends IRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, IterableType, ElementType>
{}