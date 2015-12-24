package com.ankamagames.dofus.harvey.engine.numeric.bytes.inetrfaces;
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
public interface IIEditableByteRandomVariable
extends IIEditableRandomVariable
{
	boolean setProbabilityOf(byte value, int probability);
	boolean remove(byte value);
	boolean addProbabilityTo(byte value, int delta);
	boolean removeProbabilityTo(byte value, int delta);
}