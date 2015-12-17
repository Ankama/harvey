/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories;


import com.ankamagames.dofus.harvey.engine.common.classes.composite.BasicCollectionWrapper;
import com.ankamagames.dofus.harvey.engine.common.classes.composite.BridgedScalingProbability;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedScalingProbabilityFactory
implements IBridgedProbabilityStrategyFactory<BasicCollectionWrapper<?, ?, ?>, BridgedScalingProbability<BasicCollectionWrapper<?, ?, ?>>>
{
	static protected BridgedScalingProbabilityFactory _instance = new BridgedScalingProbabilityFactory();

	static public BridgedScalingProbabilityFactory getInstance()
	{
		return _instance;
	}

	protected BridgedScalingProbabilityFactory()
	{	}

	@Override
	public BridgedScalingProbability<BasicCollectionWrapper<?, ?, ?>> getNewProbabilityStrategy(final int probability)
	{
		return new BridgedScalingProbability<BasicCollectionWrapper<?, ?, ?>>(probability);
	}
}