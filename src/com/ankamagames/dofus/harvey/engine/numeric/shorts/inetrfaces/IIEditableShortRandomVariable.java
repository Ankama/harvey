package com.ankamagames.dofus.harvey.engine.numeric.shorts.inetrfaces;
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
public interface IIEditableShortRandomVariable
extends IIEditableRandomVariable
{
	boolean setProbabilityOf(short value, int probability);
	boolean remove(short value);
	boolean addProbabilityTo(short value, int delta);
	boolean removeProbabilityTo(short value, int delta);
}