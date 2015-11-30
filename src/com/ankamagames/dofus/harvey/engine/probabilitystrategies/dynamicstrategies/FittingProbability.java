/**
 *
 */
package com.ankamagames.dofus.harvey.engine.probabilitystrategies.dynamicstrategies;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.exceptions.ProbabilityOutOfBoundException;
import com.ankamagames.dofus.harvey.engine.inetrfaces.parentedwithprobability.IParentedRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IMergeableProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IModifiableProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.IStaticProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.composite.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.parentedwithprobability.IParentedRandomVariableWithProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FittingProbability<Data, ParentType extends ICompositeRandomVariable<Data, ?, ? extends IParentedRandomVariableWithProbabilityStrategy<Data, ?, ?>>>
	implements IDynamicProbabilityStrategy, IModifiableProbabilityStrategy, IMergeableProbabilityStrategy
{
	protected @Nullable IParentedRandomVariableWithProbability<Data, ParentType> _set;
	protected int _probability;

	public FittingProbability(final IParentedRandomVariableWithProbability<Data, ParentType> set, final int probability)
	{
		_set = set;
		_probability = probability;
	}

	public FittingProbability(final IParentedRandomVariableWithProbability<Data, ParentType> set)
	{
		this(set, RandomVariableUtils.ONE);
	}

	public FittingProbability(final int probability)
	{
		_set = null;
		_probability = probability;
	}

	public FittingProbability()
	{
		this(RandomVariableUtils.ONE);
	}

	public void init(final IParentedRandomVariableWithProbability<Data, ParentType> set)
	{
		_set = set;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.probabilitystrategy.IProbabilityStrategy#getProbability()
	 */
	@Override
	public int getProbability()
	{
		final IParentedRandomVariableWithProbability<Data, ParentType> set = _set;
		if(set==null)
			return 0;
		final ParentType parent = set.getParent();
		if(parent == null)
			if(_probability <= RandomVariableUtils.ONE)
				return _probability;
			else
				return RandomVariableUtils.ONE;

		long scalingProba = 0;
		long cumuledProba = 0;
		for(final IParentedRandomVariableWithProbabilityStrategy<Data, ?, ?> bro:parent)
		{
			final IProbabilityStrategy broProbaStrat = bro.getProbabilityStrategy();
			if(broProbaStrat instanceof IStaticProbabilityStrategy)
			{
				cumuledProba += bro.getProbability();
			}
			else if(broProbaStrat instanceof FittingProbability)
				scalingProba += ((FittingProbability<?,?>)broProbaStrat).getRawProbability();
		}
		final long remainingProba = RandomVariableUtils.ONE-cumuledProba;
		if(remainingProba<=0)
			return 0;
		if(scalingProba<=remainingProba)
			return _probability;
		return (int) (_probability*remainingProba/scalingProba);
	}

	protected int getRawProbability()
	{
		return _probability;
	}

	@Override
	public void setProbability(final int probability)
	{
		_probability = probability;
	}

	@Override
	public void addProbability(final int probability) throws ProbabilityOutOfBoundException
	{
		final long newProbability = _probability + (long)probability;
		if((newProbability > Integer.MAX_VALUE)||(newProbability < 0))
			throw new ProbabilityOutOfBoundException();

		_probability = (int) newProbability;
	}

	@Override
	public boolean merge(final IProbabilityStrategy other)
	{
		if(!(other instanceof FittingProbability))
			return false;

		final int otherProbability = ((FittingProbability<?, ?>)other).getRawProbability();
		final long newProba = otherProbability+(long)_probability;
		if(newProba>Integer.MAX_VALUE)
			return false;

		setProbability((int)newProba);

		return true;
	}

	@Override
	public void removeProbability(final int probability)
			throws ProbabilityOutOfBoundException
	{
		final long newProbability = _probability - (long)probability;
		if((newProbability > Integer.MAX_VALUE)||(newProbability < 0))
			throw new ProbabilityOutOfBoundException();

		_probability = (int) newProbability;
	}
}
