/**
 *
 */
package com.ankamagames.dofus.harvey.engine.inetrfaces.parentedwithprobability;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IHasProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.parentedwithprobability.IParentedRandomVariableWithProbability;
import com.ankamagames.dofus.harvey.interfaces.parenting.IParentingRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IParentedRandomVariableWithProbabilityStrategy<Data, ParentType extends IParentingRandomVariable<Data, ?>, ProbabilityStrategy extends IProbabilityStrategy>
extends IParentedRandomVariableWithProbability<Data, ParentType>,
IHasProbabilityStrategy<ProbabilityStrategy>
{}