/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.common.classes.AbstractRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractDataWrapperRandomVariable<ProbabilityStrategy extends IProbabilityStrategy>
	extends AbstractRandomVariable
{
	protected ProbabilityStrategy _probabilityStrategy;

	public AbstractDataWrapperRandomVariable(final ProbabilityStrategy probabilityStrategy)
	{
		_probabilityStrategy = probabilityStrategy;
	}

	protected ProbabilityStrategy getProbabilityStrategy()
	{
		return _probabilityStrategy;
	}

	@Override
	public boolean hasOnlyOneValue()
	{
		return !isUnknown();
	}

	@Override
	public int getKnownProbability()
	{
		return _probabilityStrategy.getProbability();
	}
}
