/**
 *
 */
package com.ankamagames.dofus.harvey.engine.inetrfaces.withprobability;

import com.ankamagames.dofus.harvey.engine.exceptions.ProbabilityOutOfBoundException;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIEditableRandomVariableWithProbability
{
	void setProbability(int probability) throws ProbabilityOutOfBoundException;
	void addProbability(int probability) throws ProbabilityOutOfBoundException;
	void removeProbability(int probability) throws ProbabilityOutOfBoundException;
}