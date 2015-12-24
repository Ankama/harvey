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
public final class ReadOnlyOrderedDoubleWrapperRandomVariable
extends BaseOrderedDoubleWrapperRandomVariable<IProbabilityStrategy>
{
	public ReadOnlyOrderedDoubleWrapperRandomVariable(final BaseOrderedDoubleWrapperRandomVariable<?> base)
	{
		super(base.getValue(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}