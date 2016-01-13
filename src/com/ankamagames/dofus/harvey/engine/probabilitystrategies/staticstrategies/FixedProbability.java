/**
 *
 */
package com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.exceptions.ProbabilityOutOfBoundException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IMergeableProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FixedProbability
implements IStaticProbabilityStrategy, IEditableProbabilityStrategy, IMergeableProbabilityStrategy
{
	protected int _probability;

	/**
	 *
	 * The probability is given as an int representing a fixed point decimal (to avoid precision
	 * loss). To get it as a float, use {@link RandomVariableUtils.convertToFloat}
	 *
	 * @param probability
	 * @throws ProbabilityOutOfBoundException
	 */
	public FixedProbability(final int probability)
	{
		_probability = probability;
	}

	public FixedProbability()
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
	public void setProbability(final int probability)
	{
		_probability = probability;
	}

	protected void _addProbabilityNoCheck(final int probability)
	{
		_setProbabilityNoCheck(_probability + probability);
	}

	@Override
	public void addProbability(final int probability)
	{
		final long newProbability = _probability + (long)probability;
		if((newProbability > Integer.MAX_VALUE)||(newProbability < Integer.MIN_VALUE))
			throw new ProbabilityOutOfBoundException();

		_probability = (int) newProbability;
	}

	@Override
	public void removeProbability(final int probability)
	{
		addProbability(-probability);
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
