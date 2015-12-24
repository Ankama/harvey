package com.ankamagames.dofus.harvey.engine.numeric.floats.inetrfaces;
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
public interface IIEditableFloatRandomVariable
extends IIEditableRandomVariable
{
	boolean setProbabilityOf(float value, int probability);
	boolean remove(float value);
	boolean addProbabilityTo(float value, int delta);
	boolean removeProbabilityTo(float value, int delta);
}