/**
 *
 */
package com.ankamagames.dofus.harvey.engine.probabilitystrategies;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIEditableProbabilityStrategy
{
	void setProbability(int probability);
	void addProbability(int probability);
	void removeProbability(int probability);
}