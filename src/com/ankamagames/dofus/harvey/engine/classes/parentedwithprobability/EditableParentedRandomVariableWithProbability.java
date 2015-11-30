/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.parentedwithprobability;

import com.ankamagames.dofus.harvey.engine.exceptions.ProbabilityOutOfBoundException;
import com.ankamagames.dofus.harvey.engine.inetrfaces.parentedwithprobability.IEditableParentedRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IModifiableProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.parenting.IParentingRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class EditableParentedRandomVariableWithProbability
<
	Data,
	ParentType extends IParentingRandomVariable<Data, ?>,
	ChildType extends IRandomVariable<Data>,
	ProbabilityStrategy extends IModifiableProbabilityStrategy
>
extends
ParentedRandomVariableWithProbability<Data, ParentType, ChildType, ProbabilityStrategy>
implements
IEditableParentedRandomVariableWithProbabilityStrategy<Data, ParentType, ProbabilityStrategy>
{
	public EditableParentedRandomVariableWithProbability(final ParentType parent,
			final ChildType heldRandomVariable,
			final ProbabilityStrategy probabilityStrategy)
	{
		super(parent, heldRandomVariable, probabilityStrategy);
	}

	@Override
	public void setParent(@Nullable final ParentType parent)
	{
		_parent = parent;
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