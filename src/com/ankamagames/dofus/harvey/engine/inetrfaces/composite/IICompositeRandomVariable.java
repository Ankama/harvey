package com.ankamagames.dofus.harvey.engine.inetrfaces.composite;

import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IICompositeRandomVariable<Data>
{
	int getProbabilityOf(IRandomVariable<Data> value);
	boolean contains(IRandomVariable<Data> value);
}
