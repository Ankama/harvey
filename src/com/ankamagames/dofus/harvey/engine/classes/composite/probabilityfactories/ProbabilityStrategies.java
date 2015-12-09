/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite.probabilityfactories;

import com.ankamagames.dofus.harvey.engine.classes.composite.BaseRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.classes.composite.IBridgedEditableProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public enum ProbabilityStrategies
implements IBridgedProbabilityStrategyFactory<BaseRandomVariableWrapper<?, ?, ?, ?>, IBridgedEditableProbabilityStrategy<? super BaseRandomVariableWrapper<?, ?, ?, ?>>>
{
	FIXED(BridgedFixedProbabilityFactory.getInstance()),
	SCALING(BridgedScalingProbabilityFactory.getInstance()),
	FITTING(BridgedFittingProbabilityFactory.getInstance());

	protected IBridgedProbabilityStrategyFactory<BaseRandomVariableWrapper<?, ?, ?, ?>, ? extends IBridgedEditableProbabilityStrategy<? super BaseRandomVariableWrapper<?, ?, ?, ?>>> _factory;

	private ProbabilityStrategies(final IBridgedProbabilityStrategyFactory<BaseRandomVariableWrapper<?, ?, ?, ?>, ? extends IBridgedEditableProbabilityStrategy<? super BaseRandomVariableWrapper<?, ?, ?, ?>>> factorty)
	{
		_factory = factorty;
	}

	@Override
	public IBridgedEditableProbabilityStrategy<? super BaseRandomVariableWrapper<?, ?, ?, ?>> getNewProbabilityStrategy(
			final int probability)
	{
		return _factory.getNewProbabilityStrategy(probability);
	}
}