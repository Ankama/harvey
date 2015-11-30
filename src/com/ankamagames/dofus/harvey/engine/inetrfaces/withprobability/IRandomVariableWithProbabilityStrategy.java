/**
 *
 */
package com.ankamagames.dofus.harvey.engine.inetrfaces.withprobability;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IHasProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.withprobability.IRandomVariableWithProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IRandomVariableWithProbabilityStrategy<Data, ProbabilityStrategy extends IProbabilityStrategy>
extends IRandomVariableWithProbability<Data>,
IHasProbabilityStrategy<ProbabilityStrategy>
{}