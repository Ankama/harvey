package com.ankamagames.dofus.harvey.engine.base.interfaces;

import com.ankamagames.dofus.harvey.engine.editors.IRandomVariableEditor;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IHasEditor<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>, Editor extends IRandomVariableEditor<Data, ParentType>>
{
	Editor getEditor();
}
