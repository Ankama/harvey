/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.randomvariables.classes;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public abstract class AbstractCompositeRandomVariable
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>,
	RandomVariable extends ICompositeRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, IterableType, ElementsType>,
	IterableType extends IRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, IterableType, ElementsType>,
	ElementsType extends ISet<Set, SimpleSet, ElementarySet>,
	Probability extends IProbabilityStrategy
>
extends AbstractRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, IterableType, ElementsType, Probability>
implements ICompositeRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, IterableType, ElementsType>
{

	public AbstractCompositeRandomVariable(final ElementsType elements, final Probability probability) {
		super(elements, probability);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(final RandomVariable randomVariable)
	{
		return randomVariable.getElements().equals(getElements());
	}

	@Override
	public int getProbability(final Set set)
	{
		return 1;
	}


}
