/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyFixedProbabilityLongWrapperRandomVariable<Data>
extends BaseLongWrapperRandomVariable<ReadOnlyProbabilityStrategy>
{
	public ReadOnlyFixedProbabilityLongWrapperRandomVariable(final BaseLongWrapperRandomVariable<? extends FixedProbability> base)
	{
		super(base._value, new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}

	public ReadOnlyFixedProbabilityLongWrapperRandomVariable(final long value, final int probability)
	{
		super(value, new ReadOnlyProbabilityStrategy(new FixedProbability(probability)));
	}
}