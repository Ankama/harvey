/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.doubles.datawrapper;

import com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.datawrapper.BaseEditableDoubleWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FixedProbabilityDoubleWrapper
extends BaseEditableDoubleWrapperRandomVariable<FixedProbability>
{
	public FixedProbabilityDoubleWrapper(final double value, final int probability)
	{
		super(value, new FixedProbability(probability));
	}

	public FixedProbabilityDoubleWrapper(final double value)
	{
		super(value, new FixedProbability());
	}
}