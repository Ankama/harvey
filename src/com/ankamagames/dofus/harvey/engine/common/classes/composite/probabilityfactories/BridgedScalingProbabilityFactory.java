/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories;


import com.ankamagames.dofus.harvey.engine.common.classes.composite.BridgedScalingProbability;
import com.ankamagames.dofus.harvey.engine.common.classes.composite.RandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedScalingProbabilityFactory
implements IBridgedProbabilityStrategyFactory<RandomVariableWrapper<?, ?, ?>, BridgedScalingProbability<RandomVariableWrapper<?, ?, ?>>>
{
	static protected BridgedScalingProbabilityFactory _instance = new BridgedScalingProbabilityFactory();

	static public BridgedScalingProbabilityFactory getInstance()
	{
		return _instance;
	}

	protected BridgedScalingProbabilityFactory()
	{	}

	@Override
	public BridgedScalingProbability<RandomVariableWrapper<?, ?, ?>> getNewProbabilityStrategy(final int probability)
	{
		return new BridgedScalingProbability<RandomVariableWrapper<?, ?, ?>>(probability);
	}
}