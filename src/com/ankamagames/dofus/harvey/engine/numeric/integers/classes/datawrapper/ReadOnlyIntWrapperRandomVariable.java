/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyIntWrapperRandomVariable
extends BaseIntWrapperRandomVariable<IProbabilityStrategy>
{
	public ReadOnlyIntWrapperRandomVariable(final BaseIntWrapperRandomVariable<?> base)
	{
		super(base._value, new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}