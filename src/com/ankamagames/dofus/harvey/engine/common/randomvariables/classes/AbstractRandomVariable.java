/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.randomvariables.classes;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractRandomVariable
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>,
	RandomVariable extends IRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, ?, ?>,
	IterableType extends IRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, ?, ?>,
	ElementType extends ISet<Set, SimpleSet, ElementarySet>,
	Probability extends IProbabilityStrategy
>
implements IRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, IterableType, ElementType>
{
	protected ElementType _elements;
	Probability _probability;

	public AbstractRandomVariable(final ElementType elements, final Probability probability)
	{
		_elements = elements;
		_probability = probability;
	}

	@Override
	public boolean isImpossible()
	{
		return _elements.isEmpty();
	}

	@Override
	public boolean isElementaryEvent()
	{
		return _elements.isDegenerate();
	}

	@Override
	public ElementType getElements()
	{
		return _elements;
	}
}