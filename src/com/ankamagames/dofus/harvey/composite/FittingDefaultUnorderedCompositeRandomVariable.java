/**
 *
 */
package com.ankamagames.dofus.harvey.composite;

import com.ankamagames.dofus.harvey.engine.classes.composite.AbstractEditableUnorderedCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.classes.composite.BaseEditableRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.classes.composite.BridgedAbstractUnorderedCompositeRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.classes.composite.BridgedFittingDefaultUnorderedCompositeRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.classes.composite.probabilityfactories.ProbabilityStrategies;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FittingDefaultUnorderedCompositeRandomVariable<Data>
extends AbstractEditableUnorderedCompositeRandomVariable<Data, BaseEditableRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies>
{
	protected BridgedFittingDefaultUnorderedCompositeRandomVariableEditor<Data, ? extends AbstractEditableUnorderedCompositeRandomVariable<Data, BaseEditableRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies>> _editor;

	public FittingDefaultUnorderedCompositeRandomVariable()
	{
		super();
		_editor = new BridgedFittingDefaultUnorderedCompositeRandomVariableEditor<Data, AbstractEditableUnorderedCompositeRandomVariable<Data,BaseEditableRandomVariableWrapper<Data,?,?,?>, ProbabilityStrategies>>(this);
	}

	@Override
	protected BridgedAbstractUnorderedCompositeRandomVariableEditor<Data, BaseEditableRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies, ?> getEditor()
	{
		return _editor;
	}
}