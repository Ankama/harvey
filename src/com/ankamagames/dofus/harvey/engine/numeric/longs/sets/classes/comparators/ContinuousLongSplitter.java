/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.comparators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.ContinuousLongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.DegenerateContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongBound;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleContinuousLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class ContinuousLongSplitter
implements Splitter<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval>
{
	private static ContinuousLongSplitter _instance = new ContinuousLongSplitter();

	public static ContinuousLongSplitter getInstance()
	{
		return _instance ;
	}

	private ContinuousLongSplitter()
	{}

	@Override
	public IContinuousLongInterval[] split(final IContinuousLongInterval source)
	{
		final IContinuousLongBound lowerBoundObject = source.getLowerBound();
		final IContinuousLongBound upperBoundObject = source.getUpperBound();
		final long diff;
		final long tmpLowerbound;
		final long tmpUpperbound;
		if (lowerBoundObject == null || upperBoundObject == null || ((diff = (long) ((tmpUpperbound = (upperBoundObject.getValue())) - (tmpLowerbound = lowerBoundObject.getValue()))) <= 0))
			return new IContinuousLongInterval[]{};
		final long lowerbound = tmpLowerbound;
		final long upperbound = tmpUpperbound;

		if(diff==1)
		{
			return new IContinuousLongInterval[]{ContinuousLongInterval.makeInterval(tmpLowerbound, tmpUpperbound), DegenerateContinuousLongSet.makeSet(tmpUpperbound)};
		}
		final long splitBound = (long) ((lowerbound+upperbound)/2);
		return new IContinuousLongInterval[]{ContinuousLongInterval.makeInterval(tmpLowerbound, splitBound),
				ContinuousLongInterval.makeInterval(splitBound, tmpUpperbound)};
	}

	@Override
	public List<? extends IContinuousLongSet> split(final IContinuousLongSet set, int  parts)
	{

		if(parts<=0 )
			return Arrays.asList(set);
		final double size = set.size();
		if(parts > size)
			parts = (int) size;
		final long chunk = (long) (size/(parts-1));
		final long[] ret = new long[parts];
		int index = 0;
		final Iterator<IContinuousLongBound> it = set.getBoundIterator();
		IContinuousLongBound currentLowerBound = it.next();
		IContinuousLongBound currentUpperBound = it.next();
		long next = currentLowerBound.getValue();
		double currentChunckProgress = chunk-1;// because next is already the first value
		double dist = currentUpperBound.compareToContinuous(currentLowerBound);
		while(index<parts)
		{
			if(currentChunckProgress <= dist)
			{
				dist-= currentChunckProgress;
				next = (long) (next + currentChunckProgress);
				currentChunckProgress = chunk;
				ret[index++] = next;
			}
			else
			{
				currentChunckProgress -= dist;
				currentLowerBound = it.next();
				currentUpperBound = it.next();
				next = currentLowerBound.getValue();
				dist = currentUpperBound.compareToContinuous(currentLowerBound)+1;
			}
		}
		return set.split(ret);
	}
}
