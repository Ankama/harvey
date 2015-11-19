package com.ankamagames.dofus.harvey.composite;

import com.ankamagames.dofus.harvey.engine.base.classes.GenericFillingMergingCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.base.interfaces.IEditableRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.base.interfaces.IMergeableRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.base.interfaces.IterableEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.behaviours.composite.UnorderedCompositeBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class FillingMergingUnorderedCompositeRandomVariable<Data>
extends GenericFillingMergingCompositeRandomVariable
<
	Data,
	IterableEditableCompositeRandomVariable<Data, ?, ? extends IEditableRandomVariableWithProbabilityStrategy<Data,?,?>>,
	IMergeableRandomVariableWithProbabilityStrategy<Data, ?, ?>,
	UnorderedCompositeBehaviour<Data, IMergeableRandomVariableWithProbabilityStrategy<Data, ?, ?>, FillingMergingUnorderedCompositeRandomVariable<Data>>
>
{
	public FillingMergingUnorderedCompositeRandomVariable(
			final @Nullable IterableEditableCompositeRandomVariable<Data, ?, ? extends IEditableRandomVariableWithProbabilityStrategy<Data,?,?>> parent)
			throws OverOneProbabilityException
	{
		super(parent, new UnorderedCompositeBehaviour<Data, IMergeableRandomVariableWithProbabilityStrategy<Data, ?, ?>, FillingMergingUnorderedCompositeRandomVariable<Data>>());
	}

	public FillingMergingUnorderedCompositeRandomVariable() throws OverOneProbabilityException
	{
		super(new UnorderedCompositeBehaviour<Data, IMergeableRandomVariableWithProbabilityStrategy<Data, ?, ?>, FillingMergingUnorderedCompositeRandomVariable<Data>>());
	}
}
