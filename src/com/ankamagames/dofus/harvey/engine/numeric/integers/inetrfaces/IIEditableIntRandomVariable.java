package com.ankamagames.dofus.harvey.engine.numeric.integers.inetrfaces;
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
public interface IIEditableIntRandomVariable
extends IIEditableRandomVariable
{
	boolean setProbabilityOf(int value, int probability);
	boolean remove(int value);
	boolean addProbabilityTo(int value, int delta);
	boolean removeProbabilityTo(int value, int delta);
}