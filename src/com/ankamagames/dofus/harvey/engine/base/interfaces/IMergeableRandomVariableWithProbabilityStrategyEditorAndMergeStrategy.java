package com.ankamagames.dofus.harvey.engine.base.interfaces;

import com.ankamagames.dofus.harvey.engine.editors.IRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.mergestrategies.IMergeStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IMergeableRandomVariableWithProbabilityStrategyEditorAndMergeStrategy
<
	Data,
	ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>,
	ProbabilityStrategy extends IProbabilityStrategy,
	Editor extends IRandomVariableEditor<Data, ParentType>,
	MergeStrategy extends IMergeStrategy<Data, ParentType>
>
	extends
	IMergeableRandomVariableWithProbabilityStrategy<Data, ParentType, ProbabilityStrategy>,
	IMergeableRandomVariableWithEditorAndMergeStrategy<Data, ParentType, Editor, MergeStrategy>
{

}
