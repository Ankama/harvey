/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces.composite;

import com.ankamagames.dofus.harvey.engine.inetrfaces.parentedwithprobability.IEditableParentedRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.inetrfaces.withprobability.IEditableRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IModifiableProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.parenting.IEditableParentingRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableCompositeRandomVariable
<
	Data,
	ParentType extends IEditableParentingRandomVariable<Data, ?>,
	ChildType extends IEditableParentedRandomVariableWithProbabilityStrategy<Data, ParentType, ?>,
	ProbabilityStrategy extends IModifiableProbabilityStrategy
>
extends ICompositeRandomVariable<Data, ParentType, ChildType>,
IEditableRandomVariableWithProbabilityStrategy<Data, ProbabilityStrategy>,
IEditableParentingRandomVariable<Data, ChildType>
{}