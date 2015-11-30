/**
 *
 */
package com.ankamagames.dofus.harvey.withprobability;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.inetrfaces.IIRandomVariableDecorator;
import com.ankamagames.dofus.harvey.engine.inetrfaces.withprobability.IRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class RandomVariableWithProbability
<
	Data,
	ChildType extends IRandomVariable<Data>,
	ProbabilityStrategy extends IProbabilityStrategy
>
implements IRandomVariableWithProbabilityStrategy<Data, ProbabilityStrategy>,
IIRandomVariableDecorator<Data, ChildType>
{
	protected ChildType _heldRandomVariable;
	protected ProbabilityStrategy _probabilityStrategy;

	public RandomVariableWithProbability(final ChildType heldRandomVariable, final ProbabilityStrategy probabilityStrategy)
	{
		_heldRandomVariable = heldRandomVariable;
		_probabilityStrategy = probabilityStrategy;
	}

	@Override
	public ChildType getDecoratedRandomVariable()
	{
		return _heldRandomVariable;
	}

	@Override
	public ProbabilityStrategy getProbabilityStrategy()
	{
		return _probabilityStrategy;
	}

	@Override
	public int getProbabilityOf(final @Nullable Data value)
	{
		return RandomVariableUtils.multiplyFixedPrecision(getProbability(), _heldRandomVariable.getProbabilityOf(value));
	}

	@Override
	public boolean contains(final @Nullable Data value)
	{
		return _heldRandomVariable.contains(value);
	}

	@Override
	public boolean isEmpty()
	{
		return _heldRandomVariable.isEmpty();
	}

	@Override
	public int getProbability()
	{
		return _probabilityStrategy.getProbability();
	}
}