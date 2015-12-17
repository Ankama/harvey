/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.classes.composite.BasicCollectionWrapper;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.generic.interfaces.IGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseGenericRandomVariableWrapper
<
	Data,
	ChildType extends IGenericRandomVariable<Data>,
	ParentType extends AbstractCompositeGenericRandomVariable<Data, ?>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends BasicCollectionWrapper<ChildType, ParentType, ProbabilityStrategy>
implements IGenericRandomVariable<Data>
{
	public BaseGenericRandomVariableWrapper(final ChildType element, final ParentType parent, final ProbabilityStrategy probabilityStrategy)
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
	public int getProbabilityOf(@Nullable final Data value)
	{
		final int proba = getElement().getProbabilityOf(value);
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(proba, getProbabilityStrategy().getProbability());
		return 0;
	}

	@Override
	public boolean contains(@Nullable final Data value)
	{
		if(getProbabilityStrategy().getProbability()==0)
			return false;
		return getElement().contains(value);
	}
}