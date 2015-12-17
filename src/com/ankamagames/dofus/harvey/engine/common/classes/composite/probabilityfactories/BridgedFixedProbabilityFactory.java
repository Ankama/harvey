/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.BasicCollectionWrapper;
import com.ankamagames.dofus.harvey.engine.common.classes.composite.BridgedFixedProbability;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedFixedProbabilityFactory
implements IBridgedProbabilityStrategyFactory<BasicCollectionWrapper<?, ?, ?>, BridgedFixedProbability<BasicCollectionWrapper<?, ?, ?>>>
{
	static protected BridgedFixedProbabilityFactory _instance = new BridgedFixedProbabilityFactory();

	static public BridgedFixedProbabilityFactory getInstance()
	{
		return _instance;
	}

	protected BridgedFixedProbabilityFactory()
	{	}

	@Override
	public BridgedFixedProbability<BasicCollectionWrapper<?, ?, ?>> getNewProbabilityStrategy(final int probability)
	{
		return new BridgedFixedProbability<BasicCollectionWrapper<?,?,?>>(probability);
	}
}