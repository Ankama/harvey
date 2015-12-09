/**
 *
 */
package com.ankamagames.dofus.harvey.datawrapper;

import com.ankamagames.dofus.harvey.engine.classes.datawrapper.BaseEditableDataWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FixedProbabilityDataWrapper<Data>
extends BaseEditableDataWrapperRandomVariable<Data, FixedProbability>
{
	public FixedProbabilityDataWrapper(final@Nullable Data value, final int probability)
	{
		super(value, new FixedProbability(probability));
	}

	public FixedProbabilityDataWrapper(final@Nullable Data value)
	{
		super(value, new FixedProbability());
	}
}