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
public class FillingSingleValueRandomVariable<Data>
	extends GenericFillingSingleValueRandomVariable<Data, IterableEditableCompositeRandomVariable<Data, ?, ? extends IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>>,
			SingleValueBehaviour<Data, FillingSingleValueRandomVariable<Data>>>
{

	public FillingSingleValueRandomVariable(
			@Nullable final Data value,
			@Nullable final IterableEditableCompositeRandomVariable<Data, ?, ? extends IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>> parent)
			throws OverOneProbabilityException
	{
		super(parent, new SingleValueBehaviour<Data, FillingSingleValueRandomVariable<Data>>(value));
		_behaviour.init(this);
	}

	public FillingSingleValueRandomVariable(@Nullable final Data value)
			throws OverOneProbabilityException
	{
		super(new SingleValueBehaviour<Data, FillingSingleValueRandomVariable<Data>>(value));
		_behaviour.init(this);
	}
}
