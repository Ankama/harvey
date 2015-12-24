/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.datawrapper;

import com.ankamagames.dofus.harvey.engine.numeric.integers.classes.datawrapper.BaseEditableOrderedIntWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FixedProbabilityOrderedIntWrapper
extends BaseEditableOrderedIntWrapperRandomVariable<FixedProbability>
{
	public FixedProbabilityOrderedIntWrapper(final int value,
			final int probability)
	{
		super(value, new FixedProbability(probability));
	}

	public FixedProbabilityOrderedIntWrapper(final int value)
	{
		super(value, new FixedProbability());
	}
}