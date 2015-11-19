package com.ankamagames.dofus.harvey.composite;

import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IMergeableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IMergingCompositeRandomVariable<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>, ChildType extends IMergeableRandomVariable<Data, ?>>
	extends IEditableCompositeRandomVariable<Data, ParentType, ChildType>
{

}
