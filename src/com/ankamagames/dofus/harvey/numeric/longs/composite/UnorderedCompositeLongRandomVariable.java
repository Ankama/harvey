/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite.AbstractEditableUnorderedCompositeLongRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite.BaseEditableLongRandomVariableWrapper;
import com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite.BridgedUnorderedCompositeLongRandomVariableEditor;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class UnorderedCompositeLongRandomVariable
extends AbstractEditableUnorderedCompositeLongRandomVariable<BaseEditableLongRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
{
	protected BridgedUnorderedCompositeLongRandomVariableEditor<? extends UnorderedCompositeLongRandomVariable> _editor;

	public UnorderedCompositeLongRandomVariable(final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super();
		_editor = new BridgedUnorderedCompositeLongRandomVariableEditor<UnorderedCompositeLongRandomVariable>(this, defaultProbabilityStrategy);
	}

	public UnorderedCompositeLongRandomVariable()
	{
		super();
		_editor = new BridgedUnorderedCompositeLongRandomVariableEditor<UnorderedCompositeLongRandomVariable>(this);
	}

	@Override
	protected BridgedUnorderedCompositeLongRandomVariableEditor<? extends UnorderedCompositeLongRandomVariable> getEditor()
	{
		return _editor;
	}
}