package com.ankamagames.dofus.harvey.generic.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;

@NonNullByDefault
public interface IGenericRandomVariable<Data, IterableType extends IGenericRandomVariable<Data, ?, ?>, Elements extends IGenericSet<Data>>
extends IRandomVariable<IGenericSet<Data>, IterableType, Elements>
{
	int getProbabilityOf(@Nullable Data value);
	boolean contains(@Nullable Data value);
}