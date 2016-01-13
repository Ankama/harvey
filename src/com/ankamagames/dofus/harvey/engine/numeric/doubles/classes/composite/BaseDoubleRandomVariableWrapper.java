/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.classes.composite.RandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.exceptions.MultipleValuesException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.doubles.interfaces.IDoubleRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseDoubleRandomVariableWrapper
<
	ChildType extends IDoubleRandomVariable,
	ParentType extends AbstractCompositeDoubleRandomVariable<?>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends RandomVariableWrapper<ChildType, ParentType, ProbabilityStrategy>
implements IDoubleRandomVariable
{
	public BaseDoubleRandomVariableWrapper(final ChildType element, final ParentType parent, final ProbabilityStrategy probabilityStrategy)
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
	public int getProbabilityOf(final double value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(proba, getElement().getProbabilityOf(value));
		return 0;
	}

	@Override
	public boolean contains(final double value)
	{
		if(getProbabilityStrategy().getProbability()==0)
			return false;
		return getElement().contains(value);
	}

	@Override
	public boolean containsOnly(final double value)
	{
		if(getProbabilityStrategy().getProbability()==0)
			return false;
		return getElement().containsOnly(value);
	}

	@Override
	public double getOnlyValue() throws MultipleValuesException
	{
		return getElement().getOnlyValue();
	}
}