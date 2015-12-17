/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories;


import com.ankamagames.dofus.harvey.engine.common.classes.composite.BasicCollectionWrapper;
import com.ankamagames.dofus.harvey.engine.common.classes.composite.BridgedFittingProbability;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedFittingProbabilityFactory
implements IBridgedProbabilityStrategyFactory<BasicCollectionWrapper<?, ?, ?>, BridgedFittingProbability<BasicCollectionWrapper<?, ?, ?>>>
{
	static protected BridgedFittingProbabilityFactory _instance = new BridgedFittingProbabilityFactory();

	static public BridgedFittingProbabilityFactory getInstance()
	{
		return _instance;
	}

	protected BridgedFittingProbabilityFactory()
	{	}

	@Override
	public BridgedFittingProbability<BasicCollectionWrapper<?, ?, ?>> getNewProbabilityStrategy(final int probability)
	{
		return new BridgedFittingProbability<BasicCollectionWrapper<?, ?, ?>>(probability);
	}
}