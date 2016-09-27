/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IFloatBound;
import com.ankamagames.dofus.harvey.numeric.floats.sets.classes.DegenerateFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.classes.FloatInterval;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IElementaryFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatInterval;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.ISimpleFloatSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class FloatSplitter
implements Splitter<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval> {

	private static FloatSplitter _instance = new FloatSplitter();

	public static FloatSplitter getInstance()
	{
		return _instance ;
	}

	private FloatSplitter()
	{}

	@Override
	public IFloatInterval[] split(final IFloatInterval source)
	{
		final IFloatBound lowerBoundObject = source.getLowerBound();
		final IFloatBound upperBoundObject = source.getUpperBound();
		final float diff;
		final float tmpLowerbound;
		final float tmpUpperbound;
		if (lowerBoundObject == null || upperBoundObject == null || ((diff = (tmpUpperbound = (upperBoundObject.getValue())) - (tmpLowerbound = lowerBoundObject.getValue())) <= 0))
			return new IFloatInterval[]{};
		final float lowerbound = tmpLowerbound;
		final float upperbound = tmpUpperbound;

		if(diff==1)
		{
			return new IFloatInterval[]{FloatInterval.makeInterval(tmpLowerbound, true, tmpUpperbound, false), DegenerateFloatSet.makeSet(tmpUpperbound)};
		}
		final float splitBound = (lowerbound+upperbound)/2;
		return new IFloatInterval[]{FloatInterval.makeInterval(tmpLowerbound, true, splitBound, false),
				FloatInterval.makeInterval(splitBound, false, tmpUpperbound, true)};
	}

	@Override
	public List<? extends IFloatSet> split(final IFloatSet set, int  parts)
	{

		if(parts<=0 )
			return Arrays.asList(set);
		final double size = set.size();
		if(parts > size)
			parts = (int) size;
		final float chunk = (float) (size/(parts-1));
		final float[] ret = new float[parts];
		int index = 0;
		final Iterator<IFloatBound> it = set.getBoundIterator();
		IFloatBound currentLowerBound = it.next();
		IFloatBound currentUpperBound = it.next();
		float next = currentLowerBound.getValue();
		double currentChunckProgress = chunk-1;// because next is already the first value
		double dist = currentUpperBound.compareToContinuous(currentLowerBound);
		while(index<parts)
		{
			if(currentChunckProgress <= dist)
			{
				dist-= currentChunckProgress;
				next = (float) (next + currentChunckProgress);
				currentChunckProgress = chunk;
				ret[index++] = next;
			}
			else
			{
				currentChunckProgress -= dist;
				currentLowerBound = it.next();
				currentUpperBound = it.next();
				next = currentLowerBound.getValue();
				dist = currentUpperBound.compareTo(currentLowerBound)+1;
			}
		}
		return set.split(ret);
	}

}
