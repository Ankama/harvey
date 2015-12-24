package com.ankamagames.dofus.harvey.numeric.integers.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite.AbstractEditableOrderedCompositeIntRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite.BaseEditableOrderedIntRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite.BridgedOrderedCompositeIntRandomVariableEditor;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class OrderedCompositeIntRandomVariable
extends	AbstractEditableOrderedCompositeIntRandomVariable<BaseEditableOrderedIntRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
{
	protected BridgedOrderedCompositeIntRandomVariableEditor<? extends OrderedCompositeIntRandomVariable> _editor;

	public OrderedCompositeIntRandomVariable(final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super();
		_editor = new BridgedOrderedCompositeIntRandomVariableEditor<OrderedCompositeIntRandomVariable>(this, defaultProbabilityStrategy);
	}

	public OrderedCompositeIntRandomVariable()
	{
		super();
		_editor = new BridgedOrderedCompositeIntRandomVariableEditor<OrderedCompositeIntRandomVariable>(this);
	}

	@Override
	protected BridgedOrderedCompositeIntRandomVariableEditor<? extends OrderedCompositeIntRandomVariable> getEditor()
	{
		return _editor;
	}
}