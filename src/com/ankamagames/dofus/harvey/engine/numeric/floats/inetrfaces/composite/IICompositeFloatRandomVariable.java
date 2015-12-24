package com.ankamagames.dofus.harvey.engine.numeric.floats.inetrfaces.composite;

import com.ankamagames.dofus.harvey.numeric.floats.interfaces.IFloatRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IICompositeFloatRandomVariable
{
	int getProbabilityOf(IFloatRandomVariable value);
	boolean contains(IFloatRandomVariable value);
}
