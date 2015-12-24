/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.doubles.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite.AbstractEditableUnorderedCompositeDoubleRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite.BaseEditableDoubleRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite.BridgedUnorderedCompositeDoubleRandomVariableEditor;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class UnorderedCompositeDoubleRandomVariable
extends AbstractEditableUnorderedCompositeDoubleRandomVariable<BaseEditableDoubleRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
{
	protected BridgedUnorderedCompositeDoubleRandomVariableEditor<? extends UnorderedCompositeDoubleRandomVariable> _editor;

	public UnorderedCompositeDoubleRandomVariable(final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super();
		_editor = new BridgedUnorderedCompositeDoubleRandomVariableEditor<UnorderedCompositeDoubleRandomVariable>(this, defaultProbabilityStrategy);
	}

	public UnorderedCompositeDoubleRandomVariable()
	{
		super();
		_editor = new BridgedUnorderedCompositeDoubleRandomVariableEditor<UnorderedCompositeDoubleRandomVariable>(this);
	}

	@Override
	protected BridgedUnorderedCompositeDoubleRandomVariableEditor<? extends UnorderedCompositeDoubleRandomVariable> getEditor()
	{
		return _editor;
	}
}