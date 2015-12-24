/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.datawrapper;

import com.ankamagames.dofus.harvey.engine.numeric.integers.classes.datawrapper.BaseEditableIntWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FixedProbabilityIntWrapper
extends BaseEditableIntWrapperRandomVariable<FixedProbability>
{
	public FixedProbabilityIntWrapper(final int value, final int probability)
	{
		super(value, new FixedProbability(probability));
	}

	public FixedProbabilityIntWrapper(final int value)
	{
		super(value, new FixedProbability());
	}
}