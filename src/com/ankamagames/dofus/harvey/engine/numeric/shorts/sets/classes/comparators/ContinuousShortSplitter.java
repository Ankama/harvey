/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.comparators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.ContinuousShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.DegenerateContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleContinuousShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class ContinuousShortSplitter
implements Splitter<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval>
{
	private static ContinuousShortSplitter _instance = new ContinuousShortSplitter();

	public static ContinuousShortSplitter getInstance()
	{
		return _instance ;
	}

	private ContinuousShortSplitter()
	{}

	@Override
	public IContinuousShortInterval[] split(final IContinuousShortInterval source)
	{
		final IContinuousShortBound lowerBoundObject = source.getLowerBound();
		final IContinuousShortBound upperBoundObject = source.getUpperBound();
		final short diff;
		final short tmpLowerbound;
		final short tmpUpperbound;
		if (lowerBoundObject == null || upperBoundObject == null || ((diff = (short) ((tmpUpperbound = (upperBoundObject.getValue())) - (tmpLowerbound = lowerBoundObject.getValue()))) <= 0))
			return new IContinuousShortInterval[]{};
		final short lowerbound = tmpLowerbound;
		final short upperbound = tmpUpperbound;

		if(diff==1)
		{
			return new IContinuousShortInterval[]{ContinuousShortInterval.makeInterval(tmpLowerbound, tmpUpperbound), DegenerateContinuousShortSet.makeSet(tmpUpperbound)};
		}
		final short splitBound = (short) ((lowerbound+upperbound)/2);
		return new IContinuousShortInterval[]{ContinuousShortInterval.makeInterval(tmpLowerbound, splitBound),
				ContinuousShortInterval.makeInterval(splitBound, tmpUpperbound)};
	}

	@Override
	public List<? extends IContinuousShortSet> split(final IContinuousShortSet set, int  parts)
	{

		if(parts<=0 )
			return Arrays.asList(set);
		final double size = set.size();
		if(parts > size)
			parts = (int) size;
		final short chunk = (short) (size/(parts-1));
		final short[] ret = new short[parts];
		int index = 0;
		final Iterator<IContinuousShortBound> it = set.getBoundIterator();
		IContinuousShortBound currentLowerBound = it.next();
		IContinuousShortBound currentUpperBound = it.next();
		short next = currentLowerBound.getValue();
		double currentChunckProgress = chunk-1;// because next is already the first value
		double dist = currentUpperBound.compareToContinuous(currentLowerBound);
		while(index<parts)
		{
			if(currentChunckProgress <= dist)
			{
				dist-= currentChunckProgress;
				next = (short) (next + currentChunckProgress);
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
