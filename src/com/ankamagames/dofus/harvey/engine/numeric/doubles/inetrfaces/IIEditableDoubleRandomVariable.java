package com.ankamagames.dofus.harvey.engine.numeric.doubles.inetrfaces;
/**
 *
 */


import com.ankamagames.dofus.harvey.engine.common.interfaces.IIEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

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