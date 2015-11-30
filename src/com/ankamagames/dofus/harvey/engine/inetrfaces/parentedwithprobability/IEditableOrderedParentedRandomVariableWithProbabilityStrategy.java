/**
 *
 */
package com.ankamagames.dofus.harvey.engine.inetrfaces.parentedwithprobability;

import com.ankamagames.dofus.harvey.engine.inetrfaces.withprobability.IEditableOrderedRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IModifiableProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.parented.IEditableOrderedParentedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.parenting.IParentingRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableOrderedParentedRandomVariableWithProbabilityStrategy
<
	Data,
	ParentType extends IParentingRandomVariable<Data, ?>,
	ProbabilityStrategy extends IModifiableProbabilityStrategy
>
extends
IEditableOrderedParentedRandomVariable<Data, ParentType>,
IEditableOrderedRandomVariableWithProbabilityStrategy<Data, ProbabilityStrategy>
{}