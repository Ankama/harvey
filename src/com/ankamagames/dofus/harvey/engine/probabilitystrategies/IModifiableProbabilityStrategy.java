package com.ankamagames.dofus.harvey.engine.probabilitystrategies;

import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IModifiableProbabilityStrategy
	extends IProbabilityStrategy
{
	void setProbability(int probability) throws OverOneProbabilityException;
	void addProbability(int probability) throws OverOneProbabilityException;
}
