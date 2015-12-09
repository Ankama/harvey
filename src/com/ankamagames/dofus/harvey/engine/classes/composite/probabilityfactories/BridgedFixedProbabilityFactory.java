/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite.probabilityfactories;

import com.ankamagames.dofus.harvey.engine.classes.composite.BaseRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.classes.composite.BridgedFixedProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedFixedProbabilityFactory
implements IBridgedProbabilityStrategyFactory<BaseRandomVariableWrapper<?, ?, ?, ?>, BridgedFixedProbability<BaseRandomVariableWrapper<?, ?, ?, ?>>>
{
	static protected BridgedFixedProbabilityFactory _instance = new BridgedFixedProbabilityFactory();

	static public BridgedFixedProbabilityFactory getInstance()
	{
		return _instance;
	}

	protected BridgedFixedProbabilityFactory()
	{	}

	@Override
	public BridgedFixedProbability<BaseRandomVariableWrapper<?, ?, ?, ?>> getNewProbabilityStrategy(final int probability)
	{
		return new BridgedFixedProbability<BaseRandomVariableWrapper<?,?,?,?>>(probability);
	}
}