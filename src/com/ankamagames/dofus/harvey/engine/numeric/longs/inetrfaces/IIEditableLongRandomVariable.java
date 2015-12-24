package com.ankamagames.dofus.harvey.engine.numeric.longs.inetrfaces;
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
public interface IIEditableLongRandomVariable
extends IIEditableRandomVariable
{
	boolean setProbabilityOf(long value, int probability);
	boolean remove(long value);
	boolean addProbabilityTo(long value, int delta);
	boolean removeProbabilityTo(long value, int delta);
}