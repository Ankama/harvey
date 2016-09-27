/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public interface ISortedElementaryEvent
<
Bound extends IBound<Bound>,
Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
DegenerateSet extends IDegenerateSortedSet<Bound, Set, SimpleSet, ElementarySet, ?, DegenerateSet>,
RandomVariable extends ISortedRandomVariable<Bound, Set, SimpleSet, ElementarySet, DegenerateSet, RandomVariable, ?, ?>,
IterableType extends ISortedElementaryEvent<Bound, Set, SimpleSet, ElementarySet, DegenerateSet, RandomVariable, ?, ?>,
ElementsType extends IDegenerateSortedSet<Bound, Set, SimpleSet, ElementarySet, ?, DegenerateSet>
>
extends	IElementaryEvent<Set, SimpleSet, ElementarySet, DegenerateSet, RandomVariable, IterableType, ElementsType>
{}
