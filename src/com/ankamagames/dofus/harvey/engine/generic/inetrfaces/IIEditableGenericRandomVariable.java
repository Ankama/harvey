package com.ankamagames.dofus.harvey.engine.generic.inetrfaces;
/**
 *
 */


import com.ankamagames.dofus.harvey.engine.common.interfaces.IIEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIEditableGenericRandomVariable<Data>
extends IIEditableRandomVariable
{
	boolean setProbabilityOf(@Nullable Data value, int probability);
	boolean remove(@Nullable Data value);
	boolean addProbabilityTo(@Nullable Data value, int delta);
	boolean removeProbabilityTo(@Nullable Data value, int delta);
}