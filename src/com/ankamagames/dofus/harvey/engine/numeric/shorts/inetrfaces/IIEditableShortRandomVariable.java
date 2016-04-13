package com.ankamagames.dofus.harvey.engine.numeric.shorts.inetrfaces;
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
public interface IIEditableShortRandomVariable
extends IIEditableRandomVariable
{
	boolean setProbabilityOf(short value, int probability);
	boolean remove(short value);
	boolean addProbabilityTo(short value, int delta);
	boolean removeProbabilityTo(short value, int delta);
}