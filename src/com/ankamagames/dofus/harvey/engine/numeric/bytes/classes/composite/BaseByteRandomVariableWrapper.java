/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.classes.composite.BasicCollectionWrapper;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseByteRandomVariableWrapper
<
	ChildType extends IByteRandomVariable,
	ParentType extends AbstractCompositeByteRandomVariable<?>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends BasicCollectionWrapper<ChildType, ParentType, ProbabilityStrategy>
implements IByteRandomVariable
{
	public BaseByteRandomVariableWrapper(final ChildType element, final ParentType parent, final ProbabilityStrategy probabilityStrategy)
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
	public int getProbabilityOf(final byte value)
	{
		final int proba = getElement().getProbabilityOf(value);
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(proba, getProbabilityStrategy().getProbability());
		return 0;
	}

	@Override
	public boolean contains(final byte value)
	{
		if(getProbabilityStrategy().getProbability()==0)
			return false;
		return getElement().contains(value);
	}
}