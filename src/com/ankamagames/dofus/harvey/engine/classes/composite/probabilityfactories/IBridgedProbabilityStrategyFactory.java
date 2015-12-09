/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite.probabilityfactories;

import com.ankamagames.dofus.harvey.engine.classes.composite.IBridgedEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IBridgedProbabilityStrategyFactory<Bridged extends IRandomVariable<?>, ProbabilityStrategy extends IBridgedEditableProbabilityStrategy<? super Bridged>>
{
	ProbabilityStrategy getNewProbabilityStrategy(int probability);
}