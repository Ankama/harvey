/**
 *
 */
package com.ankamagames.dofus.harvey.engine.inetrfaces.parentedwithprobability;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IHasProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.parentedwithprobability.IOrderedParentedRandomVariableWithProbability;
import com.ankamagames.dofus.harvey.interfaces.parenting.IParentingRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IOrderedParentedRandomVariableWithProbabilityStrategy<Data, ParentType extends IParentingRandomVariable<Data, ?>, ProbabilityStrategy extends IProbabilityStrategy>
extends IOrderedParentedRandomVariableWithProbability<Data, ParentType>,
IHasProbabilityStrategy<ProbabilityStrategy>
{}