/**
 *
 */
package com.ankamagames.dofus.harvey.withprobability;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.withprobability.IOrderedRandomVariableWithProbability;
import com.ankamagames.dofus.harvey.ordered.OrderedDecoratorBridge;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class OrderedRandomVariableWithProbability
<
	Data,
	ChildType extends IOrderedRandomVariable<Data>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends RandomVariableWithProbability<Data, ChildType, ProbabilityStrategy>
implements IOrderedRandomVariableWithProbability<Data>
{
	protected OrderedDecoratorBridge<Data, ? extends OrderedRandomVariableWithProbability<Data, ChildType, ProbabilityStrategy>> _orderedBridge;

	public OrderedRandomVariableWithProbability(final ChildType heldRandomVariable,
			final ProbabilityStrategy probabilityStrategy)
	{
		super(heldRandomVariable, probabilityStrategy);
		_orderedBridge = new OrderedDecoratorBridge<Data, OrderedRandomVariableWithProbability<Data,ChildType,ProbabilityStrategy>>(this);
	}

	@Override
	public int getProbabilityForLowerThan(@Nullable final Data value)
	{
		return _orderedBridge.getProbabilityForLowerThan(value);
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(@Nullable final Data value)
	{
		return _orderedBridge.getProbabilityForLowerThanOrEqualTo(value);
	}

	@Override
	public int getProbabilityForGreaterThan(@Nullable final Data value)
	{
		return _orderedBridge.getProbabilityForGreaterThan(value);
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(@Nullable final Data value)
	{
		return _orderedBridge.getProbabilityForGreaterThanOrEqualTo(value);
	}

	@Override
	@Nullable
	public IOrderedRandomVariableWithProbability<Data> getLowerThan(@Nullable final Data value)
	{
		final IOrderedRandomVariable<Data> r = _heldRandomVariable.getLowerThan(value);
		if(r!=null)
			return new OrderedRandomVariableWithProbability<Data, IOrderedRandomVariable<Data>, ProbabilityStrategy>(r, _probabilityStrategy);
		return null;
	}

	@Override
	@Nullable
	public IOrderedRandomVariableWithProbability<Data> getLowerThanOrEqualTo(@Nullable final Data value)
	{
		final IOrderedRandomVariable<Data> r = _heldRandomVariable.getLowerThanOrEqualTo(value);
		if(r!=null)
			return new OrderedRandomVariableWithProbability<Data, IOrderedRandomVariable<Data>, ProbabilityStrategy>(r, _probabilityStrategy);
		return null;
	}

	@Override
	@Nullable
	public IOrderedRandomVariableWithProbability<Data> getGreaterThan(@Nullable final Data value)
	{
		final IOrderedRandomVariable<Data> r = _heldRandomVariable.getGreaterThan(value);
		if(r!=null)
			return new OrderedRandomVariableWithProbability<Data, IOrderedRandomVariable<Data>, ProbabilityStrategy>(r, _probabilityStrategy);
		return null;
	}

	@Override
	@Nullable
	public IOrderedRandomVariableWithProbability<Data> getGreaterThanOrEqualTo(@Nullable final Data value)
	{
		final IOrderedRandomVariable<Data> r = _heldRandomVariable.getGreaterThanOrEqualTo(value);
		if(r!=null)
			return new OrderedRandomVariableWithProbability<Data, IOrderedRandomVariable<Data>, ProbabilityStrategy>(r, _probabilityStrategy);
		return null;
	}
}
