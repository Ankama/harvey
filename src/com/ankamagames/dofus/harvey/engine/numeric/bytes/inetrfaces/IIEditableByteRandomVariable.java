package com.ankamagames.dofus.harvey.engine.numeric.bytes.inetrfaces;
/**
 *
 */


import com.ankamagames.dofus.harvey.engine.common.interfaces.IIEditableBasicCollection;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIEditableByteRandomVariable
extends IIEditableBasicCollection
{
	boolean containsOnly(byte value);
	boolean setProbabilityOf(byte value, int probability);
	boolean remove(byte value);
	boolean add(byte value, int probability);
	boolean addProbabilityTo(byte value, int delta);
	boolean removeProbabilityTo(byte value, int delta);
}