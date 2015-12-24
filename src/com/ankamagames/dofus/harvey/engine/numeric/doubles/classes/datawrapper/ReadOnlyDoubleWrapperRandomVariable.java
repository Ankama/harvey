/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyDoubleWrapperRandomVariable<Data>
extends BaseDoubleWrapperRandomVariable<IProbabilityStrategy>
{
	public ReadOnlyDoubleWrapperRandomVariable(final BaseDoubleWrapperRandomVariable<?> base)
	{
		super(base._value, new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}