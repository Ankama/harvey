/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite.probabilityfactories;

import com.ankamagames.dofus.harvey.engine.classes.composite.BaseRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.classes.composite.BridgedScalingProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedScalingProbabilityFactory
implements IBridgedProbabilityStrategyFactory<BaseRandomVariableWrapper<?, ?, ?, ?>, BridgedScalingProbability<BaseRandomVariableWrapper<?, ?, ?, ?>>>
{
	static protected BridgedScalingProbabilityFactory _instance = new BridgedScalingProbabilityFactory();

	static public BridgedScalingProbabilityFactory getInstance()
	{
		return _instance;
	}

	protected BridgedScalingProbabilityFactory()
	{	}

	@Override
	public BridgedScalingProbability<BaseRandomVariableWrapper<?, ?, ?, ?>> getNewProbabilityStrategy(final int probability)
	{
		return new BridgedScalingProbability<BaseRandomVariableWrapper<?, ?, ?, ?>>(probability);
	}
}