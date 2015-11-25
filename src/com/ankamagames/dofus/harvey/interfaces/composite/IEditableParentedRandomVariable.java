package com.ankamagames.dofus.harvey.interfaces.composite;

import com.ankamagames.dofus.harvey.engine.inetrfaces.composite.IIEditableParentedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IEditableParentedRandomVariable<Data, ParentType extends IRandomVariable<Data>>
extends IEditableRandomVariable<Data>,
IParentedRandomVariable<Data, ParentType>,
IIEditableParentedRandomVariable<Data, ParentType>
{}