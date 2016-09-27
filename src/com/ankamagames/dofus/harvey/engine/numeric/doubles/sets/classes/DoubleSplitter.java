/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.interfaces.IDoubleBound;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.classes.DegenerateDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.classes.DoubleInterval;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IElementaryDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleInterval;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.ISimpleDoubleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class DoubleSplitter
implements Splitter<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval> {

	private static DoubleSplitter _instance = new DoubleSplitter();

	public static DoubleSplitter getInstance()
	{
		return _instance ;
	}

	private DoubleSplitter()
	{}

	@Override
	public IDoubleInterval[] split(final IDoubleInterval source)
	{
		final IDoubleBound lowerBoundObject = source.getLowerBound();
		final IDoubleBound upperBoundObject = source.getUpperBound();
		final double diff;
		final double tmpLowerbound;
		final double tmpUpperbound;
		if (lowerBoundObject == null || upperBoundObject == null || ((diff = (tmpUpperbound = (upperBoundObject.getValue())) - (tmpLowerbound = lowerBoundObject.getValue())) <= 0))
			return new IDoubleInterval[]{};
		final double lowerbound = tmpLowerbound;
		final double upperbound = tmpUpperbound;

		if(diff==1)
		{
			return new IDoubleInterval[]{DoubleInterval.makeInterval(tmpLowerbound, true, tmpUpperbound, false), DegenerateDoubleSet.makeSet(tmpUpperbound)};
		}
		final double splitBound = (lowerbound+upperbound)/2;
		return new IDoubleInterval[]{DoubleInterval.makeInterval(tmpLowerbound, true, splitBound, false),
				DoubleInterval.makeInterval(splitBound, false, tmpUpperbound, true)};
	}

	@Override
	public List<? extends IDoubleSet> split(final IDoubleSet set, int  parts)
	{

		if(parts<=0 )
			return Arrays.asList(set);
		final double size = set.size();
		if(parts > size)
			parts = (int) size;
		final double chunk = (double) (size/(parts-1));
		final double[] ret = new double[parts];
		int index = 0;
		final Iterator<IDoubleBound> it = set.getBoundIterator();
		IDoubleBound currentLowerBound = it.next();
		IDoubleBound currentUpperBound = it.next();
		double next = currentLowerBound.getValue();
		double currentChunckProgress = chunk-1;// because next is already the first value
		double dist = currentUpperBound.compareToContinuous(currentLowerBound);
		while(index<parts)
		{
			if(currentChunckProgress <= dist)
			{
				dist-= currentChunckProgress;
				next = (double) (next + currentChunckProgress);
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
