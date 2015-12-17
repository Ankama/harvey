package com.ankamagames.dofus.harvey.generic.interfaces;

import com.ankamagames.dofus.harvey.engine.common.interfaces.IBasicCollection;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public interface IGenericRandomVariable<Data>
extends IBasicCollection
{
	int getProbabilityOf(@Nullable Data value);
	boolean contains(@Nullable Data value);
}