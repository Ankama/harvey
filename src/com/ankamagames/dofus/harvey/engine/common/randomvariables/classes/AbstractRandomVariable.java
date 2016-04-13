/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.randomvariables.classes;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractRandomVariable<Set extends ISet<Set>, IterableType extends IRandomVariable<Set, ?, ?>, ElementType extends ISet<Set>, Probability extends IProbabilityStrategy>
implements IRandomVariable<Set, IterableType, ElementType>
{
	protected ElementType _elements;
	Probability _probability;
	
	public AbstractRandomVariable(final ElementType elements, final Probability probability)
	{
		_elements = elements;
		_probability = probability;
	}
	
	@Override
	public ElementType getElements()
	{
		return _elements;
	}
	
	@Override
	public int getProbability()
	{
		return _probability.getProbability();
	}
	
	@Override
	public boolean isElementaryEvent()
	{
		return (!_elements.isEmpty())&&(_elements.isDegenerate());
	}
	
	@Override
	public String toString()
	{
		return "("+getElements()+", "+RandomVariableUtils.convertToFloat(getProbability())+")";
	}
}