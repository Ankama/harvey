package com.ankamagames.dofus.harvey.numeric.longs.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite.AbstractEditableOrderedCompositeLongRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite.BaseEditableOrderedLongRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite.BridgedOrderedCompositeLongRandomVariableEditor;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class OrderedCompositeLongRandomVariable
extends	AbstractEditableOrderedCompositeLongRandomVariable<BaseEditableOrderedLongRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
{
	protected BridgedOrderedCompositeLongRandomVariableEditor<? extends OrderedCompositeLongRandomVariable> _editor;

	public OrderedCompositeLongRandomVariable(final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super();
		_editor = new BridgedOrderedCompositeLongRandomVariableEditor<OrderedCompositeLongRandomVariable>(this, defaultProbabilityStrategy);
	}

	public OrderedCompositeLongRandomVariable()
	{
		super();
		_editor = new BridgedOrderedCompositeLongRandomVariableEditor<OrderedCompositeLongRandomVariable>(this);
	}

	@Override
	protected BridgedOrderedCompositeLongRandomVariableEditor<? extends OrderedCompositeLongRandomVariable> getEditor()
	{
		return _editor;
	}
}