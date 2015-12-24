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
public final class ReadOnlyOrderedShortWrapperRandomVariable
extends BaseOrderedShortWrapperRandomVariable<IProbabilityStrategy>
{
	public ReadOnlyOrderedShortWrapperRandomVariable(final BaseOrderedShortWrapperRandomVariable<?> base)
	{
		super(base.getValue(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}