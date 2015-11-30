/**
 *
 */
package com.ankamagames.dofus.harvey.withprobability;

import com.ankamagames.dofus.harvey.engine.exceptions.ProbabilityOutOfBoundException;
import com.ankamagames.dofus.harvey.engine.inetrfaces.withprobability.IEditableOrderedRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IModifiableProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class EditableOrderedRandomVariableWithProbability
<
	Data,
	ChildType extends IOrderedRandomVariable<Data>,
	ProbabilityStrategy extends IModifiableProbabilityStrategy
>
extends OrderedRandomVariableWithProbability<Data, ChildType, ProbabilityStrategy>
implements IEditableOrderedRandomVariableWithProbabilityStrategy<Data, ProbabilityStrategy>
{
	public EditableOrderedRandomVariableWithProbability(
			final ChildType heldRandomVariable,
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
