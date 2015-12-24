/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.floats.datawrapper;

import com.ankamagames.dofus.harvey.engine.numeric.floats.classes.datawrapper.BaseEditableOrderedFloatWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FixedProbabilityOrderedFloatWrapper
extends BaseEditableOrderedFloatWrapperRandomVariable<FixedProbability>
{
	public FixedProbabilityOrderedFloatWrapper(final float value,
			final int probability)
	{
		super(value, new FixedProbability(probability));
	}

	public FixedProbabilityOrderedFloatWrapper(final float value)
	{
		super(value, new FixedProbability());
	}
}