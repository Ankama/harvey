package com.ankamagames.dofus.harvey.engine.generic.inetrfaces.composite;

import com.ankamagames.dofus.harvey.generic.interfaces.IGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IICompositeGenericRandomVariable<Data>
{
	int getProbabilityOf(IGenericRandomVariable<Data> value);
	boolean contains(IGenericRandomVariable<Data> value);
}
