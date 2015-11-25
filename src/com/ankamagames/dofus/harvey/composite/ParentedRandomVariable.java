/**
 *
 */
package com.ankamagames.dofus.harvey.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.inetrfaces.IIRandomVariableDecorator;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IHasProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.composite.IParentedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ParentedRandomVariable
<
	Data,
	ParentType extends IRandomVariable<Data>,
	ChildType extends IRandomVariable<Data>,
	ProbabilityStrategy extends IProbabilityStrategy
>
implements IParentedRandomVariable<Data, ParentType>,
IHasProbabilityStrategy<ProbabilityStrategy>, IIRandomVariableDecorator<Data, ChildType>
{
	protected ChildType _heldRandomVariable;
	protected @Nullable ParentType _parent;
	protected ProbabilityStrategy _probabilityStrategy;

	public ParentedRandomVariable(final ChildType heldRandomVariable, final @Nullable ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		_heldRandomVariable = heldRandomVariable;
		_parent = parent;
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
	public @Nullable ParentType getParent()
	{
		return _parent;
	}

	@Override
	public int getProbability()
	{
		return _probabilityStrategy.getProbability();
	}
}
