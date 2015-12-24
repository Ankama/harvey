package com.ankamagames.dofus.harvey.numeric.doubles.interfaces;

import com.ankamagames.dofus.harvey.engine.common.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IDoubleRandomVariable
extends IRandomVariable
{
	int getProbabilityOf(double value);
	boolean contains(double value);
	boolean containsOnly(double value);
	/**
	 * @return Double.NaN if value is unknown
	 */
	double getOnlyValue();
}