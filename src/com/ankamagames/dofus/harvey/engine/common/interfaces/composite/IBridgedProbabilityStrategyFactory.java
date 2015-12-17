/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.interfaces.composite;

import com.ankamagames.dofus.harvey.engine.common.interfaces.IBasicCollection;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IBridgedProbabilityStrategyFactory
<
	Bridged extends IBasicCollection,
	ProbabilityStrategy extends IBridgedEditableProbabilityStrategy<? super Bridged>
>
{
	ProbabilityStrategy getNewProbabilityStrategy(int probability);
}