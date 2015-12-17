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
public final class ReadOnlyByteWrapperRandomVariable<Data>
extends BaseByteWrapperRandomVariable<IProbabilityStrategy>
{
	public ReadOnlyByteWrapperRandomVariable(final BaseByteWrapperRandomVariable<?> base)
	{
		super(base._value, new ReadOnlyProbabilityStrategy(base._probabilityStrategy));
	}
}