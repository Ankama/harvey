package com.ankamagames.dofus.harvey.generic.interfaces;

import com.ankamagames.dofus.harvey.engine.common.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.engine.exceptions.MultipleValuesException;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public interface IGenericRandomVariable<Data>
extends IRandomVariable
{
	int getProbabilityOf(@Nullable Data value);
	boolean contains(@Nullable Data value);
	boolean containsOnly(@Nullable Data value);
	@Nullable Data getOnlyValue() throws MultipleValuesException;
}