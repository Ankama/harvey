/**
 *
 */
package com.ankamagames.dofus.harvey.engine.inetrfaces.withprobability;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IHasProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IModifiableProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.withprobability.IRandomVariableWithProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableRandomVariableWithProbabilityStrategy<Data, ProbabilityStrategy extends IModifiableProbabilityStrategy>
extends IRandomVariableWithProbability<Data>,
IHasProbabilityStrategy<ProbabilityStrategy>,
IIEditableRandomVariableWithProbability
{}