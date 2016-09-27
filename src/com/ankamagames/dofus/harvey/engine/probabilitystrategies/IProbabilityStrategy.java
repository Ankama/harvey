package com.ankamagames.dofus.harvey.engine.probabilitystrategies;

import com.ankamagames.dofus.harvey.RandomVariableUtils;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IProbabilityStrategy
{
	/**
	 * The probability is given as an int representing a fixed point decimal (to avoid precision
	 * loss). To get it as a float, use {@link RandomVariableUtils.convertToFloat}
	 *
	 * @return
	 */
	int getProbability();
}