/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class RandomVariableWrapper
<
	ChildType extends IRandomVariable,
	ParentType extends AbstractCompositeRandomVariable<?>,
	ProbabilityStrategy extends IProbabilityStrategy
>
implements IRandomVariable
{
	private ChildType _element;
	private ParentType _parent;
	private ProbabilityStrategy _probabilityStrategy;

	/**
	 * @return the _element
	 */
	protected ChildType getElement()
	{
		return _element;
	}

	/**
	 * @param _element the _element to set
	 */
	protected void setElement(final ChildType element)
	{
		_element = element;
	}

	/**
	 * @return the _parent
	 */
	protected ParentType getParent()
	{
		return _parent;
	}

	/**
	 * @param _parent the _parent to set
	 */
	protected void setParent(final ParentType parent)
	{
		_parent = parent;
	}

	/**
	 * @return the _probabilityStrategy
	 */
	protected ProbabilityStrategy getProbabilityStrategy()
	{
		return _probabilityStrategy;
	}

	/**
	 * @param _probabilityStrategy the _probabilityStrategy to set
	 */
	protected void setProbabilityStrategy(final ProbabilityStrategy probabilityStrategy)
	{
		_probabilityStrategy = probabilityStrategy;
	}

	public RandomVariableWrapper(final ChildType element, final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		_element = element;
		_parent = parent;
		_probabilityStrategy = probabilityStrategy;
	}

	@Override
	public boolean isUnknown()
	{
		if(getProbabilityStrategy().getProbability()==0)
			return true;
		return getElement().isUnknown();
	}

	@Override
	public boolean isKnown()
	{
		return getElement().hasOnlyOneValue()&&(getKnownProbability()>=RandomVariableUtils.ONE);
	}

	@Override
	public boolean hasOnlyOneValue()
	{
		return getElement().hasOnlyOneValue();
	}

	@Override
	public int getKnownProbability()
	{
		return RandomVariableUtils.multiplyFixedPrecision(getElement().getKnownProbability(), getProbabilityStrategy().getProbability());
	}

	@Override
	public int getUnknownProbability()
	{
		return RandomVariableUtils.ONE - getKnownProbability();
	}
}