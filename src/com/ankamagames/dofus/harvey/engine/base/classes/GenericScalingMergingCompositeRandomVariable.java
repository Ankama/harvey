/**
 *
 */
package com.ankamagames.dofus.harvey.engine.base.classes;

import com.ankamagames.dofus.harvey.engine.base.interfaces.IEditableRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.behaviours.composite.ICompositeBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.dynamicstrategies.ScalingProbability;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IMergeableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class GenericScalingMergingCompositeRandomVariable
<
	Data,
	ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>&Iterable<? extends IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>>,
	ChildType extends IMergeableRandomVariable<Data, ?>,
	Behaviour extends ICompositeBehaviour<Data, ChildType>
>
extends MergingCompositeRandomVariable<Data, ParentType, ChildType, Behaviour, ScalingProbability<Data, ParentType>>
{
	public GenericScalingMergingCompositeRandomVariable(@Nullable final ParentType parent, final Behaviour behaviour, final int probability) throws OverOneProbabilityException
	{
		super(parent, behaviour, new ScalingProbability<Data, ParentType>(probability));
		getProbabilityStrategy().init(this);
	}

	public GenericScalingMergingCompositeRandomVariable(final Behaviour behaviour, final int probability) throws OverOneProbabilityException
	{
		super(behaviour, new ScalingProbability<Data, ParentType>(probability));
		getProbabilityStrategy().init(this);
	}

	public GenericScalingMergingCompositeRandomVariable(@Nullable final ParentType parent, final Behaviour behaviour) throws OverOneProbabilityException
	{
		super(parent, behaviour, new ScalingProbability<Data, ParentType>());
		getProbabilityStrategy().init(this);
	}

	public GenericScalingMergingCompositeRandomVariable(final Behaviour behaviour) throws OverOneProbabilityException
	{
		super(behaviour, new ScalingProbability<Data, ParentType>());
		getProbabilityStrategy().init(this);
	}
}
