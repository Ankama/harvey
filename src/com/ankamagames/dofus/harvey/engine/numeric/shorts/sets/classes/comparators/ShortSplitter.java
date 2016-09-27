/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.comparators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;
import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.ShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.DegenerateShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class ShortSplitter
implements Splitter<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval>
{
	private static ShortSplitter _instance = new ShortSplitter();

	public static ShortSplitter getInstance()
	{
		return _instance ;
	}

	private ShortSplitter()
	{}

	@Override
	public IShortInterval[] split(final IShortInterval source)
	{
		final IShortBound lowerBoundObject = source.getLowerBound();
		final IShortBound upperBoundObject = source.getUpperBound();
		final short diff;
		final short tmpLowerbound;
		final short tmpUpperbound;
		if (lowerBoundObject == null || upperBoundObject == null || ((diff = (short) ((tmpUpperbound = (upperBoundObject.getValue())) - (tmpLowerbound = lowerBoundObject.getValue()))) <= 0))
			return new IShortInterval[]{};
		final short lowerbound = tmpLowerbound;
		final short upperbound = tmpUpperbound;

		if(diff==1)
		{
			return new IShortInterval[]{ShortInterval.makeInterval(tmpLowerbound, tmpUpperbound), DegenerateShortSet.makeSet(tmpUpperbound)};
		}
		final short splitBound = (short) ((lowerbound+upperbound)/2);
		return new IShortInterval[]{ShortInterval.makeInterval(tmpLowerbound, splitBound),
				ShortInterval.makeInterval(splitBound, tmpUpperbound)};
	}

	@Override
	public List<? extends IShortSet> split(final IShortSet set, int  parts)
	{

		if(parts<=0 )
			return Arrays.asList(set);
		final double size = set.size();
		if(parts > size)
			parts = (int) size;
		final short chunk = (short) (size/(parts-1));
		final short[] ret = new short[parts];
		int index = 0;
		final Iterator<IShortBound> it = set.getBoundIterator();
		IShortBound currentLowerBound = it.next();
		IShortBound currentUpperBound = it.next();
		short next = currentLowerBound.getValue();
		double currentChunckProgress = chunk-1;// because next is already the first value
		double dist = currentUpperBound.compareTo(currentLowerBound);
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
				dist = currentUpperBound.compareTo(currentLowerBound)+1;
			}
		}
		return set.split(ret);
	}
}
