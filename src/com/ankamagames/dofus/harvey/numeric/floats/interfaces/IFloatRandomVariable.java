package com.ankamagames.dofus.harvey.numeric.floats.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IRandomVariable;

@NonNullByDefault
public interface IFloatRandomVariable
extends IRandomVariable
{
	int getProbabilityOf(float value);
	boolean contains(float value);
	boolean containsOnly(float value);
	/**
	 * @return Float.NaN if value is unknown
	 */
	float getOnlyValue();
}