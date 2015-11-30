/**
 *
 */
package com.ankamagames.dofus.harvey.engine.probabilitystrategies;

import com.ankamagames.dofus.harvey.engine.exceptions.ProbabilityOutOfBoundException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyProbabilityStrategy
	implements IProbabilityStrategy
{
	public static final ReadOnlyProbabilityStrategy ZERO_PROBABILITY = getZeroConst();

	private static ReadOnlyProbabilityStrategy getZeroConst()
	{
		try
		{
			return new ReadOnlyProbabilityStrategy(new FixedProbability(0));
		} catch (final ProbabilityOutOfBoundException e)
		{
			throw new RuntimeException(e);
		}
	}

	IProbabilityStrategy _probabilityStrategy;
	public ReadOnlyProbabilityStrategy(final IProbabilityStrategy probabilityStrategy)
	{
		_probabilityStrategy = probabilityStrategy;
	}

	@Override
	public int getProbability()
	{
		return _probabilityStrategy.getProbability();
	}
}
