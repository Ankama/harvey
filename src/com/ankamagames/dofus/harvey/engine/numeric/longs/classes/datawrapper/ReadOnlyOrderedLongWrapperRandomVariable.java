/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyOrderedLongWrapperRandomVariable
extends BaseOrderedLongWrapperRandomVariable<IProbabilityStrategy>
{
	public ReadOnlyOrderedLongWrapperRandomVariable(final BaseOrderedLongWrapperRandomVariable<?> base)
	{
		super(base.getValue(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}