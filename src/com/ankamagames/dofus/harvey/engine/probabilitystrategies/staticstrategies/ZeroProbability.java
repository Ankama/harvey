/**
 *
 */
package com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ZeroProbability
	implements IProbabilityStrategy, IStaticProbabilityStrategy
{
	protected static ZeroProbability _instance = new ZeroProbability();

	public static ZeroProbability getInstance() { return _instance; }

	protected ZeroProbability()
	{}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy#getProbability()
	 */
	@Override
	public int getProbability()
	{
		return 0;
	}
}
