/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.comparators.LongSplitter;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.DegenerateLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.EmptyLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.LongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.LongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IEmptyLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongBound;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class LongIntervalCreationToolbox<BridgedType extends ILongInterval>
extends LongSetCreationToolbox<BridgedType>
implements IIntervalCreationToolbox<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval, IEmptyLongSet, BridgedType>
{
	protected final @Nullable Splitter<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval> _splitter;

	public LongIntervalCreationToolbox(final BridgedType bridged)
	{
		super(bridged);
		_splitter = LongSplitter.getInstance();
	}

	@Override
	public ILongInterval makeIntervalFromLowerBounds(final ILongSet lowerBoundSet, final ILongSet upperBoundSet)
	{
		final ILongBound lowerBound = lowerBoundSet.getLowerBound();
		final ILongBound upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return LongInterval.makeInterval(lowerBound.getValue(), upperBound.getValue());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyLongSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public ILongInterval makeIntervalFromUpperBounds(final ILongSet lowerBoundSet, final ILongSet upperBoundSet)
	{
		final ILongBound lowerBound = lowerBoundSet.getUpperBound();
		final ILongBound upperBound = upperBoundSet.getUpperBound();
		if(lowerBound != null && upperBound != null)
			return LongInterval.makeInterval(lowerBound.getValue(), upperBound.getValue());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyLongSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public ILongInterval makeInvertedInterval(final ILongSet lowerBoundSet, final ILongSet upperBoundSet)
	{
		final ILongBound lowerBound = lowerBoundSet.getUpperBound();
		final ILongBound upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return LongInterval.makeInterval(lowerBound.getValue(), upperBound.getValue());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyLongSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public ISimpleLongSet makeSimpleCompositeSet(
		final IElementaryLongSet... parts)
	{
		return LongSet.makeSet(parts);
	}

	@Override
	public List<? extends IElementaryLongSet> split(final int numberOfParts)
	{
		final Splitter<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval> splitter = _splitter;
		if(splitter!=null)
		{
			return Arrays.asList(splitter.split(_bridged));
		}
		final ILongBound lowerBound = _bridged.getLowerBound();
		final ILongBound upperBound = _bridged.getUpperBound();
		if(lowerBound == null || upperBound == null)
			return Arrays.asList(_bridged);
		final int dist = (int)(upperBound.getValue() - lowerBound.getValue());
		if(dist<numberOfParts)
		{
			final ArrayList<DegenerateLongSet> r = new ArrayList<DegenerateLongSet>(dist);
			long currentValue = lowerBound.getValue();
			r.add(DegenerateLongSet.makeSet(currentValue));
			for(int i = 0 ; i < dist ; i++)
			{
				currentValue = currentValue+1;
				r.add(DegenerateLongSet.makeSet(currentValue));
			}
			return r;
		}
		long nextSplit = lowerBound.getValue();
		@SuppressWarnings("unchecked")
		final
		long[] splitArray = new long[numberOfParts-1];
		int lastIndex = 0;
		for(int i = 1 ; i < numberOfParts ; i++)
		{
			final int index = (int)(i*dist/(float)numberOfParts+0.5f);
			for(int j = 0 ; j < index-lastIndex ; j++)
				nextSplit = nextSplit+1;
			splitArray[i-1] = nextSplit;
			lastIndex = index;
		}
		final boolean[] isIntervalStart = new boolean[numberOfParts-1];
		Arrays.fill(isIntervalStart, false);
		return split(splitArray, isIntervalStart);
	}

	public List<IElementaryLongSet> split(final long[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IElementaryLongSet> r = new ArrayList<IElementaryLongSet>(values.length + 1);
		if(values.length == 0)
		{
			r.add(_bridged);
			return r;
		}
		final EmptyLongSet empty = EmptyLongSet.getInstance();

		final ILongBound lowerBound = _bridged.getLowerBound();
		final ILongBound upperBound = _bridged.getUpperBound();
		if(lowerBound == null || upperBound == null)
		{
			for(int i = 0 ; i <= values.length ; i++)
				r.add(empty);
			return r;
		}

		int i = 0;
		int compare = 1;
		while((i<values.length) && ((compare = (int)Math.signum(values[i] - lowerBound.getValue())))<0)
		{
			r.add(empty);
			i++;
		}

		long intervalStart = lowerBound.getValue();
		if((i<values.length) && (compare == 0))
		{
			if(!isIntervalStart[i])
			{
				r.add(DegenerateLongSet.makeSet(values[i]));
				intervalStart = intervalStart+1; // get successor
			}
			else
				r.add(empty);
			i++;
		}

		if(i<values.length)
		{
			long intervalEnd;
			long nextIntervalStart;
			if(isIntervalStart[i])
			{
				intervalEnd = values[i]-1; // get predecessor
				nextIntervalStart = values[i];
			}
			else
			{
				intervalEnd = values[i];
				nextIntervalStart = values[i]+1;
			}

			while((i<values.length) && ((compare = (int)Math.signum(intervalEnd - upperBound.getValue())))<=0)
			{
				if((intervalEnd - intervalStart)>0)
				{
					if(_splitter != null)
						r.add(LongInterval.makeInterval(intervalStart, intervalEnd));
					else
						r.add(LongInterval.makeInterval(intervalStart, intervalEnd));
				}
				else
					r.add(DegenerateLongSet.makeSet(intervalEnd));
				intervalStart = nextIntervalStart;
				i++;
				if(i<values.length)
				{
					if(isIntervalStart[i])
					{
						intervalEnd = values[i]+1;
						nextIntervalStart = values[i];
					}
					else
					{
						intervalEnd = values[i];
						nextIntervalStart = values[i]+1;
					}
				}
			}

			if((compare = (int)Math.signum(intervalStart - upperBound.getValue()))<0)
			{
				final Splitter<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval> splitter = _splitter;
				if (splitter != null)
					r.add(LongInterval.makeInterval(intervalStart, upperBound.getValue()));
				else
					r.add(LongInterval.makeInterval(intervalStart, upperBound.getValue()));
			} else if(compare==0)
				r.add(DegenerateLongSet.makeSet(intervalStart));
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
				if((compare = (int)Math.signum(intervalStart - upperBound.getValue()))<0)
				{
					final Splitter<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval> splitter = _splitter;
					if (splitter != null)
						r.add(LongInterval.makeInterval(intervalStart, upperBound.getValue()));
					else
						r.add(LongInterval.makeInterval(intervalStart, upperBound.getValue()));
				} else if(compare==0)
					r.add(DegenerateLongSet.makeSet(intervalStart));
			}
			else //compare < 0
				r.add(_bridged);
		}

		return r;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IElementaryLongSet> splitOnRange(final ILongSet set)
	{
		final ILongBound lowerBound = set.getLowerBound();
		final ILongBound upperBound = set.getUpperBound();
		if((lowerBound != null) && (upperBound != null))
			return split( new long[]{lowerBound.getValue(), upperBound.getValue()}, new boolean[]{true, false});
		return Arrays.asList(_bridged.getAsElementarySet());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IElementaryLongSet> splitOnLowerBound(final ILongSet set)
	{
		final ILongBound lowerBound = set.getLowerBound();
		if(lowerBound!=null)
			return split(new long[]{lowerBound.getValue()}, new boolean[]{true});
		return Arrays.asList(_bridged.getAsElementarySet());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IElementaryLongSet> splitOnUpperBound(final ILongSet set)
	{
		final ILongBound upperBound = set.getUpperBound();
		if(upperBound != null)
			return split( new long[]{upperBound.getValue()}, new boolean[]{false});
		return Arrays.asList(_bridged.getAsElementarySet());
	}
}
