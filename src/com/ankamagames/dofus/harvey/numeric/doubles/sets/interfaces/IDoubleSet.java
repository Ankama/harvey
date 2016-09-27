/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.interfaces.IDoubleBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDoubleSet
extends IContinuousSet<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet>
{
	@Override
	@Nullable IDoubleBound getLowerBound();
	@Override
	@Nullable IDoubleBound getUpperBound();
	boolean contains(double value);
	/**
	 * Splits the set in values.length+1 parts, some empty if needed.
	 *
	 * @param values the values at which the set has to be split.
	 * @param isIntervalStart indicates if the value stored at the same index will be the start of a new chunk or the end of the current
	 * @return values.length+1 chunks
	 */
	List<? extends IDoubleSet> split(double[] values, boolean[] isIntervalStart);
	List<? extends IDoubleSet> split(double... values);
	@Override
	ISimpleDoubleSet getSimpleSet();

	@Override
	IDoubleSet unite(IDoubleSet set);
	@Override
	IDoubleSet intersect(IDoubleSet set);
}