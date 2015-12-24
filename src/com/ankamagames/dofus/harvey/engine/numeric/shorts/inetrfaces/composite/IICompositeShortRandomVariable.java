package com.ankamagames.dofus.harvey.engine.numeric.shorts.inetrfaces.composite;

import com.ankamagames.dofus.harvey.numeric.shorts.interfaces.IShortRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IICompositeShortRandomVariable
{
	int getProbabilityOf(IShortRandomVariable value);
	boolean contains(IShortRandomVariable value);
}
