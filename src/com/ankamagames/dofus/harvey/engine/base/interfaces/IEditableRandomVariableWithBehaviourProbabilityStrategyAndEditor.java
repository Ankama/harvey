package com.ankamagames.dofus.harvey.engine.base.interfaces;

import com.ankamagames.dofus.harvey.engine.behaviours.IRandomVariableBehaviour;
import com.ankamagames.dofus.harvey.engine.editors.IRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IEditableRandomVariableWithBehaviourProbabilityStrategyAndEditor
<
	Data,
	ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>,
	Behaviour extends IRandomVariableBehaviour<Data>,
	ProbabilityStrategy extends IProbabilityStrategy,
	Editor extends IRandomVariableEditor<Data, ParentType>
>
extends IEditableRandomVariableWithBehaviourAndProbabilityStrategy<Data, ParentType, Behaviour, ProbabilityStrategy>, IEditableRandomVariableWithEditor<Data, ParentType, Editor>
{}
