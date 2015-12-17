/**
 *
 */
package com.ankamagames.dofus.harvey.generic.datawrapper;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.engine.generic.classes.datawrapper.BaseEditableOrderedGenericDataWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FixedProbabilityOrderedGenericDataWrapper<Data>
extends BaseEditableOrderedGenericDataWrapperRandomVariable<Data, FixedProbability>
{
	public FixedProbabilityOrderedGenericDataWrapper(@Nullable final Data value,
			final int probability, @Nullable final Comparator<Data> comparator)
	{
		super(value, new FixedProbability(probability), comparator);
	}

	public FixedProbabilityOrderedGenericDataWrapper(@Nullable final Data value,
			final int probability)
	{
		super(value, new FixedProbability(probability));
	}

	public FixedProbabilityOrderedGenericDataWrapper(@Nullable final Data value,
			@Nullable final Comparator<Data> comparator)
	{
		super(value, new FixedProbability(), comparator);
	}

	public FixedProbabilityOrderedGenericDataWrapper(@Nullable final Data value)
	{
		super(value, new FixedProbability());
	}
}