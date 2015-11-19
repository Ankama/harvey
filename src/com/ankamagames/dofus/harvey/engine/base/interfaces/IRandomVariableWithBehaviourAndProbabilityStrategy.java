package com.ankamagames.dofus.harvey.engine.base.interfaces;

import com.ankamagames.dofus.harvey.engine.behaviours.IRandomVariableBehaviour;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IRandomVariableWithBehaviourAndProbabilityStrategy<Data, ParentType extends ICompositeRandomVariable<Data, ?, ?>, Behaviour extends IRandomVariableBehaviour<Data>, ProbabilityStrategy extends IProbabilityStrategy>
extends IRandomVariableWithBehaviour<Data, ParentType, Behaviour>, IRandomVariableWithProbabilityStrategy<Data, ParentType, ProbabilityStrategy>
{}
