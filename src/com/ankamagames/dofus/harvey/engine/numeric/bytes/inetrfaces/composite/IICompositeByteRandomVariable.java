package com.ankamagames.dofus.harvey.engine.numeric.bytes.inetrfaces.composite;

import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IICompositeByteRandomVariable
{
	int getProbabilityOf(IByteRandomVariable value);
	boolean contains(IByteRandomVariable value);
}
