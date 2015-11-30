/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.parentedwithprobability;

import com.ankamagames.dofus.harvey.engine.exceptions.ProbabilityOutOfBoundException;
import com.ankamagames.dofus.harvey.engine.inetrfaces.withprobability.IEditableOrderedRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IModifiableProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.parenting.IParentingRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class EditableOrderedParentedRandomVariableWithProbability
<
	Data,
	ParentType extends IParentingRandomVariable<Data, ?>,
	ChildType extends IOrderedRandomVariable<Data>,
	ProbabilityStrategy extends IModifiableProbabilityStrategy
>
extends
OrderedParentedRandomVariableWithProbability<Data, ParentType, ChildType, ProbabilityStrategy>
implements
IEditableOrderedRandomVariableWithProbabilityStrategy<Data, ProbabilityStrategy>
{
	public EditableOrderedParentedRandomVariableWithProbability(
			final ParentType parent, final ChildType heldRandomVariable,
			final ProbabilityStrategy probabilityStrategy)
	{
		super(parent, heldRandomVariable, probabilityStrategy);
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