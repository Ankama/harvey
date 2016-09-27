/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.comparators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;
import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.LongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.DegenerateLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongBound;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class LongSplitter
implements Splitter<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval>
{
	private static LongSplitter _instance = new LongSplitter();

	public static LongSplitter getInstance()
	{
		return _instance ;
	}

	private LongSplitter()
	{}

	@Override
	public ILongInterval[] split(final ILongInterval source)
	{
		final ILongBound lowerBoundObject = source.getLowerBound();
		final ILongBound upperBoundObject = source.getUpperBound();
		final long diff;
		final long tmpLowerbound;
		final long tmpUpperbound;
		if (lowerBoundObject == null || upperBoundObject == null || ((diff = (long) ((tmpUpperbound = (upperBoundObject.getValue())) - (tmpLowerbound = lowerBoundObject.getValue()))) <= 0))
			return new ILongInterval[]{};
		final long lowerbound = tmpLowerbound;
		final long upperbound = tmpUpperbound;

		if(diff==1)
		{
			return new ILongInterval[]{LongInterval.makeInterval(tmpLowerbound, tmpUpperbound), DegenerateLongSet.makeSet(tmpUpperbound)};
		}
		final long splitBound = (long) ((lowerbound+upperbound)/2);
		return new ILongInterval[]{LongInterval.makeInterval(tmpLowerbound, splitBound),
				LongInterval.makeInterval(splitBound, tmpUpperbound)};
	}

	@Override
	public List<? extends ILongSet> split(final ILongSet set, int  parts)
	{

		if(parts<=0 )
			return Arrays.asList(set);
		final double size = set.size();
		if(parts > size)
			parts = (int) size;
		final long chunk = (long) (size/(parts-1));
		final long[] ret = new long[parts];
		int index = 0;
		final Iterator<ILongBound> it = set.getBoundIterator();
		ILongBound currentLowerBound = it.next();
		ILongBound currentUpperBound = it.next();
		long next = currentLowerBound.getValue();
		double currentChunckProgress = chunk-1;// because next is already the first value
		double dist = currentUpperBound.compareTo(currentLowerBound);
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
				dist = currentUpperBound.compareTo(currentLowerBound)+1;
			}
		}
		return set.split(ret);
	}
}
