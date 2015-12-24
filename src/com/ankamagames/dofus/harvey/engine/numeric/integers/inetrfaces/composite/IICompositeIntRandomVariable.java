package com.ankamagames.dofus.harvey.engine.numeric.integers.inetrfaces.composite;

import com.ankamagames.dofus.harvey.numeric.integers.interfaces.IIntRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IICompositeIntRandomVariable
{
	int getProbabilityOf(IIntRandomVariable value);
	boolean contains(IIntRandomVariable value);
}
