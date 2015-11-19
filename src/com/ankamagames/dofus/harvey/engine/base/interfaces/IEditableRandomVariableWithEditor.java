package com.ankamagames.dofus.harvey.engine.base.interfaces;

import com.ankamagames.dofus.harvey.engine.editors.AbstractRandomVariableEditor.EditorToken;
import com.ankamagames.dofus.harvey.engine.editors.IRandomVariableEditor;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public interface IEditableRandomVariableWithEditor<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>, Editor extends IRandomVariableEditor<Data, ParentType>>
	extends IEditableRandomVariable<Data, ParentType>, IHasEditor<Data, ParentType, Editor>
{
	public void setParent(@Nullable ParentType parent, EditorToken token);
}
