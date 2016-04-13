package com.ankamagames.dofus.harvey.numeric.shorts.interfaces;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.engine.exceptions.MultipleValuesException;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IShortRandomVariable
extends IRandomVariable
{
	int getProbabilityOf(short value);
	boolean contains(short value);
	boolean containsOnly(short value);
	short getOnlyValue() throws MultipleValuesException;
}