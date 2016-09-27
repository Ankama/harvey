/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.randomvariables.classes;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.ISortedElementaryEvent;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.ISortedRandomVariable;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public abstract class AbstractSortedElementaryEvent
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	DegenerateSet extends IDegenerateSortedSet<Bound, Set, SimpleSet, ElementarySet, ?, DegenerateSet>,
	RandomVariable extends ISortedRandomVariable<Bound, Set, SimpleSet, ElementarySet, DegenerateSet, RandomVariable, ?, ?>,
	IterableType extends ISortedElementaryEvent<Bound, Set, SimpleSet, ElementarySet, DegenerateSet, RandomVariable, ?, ?>,
	ElementType extends IDegenerateSortedSet<Bound, Set, SimpleSet, ElementarySet, ?, DegenerateSet>,
	Probability extends IProbabilityStrategy
>
extends	AbstractElementaryEvent<Set, SimpleSet, ElementarySet, DegenerateSet, RandomVariable, IterableType, ElementType, Probability>
implements ISortedElementaryEvent<Bound, Set, SimpleSet, ElementarySet, DegenerateSet, RandomVariable, IterableType, ElementType>
{
	public AbstractSortedElementaryEvent(final ElementType elements, final Probability probability)
	{
		super(elements, probability);
	}
}
