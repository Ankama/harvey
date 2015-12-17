package com.ankamagames.dofus.harvey.generic.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.generic.classes.composite.AbstractEditableOrderedCompositeGenericRandomVariable;
import com.ankamagames.dofus.harvey.engine.generic.classes.composite.BaseEditableOrderedGenericRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.generic.classes.composite.BridgedOrderedCompositeGenericRandomVariableEditor;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class OrderedCompositeGenericRandomVariable<Data>
extends	AbstractEditableOrderedCompositeGenericRandomVariable<Data, BaseEditableOrderedGenericRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies>
{
	protected BridgedOrderedCompositeGenericRandomVariableEditor<Data, ? extends OrderedCompositeGenericRandomVariable<Data>> _editor;

	public OrderedCompositeGenericRandomVariable(final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super();
		_editor = new BridgedOrderedCompositeGenericRandomVariableEditor<Data, OrderedCompositeGenericRandomVariable<Data>>(this, defaultProbabilityStrategy);
	}

	public OrderedCompositeGenericRandomVariable()
	{
		super();
		_editor = new BridgedOrderedCompositeGenericRandomVariableEditor<Data, OrderedCompositeGenericRandomVariable<Data>>(this);
	}

	@Override
	protected BridgedOrderedCompositeGenericRandomVariableEditor<Data, ? extends OrderedCompositeGenericRandomVariable<Data>> getEditor()
	{
		return _editor;
	}
}