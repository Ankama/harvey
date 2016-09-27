/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.comparators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.ContinuousIntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.DegenerateContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerBound;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleContinuousIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class ContinuousIntegerSplitter
implements Splitter<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, IContinuousIntegerInterval>
{
	private static ContinuousIntegerSplitter _instance = new ContinuousIntegerSplitter();

	public static ContinuousIntegerSplitter getInstance()
	{
		return _instance ;
	}

	private ContinuousIntegerSplitter()
	{}

	@Override
	public IContinuousIntegerInterval[] split(final IContinuousIntegerInterval source)
	{
		final IContinuousIntegerBound lowerBoundObject = source.getLowerBound();
		final IContinuousIntegerBound upperBoundObject = source.getUpperBound();
		final int diff;
		final int tmpLowerbound;
		final int tmpUpperbound;
		if (lowerBoundObject == null || upperBoundObject == null || ((diff = (tmpUpperbound = (upperBoundObject.getValue())) - (tmpLowerbound = lowerBoundObject.getValue())) <= 0))
			return new IContinuousIntegerInterval[]{};
		final int lowerbound = tmpLowerbound;
		final int upperbound = tmpUpperbound;

		if(diff==1)
		{
			return new IContinuousIntegerInterval[]{ContinuousIntegerInterval.makeInterval(tmpLowerbound, tmpUpperbound), DegenerateContinuousIntegerSet.makeSet(tmpUpperbound)};
		}
		final int splitBound = (lowerbound+upperbound)/2;
		return new IContinuousIntegerInterval[]{ContinuousIntegerInterval.makeInterval(tmpLowerbound, splitBound),
				ContinuousIntegerInterval.makeInterval(splitBound, tmpUpperbound)};
	}

	@Override
	public List<? extends IContinuousIntegerSet> split(final IContinuousIntegerSet set, int  parts)
	{

		if(parts<=0 )
			return Arrays.asList(set);
		final double size = set.size();
		if(parts > size)
			parts = (int) size;
		final int chunk = (int) (size/(parts-1));
		final int[] ret = new int[parts];
		int index = 0;
		final Iterator<IContinuousIntegerBound> it = set.getBoundIterator();
		IContinuousIntegerBound currentLowerBound = it.next();
		IContinuousIntegerBound currentUpperBound = it.next();
		int next = currentLowerBound.getValue();
		double currentChunckProgress = chunk-1;// because next is already the first value
		double dist = currentUpperBound.compareToContinuous(currentLowerBound);
		while(index<parts)
		{
			if(currentChunckProgress <= dist)
			{
				dist-= currentChunckProgress;
				next = (int) (next + currentChunckProgress);
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
