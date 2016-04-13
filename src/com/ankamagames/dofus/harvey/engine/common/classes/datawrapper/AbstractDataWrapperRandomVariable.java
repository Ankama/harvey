/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes.datawrapper;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.classes.AbstractElementaryEvent;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSet;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractDataWrapperRandomVariable<Set extends IDegenerateSet<Set>, ProbabilityStrategy extends IProbabilityStrategy>
	extends AbstractElementaryEvent<Set>
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
	public int getProbability()
	{
		return _probabilityStrategy.getProbability();
	}
	
	
}