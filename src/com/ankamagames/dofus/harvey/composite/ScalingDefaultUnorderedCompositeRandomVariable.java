/**
 *
 */
package com.ankamagames.dofus.harvey.composite;

import com.ankamagames.dofus.harvey.engine.classes.composite.AbstractEditableUnorderedCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.classes.composite.BaseEditableRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.classes.composite.BridgedAbstractCompositeRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.classes.composite.BridgedScalingDefaultCompositeRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.classes.composite.probabilityfactories.ProbabilityStrategies;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ScalingDefaultUnorderedCompositeRandomVariable<Data>
extends AbstractEditableUnorderedCompositeRandomVariable<Data, BaseEditableRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies>
{
	protected BridgedScalingDefaultCompositeRandomVariableEditor<Data, ? extends AbstractEditableUnorderedCompositeRandomVariable<Data, BaseEditableRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies>> _editor;

	public ScalingDefaultUnorderedCompositeRandomVariable()
	{
		super();
		_editor = new BridgedScalingDefaultCompositeRandomVariableEditor<Data, AbstractEditableUnorderedCompositeRandomVariable<Data,BaseEditableRandomVariableWrapper<Data,?,?,?>, ProbabilityStrategies>>(this);
	}

	@Override
	protected BridgedAbstractCompositeRandomVariableEditor<Data, BaseEditableRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies, ?> getEditor()
	{
		return _editor;
	}
}