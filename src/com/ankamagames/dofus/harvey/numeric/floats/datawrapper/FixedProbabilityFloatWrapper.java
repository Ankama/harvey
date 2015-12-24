/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.floats.datawrapper;

import com.ankamagames.dofus.harvey.engine.numeric.floats.classes.datawrapper.BaseEditableFloatWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FixedProbabilityFloatWrapper
extends BaseEditableFloatWrapperRandomVariable<FixedProbability>
{
	public FixedProbabilityFloatWrapper(final float value, final int probability)
	{
		super(value, new FixedProbability(probability));
	}

	public FixedProbabilityFloatWrapper(final float value)
	{
		super(value, new FixedProbability());
	}
}