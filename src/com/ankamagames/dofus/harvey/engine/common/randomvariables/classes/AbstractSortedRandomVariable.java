/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.randomvariables.classes;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
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
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractSortedRandomVariable
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	DegenerateSet extends IDegenerateSortedSet<Bound, Set, SimpleSet, ElementarySet, ?, DegenerateSet>,
	RandomVariable extends ISortedRandomVariable<Bound, Set, SimpleSet, ElementarySet, DegenerateSet, RandomVariable, ?, ?>,
	IterableType extends ISortedElementaryEvent<Bound, Set, SimpleSet, ElementarySet, DegenerateSet, RandomVariable, ?, ?>,
	ElementsType extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Probability extends IProbabilityStrategy
>
extends AbstractRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, IterableType, ElementsType, IProbabilityStrategy>
implements ISortedRandomVariable<Bound, Set, SimpleSet, ElementarySet, DegenerateSet, RandomVariable, IterableType, ElementsType>
{

	public AbstractSortedRandomVariable(final ElementsType elements, final Probability probability)
	{
		super(elements, probability);
	}

	@Override
	public boolean equals(final RandomVariable randomVariable)
	{
		return getElements().equals(randomVariable.getElements());
	}

	@Override
	public int getProbability(final Set set)
	{
		final ISortedSet<Bound, Set, SimpleSet, ElementarySet> intersect = getElements().intersect(set);
		return RandomVariableUtils.convertFromDouble(intersect.size()/getElements().size());
	}
}