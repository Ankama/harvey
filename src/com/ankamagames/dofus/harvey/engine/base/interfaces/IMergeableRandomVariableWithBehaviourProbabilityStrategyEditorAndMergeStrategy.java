package com.ankamagames.dofus.harvey.engine.base.interfaces;

import com.ankamagames.dofus.harvey.engine.behaviours.IRandomVariableBehaviour;
import com.ankamagames.dofus.harvey.engine.editors.IRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.mergestrategies.IMergeStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IMergeableRandomVariableWithBehaviourProbabilityStrategyEditorAndMergeStrategy
<
	Data,
	ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>,
	Behaviour extends IRandomVariableBehaviour<Data>,
	ProbabilityStrategy extends IProbabilityStrategy,
	Editor extends IRandomVariableEditor<Data, ParentType>,
	MergeStrategy extends IMergeStrategy<Data, ParentType>
>
	extends
	IMergeableRandomVariableWithProbabilityStrategyEditorAndMergeStrategy<Data, ParentType, ProbabilityStrategy, Editor, MergeStrategy>,
	IEditableRandomVariableWithBehaviourProbabilityStrategyAndEditor<Data, ParentType, Behaviour, ProbabilityStrategy, Editor>
{}
