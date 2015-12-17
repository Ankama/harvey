/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.interfaces.IBasicCollection;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedFixedProbability<Bridged extends IBasicCollection>
extends FixedProbability
implements IBridgedEditableProbabilityStrategy<Bridged>
{
	public BridgedFixedProbability(final int probability)
	{
		super(probability);
	}

	public BridgedFixedProbability()
	{
		super();
	}

	@Override
	public void init(final Bridged bridged)
	{	}
}
