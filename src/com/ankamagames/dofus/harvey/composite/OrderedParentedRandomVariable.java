/**
 *
 */
package com.ankamagames.dofus.harvey.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.composite.IOrderedParentedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class OrderedParentedRandomVariable
<
	Data,
	ParentType extends IRandomVariable<Data>,
	ChildType extends IOrderedRandomVariable<Data>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends ParentedRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy>
implements IOrderedParentedRandomVariable<Data, ParentType>
{
	public OrderedParentedRandomVariable(final ChildType heldRandomVariable,
			final @Nullable ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(heldRandomVariable, parent, probabilityStrategy);
	}

	@Override
	public int getProbabilityForLowerThan(@Nullable final Data value)
	{
		return RandomVariableUtils.multiplyFixedPrecision(getProbability(), _heldRandomVariable.getProbabilityForLowerThan(value));
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(@Nullable final Data value)
	{
		return RandomVariableUtils.multiplyFixedPrecision(getProbability(), _heldRandomVariable.getProbabilityForLowerThanOrEqualTo(value));
	}

	@Override
	public int getProbabilityForGreaterThan(@Nullable final Data value)
	{
		return RandomVariableUtils.multiplyFixedPrecision(getProbability(), _heldRandomVariable.getProbabilityForGreaterThan(value));
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(@Nullable final Data value)
	{
		return RandomVariableUtils.multiplyFixedPrecision(getProbability(), _heldRandomVariable.getProbabilityForGreaterThanOrEqualTo(value));
	}

	@Override
	@Nullable
	public IOrderedRandomVariable<Data> getLowerThan(@Nullable final Data value)
	{
		final IOrderedRandomVariable<Data> r = _heldRandomVariable.getLowerThan(value);
		if(r!=null)
			return new OrderedParentedRandomVariable<Data, ParentType, IOrderedRandomVariable<Data>, ProbabilityStrategy>(r, _parent, _probabilityStrategy);
		return null;
	}

	@Override
	@Nullable
	public IOrderedRandomVariable<Data> getLowerThanOrEqualTo(@Nullable final Data value)
	{
		final IOrderedRandomVariable<Data> r = _heldRandomVariable.getLowerThanOrEqualTo(value);
		if(r!=null)
			return new OrderedParentedRandomVariable<Data, ParentType, IOrderedRandomVariable<Data>, ProbabilityStrategy>(r, _parent, _probabilityStrategy);
		return null;
	}

	@Override
	@Nullable
	public IOrderedRandomVariable<Data> getGreaterThan(@Nullable final Data value)
	{
		final IOrderedRandomVariable<Data> r = _heldRandomVariable.getGreaterThan(value);
		if(r!=null)
			return new OrderedParentedRandomVariable<Data, ParentType, IOrderedRandomVariable<Data>, ProbabilityStrategy>(r, _parent, _probabilityStrategy);
		return null;
	}

	@Override
	@Nullable
	public IOrderedRandomVariable<Data> getGreaterThanOrEqualTo(@Nullable final Data value)
	{
		final IOrderedRandomVariable<Data> r = _heldRandomVariable.getGreaterThanOrEqualTo(value);
		if(r!=null)
			return new OrderedParentedRandomVariable<Data, ParentType, IOrderedRandomVariable<Data>, ProbabilityStrategy>(r, _parent, _probabilityStrategy);
		return null;
	}
}
