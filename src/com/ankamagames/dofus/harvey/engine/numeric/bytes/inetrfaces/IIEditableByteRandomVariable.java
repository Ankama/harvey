package com.ankamagames.dofus.harvey.engine.numeric.bytes.inetrfaces;
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
public interface IIEditableByteRandomVariable
extends IIEditableRandomVariable
{
	boolean setProbabilityOf(byte value, int probability);
	boolean remove(byte value);
	boolean addProbabilityTo(byte value, int delta);
	boolean removeProbabilityTo(byte value, int delta);
}