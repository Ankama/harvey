package com.ankamagames.dofus.harvey.engine.numeric.doubles.inetrfaces;
/**
 *
 */


import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IIEditableRandomVariable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIEditableDoubleRandomVariable
extends IIEditableRandomVariable
{
	boolean setProbabilityOf(double value, int probability);
	boolean remove(double value);
	boolean addProbabilityTo(double value, int delta);
	boolean removeProbabilityTo(double value, int delta);
}