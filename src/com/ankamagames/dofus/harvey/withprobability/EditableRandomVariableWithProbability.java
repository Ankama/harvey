/**
 *
 */
package com.ankamagames.dofus.harvey.withprobability;

import com.ankamagames.dofus.harvey.engine.exceptions.ProbabilityOutOfBoundException;
import com.ankamagames.dofus.harvey.engine.inetrfaces.withprobability.IEditableRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IModifiableProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class EditableRandomVariableWithProbability
<
	Data,
	ChildType extends IRandomVariable<Data>,
	ProbabilityStrategy extends IModifiableProbabilityStrategy
>
extends RandomVariableWithProbability<Data, ChildType, ProbabilityStrategy>
implements IEditableRandomVariableWithProbabilityStrategy<Data, ProbabilityStrategy>
{
	public EditableRandomVariableWithProbability(final ChildType heldRandomVariable,
			final ProbabilityStrategy probabilityStrategy)
	{
		super(heldRandomVariable, probabilityStrategy);
	}

	@Override
	public void setProbability(final int probability)
			throws ProbabilityOutOfBoundException
	{
		_probabilityStrategy.setProbability(probability);
	}

	@Override
	public void addProbability(final int probability)
			throws ProbabilityOutOfBoundException
	{
		_probabilityStrategy.addProbability(probability);
	}

	@Override
	public void removeProbability(final int probability)
			throws ProbabilityOutOfBoundException
	{
		_probabilityStrategy.removeProbability(probability);
	}
}
