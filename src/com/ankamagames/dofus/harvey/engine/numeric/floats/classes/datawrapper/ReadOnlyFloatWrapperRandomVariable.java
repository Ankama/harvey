/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyFloatWrapperRandomVariable<Data>
extends BaseFloatWrapperRandomVariable<IProbabilityStrategy>
{
	public ReadOnlyFloatWrapperRandomVariable(final BaseFloatWrapperRandomVariable<?> base)
	{
		super(base._value, new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}