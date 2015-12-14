/**
 *
 */
package com.ankamagames.dofus.harvey.composite;

import com.ankamagames.dofus.harvey.engine.classes.composite.AbstractEditableOrderedCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.classes.composite.BaseEditableOrderedRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.classes.composite.BridgedAbstractOrderedCompositeRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.classes.composite.BridgedScalingDefaultOrderedCompositeRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.classes.composite.probabilityfactories.ProbabilityStrategies;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ScalingDefaultOrderedCompositeRandomVariable<Data>
extends AbstractEditableOrderedCompositeRandomVariable<Data, BaseEditableOrderedRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies>
{
	protected BridgedScalingDefaultOrderedCompositeRandomVariableEditor<Data, ? extends AbstractEditableOrderedCompositeRandomVariable<Data, BaseEditableOrderedRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies>> _editor;

	public ScalingDefaultOrderedCompositeRandomVariable()
	{
		super();
		_editor = new BridgedScalingDefaultOrderedCompositeRandomVariableEditor<Data, AbstractEditableOrderedCompositeRandomVariable<Data,BaseEditableOrderedRandomVariableWrapper<Data,?,?,?>, ProbabilityStrategies>>(this);
	}

	@Override
	protected BridgedAbstractOrderedCompositeRandomVariableEditor<Data, BaseEditableOrderedRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies, ?> getEditor()
	{
		return _editor;
	}
}