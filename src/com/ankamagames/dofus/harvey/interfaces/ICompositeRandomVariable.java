package com.ankamagames.dofus.harvey.interfaces;

import com.ankamagames.dofus.harvey.engine.behaviours.composite.ICompositeBehaviour;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface ICompositeRandomVariable<Data, ParentType extends ICompositeRandomVariable<Data, ?, ?>, ChildType extends IRandomVariable<Data, ?>>
extends IRandomVariable<Data, ParentType>, ICompositeBehaviour<Data, ChildType>
{}