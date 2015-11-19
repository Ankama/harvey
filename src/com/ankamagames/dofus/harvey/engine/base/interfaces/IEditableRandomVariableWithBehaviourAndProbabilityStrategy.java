package com.ankamagames.dofus.harvey.engine.base.interfaces;

import com.ankamagames.dofus.harvey.engine.behaviours.IRandomVariableBehaviour;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IEditableRandomVariableWithBehaviourAndProbabilityStrategy<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>, Behaviour extends IRandomVariableBehaviour<Data>, ProbabilityStrategy extends IProbabilityStrategy>
extends IEditableRandomVariableWithBehaviour<Data, ParentType, Behaviour>, IEditableRandomVariableWithProbabilityStrategy<Data, ParentType, ProbabilityStrategy>
{}
