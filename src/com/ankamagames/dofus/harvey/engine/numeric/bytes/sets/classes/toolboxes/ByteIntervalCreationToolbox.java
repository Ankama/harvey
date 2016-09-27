/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.comparators.ByteSplitter;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.ByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.ByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.DegenerateByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.EmptyByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IEmptyByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ByteIntervalCreationToolbox<BridgedType extends IByteInterval>
extends ByteSetCreationToolbox<BridgedType>
implements IIntervalCreationToolbox<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval, IEmptyByteSet, BridgedType>
{
	protected final @Nullable Splitter<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval> _splitter;

	public ByteIntervalCreationToolbox(final BridgedType bridged)
	{
		super(bridged);
		_splitter = ByteSplitter.getInstance();
	}

	@Override
	public IByteInterval makeIntervalFromLowerBounds(final IByteSet lowerBoundSet, final IByteSet upperBoundSet)
	{
		final IByteBound lowerBound = lowerBoundSet.getLowerBound();
		final IByteBound upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return ByteInterval.makeInterval(lowerBound.getValue(), upperBound.getValue());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyByteSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public IByteInterval makeIntervalFromUpperBounds(final IByteSet lowerBoundSet, final IByteSet upperBoundSet)
	{
		final IByteBound lowerBound = lowerBoundSet.getUpperBound();
		final IByteBound upperBound = upperBoundSet.getUpperBound();
		if(lowerBound != null && upperBound != null)
			return ByteInterval.makeInterval(lowerBound.getValue(), upperBound.getValue());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyByteSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public IByteInterval makeInvertedInterval(final IByteSet lowerBoundSet, final IByteSet upperBoundSet)
	{
		final IByteBound lowerBound = lowerBoundSet.getUpperBound();
		final IByteBound upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return ByteInterval.makeInterval(lowerBound.getValue(), upperBound.getValue());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyByteSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public ISimpleByteSet makeSimpleCompositeSet(
		final IElementaryByteSet... parts)
	{
		return ByteSet.makeSet(parts);
	}

	@Override
	public List<? extends IElementaryByteSet> split(final int numberOfParts)
	{
		final Splitter<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval> splitter = _splitter;
		if(splitter!=null)
		{
			return Arrays.asList(splitter.split(_bridged));
		}
		final IByteBound lowerBound = _bridged.getLowerBound();
		final IByteBound upperBound = _bridged.getUpperBound();
		if(lowerBound == null || upperBound == null)
			return Arrays.asList(_bridged);
		final int dist = upperBound.getValue() - lowerBound.getValue();
		if(dist<numberOfParts)
		{
			final ArrayList<DegenerateByteSet> r = new ArrayList<DegenerateByteSet>(dist);
			byte currentValue = lowerBound.getValue();
			r.add(DegenerateByteSet.makeSet(currentValue));
			for(int i = 0 ; i < dist ; i++)
			{
				currentValue = (byte) (currentValue+1);
				r.add(DegenerateByteSet.makeSet(currentValue));
			}
			return r;
		}
		byte nextSplit = lowerBound.getValue();
		@SuppressWarnings("unchecked")
		final
		byte[] splitArray = new byte[numberOfParts-1];
		int lastIndex = 0;
		for(int i = 1 ; i < numberOfParts ; i++)
		{
			final int index = (int)(i*dist/(float)numberOfParts+0.5f);
			for(int j = 0 ; j < index-lastIndex ; j++)
				nextSplit = (byte) (nextSplit+1);
			splitArray[i-1] = nextSplit;
			lastIndex = index;
		}
		final boolean[] isIntervalStart = new boolean[numberOfParts-1];
		Arrays.fill(isIntervalStart, false);
		return split(splitArray, isIntervalStart);
	}

	public List<IElementaryByteSet> split(final byte[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IElementaryByteSet> r = new ArrayList<IElementaryByteSet>(values.length + 1);
		if(values.length == 0)
		{
			r.add(_bridged);
			return r;
		}
		final EmptyByteSet empty = EmptyByteSet.getInstance();

		final IByteBound lowerBound = _bridged.getLowerBound();
		final IByteBound upperBound = _bridged.getUpperBound();
		if(lowerBound == null || upperBound == null)
		{
			for(int i = 0 ; i <= values.length ; i++)
				r.add(empty);
			return r;
		}

		int i = 0;
		int compare = 1;
		while((i<values.length) && ((compare = values[i] - lowerBound.getValue()))<0)
		{
			r.add(empty);
			i++;
		}

		byte intervalStart = lowerBound.getValue();
		if((i<values.length) && (compare == 0))
		{
			if(!isIntervalStart[i])
			{
				r.add(DegenerateByteSet.makeSet(values[i]));
				intervalStart = (byte) (intervalStart+1); // get successor
			}
			else
				r.add(empty);
			i++;
		}

		if(i<values.length)
		{
			byte intervalEnd;
			byte nextIntervalStart;
			if(isIntervalStart[i])
			{
				intervalEnd = (byte) (values[i]-1); // get predecessor
				nextIntervalStart = values[i];
			}
			else
			{
				intervalEnd = values[i];
				nextIntervalStart = (byte) (values[i]+1);
			}

			while((i<values.length) && ((compare = intervalEnd - upperBound.getValue()))<=0)
			{
				if((intervalEnd - intervalStart)>0)
				{
					if(_splitter != null)
						r.add(ByteInterval.makeInterval(intervalStart, intervalEnd));
					else
						r.add(ByteInterval.makeInterval(intervalStart, intervalEnd));
				}
				else
					r.add(DegenerateByteSet.makeSet(intervalEnd));
				intervalStart = nextIntervalStart;
				i++;
				if(i<values.length)
				{
					if(isIntervalStart[i])
					{
						intervalEnd = (byte) (values[i]+1);
						nextIntervalStart = values[i];
					}
					else
					{
						intervalEnd = values[i];
						nextIntervalStart = (byte) (values[i]+1);
					}
				}
			}

			if((compare = (intervalStart - upperBound.getValue()))<0)
			{
				final Splitter<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval> splitter = _splitter;
				if (splitter != null)
					r.add(ByteInterval.makeInterval(intervalStart, upperBound.getValue()));
				else
					r.add(ByteInterval.makeInterval(intervalStart, upperBound.getValue()));
			} else if(compare==0)
				r.add(DegenerateByteSet.makeSet(intervalStart));
			else
				r.add(empty);

			while(i<values.length)
			{
				r.add(empty);
				i++;
			}
		}
		else
		{
			if(compare == 0)
			{
				if((compare = (intervalStart - upperBound.getValue()))<0)
				{
					final Splitter<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval> splitter = _splitter;
					if (splitter != null)
						r.add(ByteInterval.makeInterval(intervalStart, upperBound.getValue()));
					else
						r.add(ByteInterval.makeInterval(intervalStart, upperBound.getValue()));
				} else if(compare==0)
					r.add(DegenerateByteSet.makeSet(intervalStart));
			}
			else //compare < 0
				r.add(_bridged);
		}

		return r;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IElementaryByteSet> splitOnRange(final IByteSet set)
	{
		final IByteBound lowerBound = set.getLowerBound();
		final IByteBound upperBound = set.getUpperBound();
		if((lowerBound != null) && (upperBound != null))
			return split( new byte[]{lowerBound.getValue(), upperBound.getValue()}, new boolean[]{true, false});
		return Arrays.asList(_bridged.getAsElementarySet());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IElementaryByteSet> splitOnLowerBound(final IByteSet set)
	{
		final IByteBound lowerBound = set.getLowerBound();
		if(lowerBound!=null)
			return split(new byte[]{lowerBound.getValue()}, new boolean[]{true});
		return Arrays.asList(_bridged.getAsElementarySet());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IElementaryByteSet> splitOnUpperBound(final IByteSet set)
	{
		final IByteBound upperBound = set.getUpperBound();
		if(upperBound != null)
			return split( new byte[]{upperBound.getValue()}, new boolean[]{false});
		return Arrays.asList(_bridged.getAsElementarySet());
	}
}
