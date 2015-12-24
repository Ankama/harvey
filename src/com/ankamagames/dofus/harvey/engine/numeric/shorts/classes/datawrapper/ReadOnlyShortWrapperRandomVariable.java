/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyShortWrapperRandomVariable<Data>
extends BaseShortWrapperRandomVariable<IProbabilityStrategy>
{
	public ReadOnlyShortWrapperRandomVariable(final BaseShortWrapperRandomVariable<?> base)
	{
		super(base._value, new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}