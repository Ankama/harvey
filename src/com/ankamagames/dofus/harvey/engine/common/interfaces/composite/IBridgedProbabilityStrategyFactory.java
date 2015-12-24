/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.interfaces.composite;

import com.ankamagames.dofus.harvey.engine.common.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

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