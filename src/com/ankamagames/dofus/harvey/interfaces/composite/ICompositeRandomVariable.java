package com.ankamagames.dofus.harvey.interfaces.composite;

import com.ankamagames.dofus.harvey.engine.inetrfaces.composite.IICompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface ICompositeRandomVariable<Data, ParentType extends ICompositeRandomVariable<Data, ?, ?>, ChildType extends IParentedRandomVariable<Data, ?>>
extends IParentedRandomVariable<Data, ParentType>,
IICompositeRandomVariable<Data, ChildType>
{}