/**
 *
 */
package com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IMergeableProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IModifiableProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FixedProbability
	implements IStaticProbabilityStrategy, IModifiableProbabilityStrategy, IMergeableProbabilityStrategy
{
	protected int _probability;

	/**
	 *
	 * The probability is given as an int representing a fixed point decimal (to avoid precision
	 * loss). To get it as a float, use {@link RandomVariableUtils.convertToFloat}
	 *
	 * @param probability
	 * @throws OverOneProbabilityException
	 */
	public FixedProbability(final int probability) throws OverOneProbabilityException
	{
		if(probability>RandomVariableUtils.ONE)
			throw new OverOneProbabilityException();
		_probability = probability;
	}

	public FixedProbability() throws OverOneProbabilityException
	{
		this(RandomVariableUtils.ONE);
	}

	/* (non-Javadoc)
	 *
	 * The probability is given as an int representing a fixed point decimal (to avoid precision
	 * loss). To get it as a float, use {@link RandomVariableUtils.convertToFloat}
	 *
	 * @see com.ankamagames.dofus.harvey.probabilityelement.IProbabilityElementStrategy#getProbability()
	 */
	@Override
	public int getProbability()
	{
		return _probability;
	}

	protected void _setProbabilityNoCheck(final int probability)
	{
		_probability = probability;
	}

	@Override
	public void setProbability(final int probability) throws OverOneProbabilityException
	{
		if(probability>RandomVariableUtils.ONE)
			throw new OverOneProbabilityException();
		_probability = probability;
	}

	protected void _addProbabilityNoCheck(final int probability)
	{
		_setProbabilityNoCheck(_probability + probability);
	}

	@Override
	public void addProbability(final int probability) throws OverOneProbabilityException
	{
		final long newProbability = _probability + (long)probability;
		if((newProbability > RandomVariableUtils.ONE)||(newProbability < 0))
			throw new OverOneProbabilityException();

		_probability = (int) newProbability;
	}

	@Override
	public boolean merge(final IProbabilityStrategy other)
	{
		if(!(other instanceof FixedProbability))
			return false;

		final int otherProbability = other.getProbability();
		final int newProba = otherProbability+_probability;
		if(newProba>RandomVariableUtils.ONE)
			return false;

		_setProbabilityNoCheck(newProba);

		return true;
	}
}
