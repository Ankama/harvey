package com.ankamagames.dofus.harvey.generic.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.generic.classes.composite.AbstractBridgedOrderedCompositeGenericRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.generic.classes.composite.AbstractEditableOrderedCompositeGenericRandomVariable;
import com.ankamagames.dofus.harvey.engine.generic.classes.composite.BaseEditableOrderedGenericRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.generic.classes.composite.BridgedOrderedCompositeGenericRandomVariableEditor;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class OrderedCompositeGenericRandomVariable<Data>
extends	AbstractEditableOrderedCompositeGenericRandomVariable<Data, BaseEditableOrderedGenericRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies>
{
	protected BridgedOrderedCompositeGenericRandomVariableEditor<Data, ? extends AbstractEditableOrderedCompositeGenericRandomVariable<Data, BaseEditableOrderedGenericRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies>> _editor;

	public OrderedCompositeGenericRandomVariable(final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super();
		_editor = new BridgedOrderedCompositeGenericRandomVariableEditor<Data, AbstractEditableOrderedCompositeGenericRandomVariable<Data,BaseEditableOrderedGenericRandomVariableWrapper<Data,?,?,?>, ProbabilityStrategies>>(this, defaultProbabilityStrategy);
	}

	public OrderedCompositeGenericRandomVariable()
	{
		super();
		_editor = new BridgedOrderedCompositeGenericRandomVariableEditor<Data, AbstractEditableOrderedCompositeGenericRandomVariable<Data,BaseEditableOrderedGenericRandomVariableWrapper<Data,?,?,?>, ProbabilityStrategies>>(this);
	}

	@Override
	protected AbstractBridgedOrderedCompositeGenericRandomVariableEditor<Data, BaseEditableOrderedGenericRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies, ?> getEditor()
	{
		return _editor;
	}
}