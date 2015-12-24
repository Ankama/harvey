package com.ankamagames.dofus.harvey.numeric.doubles.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite.AbstractEditableOrderedCompositeDoubleRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite.BaseEditableOrderedDoubleRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite.BridgedOrderedCompositeDoubleRandomVariableEditor;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class OrderedCompositeDoubleRandomVariable
extends	AbstractEditableOrderedCompositeDoubleRandomVariable<BaseEditableOrderedDoubleRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
{
	protected BridgedOrderedCompositeDoubleRandomVariableEditor<? extends OrderedCompositeDoubleRandomVariable> _editor;

	public OrderedCompositeDoubleRandomVariable(final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super();
		_editor = new BridgedOrderedCompositeDoubleRandomVariableEditor<OrderedCompositeDoubleRandomVariable>(this, defaultProbabilityStrategy);
	}

	public OrderedCompositeDoubleRandomVariable()
	{
		super();
		_editor = new BridgedOrderedCompositeDoubleRandomVariableEditor<OrderedCompositeDoubleRandomVariable>(this);
	}

	@Override
	protected BridgedOrderedCompositeDoubleRandomVariableEditor<? extends OrderedCompositeDoubleRandomVariable> getEditor()
	{
		return _editor;
	}
}