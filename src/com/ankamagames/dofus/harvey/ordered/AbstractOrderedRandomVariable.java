/**
 *
 */
package com.ankamagames.dofus.harvey.ordered;

import com.ankamagames.dofus.harvey.engine.base.classes.BaseRandomVariable;
import com.ankamagames.dofus.harvey.engine.behaviours.IOrderedRandomVariableBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractOrderedRandomVariable<Data, ParentType extends ICompositeRandomVariable<Data, ?, ?>, Behaviour extends IOrderedRandomVariableBehaviour<Data, ParentType>, ProbabilityStrategy extends IProbabilityStrategy>
	extends BaseRandomVariable<Data, ParentType, Behaviour, ProbabilityStrategy>
	implements IOrderedRandomVariable<Data, ParentType>
{
	/**
	 * @param probabilityStrategy
	 * @param parent
	 * @throws OverOneProbabilityException
	 */
	public AbstractOrderedRandomVariable(@Nullable final ParentType parent, final Behaviour randomVariableBehaviour, final ProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(parent, randomVariableBehaviour, probabilityStrategy);
	}


	public AbstractOrderedRandomVariable(final Behaviour randomVariableBehaviour, final ProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(randomVariableBehaviour, probabilityStrategy);
	}

	@Override
	public int compareTo(@Nullable final Data o)
	{
		return _behaviour.compareTo(o);
	}

	@Override
	public int getProbabilityForLowerThan(final Data value,
			@Nullable final IRandomVariable<Data, ?> context)
	{
		return _behaviour.getProbabilityForLowerThan(value, context);
	}

	@Override
	public int getProbabilityForLowerThan(final Data value)
	{
		return getProbabilityForLowerThan(value, getParent());
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final Data value,
			@Nullable final IRandomVariable<Data, ?> context)
	{
		return _behaviour.getProbabilityForLowerThanOrEqualTo(value, context);
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final Data value)
	{
		return getProbabilityForLowerThanOrEqualTo(value, getParent());
	}

	@Override
	public int getProbabilityForGreaterThan(final Data value,
			@Nullable final IRandomVariable<Data, ?> context)
	{
		return _behaviour.getProbabilityForGreaterThan(value, context);
	}

	@Override
	public int getProbabilityForGreaterThan(final Data value)
	{
		return getProbabilityForGreaterThan(value, getParent());
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final Data value,
			@Nullable final IRandomVariable<Data, ?> context)
	{
		return _behaviour.getProbabilityForGreaterThanOrEqualTo(value, context);
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final Data value)
	{
		return getProbabilityForGreaterThanOrEqualTo(value, getParent());
	}

	@Override
	public IOrderedRandomVariable<Data, ParentType> getLowerThan(final Data value)
	{
		return _behaviour.getLowerThan(value);
	}


	@Override
	public IOrderedRandomVariable<Data, ParentType> getLowerThanOrEqualTo(
			final Data value)
	{
		return _behaviour.getLowerThanOrEqualTo(value);
	}


	@Override
	public IOrderedRandomVariable<Data, ParentType> getGreaterThan(final Data value)
	{
		return _behaviour.getGreaterThan(value);
	}


	@Override
	public IOrderedRandomVariable<Data, ParentType> getGreaterThanOrEqualTo(
			final Data value)
	{
		return _behaviour.getGreaterThanOrEqualTo(value);
	}
}
