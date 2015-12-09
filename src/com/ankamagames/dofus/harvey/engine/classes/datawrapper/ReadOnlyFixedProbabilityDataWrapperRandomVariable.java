/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyFixedProbabilityDataWrapperRandomVariable<Data>
extends BaseDataWrapperRandomVariable<Data, ReadOnlyProbabilityStrategy>
{
	public ReadOnlyFixedProbabilityDataWrapperRandomVariable(final BaseDataWrapperRandomVariable<Data, ? extends FixedProbability> base)
	{
		super(base._value, new ReadOnlyProbabilityStrategy(base._probabilityStrategy));
	}

	public ReadOnlyFixedProbabilityDataWrapperRandomVariable(@Nullable final Data value, final int probability)
	{
		super(value, new ReadOnlyProbabilityStrategy(new FixedProbability(probability)));
	}
}
