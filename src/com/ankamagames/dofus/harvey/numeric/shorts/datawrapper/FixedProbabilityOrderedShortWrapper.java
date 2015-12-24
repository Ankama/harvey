/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.datawrapper;

import com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.datawrapper.BaseEditableOrderedShortWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FixedProbabilityOrderedShortWrapper
extends BaseEditableOrderedShortWrapperRandomVariable<FixedProbability>
{
	public FixedProbabilityOrderedShortWrapper(final short value,
			final int probability)
	{
		super(value, new FixedProbability(probability));
	}

	public FixedProbabilityOrderedShortWrapper(final short value)
	{
		super(value, new FixedProbability());
	}
}