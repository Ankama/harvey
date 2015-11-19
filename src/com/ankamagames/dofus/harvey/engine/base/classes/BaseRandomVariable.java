package com.ankamagames.dofus.harvey.engine.base.classes;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.base.interfaces.IRandomVariableWithBehaviourAndProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.behaviours.IRandomVariableBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.exceptions.WrongContextException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class BaseRandomVariable<Data, ParentType extends ICompositeRandomVariable<Data, ?, ?>, Behaviour extends IRandomVariableBehaviour<Data>, ProbabilityStrategy extends IProbabilityStrategy>
implements IRandomVariableWithBehaviourAndProbabilityStrategy<Data, ParentType, Behaviour, ProbabilityStrategy>
{

	@Nullable
	protected ParentType _parent;
	protected Behaviour _behaviour;
	protected ProbabilityStrategy _probabilityStrategy;

	/**
	 * @param probabilityStrategy
	 * @param parent
	 * @throws OverOneProbabilityException
	 */
	public BaseRandomVariable(@Nullable final ParentType parent, final Behaviour randomVariableBehaviour, final ProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		_parent = parent;
		_behaviour = randomVariableBehaviour;
		_probabilityStrategy = probabilityStrategy;
	}

	public BaseRandomVariable(final Behaviour randomVariableBehaviour, final ProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		this((ParentType)null, randomVariableBehaviour, probabilityStrategy);
	}


	@Override
	public int getProbability(@Nullable final IRandomVariable<Data, ?> context)
	{
		if((context!=null)&&(context.equals(this)))
			return RandomVariableUtils.ONE;

		final @Nullable ParentType parent = _parent;
		if(parent != null)
		{
			return RandomVariableUtils.multiplyFixedPrecision(parent.getProbability(context),_probabilityStrategy.getProbability());
		}

		if(context == null)
		{
			return _probabilityStrategy.getProbability();
		}
		else
			throw new WrongContextException();
	}

	@Override
	public int getProbability()
	{
		return getProbability((IEditableRandomVariable<Data, ?>)null);
	}

	@Override
	@Nullable
	public ParentType getParent()
	{
		return _parent;
	}

	@Override
	public ProbabilityStrategy getProbabilityStrategy()
	{
		return _probabilityStrategy;
	}

	@Override
	public boolean isEmpty()
	{
		return _behaviour.isEmpty();
	}

	@Override
	public boolean contains(@Nullable final Data value)
	{
		return _behaviour.contains(value);
	}

	@Override
	public int getProbabilityOf(@Nullable final Data value)
	{
		return _behaviour.getProbabilityOf(value, getParent());
	}

	@Override
	public int getProbabilityOf(@Nullable final Data value, @Nullable final IRandomVariable<Data, ?> context)
	{
		return _behaviour.getProbabilityOf(value, context);
	}

	@Override
	public Behaviour getBehaviour()
	{
		return _behaviour;
	}
}