/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.parentedwithprobability;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.parentedwithprobability.IOrderedParentedRandomVariableWithProbability;
import com.ankamagames.dofus.harvey.interfaces.parenting.IParentingRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.withprobability.IOrderedRandomVariableWithProbability;
import com.ankamagames.dofus.harvey.ordered.OrderedDecoratorBridge;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class OrderedParentedRandomVariableWithProbability
<
	Data,
	ParentType extends IParentingRandomVariable<Data, ?>,
	ChildType extends IOrderedRandomVariable<Data>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends
ParentedRandomVariableWithProbability<Data, ParentType, ChildType, ProbabilityStrategy>
implements
IOrderedParentedRandomVariableWithProbability<Data, ParentType>
{
	protected OrderedDecoratorBridge<Data, ? extends OrderedParentedRandomVariableWithProbability<Data, ParentType, ChildType, ProbabilityStrategy>> _orderedBridge;

	public OrderedParentedRandomVariableWithProbability(final @Nullable ParentType parent,
			final ChildType heldRandomVariable,
			final ProbabilityStrategy probabilityStrategy)
	{
		super(parent, heldRandomVariable, probabilityStrategy);
		_orderedBridge = new OrderedDecoratorBridge<Data, OrderedParentedRandomVariableWithProbability<Data, ParentType, ChildType, ProbabilityStrategy>>(this);
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
	public @Nullable IOrderedRandomVariableWithProbability<Data> getLowerThan(@Nullable final Data value)
	{
		final IOrderedRandomVariable<Data> r = _heldRandomVariable.getLowerThan(value);
		if(r!=null)
			return new OrderedParentedRandomVariableWithProbability<Data, ParentType, IOrderedRandomVariable<Data>, ProbabilityStrategy>(_parent, r, _probabilityStrategy);
		return null;
	}

	@Override
	public @Nullable IOrderedRandomVariableWithProbability<Data> getLowerThanOrEqualTo(@Nullable final Data value)
	{
		final IOrderedRandomVariable<Data> r = _heldRandomVariable.getLowerThanOrEqualTo(value);
		if(r!=null)
			return new OrderedParentedRandomVariableWithProbability<Data, ParentType, IOrderedRandomVariable<Data>, ProbabilityStrategy>(_parent, r, _probabilityStrategy);
		return null;
	}

	@Override
	public @Nullable IOrderedRandomVariableWithProbability<Data> getGreaterThan(@Nullable final Data value)
	{
		final IOrderedRandomVariable<Data> r = _heldRandomVariable.getGreaterThan(value);
		if(r!=null)
			return new OrderedParentedRandomVariableWithProbability<Data, ParentType, IOrderedRandomVariable<Data>, ProbabilityStrategy>(_parent, r, _probabilityStrategy);
		return null;
	}

	@Override
	public @Nullable IOrderedRandomVariableWithProbability<Data> getGreaterThanOrEqualTo(@Nullable final Data value)
	{
		final IOrderedRandomVariable<Data> r = _heldRandomVariable.getGreaterThanOrEqualTo(value);
		if(r!=null)
			return new OrderedParentedRandomVariableWithProbability<Data, ParentType, IOrderedRandomVariable<Data>, ProbabilityStrategy>(_parent, r, _probabilityStrategy);
		return null;
	}
}
