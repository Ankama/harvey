/**
 *
 */
package com.ankamagames.dofus.harvey.composite;

import com.ankamagames.dofus.harvey.engine.base.classes.EditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.base.interfaces.IEditableRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.behaviours.composite.UnorderedCompositeBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class DefaultUnorderedCompositeRandomVariable<Data, ProbabilityStrategy extends IProbabilityStrategy>
extends
	EditableCompositeRandomVariable
	<
		Data,
		IEditableCompositeRandomVariable<Data, ?, ?>,
		IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>,
		UnorderedCompositeBehaviour<Data, IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>, DefaultUnorderedCompositeRandomVariable<Data, ProbabilityStrategy>>,
		ProbabilityStrategy
	>
{
	public DefaultUnorderedCompositeRandomVariable(final @Nullable IEditableCompositeRandomVariable<Data, ?, ?> parent, final ProbabilityStrategy probabilityStrategy)
			throws OverOneProbabilityException
	{
		super(parent, new UnorderedCompositeBehaviour<Data, IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>, DefaultUnorderedCompositeRandomVariable<Data, ProbabilityStrategy>>(), probabilityStrategy);
		_behaviour.init(this);
	}

	public DefaultUnorderedCompositeRandomVariable(final ProbabilityStrategy probabilityStrategy)
			throws OverOneProbabilityException
	{
		super(new UnorderedCompositeBehaviour<Data, IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>, DefaultUnorderedCompositeRandomVariable<Data, ProbabilityStrategy>>(), probabilityStrategy);
		_behaviour.init(this);
	}
}
