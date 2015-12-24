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
public final class ReadOnlyOrderedFloatWrapperRandomVariable
extends BaseOrderedFloatWrapperRandomVariable<IProbabilityStrategy>
{
	public ReadOnlyOrderedFloatWrapperRandomVariable(final BaseOrderedFloatWrapperRandomVariable<?> base)
	{
		super(base.getValue(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}