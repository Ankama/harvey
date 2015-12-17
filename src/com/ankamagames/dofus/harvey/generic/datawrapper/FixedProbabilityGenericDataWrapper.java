/**
 *
 */
package com.ankamagames.dofus.harvey.generic.datawrapper;

import com.ankamagames.dofus.harvey.engine.generic.classes.datawrapper.BaseEditableGenericDataWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FixedProbabilityGenericDataWrapper<Data>
extends BaseEditableGenericDataWrapperRandomVariable<Data, FixedProbability>
{
	public FixedProbabilityGenericDataWrapper(final@Nullable Data value, final int probability)
	{
		super(value, new FixedProbability(probability));
	}

	public FixedProbabilityGenericDataWrapper(final@Nullable Data value)
	{
		super(value, new FixedProbability());
	}
}