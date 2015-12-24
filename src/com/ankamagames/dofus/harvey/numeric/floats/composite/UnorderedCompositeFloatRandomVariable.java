/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.floats.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite.AbstractEditableUnorderedCompositeFloatRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite.BaseEditableFloatRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite.BridgedUnorderedCompositeFloatRandomVariableEditor;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class UnorderedCompositeFloatRandomVariable
extends AbstractEditableUnorderedCompositeFloatRandomVariable<BaseEditableFloatRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
{
	protected BridgedUnorderedCompositeFloatRandomVariableEditor<? extends UnorderedCompositeFloatRandomVariable> _editor;

	public UnorderedCompositeFloatRandomVariable(final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super();
		_editor = new BridgedUnorderedCompositeFloatRandomVariableEditor<UnorderedCompositeFloatRandomVariable>(this, defaultProbabilityStrategy);
	}

	public UnorderedCompositeFloatRandomVariable()
	{
		super();
		_editor = new BridgedUnorderedCompositeFloatRandomVariableEditor<UnorderedCompositeFloatRandomVariable>(this);
	}

	@Override
	protected BridgedUnorderedCompositeFloatRandomVariableEditor<? extends UnorderedCompositeFloatRandomVariable> getEditor()
	{
		return _editor;
	}
}