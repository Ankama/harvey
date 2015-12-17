/**
 *
 */
package com.ankamagames.dofus.harvey.generic.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
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
	protected BridgedUnorderedCompositeGenericRandomVariableEditor<Data, ? extends UnorderedCompositeGenericRandomVariable<Data>> _editor;

	public UnorderedCompositeGenericRandomVariable(final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super();
		_editor = new BridgedUnorderedCompositeGenericRandomVariableEditor<Data, UnorderedCompositeGenericRandomVariable<Data>>(this, defaultProbabilityStrategy);
	}

	public UnorderedCompositeGenericRandomVariable()
	{
		super();
		_editor = new BridgedUnorderedCompositeGenericRandomVariableEditor<Data, UnorderedCompositeGenericRandomVariable<Data>>(this);
	}

	@Override
	protected BridgedUnorderedCompositeGenericRandomVariableEditor<Data, ? extends UnorderedCompositeGenericRandomVariable<Data>> getEditor()
	{
		return _editor;
	}
}