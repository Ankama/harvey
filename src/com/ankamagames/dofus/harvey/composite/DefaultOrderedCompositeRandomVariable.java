package com.ankamagames.dofus.harvey.composite;

import com.ankamagames.dofus.harvey.engine.classes.composite.AbstractEditableOrderedCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.classes.composite.BaseEditableOrderedRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.classes.composite.BridgedAbstractOrderedCompositeRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.classes.composite.BridgedDefaultOrderedCompositeRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.classes.composite.probabilityfactories.ProbabilityStrategies;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class DefaultOrderedCompositeRandomVariable<Data>
extends	AbstractEditableOrderedCompositeRandomVariable<Data, BaseEditableOrderedRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies>
{
	protected BridgedDefaultOrderedCompositeRandomVariableEditor<Data, ? extends AbstractEditableOrderedCompositeRandomVariable<Data, BaseEditableOrderedRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies>> _editor;

	public DefaultOrderedCompositeRandomVariable()
	{
		super();
		_editor = new BridgedDefaultOrderedCompositeRandomVariableEditor<Data, AbstractEditableOrderedCompositeRandomVariable<Data,BaseEditableOrderedRandomVariableWrapper<Data,?,?,?>, ProbabilityStrategies>>(this);
	}

	@Override
	protected BridgedAbstractOrderedCompositeRandomVariableEditor<Data, BaseEditableOrderedRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies, ?> getEditor()
	{
		return _editor;
	}
}