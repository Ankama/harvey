/**
 *
 */
package com.ankamagames.dofus.harvey.singlevalue;

import com.ankamagames.dofus.harvey.engine.behaviours.singlevalue.SingleValueBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Holds one value of type Data with associated probability
 *
 * @author sgros
 *
 */
@NonNullByDefault
public class FixedSingleValueRandomVariable<Data>
extends MergeableSingleValueRandomVariable<Data, IEditableCompositeRandomVariable<Data, ?, ?>, SingleValueBehaviour<Data, FixedSingleValueRandomVariable<Data>>, FixedProbability>
{
	public FixedSingleValueRandomVariable(@Nullable final Data value, final @Nullable IEditableCompositeRandomVariable<Data, ?, ?> parent, final int probability) throws OverOneProbabilityException
	{
		super(parent, new SingleValueBehaviour<Data, FixedSingleValueRandomVariable<Data>>(value), new FixedProbability(probability));
		_behaviour.init(this);
	}

	public FixedSingleValueRandomVariable(@Nullable final Data value, final int probability) throws OverOneProbabilityException
	{
		super(new SingleValueBehaviour<Data, FixedSingleValueRandomVariable<Data>>(value), new FixedProbability(probability));
		_behaviour.init(this);
	}

	public FixedSingleValueRandomVariable(@Nullable final Data value, final @Nullable IEditableCompositeRandomVariable<Data, ?, ?> parent) throws OverOneProbabilityException
	{
		super(parent, new SingleValueBehaviour<Data, FixedSingleValueRandomVariable<Data>>(value), new FixedProbability());
		_behaviour.init(this);
	}

	public FixedSingleValueRandomVariable(@Nullable final Data value) throws OverOneProbabilityException
	{
		super(new SingleValueBehaviour<Data, FixedSingleValueRandomVariable<Data>>(value), new FixedProbability());
		_behaviour.init(this);
	}
}
