/**
 *
 */
package com.ankamagames.dofus.harvey.singlevalue;

import com.ankamagames.dofus.harvey.engine.base.interfaces.IterableEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.behaviours.singlevalue.OrderedSingleValueBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class OrderedSingleValueRandomVariableView<Data>
extends GenericOrderedSingleValueRandomVariableView
<
	Data,
	IterableEditableCompositeRandomVariable<Data, ?, ?>,
	OrderedSingleValueBehaviour
	<
		Data,
		IterableEditableCompositeRandomVariable<Data, ?, ?>,
		?,
		OrderedSingleValueRandomVariableView<Data>
	>
>
{
	public OrderedSingleValueRandomVariableView(
			@Nullable final Data value,
			final IterableEditableCompositeRandomVariable<Data, ?, ?> parent,
			final IProbabilityStrategy probabilityStrategy)
			throws OverOneProbabilityException
	{
		super(parent,
				new OrderedSingleValueBehaviour<Data,
					IterableEditableCompositeRandomVariable<Data, ?, ?>,
					OrderedSingleValueBehaviour<Data,IterableEditableCompositeRandomVariable<Data, ?, ?>,?,OrderedSingleValueRandomVariableView<Data>>,
					OrderedSingleValueRandomVariableView<Data>
				>(value),
				probabilityStrategy);
		_behaviour.init(this);
	}

	public OrderedSingleValueRandomVariableView(
			@Nullable final Data value,
			final IProbabilityStrategy probabilityStrategy)
			throws OverOneProbabilityException
	{
		super(new OrderedSingleValueBehaviour<Data,
				IterableEditableCompositeRandomVariable<Data, ?, ?>,
				OrderedSingleValueBehaviour<Data,IterableEditableCompositeRandomVariable<Data, ?, ?>,?,OrderedSingleValueRandomVariableView<Data>>,
				OrderedSingleValueRandomVariableView<Data>
			>(value),
			probabilityStrategy);
		_behaviour.init(this);
	}

}
