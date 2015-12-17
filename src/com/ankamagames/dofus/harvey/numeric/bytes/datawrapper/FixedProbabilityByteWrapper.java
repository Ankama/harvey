/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.datawrapper;

import com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.datawrapper.BaseEditableByteWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FixedProbabilityByteWrapper
extends BaseEditableByteWrapperRandomVariable<FixedProbability>
{
	public FixedProbabilityByteWrapper(final byte value, final int probability)
	{
		super(value, new FixedProbability(probability));
	}

	public FixedProbabilityByteWrapper(final byte value)
	{
		super(value, new FixedProbability());
	}
}