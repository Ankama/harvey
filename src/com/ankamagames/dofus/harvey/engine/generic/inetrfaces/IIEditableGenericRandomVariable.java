package com.ankamagames.dofus.harvey.engine.generic.inetrfaces;
/**
 *
 */


import com.ankamagames.dofus.harvey.engine.common.interfaces.IIEditableBasicCollection;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIEditableGenericRandomVariable<Data>
extends IIEditableBasicCollection
{
	boolean containsOnly(@Nullable Data value);
	boolean setProbabilityOf(@Nullable Data value, int probability);
	boolean remove(@Nullable Data value);
	boolean add(@Nullable Data value, int probability);
	boolean addProbabilityTo(@Nullable Data value, int delta);
	boolean removeProbabilityTo(@Nullable Data value, int delta);
}