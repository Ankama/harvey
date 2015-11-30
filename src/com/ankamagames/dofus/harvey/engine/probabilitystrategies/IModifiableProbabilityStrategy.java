package com.ankamagames.dofus.harvey.engine.probabilitystrategies;

import com.ankamagames.dofus.harvey.engine.inetrfaces.withprobability.IIEditableRandomVariableWithProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IModifiableProbabilityStrategy
extends IProbabilityStrategy,
IIEditableRandomVariableWithProbability
{}