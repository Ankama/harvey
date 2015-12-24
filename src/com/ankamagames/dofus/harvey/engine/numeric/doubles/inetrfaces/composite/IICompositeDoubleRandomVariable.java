package com.ankamagames.dofus.harvey.engine.numeric.doubles.inetrfaces.composite;

import com.ankamagames.dofus.harvey.numeric.doubles.interfaces.IDoubleRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IICompositeDoubleRandomVariable
{
	int getProbabilityOf(IDoubleRandomVariable value);
	boolean contains(IDoubleRandomVariable value);
}
