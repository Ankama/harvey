/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.parentedwithprobability;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.parentedwithprobability.IParentedRandomVariableWithProbability;
import com.ankamagames.dofus.harvey.interfaces.parenting.IParentingRandomVariable;
import com.ankamagames.dofus.harvey.withprobability.RandomVariableWithProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ParentedRandomVariableWithProbability
<
	Data,
	ParentType extends IParentingRandomVariable<Data, ?>,
	ChildType extends IRandomVariable<Data>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends RandomVariableWithProbability<Data, ChildType, ProbabilityStrategy>
implements IParentedRandomVariableWithProbability<Data, ParentType>
{
	protected @Nullable ParentType _parent;

	public ParentedRandomVariableWithProbability(@Nullable final ParentType parent, final ChildType heldRandomVariable,
			final ProbabilityStrategy probabilityStrategy)
	{
		super(heldRandomVariable, probabilityStrategy);
		_parent = parent;
	}

	@Override
	@Nullable
	public ParentType getParent()
	{
		return _parent;
	}
}