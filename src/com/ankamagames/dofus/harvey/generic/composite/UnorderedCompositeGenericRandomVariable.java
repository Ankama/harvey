/**
 *
 */
package com.ankamagames.dofus.harvey.generic.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.generic.classes.composite.AbstractBridgedUnorderedCompositeGenericRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.generic.classes.composite.AbstractEditableUnorderedCompositeGenericRandomVariable;
import com.ankamagames.dofus.harvey.engine.generic.classes.composite.BaseEditableGenericRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.generic.classes.composite.BridgedUnorderedCompositeGenericRandomVariableEditor;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class UnorderedCompositeGenericRandomVariable<Data>
extends AbstractEditableUnorderedCompositeGenericRandomVariable<Data, BaseEditableGenericRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies>
{
	protected BridgedUnorderedCompositeGenericRandomVariableEditor<Data, ? extends AbstractEditableUnorderedCompositeGenericRandomVariable<Data, BaseEditableGenericRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies>> _editor;

	public UnorderedCompositeGenericRandomVariable(final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super();
		_editor = new BridgedUnorderedCompositeGenericRandomVariableEditor<Data, AbstractEditableUnorderedCompositeGenericRandomVariable<Data,BaseEditableGenericRandomVariableWrapper<Data,?,?,?>, ProbabilityStrategies>>(this, defaultProbabilityStrategy);
	}

	public UnorderedCompositeGenericRandomVariable()
	{
		super();
		_editor = new BridgedUnorderedCompositeGenericRandomVariableEditor<Data, AbstractEditableUnorderedCompositeGenericRandomVariable<Data,BaseEditableGenericRandomVariableWrapper<Data,?,?,?>, ProbabilityStrategies>>(this);
	}

	@Override
	protected AbstractBridgedUnorderedCompositeGenericRandomVariableEditor<Data, BaseEditableGenericRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies, ?> getEditor()
	{
		return _editor;
	}
}