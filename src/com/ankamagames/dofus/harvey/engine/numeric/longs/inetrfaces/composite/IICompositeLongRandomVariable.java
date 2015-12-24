package com.ankamagames.dofus.harvey.engine.numeric.longs.inetrfaces.composite;

import com.ankamagames.dofus.harvey.numeric.longs.interfaces.ILongRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IICompositeLongRandomVariable
{
	int getProbabilityOf(ILongRandomVariable value);
	boolean contains(ILongRandomVariable value);
}
