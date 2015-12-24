package com.ankamagames.dofus.harvey.numeric.floats.interfaces;

import com.ankamagames.dofus.harvey.engine.common.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

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