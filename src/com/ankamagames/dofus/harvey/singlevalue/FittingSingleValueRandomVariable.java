/**
 *
 */
package com.ankamagames.dofus.harvey.singlevalue;

import com.ankamagames.dofus.harvey.engine.base.interfaces.IEditableRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.base.interfaces.IterableEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.behaviours.singlevalue.SingleValueBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FittingSingleValueRandomVariable<Data>
	extends GenericFittingSingleValueRandomVariable<Data, IterableEditableCompositeRandomVariable<Data, ?, ? extends IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>>,
			SingleValueBehaviour<Data, FittingSingleValueRandomVariable<Data>>>
{
	public FittingSingleValueRandomVariable(
			@Nullable final Data value,
			final int probability,
			@Nullable final IterableEditableCompositeRandomVariable<Data, ?, ? extends IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>> parent)
			throws OverOneProbabilityException
	{
		super(parent, new SingleValueBehaviour<Data, FittingSingleValueRandomVariable<Data>>(value), probability);
		_behaviour.init(this);
	}

	public FittingSingleValueRandomVariable(
			@Nullable final Data value,
			final int probability)
			throws OverOneProbabilityException
	{
		super(new SingleValueBehaviour<Data, FittingSingleValueRandomVariable<Data>>(value), probability);
		_behaviour.init(this);
	}

	public FittingSingleValueRandomVariable(
			@Nullable final Data value,
			@Nullable final IterableEditableCompositeRandomVariable<Data, ?, ? extends IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>> parent)
			throws OverOneProbabilityException
	{
		super(parent, new SingleValueBehaviour<Data, FittingSingleValueRandomVariable<Data>>(value));
		_behaviour.init(this);
	}

	public FittingSingleValueRandomVariable(@Nullable final Data value)
			throws OverOneProbabilityException
	{
		super(new SingleValueBehaviour<Data, FittingSingleValueRandomVariable<Data>>(value));
		_behaviour.init(this);
	}

}
