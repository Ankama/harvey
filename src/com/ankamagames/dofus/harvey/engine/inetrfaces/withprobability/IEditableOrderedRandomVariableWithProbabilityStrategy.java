/**
 *
 */
package com.ankamagames.dofus.harvey.engine.inetrfaces.withprobability;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IModifiableProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableOrderedRandomVariableWithProbabilityStrategy<Data, ProbabilityStrategy extends IModifiableProbabilityStrategy>
	extends IEditableRandomVariableWithProbabilityStrategy<Data, ProbabilityStrategy>,
	IOrderedRandomVariableWithProbabilityStrategy<Data, ProbabilityStrategy>
{}