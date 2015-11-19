package com.ankamagames.dofus.harvey.composite;

import com.ankamagames.dofus.harvey.engine.base.classes.GenericFittingCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.base.interfaces.IEditableRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.base.interfaces.IterableEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.behaviours.composite.UnorderedCompositeBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class FittingUnorderedCompositeRandomVariable<Data>
extends GenericFittingCompositeRandomVariable
<
	Data,
	IterableEditableCompositeRandomVariable<Data, ?, ? extends IEditableRandomVariableWithProbabilityStrategy<Data,?,?>>,
	IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>,
	UnorderedCompositeBehaviour<Data, IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>, FittingUnorderedCompositeRandomVariable<Data>>
>
{
	public FittingUnorderedCompositeRandomVariable(final int probability, final @Nullable IterableEditableCompositeRandomVariable<Data, ?, ? extends IEditableRandomVariableWithProbabilityStrategy<Data,?,?>> parent)
			throws OverOneProbabilityException
	{
		super(parent, new UnorderedCompositeBehaviour<Data, IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>, FittingUnorderedCompositeRandomVariable<Data>>(), probability);
		_behaviour.init(this);
	}

	public FittingUnorderedCompositeRandomVariable(final int probability)
			throws OverOneProbabilityException
	{
		super(new UnorderedCompositeBehaviour<Data, IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>, FittingUnorderedCompositeRandomVariable<Data>>(), probability);
		_behaviour.init(this);
	}

	public FittingUnorderedCompositeRandomVariable(final @Nullable IterableEditableCompositeRandomVariable<Data, ?, ? extends IEditableRandomVariableWithProbabilityStrategy<Data,?,?>> parent)
			throws OverOneProbabilityException
	{
		super(parent, new UnorderedCompositeBehaviour<Data, IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>, FittingUnorderedCompositeRandomVariable<Data>>());
		_behaviour.init(this);
	}

	public FittingUnorderedCompositeRandomVariable() throws OverOneProbabilityException
	{
		super(new UnorderedCompositeBehaviour<Data, IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>, FittingUnorderedCompositeRandomVariable<Data>>());
		_behaviour.init(this);
	}
}
