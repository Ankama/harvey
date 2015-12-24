package com.ankamagames.dofus.harvey.numeric.integers.interfaces;

import com.ankamagames.dofus.harvey.engine.common.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.engine.exceptions.MultipleValuesException;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IIntRandomVariable
extends IRandomVariable
{
	int getProbabilityOf(int value);
	boolean contains(int value);
	boolean containsOnly(int value);
	int getOnlyValue() throws MultipleValuesException;
}