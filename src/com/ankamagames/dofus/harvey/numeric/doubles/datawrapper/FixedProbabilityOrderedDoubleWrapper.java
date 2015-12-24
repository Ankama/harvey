/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.doubles.datawrapper;

import com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.datawrapper.BaseEditableOrderedDoubleWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FixedProbabilityOrderedDoubleWrapper
extends BaseEditableOrderedDoubleWrapperRandomVariable<FixedProbability>
{
	public FixedProbabilityOrderedDoubleWrapper(final double value,
			final int probability)
	{
		super(value, new FixedProbability(probability));
	}

	public FixedProbabilityOrderedDoubleWrapper(final double value)
	{
		super(value, new FixedProbability());
	}
}