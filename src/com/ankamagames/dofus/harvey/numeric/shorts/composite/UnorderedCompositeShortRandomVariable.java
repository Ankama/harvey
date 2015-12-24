/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite.AbstractEditableUnorderedCompositeShortRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite.BaseEditableShortRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite.BridgedUnorderedCompositeShortRandomVariableEditor;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class UnorderedCompositeShortRandomVariable
extends AbstractEditableUnorderedCompositeShortRandomVariable<BaseEditableShortRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
{
	protected BridgedUnorderedCompositeShortRandomVariableEditor<? extends UnorderedCompositeShortRandomVariable> _editor;

	public UnorderedCompositeShortRandomVariable(final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super();
		_editor = new BridgedUnorderedCompositeShortRandomVariableEditor<UnorderedCompositeShortRandomVariable>(this, defaultProbabilityStrategy);
	}

	public UnorderedCompositeShortRandomVariable()
	{
		super();
		_editor = new BridgedUnorderedCompositeShortRandomVariableEditor<UnorderedCompositeShortRandomVariable>(this);
	}

	@Override
	protected BridgedUnorderedCompositeShortRandomVariableEditor<? extends UnorderedCompositeShortRandomVariable> getEditor()
	{
		return _editor;
	}
}