/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IFloatBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IFloatSet
extends IContinuousSet<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet>
{
	@Override
	@Nullable IFloatBound getLowerBound();
	@Override
	@Nullable IFloatBound getUpperBound();
	boolean contains(float value);
	/**
	 * Splits the set in values.length+1 parts, some empty if needed.
	 *
	 * @param values the values at which the set has to be split.
	 * @param isIntervalStart indicates if the value stored at the same index will be the start of a new chunk or the end of the current
	 * @return values.length+1 chunks
	 */
	List<? extends IFloatSet> split(float[] values, boolean[] isIntervalStart);
	List<? extends IFloatSet> split(float... values);
	@Override
	ISimpleFloatSet getSimpleSet();

	@Override
	IFloatSet unite(IFloatSet set);
	@Override
	IFloatSet intersect(IFloatSet set);
}