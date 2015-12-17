package com.ankamagames.dofus.harvey.numeric.bytes.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite.AbstractBridgedOrderedCompositeByteRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite.AbstractEditableOrderedCompositeByteRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite.BaseEditableOrderedByteRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite.BridgedOrderedCompositeByteRandomVariableEditor;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class OrderedCompositeByteRandomVariable
extends	AbstractEditableOrderedCompositeByteRandomVariable<BaseEditableOrderedByteRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
{
	protected BridgedOrderedCompositeByteRandomVariableEditor<? extends AbstractEditableOrderedCompositeByteRandomVariable<BaseEditableOrderedByteRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>> _editor;

	public OrderedCompositeByteRandomVariable(final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super();
		_editor = new BridgedOrderedCompositeByteRandomVariableEditor<AbstractEditableOrderedCompositeByteRandomVariable<BaseEditableOrderedByteRandomVariableWrapper<?,?,?>, ProbabilityStrategies>>(this, defaultProbabilityStrategy);
	}

	public OrderedCompositeByteRandomVariable()
	{
		super();
		_editor = new BridgedOrderedCompositeByteRandomVariableEditor<AbstractEditableOrderedCompositeByteRandomVariable<BaseEditableOrderedByteRandomVariableWrapper<?,?,?>, ProbabilityStrategies>>(this);
	}

	@Override
	protected AbstractBridgedOrderedCompositeByteRandomVariableEditor<BaseEditableOrderedByteRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies, ?> getEditor()
	{
		return _editor;
	}
}