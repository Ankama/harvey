package com.ankamagames.dofus.harvey.composite;

import com.ankamagames.dofus.harvey.engine.base.classes.GenericScalingCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.base.interfaces.IEditableRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.base.interfaces.IterableEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.behaviours.composite.UnorderedCompositeBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class ScalingUnorderedCompositeRandomVariable<Data>
extends GenericScalingCompositeRandomVariable
<
	Data,
	IterableEditableCompositeRandomVariable<Data, ?, ? extends IEditableRandomVariableWithProbabilityStrategy<Data,?,?>>,
	IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>,
	UnorderedCompositeBehaviour<Data, IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>, ScalingUnorderedCompositeRandomVariable<Data>>
>
{
	public ScalingUnorderedCompositeRandomVariable(final int probability, final @Nullable IterableEditableCompositeRandomVariable<Data, ?, ? extends IEditableRandomVariableWithProbabilityStrategy<Data,?,?>> parent)
			throws OverOneProbabilityException
	{
		super(parent, new UnorderedCompositeBehaviour<Data, IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>, ScalingUnorderedCompositeRandomVariable<Data>>(), probability);
		_behaviour.init(this);
	}

	public ScalingUnorderedCompositeRandomVariable(final int probability)
			throws OverOneProbabilityException
	{
		super(new UnorderedCompositeBehaviour<Data, IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>, ScalingUnorderedCompositeRandomVariable<Data>>(), probability);
		_behaviour.init(this);
	}

	public ScalingUnorderedCompositeRandomVariable(final @Nullable IterableEditableCompositeRandomVariable<Data, ?, ? extends IEditableRandomVariableWithProbabilityStrategy<Data,?,?>> parent)
			throws OverOneProbabilityException
	{
		super(parent, new UnorderedCompositeBehaviour<Data, IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>, ScalingUnorderedCompositeRandomVariable<Data>>());
		_behaviour.init(this);
	}

	public ScalingUnorderedCompositeRandomVariable() throws OverOneProbabilityException
	{
		super(new UnorderedCompositeBehaviour<Data, IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>, ScalingUnorderedCompositeRandomVariable<Data>>());
		_behaviour.init(this);
	}
}
