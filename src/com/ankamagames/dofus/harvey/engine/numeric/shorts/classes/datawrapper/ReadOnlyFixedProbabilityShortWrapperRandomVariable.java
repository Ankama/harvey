/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyFixedProbabilityShortWrapperRandomVariable<Data>
extends BaseShortWrapperRandomVariable<ReadOnlyProbabilityStrategy>
{
	public ReadOnlyFixedProbabilityShortWrapperRandomVariable(final BaseShortWrapperRandomVariable<? extends FixedProbability> base)
	{
		super(base._value, new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}

	public ReadOnlyFixedProbabilityShortWrapperRandomVariable(final short value, final int probability)
	{
		super(value, new ReadOnlyProbabilityStrategy(new FixedProbability(probability)));
	}
}