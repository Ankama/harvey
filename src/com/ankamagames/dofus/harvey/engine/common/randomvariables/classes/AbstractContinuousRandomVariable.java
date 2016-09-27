/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.randomvariables.classes;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IContinuousElementaryEvent;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IContinuousRandomVariable;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementaryContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleContinuousSet;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public abstract class AbstractContinuousRandomVariable
<
	Bound extends IContinuousBound<Bound>,
	Set extends IContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	DegenerateSet extends IDegenerateContinuousSet<Bound, Set, SimpleSet, ElementarySet, ?, DegenerateSet>,
	RandomVariable extends IContinuousRandomVariable<Bound, Set, SimpleSet, ElementarySet, DegenerateSet, RandomVariable, ?, ?>,
	IterableType extends IContinuousElementaryEvent<Bound, Set, SimpleSet, ElementarySet, DegenerateSet, RandomVariable, ?, ?>,
	ElementsType extends IContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	Probability extends IProbabilityStrategy
>
extends AbstractSortedRandomVariable<Bound, Set, SimpleSet, ElementarySet, DegenerateSet, RandomVariable, IterableType, ElementsType, Probability>
implements IContinuousRandomVariable<Bound, Set, SimpleSet, ElementarySet, DegenerateSet, RandomVariable, IterableType, ElementsType> {

	public AbstractContinuousRandomVariable(final ElementsType elements, final Probability probability)
	{
		super(elements, probability);
	}
}
