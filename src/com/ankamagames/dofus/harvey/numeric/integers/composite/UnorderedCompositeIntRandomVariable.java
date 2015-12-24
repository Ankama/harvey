/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite.AbstractEditableUnorderedCompositeIntRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite.BaseEditableIntRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite.BridgedUnorderedCompositeIntRandomVariableEditor;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class UnorderedCompositeIntRandomVariable
extends AbstractEditableUnorderedCompositeIntRandomVariable<BaseEditableIntRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
{
	protected BridgedUnorderedCompositeIntRandomVariableEditor<? extends UnorderedCompositeIntRandomVariable> _editor;

	public UnorderedCompositeIntRandomVariable(final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super();
		_editor = new BridgedUnorderedCompositeIntRandomVariableEditor<UnorderedCompositeIntRandomVariable>(this, defaultProbabilityStrategy);
	}

	public UnorderedCompositeIntRandomVariable()
	{
		super();
		_editor = new BridgedUnorderedCompositeIntRandomVariableEditor<UnorderedCompositeIntRandomVariable>(this);
	}

	@Override
	protected BridgedUnorderedCompositeIntRandomVariableEditor<? extends UnorderedCompositeIntRandomVariable> getEditor()
	{
		return _editor;
	}
}