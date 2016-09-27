/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.comparators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.DegenerateIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.IntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerBound;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class IntegerSplitter
implements Splitter<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval>
{
	private static IntegerSplitter _instance = new IntegerSplitter();

	public static IntegerSplitter getInstance()
	{
		return _instance ;
	}

	private IntegerSplitter()
	{}

	@Override
	public IIntegerInterval[] split(final IIntegerInterval source)
	{
		final IIntegerBound lowerBoundObject = source.getLowerBound();
		final IIntegerBound upperBoundObject = source.getUpperBound();
		final int diff;
		final int tmpLowerbound;
		final int tmpUpperbound;
		if (lowerBoundObject == null || upperBoundObject == null || ((diff = (int) ((tmpUpperbound = (upperBoundObject.getValue())) - (tmpLowerbound = lowerBoundObject.getValue()))) <= 0))
			return new IIntegerInterval[]{};
		final int lowerbound = tmpLowerbound;
		final int upperbound = tmpUpperbound;

		if(diff==1)
		{
			return new IIntegerInterval[]{IntegerInterval.makeInterval(tmpLowerbound, tmpUpperbound), DegenerateIntegerSet.makeSet(tmpUpperbound)};
		}
		final int splitBound = (int) ((lowerbound+upperbound)/2);
		return new IIntegerInterval[]{IntegerInterval.makeInterval(tmpLowerbound, splitBound),
				IntegerInterval.makeInterval(splitBound, tmpUpperbound)};
	}

	@Override
	public List<? extends IIntegerSet> split(final IIntegerSet set, int  parts)
	{

		if(parts<=0 )
			return Arrays.asList(set);
		final double size = set.size();
		if(parts > size)
			parts = (int) size;
		final int chunk = (int) (size/(parts-1));
		final int[] ret = new int[parts];
		int index = 0;
		final Iterator<IIntegerBound> it = set.getBoundIterator();
		IIntegerBound currentLowerBound = it.next();
		IIntegerBound currentUpperBound = it.next();
		int next = currentLowerBound.getValue();
		double currentChunckProgress = chunk-1;// because next is already the first value
		double dist = currentUpperBound.compareTo(currentLowerBound);
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
				dist = currentUpperBound.compareTo(currentLowerBound)+1;
			}
		}
		return set.split(ret);
	}
}
