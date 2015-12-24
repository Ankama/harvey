package com.ankamagames.dofus.harvey.numeric.floats.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite.AbstractEditableOrderedCompositeFloatRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite.BaseEditableOrderedFloatRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite.BridgedOrderedCompositeFloatRandomVariableEditor;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class OrderedCompositeFloatRandomVariable
extends	AbstractEditableOrderedCompositeFloatRandomVariable<BaseEditableOrderedFloatRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
{
	protected BridgedOrderedCompositeFloatRandomVariableEditor<? extends OrderedCompositeFloatRandomVariable> _editor;

	public OrderedCompositeFloatRandomVariable(final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super();
		_editor = new BridgedOrderedCompositeFloatRandomVariableEditor<OrderedCompositeFloatRandomVariable>(this, defaultProbabilityStrategy);
	}

	public OrderedCompositeFloatRandomVariable()
	{
		super();
		_editor = new BridgedOrderedCompositeFloatRandomVariableEditor<OrderedCompositeFloatRandomVariable>(this);
	}

	@Override
	protected BridgedOrderedCompositeFloatRandomVariableEditor<? extends OrderedCompositeFloatRandomVariable> getEditor()
	{
		return _editor;
	}
}