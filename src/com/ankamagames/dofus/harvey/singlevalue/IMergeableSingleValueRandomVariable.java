package com.ankamagames.dofus.harvey.singlevalue;

import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IEditableSingleValueRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IMergeableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IMergeableSingleValueRandomVariable<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>>
	extends IEditableSingleValueRandomVariable<Data, ParentType>,
	IMergeableRandomVariable<Data, ParentType>
{}
