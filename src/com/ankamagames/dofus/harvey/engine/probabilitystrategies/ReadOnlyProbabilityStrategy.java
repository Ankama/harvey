/**
 *
 */
package com.ankamagames.dofus.harvey.engine.probabilitystrategies;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.OneProbability;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.ZeroProbability;

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
	public static final ReadOnlyProbabilityStrategy ONE_PROBABILITY = getOneConst();

	private static ReadOnlyProbabilityStrategy getZeroConst()
	{
		return new ReadOnlyProbabilityStrategy(ZeroProbability.getInstance());
	}

	private static ReadOnlyProbabilityStrategy getOneConst()
	{
		return new ReadOnlyProbabilityStrategy(OneProbability.getInstance());
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
