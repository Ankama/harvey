package com.ankamagames.dofus.harvey.engine.base.interfaces;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IRandomVariableWithProbabilityStrategy<Data, ParentType extends ICompositeRandomVariable<Data, ?, ?>, ProbabilityStrategy extends IProbabilityStrategy>
extends IRandomVariable<Data, ParentType>, IHasProbabilityStrategy<ProbabilityStrategy>
{}