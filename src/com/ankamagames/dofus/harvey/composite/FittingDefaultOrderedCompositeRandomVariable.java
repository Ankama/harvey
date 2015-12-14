/**
 *
 */
package com.ankamagames.dofus.harvey.composite;

import com.ankamagames.dofus.harvey.engine.classes.composite.AbstractEditableOrderedCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.classes.composite.BaseEditableOrderedRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.classes.composite.BridgedAbstractOrderedCompositeRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.classes.composite.BridgedFittingDefaultOrderedCompositeRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.classes.composite.probabilityfactories.ProbabilityStrategies;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FittingDefaultOrderedCompositeRandomVariable<Data>
extends AbstractEditableOrderedCompositeRandomVariable<Data, BaseEditableOrderedRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies>
{
	protected BridgedFittingDefaultOrderedCompositeRandomVariableEditor<Data, ? extends AbstractEditableOrderedCompositeRandomVariable<Data, BaseEditableOrderedRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies>> _editor;

	public FittingDefaultOrderedCompositeRandomVariable()
	{
		super();
		_editor = new BridgedFittingDefaultOrderedCompositeRandomVariableEditor<Data, AbstractEditableOrderedCompositeRandomVariable<Data,BaseEditableOrderedRandomVariableWrapper<Data,?,?,?>, ProbabilityStrategies>>(this);
	}

	@Override
	protected BridgedAbstractOrderedCompositeRandomVariableEditor<Data, BaseEditableOrderedRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies, ?> getEditor()
	{
		return _editor;
	}
}