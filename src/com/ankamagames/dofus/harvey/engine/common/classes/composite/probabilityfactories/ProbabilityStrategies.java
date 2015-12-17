/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.BasicCollectionWrapper;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public enum ProbabilityStrategies
implements IBridgedProbabilityStrategyFactory<BasicCollectionWrapper<?, ?, ?>, IBridgedEditableProbabilityStrategy<? super BasicCollectionWrapper<?, ?, ?>>>
{
	FIXED(BridgedFixedProbabilityFactory.getInstance()),
	SCALING(BridgedScalingProbabilityFactory.getInstance()),
	FITTING(BridgedFittingProbabilityFactory.getInstance());

	protected IBridgedProbabilityStrategyFactory<BasicCollectionWrapper<?, ?, ?>, ? extends IBridgedEditableProbabilityStrategy<? super BasicCollectionWrapper<?, ?, ?>>> _factory;

	private ProbabilityStrategies(final IBridgedProbabilityStrategyFactory<BasicCollectionWrapper<?, ?, ?>, ? extends IBridgedEditableProbabilityStrategy<? super BasicCollectionWrapper<?, ?, ?>>> factorty)
	{
		_factory = factorty;
	}

	@Override
	public IBridgedEditableProbabilityStrategy<? super BasicCollectionWrapper<?, ?, ?>> getNewProbabilityStrategy(
			final int probability)
	{
		return _factory.getNewProbabilityStrategy(probability);
	}
}