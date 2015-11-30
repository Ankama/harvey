/**
 *
 */
package com.ankamagames.dofus.harvey.engine.inetrfaces.withprobability;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IHasProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.withprobability.IOrderedRandomVariableWithProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IOrderedRandomVariableWithProbabilityStrategy<Data, ProbabilityStrategy extends IProbabilityStrategy>
	extends IOrderedRandomVariableWithProbability<Data>,
	IHasProbabilityStrategy<ProbabilityStrategy>
{}