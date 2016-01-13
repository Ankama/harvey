/**
 *
 */
package com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class OneProbability
implements IProbabilityStrategy, IStaticProbabilityStrategy
{
	protected static OneProbability _instance = new OneProbability();

	public static OneProbability getInstance() { return _instance; }

	protected OneProbability()
	{}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy#getProbability()
	 */
	@Override
	public int getProbability()
	{
		return RandomVariableUtils.ONE;
	}
}
