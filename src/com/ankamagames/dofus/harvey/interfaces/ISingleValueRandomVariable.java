package com.ankamagames.dofus.harvey.interfaces;

import com.ankamagames.dofus.harvey.engine.behaviours.singlevalue.ISingleValueBehaviour;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface ISingleValueRandomVariable<Data, ParentType extends ICompositeRandomVariable<Data, ?, ?>>
extends IRandomVariable<Data, ParentType>, ISingleValueBehaviour<Data>
{}