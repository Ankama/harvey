/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.RandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public enum ProbabilityStrategies
implements IBridgedProbabilityStrategyFactory<RandomVariableWrapper<?, ?, ?>, IBridgedEditableProbabilityStrategy<? super RandomVariableWrapper<?, ?, ?>>>
{
	FIXED(BridgedFixedProbabilityFactory.getInstance()),
	SCALING(BridgedScalingProbabilityFactory.getInstance()),
	FITTING(BridgedFittingProbabilityFactory.getInstance());

	protected IBridgedProbabilityStrategyFactory<RandomVariableWrapper<?, ?, ?>, ? extends IBridgedEditableProbabilityStrategy<? super RandomVariableWrapper<?, ?, ?>>> _factory;

	private ProbabilityStrategies(final IBridgedProbabilityStrategyFactory<RandomVariableWrapper<?, ?, ?>, ? extends IBridgedEditableProbabilityStrategy<? super RandomVariableWrapper<?, ?, ?>>> factorty)
	{
		_factory = factorty;
	}

	@Override
	public IBridgedEditableProbabilityStrategy<? super RandomVariableWrapper<?, ?, ?>> getNewProbabilityStrategy(
			final int probability)
	{
		return _factory.getNewProbabilityStrategy(probability);
	}
}