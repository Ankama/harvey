/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite.AbstractBridgedUnorderedCompositeByteRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite.AbstractEditableUnorderedCompositeByteRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite.BaseEditableByteRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite.BridgedUnorderedCompositeByteRandomVariableEditor;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class UnorderedCompositeByteRandomVariable
extends AbstractEditableUnorderedCompositeByteRandomVariable<BaseEditableByteRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
{
	protected BridgedUnorderedCompositeByteRandomVariableEditor<? extends AbstractEditableUnorderedCompositeByteRandomVariable<BaseEditableByteRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>> _editor;

	public UnorderedCompositeByteRandomVariable(final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super();
		_editor = new BridgedUnorderedCompositeByteRandomVariableEditor<AbstractEditableUnorderedCompositeByteRandomVariable<BaseEditableByteRandomVariableWrapper<?,?,?>, ProbabilityStrategies>>(this, defaultProbabilityStrategy);
	}

	public UnorderedCompositeByteRandomVariable()
	{
		super();
		_editor = new BridgedUnorderedCompositeByteRandomVariableEditor<AbstractEditableUnorderedCompositeByteRandomVariable<BaseEditableByteRandomVariableWrapper<?,?,?>, ProbabilityStrategies>>(this);
	}

	@Override
	protected AbstractBridgedUnorderedCompositeByteRandomVariableEditor<BaseEditableByteRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies, ?> getEditor()
	{
		return _editor;
	}
}