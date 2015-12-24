/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.datawrapper;

import com.ankamagames.dofus.harvey.engine.numeric.longs.classes.datawrapper.BaseEditableOrderedLongWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FixedProbabilityOrderedLongWrapper
extends BaseEditableOrderedLongWrapperRandomVariable<FixedProbability>
{
	public FixedProbabilityOrderedLongWrapper(final long value,
			final int probability)
	{
		super(value, new FixedProbability(probability));
	}

	public FixedProbabilityOrderedLongWrapper(final long value)
	{
		super(value, new FixedProbability());
	}
}