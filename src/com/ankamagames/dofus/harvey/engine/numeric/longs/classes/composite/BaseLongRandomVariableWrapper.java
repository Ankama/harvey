/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.classes.composite.RandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.exceptions.MultipleValuesException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.longs.interfaces.ILongRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseLongRandomVariableWrapper
<
	ChildType extends ILongRandomVariable,
	ParentType extends AbstractCompositeLongRandomVariable<?>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends RandomVariableWrapper<ChildType, ParentType, ProbabilityStrategy>
implements ILongRandomVariable
{
	public BaseLongRandomVariableWrapper(final ChildType element, final ParentType parent, final ProbabilityStrategy probabilityStrategy)
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
	public int getProbabilityOf(final long value)
	{
		final int proba = getElement().getProbabilityOf(value);
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(proba, getProbabilityStrategy().getProbability());
		return 0;
	}

	@Override
	public boolean contains(final long value)
	{
		if(getProbabilityStrategy().getProbability()==0)
			return false;
		return getElement().contains(value);
	}

	@Override
	public boolean containsOnly(final long value)
	{
		return getElement().containsOnly(value);
	}

	@Override
	public long getOnlyValue() throws MultipleValuesException
	{
		return getElement().getOnlyValue();
	}
}