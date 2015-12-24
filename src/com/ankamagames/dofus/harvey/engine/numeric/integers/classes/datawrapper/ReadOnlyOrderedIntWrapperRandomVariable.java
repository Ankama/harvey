/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyOrderedIntWrapperRandomVariable
extends BaseOrderedIntWrapperRandomVariable<IProbabilityStrategy>
{
	public ReadOnlyOrderedIntWrapperRandomVariable(final BaseOrderedIntWrapperRandomVariable<?> base)
	{
		super(base.getValue(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}