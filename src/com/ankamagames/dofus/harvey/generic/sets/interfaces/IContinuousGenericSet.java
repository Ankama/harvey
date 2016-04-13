/**
 * 
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparator;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IContinuousGenericSet<Data>
extends IContinuousSet<IContinuousGenericSet<Data>>
{
	ContinuousComparator<? super Data> getComparator();
	@Nullable Data getLowerBound();
	@Nullable Data getUpperBound();
	boolean contains(@Nullable Data value);
	/**
	 * Splits the set in values.length+1 parts, some empty if needed.
	 * 
	 * @param values the values at which the set has to be split.
	 * @param isIntervalStart indicates if the value stored at the same index will be the start of a new chunk or the end of the current
	 * @return values.length+1 chunks
	 */
	List<? extends IContinuousGenericSet<Data>> split(Data[] values, boolean[] isIntervalStart);
	List<? extends IContinuousGenericSet<Data>> split(Data... values);
	@Override
	ICompositeContinuousGenericSet<Data, ?> getMergedSet();
}