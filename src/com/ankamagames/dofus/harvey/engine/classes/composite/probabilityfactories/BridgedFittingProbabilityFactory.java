/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite.probabilityfactories;

import com.ankamagames.dofus.harvey.engine.classes.composite.BaseRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.classes.composite.BridgedFittingProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedFittingProbabilityFactory
implements IBridgedProbabilityStrategyFactory<BaseRandomVariableWrapper<?, ?, ?, ?>, BridgedFittingProbability<BaseRandomVariableWrapper<?, ?, ?, ?>>>
{
	static protected BridgedFittingProbabilityFactory _instance = new BridgedFittingProbabilityFactory();

	static public BridgedFittingProbabilityFactory getInstance()
	{
		return _instance;
	}

	protected BridgedFittingProbabilityFactory()
	{	}

	@Override
	public BridgedFittingProbability<BaseRandomVariableWrapper<?, ?, ?, ?>> getNewProbabilityStrategy(final int probability)
	{
		return new BridgedFittingProbability<BaseRandomVariableWrapper<?, ?, ?, ?>>(probability);
	}
}