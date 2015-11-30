package com.ankamagames.dofus.harvey.engine.inetrfaces.parentedwithprobability;

import com.ankamagames.dofus.harvey.engine.inetrfaces.withprobability.IEditableRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IModifiableProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.parented.IEditableParentedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.parentedwithprobability.IParentedRandomVariableWithProbability;
import com.ankamagames.dofus.harvey.interfaces.parenting.IParentingRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IEditableParentedRandomVariableWithProbabilityStrategy
<
	Data,
	ParentType extends IParentingRandomVariable<Data, ?>,
	ProbabilityStrategy extends IModifiableProbabilityStrategy
>
extends IParentedRandomVariableWithProbability<Data, ParentType>,
IEditableParentedRandomVariable<Data, ParentType>,
IEditableRandomVariableWithProbabilityStrategy<Data, ProbabilityStrategy>
{}