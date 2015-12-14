/**
 *
 */
package com.ankamagames.dofus.harvey.datawrapper;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.engine.classes.datawrapper.BaseEditableOrderedDataWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FixedProbabilityOrderedDataWrapper<Data>
extends BaseEditableOrderedDataWrapperRandomVariable<Data, FixedProbability>
{
	public FixedProbabilityOrderedDataWrapper(@Nullable final Data value,
			final int probability, @Nullable final Comparator<Data> comparator)
	{
		super(value, new FixedProbability(probability), comparator);
	}

	public FixedProbabilityOrderedDataWrapper(@Nullable final Data value,
			final int probability)
	{
		super(value, new FixedProbability(probability));
	}

	public FixedProbabilityOrderedDataWrapper(@Nullable final Data value,
			@Nullable final Comparator<Data> comparator)
	{
		super(value, new FixedProbability(), comparator);
	}

	public FixedProbabilityOrderedDataWrapper(@Nullable final Data value)
	{
		super(value, new FixedProbability());
	}
}