/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.comparators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.ContinuousByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.DegenerateContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleContinuousByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class ContinuousByteSplitter
implements Splitter<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IContinuousByteInterval>
{
	private static ContinuousByteSplitter _instance = new ContinuousByteSplitter();

	public static ContinuousByteSplitter getInstance()
	{
		return _instance ;
	}

	private ContinuousByteSplitter()
	{}

	@Override
	public IContinuousByteInterval[] split(final IContinuousByteInterval source)
	{
		final IContinuousByteBound lowerBoundObject = source.getLowerBound();
		final IContinuousByteBound upperBoundObject = source.getUpperBound();
		final byte diff;
		final byte tmpLowerbound;
		final byte tmpUpperbound;
		if (lowerBoundObject == null || upperBoundObject == null || ((diff = (byte) ((tmpUpperbound = (upperBoundObject.getValue())) - (tmpLowerbound = lowerBoundObject.getValue()))) <= 0))
			return new IContinuousByteInterval[]{};
		final byte lowerbound = tmpLowerbound;
		final byte upperbound = tmpUpperbound;

		if(diff==1)
		{
			return new IContinuousByteInterval[]{ContinuousByteInterval.makeInterval(tmpLowerbound, tmpUpperbound), DegenerateContinuousByteSet.makeSet(tmpUpperbound)};
		}
		final byte splitBound = (byte) ((lowerbound+upperbound)/2);
		return new IContinuousByteInterval[]{ContinuousByteInterval.makeInterval(tmpLowerbound, splitBound),
				ContinuousByteInterval.makeInterval(splitBound, tmpUpperbound)};
	}

	@Override
	public List<? extends IContinuousByteSet> split(final IContinuousByteSet set, int  parts)
	{

		if(parts<=0 )
			return Arrays.asList(set);
		final double size = set.size();
		if(parts > size)
			parts = (int) size;
		final byte chunk = (byte) (size/(parts-1));
		final byte[] ret = new byte[parts];
		int index = 0;
		final Iterator<IContinuousByteBound> it = set.getBoundIterator();
		IContinuousByteBound currentLowerBound = it.next();
		IContinuousByteBound currentUpperBound = it.next();
		byte next = currentLowerBound.getValue();
		double currentChunckProgress = chunk-1;// because next is already the first value
		double dist = currentUpperBound.compareToContinuous(currentLowerBound);
		while(index<parts)
		{
			if(currentChunckProgress <= dist)
			{
				dist-= currentChunckProgress;
				next = (byte) (next + currentChunckProgress);
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
