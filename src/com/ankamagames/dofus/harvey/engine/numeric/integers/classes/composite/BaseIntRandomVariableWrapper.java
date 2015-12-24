/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.classes.composite.RandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.exceptions.MultipleValuesException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.integers.interfaces.IIntRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseIntRandomVariableWrapper
<
	ChildType extends IIntRandomVariable,
	ParentType extends AbstractCompositeIntRandomVariable<?>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends RandomVariableWrapper<ChildType, ParentType, ProbabilityStrategy>
implements IIntRandomVariable
{
	public BaseIntRandomVariableWrapper(final ChildType element, final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(element, parent, probabilityStrategy);
	}

	@Override
	protected ChildType getElement()
	{
		return super.getElement();
	}

	@Override
	protected ParentType getParent()
	{
		return super.getParent();
	}

	@Override
	protected ProbabilityStrategy getProbabilityStrategy()
	{
		return super.getProbabilityStrategy();
	}

	@Override
	public int getProbabilityOf(final int value)
	{
		final int proba = getElement().getProbabilityOf(value);
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(proba, getProbabilityStrategy().getProbability());
		return 0;
	}

	@Override
	public boolean contains(final int value)
	{
		if(getProbabilityStrategy().getProbability()==0)
			return false;
		return getElement().contains(value);
	}

	@Override
	public boolean containsOnly(final int value)
	{
		return getElement().contains(value);
	}

	@Override
	public int getOnlyValue() throws MultipleValuesException
	{
		return getElement().getOnlyValue();
	}
}