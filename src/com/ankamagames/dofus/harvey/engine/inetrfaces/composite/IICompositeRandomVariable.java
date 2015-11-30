package com.ankamagames.dofus.harvey.engine.inetrfaces.composite;

import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public interface IICompositeRandomVariable<Data>
{
	int getProbability(@Nullable IRandomVariable<Data> context);
	int getProbabilityOf(@Nullable Data value, @Nullable IRandomVariable<Data> context);
}