package com.ankamagames.dofus.harvey.numeric.bytes.interfaces;

import com.ankamagames.dofus.harvey.engine.common.interfaces.IBasicCollection;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IByteRandomVariable
extends IBasicCollection
{
	int getProbabilityOf(byte value);
	boolean contains(byte value);
}