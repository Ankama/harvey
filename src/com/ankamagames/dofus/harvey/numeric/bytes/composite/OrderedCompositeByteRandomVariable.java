package com.ankamagames.dofus.harvey.numeric.bytes.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite.AbstractEditableOrderedCompositeByteRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite.BaseEditableOrderedByteRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite.BridgedOrderedCompositeByteRandomVariableEditor;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class OrderedCompositeByteRandomVariable
extends	AbstractEditableOrderedCompositeByteRandomVariable<BaseEditableOrderedByteRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
{
	protected BridgedOrderedCompositeByteRandomVariableEditor<? extends OrderedCompositeByteRandomVariable> _editor;

	public OrderedCompositeByteRandomVariable(final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super();
		_editor = new BridgedOrderedCompositeByteRandomVariableEditor<OrderedCompositeByteRandomVariable>(this, defaultProbabilityStrategy);
	}

	public OrderedCompositeByteRandomVariable()
	{
		super();
		_editor = new BridgedOrderedCompositeByteRandomVariableEditor<OrderedCompositeByteRandomVariable>(this);
	}

	@Override
	protected BridgedOrderedCompositeByteRandomVariableEditor<? extends OrderedCompositeByteRandomVariable> getEditor()
	{
		return _editor;
	}
}