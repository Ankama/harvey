package com.ankamagames.dofus.harvey.numeric.shorts.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite.AbstractEditableOrderedCompositeShortRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite.BaseEditableOrderedShortRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite.BridgedOrderedCompositeShortRandomVariableEditor;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class OrderedCompositeShortRandomVariable
extends	AbstractEditableOrderedCompositeShortRandomVariable<BaseEditableOrderedShortRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
{
	protected BridgedOrderedCompositeShortRandomVariableEditor<? extends OrderedCompositeShortRandomVariable> _editor;

	public OrderedCompositeShortRandomVariable(final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super();
		_editor = new BridgedOrderedCompositeShortRandomVariableEditor<OrderedCompositeShortRandomVariable>(this, defaultProbabilityStrategy);
	}

	public OrderedCompositeShortRandomVariable()
	{
		super();
		_editor = new BridgedOrderedCompositeShortRandomVariableEditor<OrderedCompositeShortRandomVariable>(this);
	}

	@Override
	protected BridgedOrderedCompositeShortRandomVariableEditor<? extends OrderedCompositeShortRandomVariable> getEditor()
	{
		return _editor;
	}
}