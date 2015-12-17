/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyFixedProbabilityByteWrapperRandomVariable<Data>
extends BaseByteWrapperRandomVariable<ReadOnlyProbabilityStrategy>
{
	public ReadOnlyFixedProbabilityByteWrapperRandomVariable(final BaseByteWrapperRandomVariable<? extends FixedProbability> base)
	{
		super(base._value, new ReadOnlyProbabilityStrategy(base._probabilityStrategy));
	}

	public ReadOnlyFixedProbabilityByteWrapperRandomVariable(final byte value, final int probability)
	{
		super(value, new ReadOnlyProbabilityStrategy(new FixedProbability(probability)));
	}
}