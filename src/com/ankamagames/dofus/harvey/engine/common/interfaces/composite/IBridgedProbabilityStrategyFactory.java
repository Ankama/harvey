/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.interfaces.composite;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IRandomVariable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IBridgedProbabilityStrategyFactory
<
	Bridged extends IRandomVariable,
	ProbabilityStrategy extends IBridgedEditableProbabilityStrategy<? super Bridged>
>
{
	ProbabilityStrategy getNewProbabilityStrategy(int probability);
}