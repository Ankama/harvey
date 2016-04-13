package com.ankamagames.dofus.harvey.numeric.doubles.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IRandomVariable;

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