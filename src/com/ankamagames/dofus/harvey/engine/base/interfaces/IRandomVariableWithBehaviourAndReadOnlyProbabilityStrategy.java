package com.ankamagames.dofus.harvey.engine.base.interfaces;

import com.ankamagames.dofus.harvey.engine.behaviours.IRandomVariableBehaviour;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IRandomVariableWithBehaviourAndReadOnlyProbabilityStrategy<Data, ParentType extends ICompositeRandomVariable<Data, ?, ?>, Behaviour extends IRandomVariableBehaviour<Data>>
extends IRandomVariableWithBehaviourAndProbabilityStrategy<Data, ParentType, Behaviour, ReadOnlyProbabilityStrategy>, IRandomVariableWithReadOnlyProbabilityStrategy<Data, ParentType>
{}