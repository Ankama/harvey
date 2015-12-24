package com.ankamagames.dofus.harvey.numeric.bytes.interfaces;

import com.ankamagames.dofus.harvey.engine.common.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.engine.exceptions.MultipleValuesException;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IByteRandomVariable
extends IRandomVariable
{
	int getProbabilityOf(byte value);
	boolean contains(byte value);
	boolean containsOnly(byte value);
	byte getOnlyValue() throws MultipleValuesException;
}