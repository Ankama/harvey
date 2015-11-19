package com.ankamagames.dofus.harvey.engine.base.interfaces;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IHasProbabilityStrategy<ProbabilityStrategy extends IProbabilityStrategy>
{
	ProbabilityStrategy getProbabilityStrategy();
}
