/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
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
	protected BridgedUnorderedCompositeByteRandomVariableEditor<? extends UnorderedCompositeByteRandomVariable> _editor;

	public UnorderedCompositeByteRandomVariable(final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super();
		_editor = new BridgedUnorderedCompositeByteRandomVariableEditor<UnorderedCompositeByteRandomVariable>(this, defaultProbabilityStrategy);
	}

	public UnorderedCompositeByteRandomVariable()
	{
		super();
		_editor = new BridgedUnorderedCompositeByteRandomVariableEditor<UnorderedCompositeByteRandomVariable>(this);
	}

	@Override
	protected BridgedUnorderedCompositeByteRandomVariableEditor<? extends UnorderedCompositeByteRandomVariable> getEditor()
	{
		return _editor;
	}
}