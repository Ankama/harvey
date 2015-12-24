/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyFixedProbabilityGenericDataWrapperRandomVariable<Data>
extends BaseGenericDataWrapperRandomVariable<Data, ReadOnlyProbabilityStrategy>
{
	public ReadOnlyFixedProbabilityGenericDataWrapperRandomVariable(final BaseGenericDataWrapperRandomVariable<Data, ? extends FixedProbability> base)
	{
		super(base._value, new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}

	public ReadOnlyFixedProbabilityGenericDataWrapperRandomVariable(@Nullable final Data value, final int probability)
	{
		super(value, new ReadOnlyProbabilityStrategy(new FixedProbability(probability)));
	}
}
