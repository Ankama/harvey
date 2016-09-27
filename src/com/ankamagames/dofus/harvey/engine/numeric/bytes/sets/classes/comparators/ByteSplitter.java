/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.comparators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;
import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.ByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.DegenerateByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class ByteSplitter
implements Splitter<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval>
{
	private static ByteSplitter _instance = new ByteSplitter();

	public static ByteSplitter getInstance()
	{
		return _instance ;
	}

	private ByteSplitter()
	{}

	@Override
	public IByteInterval[] split(final IByteInterval source)
	{
		final IByteBound lowerBoundObject = source.getLowerBound();
		final IByteBound upperBoundObject = source.getUpperBound();
		final byte diff;
		final byte tmpLowerbound;
		final byte tmpUpperbound;
		if (lowerBoundObject == null || upperBoundObject == null || ((diff = (byte) ((tmpUpperbound = (upperBoundObject.getValue())) - (tmpLowerbound = lowerBoundObject.getValue()))) <= 0))
			return new IByteInterval[]{};
		final byte lowerbound = tmpLowerbound;
		final byte upperbound = tmpUpperbound;

		if(diff==1)
		{
			return new IByteInterval[]{ByteInterval.makeInterval(tmpLowerbound, tmpUpperbound), DegenerateByteSet.makeSet(tmpUpperbound)};
		}
		final byte splitBound = (byte) ((lowerbound+upperbound)/2);
		return new IByteInterval[]{ByteInterval.makeInterval(tmpLowerbound, splitBound),
				ByteInterval.makeInterval(splitBound, tmpUpperbound)};
	}

	@Override
	public List<? extends IByteSet> split(final IByteSet set, int  parts)
	{

		if(parts<=0 )
			return Arrays.asList(set);
		final double size = set.size();
		if(parts > size)
			parts = (int) size;
		final byte chunk = (byte) (size/(parts-1));
		final byte[] ret = new byte[parts];
		int index = 0;
		final Iterator<IByteBound> it = set.getBoundIterator();
		IByteBound currentLowerBound = it.next();
		IByteBound currentUpperBound = it.next();
		byte next = currentLowerBound.getValue();
		double currentChunckProgress = chunk-1;// because next is already the first value
		double dist = currentUpperBound.compareTo(currentLowerBound);
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
				dist = currentUpperBound.compareTo(currentLowerBound)+1;
			}
		}
		return set.split(ret);
	}
}
