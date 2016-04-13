package com.ankamagames.dofus.harvey.numeric.longs.interfaces;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.engine.exceptions.MultipleValuesException;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface ILongRandomVariable
extends IRandomVariable
{
	int getProbabilityOf(long value);
	boolean contains(long value);
	boolean containsOnly(long value);
	long getOnlyValue() throws MultipleValuesException;
}