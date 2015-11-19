package com.ankamagames.dofus.harvey.composite;

import com.ankamagames.dofus.harvey.engine.base.classes.GenericFillingCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.base.interfaces.IEditableRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.base.interfaces.IterableEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.behaviours.composite.UnorderedCompositeBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class FillingUnorderedCompositeRandomVariable<Data>
extends GenericFillingCompositeRandomVariable
<
	Data,
	IterableEditableCompositeRandomVariable<Data, ?, ? extends IEditableRandomVariableWithProbabilityStrategy<Data,?,?>>,
	IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>,
	UnorderedCompositeBehaviour<Data, IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>, FillingUnorderedCompositeRandomVariable<Data>>
>
{
	public FillingUnorderedCompositeRandomVariable(
			final @Nullable IterableEditableCompositeRandomVariable<Data, ?, ? extends IEditableRandomVariableWithProbabilityStrategy<Data,?,?>> parent)
			throws OverOneProbabilityException
	{
		super(parent, new UnorderedCompositeBehaviour<Data, IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>, FillingUnorderedCompositeRandomVariable<Data>>());
		_behaviour.init(this);
	}

	public FillingUnorderedCompositeRandomVariable() throws OverOneProbabilityException
	{
		super(new UnorderedCompositeBehaviour<Data, IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>, FillingUnorderedCompositeRandomVariable<Data>>());
		_behaviour.init(this);
	}
}
