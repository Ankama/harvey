package com.ankamagames.dofus.harvey.engine.base.interfaces;

import com.ankamagames.dofus.harvey.engine.editors.IRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.mergestrategies.IMergeStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IMergeableRandomVariableWithEditorAndMergeStrategy<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>, Editor extends IRandomVariableEditor<Data, ParentType>, MergeStrategy extends IMergeStrategy<Data, ParentType>>
	extends IMergeableRandomVariableWithMergeStrategy<Data, ParentType, MergeStrategy>, IEditableRandomVariableWithEditor<Data, ParentType, Editor>
{}