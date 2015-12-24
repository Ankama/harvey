/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyOrderedByteWrapperRandomVariable
extends BaseOrderedByteWrapperRandomVariable<IProbabilityStrategy>
{
	public ReadOnlyOrderedByteWrapperRandomVariable(final BaseOrderedByteWrapperRandomVariable<?> base)
	{
		super(base.getValue(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}