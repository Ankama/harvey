/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.classes.composite.RandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.exceptions.MultipleValuesException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.shorts.interfaces.IShortRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseShortRandomVariableWrapper
<
	ChildType extends IShortRandomVariable,
	ParentType extends AbstractCompositeShortRandomVariable<?>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends RandomVariableWrapper<ChildType, ParentType, ProbabilityStrategy>
implements IShortRandomVariable
{
	public BaseShortRandomVariableWrapper(final ChildType element, final ParentType parent, final ProbabilityStrategy probabilityStrategy)
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
	public int getProbabilityOf(final short value)
	{
		final int proba = getElement().getProbabilityOf(value);
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(proba, getProbabilityStrategy().getProbability());
		return 0;
	}

	@Override
	public boolean contains(final short value)
	{
		if(getProbabilityStrategy().getProbability()==0)
			return false;
		return getElement().contains(value);
	}

	@Override
	public boolean containsOnly(final short value)
	{
		return getElement().containsOnly(value);
	}

	@Override
	public short getOnlyValue() throws MultipleValuesException
	{
		return getElement().getOnlyValue();
	}
}