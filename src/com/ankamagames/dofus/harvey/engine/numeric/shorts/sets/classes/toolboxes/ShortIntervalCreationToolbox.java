/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.comparators.ShortSplitter;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.ShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.ShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.DegenerateShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.EmptyShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IEmptyShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ShortIntervalCreationToolbox<BridgedType extends IShortInterval>
extends ShortSetCreationToolbox<BridgedType>
implements IIntervalCreationToolbox<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval, IEmptyShortSet, BridgedType>
{
	protected final @Nullable Splitter<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval> _splitter;

	public ShortIntervalCreationToolbox(final BridgedType bridged)
	{
		super(bridged);
		_splitter = ShortSplitter.getInstance();
	}

	@Override
	public IShortInterval makeIntervalFromLowerBounds(final IShortSet lowerBoundSet, final IShortSet upperBoundSet)
	{
		final IShortBound lowerBound = lowerBoundSet.getLowerBound();
		final IShortBound upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return ShortInterval.makeInterval(lowerBound.getValue(), upperBound.getValue());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyShortSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public IShortInterval makeIntervalFromUpperBounds(final IShortSet lowerBoundSet, final IShortSet upperBoundSet)
	{
		final IShortBound lowerBound = lowerBoundSet.getUpperBound();
		final IShortBound upperBound = upperBoundSet.getUpperBound();
		if(lowerBound != null && upperBound != null)
			return ShortInterval.makeInterval(lowerBound.getValue(), upperBound.getValue());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyShortSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public IShortInterval makeInvertedInterval(final IShortSet lowerBoundSet, final IShortSet upperBoundSet)
	{
		final IShortBound lowerBound = lowerBoundSet.getUpperBound();
		final IShortBound upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return ShortInterval.makeInterval(lowerBound.getValue(), upperBound.getValue());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyShortSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public ISimpleShortSet makeSimpleCompositeSet(
		final IElementaryShortSet... parts)
	{
		return ShortSet.makeSet(parts);
	}

	@Override
	public List<? extends IElementaryShortSet> split(final int numberOfParts)
	{
		final Splitter<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval> splitter = _splitter;
		if(splitter!=null)
		{
			return Arrays.asList(splitter.split(_bridged));
		}
		final IShortBound lowerBound = _bridged.getLowerBound();
		final IShortBound upperBound = _bridged.getUpperBound();
		if(lowerBound == null || upperBound == null)
			return Arrays.asList(_bridged);
		final int dist = upperBound.getValue() - lowerBound.getValue();
		if(dist<numberOfParts)
		{
			final ArrayList<DegenerateShortSet> r = new ArrayList<DegenerateShortSet>(dist);
			short currentValue = lowerBound.getValue();
			r.add(DegenerateShortSet.makeSet(currentValue));
			for(int i = 0 ; i < dist ; i++)
			{
				currentValue = (short) (currentValue+1);
				r.add(DegenerateShortSet.makeSet(currentValue));
			}
			return r;
		}
		short nextSplit = lowerBound.getValue();
		@SuppressWarnings("unchecked")
		final
		short[] splitArray = new short[numberOfParts-1];
		int lastIndex = 0;
		for(int i = 1 ; i < numberOfParts ; i++)
		{
			final int index = (int)(i*dist/(float)numberOfParts+0.5f);
			for(int j = 0 ; j < index-lastIndex ; j++)
				nextSplit = (short) (nextSplit+1);
			splitArray[i-1] = nextSplit;
			lastIndex = index;
		}
		final boolean[] isIntervalStart = new boolean[numberOfParts-1];
		Arrays.fill(isIntervalStart, false);
		return split(splitArray, isIntervalStart);
	}

	public List<IElementaryShortSet> split(final short[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IElementaryShortSet> r = new ArrayList<IElementaryShortSet>(values.length + 1);
		if(values.length == 0)
		{
			r.add(_bridged);
			return r;
		}
		final EmptyShortSet empty = EmptyShortSet.getInstance();

		final IShortBound lowerBound = _bridged.getLowerBound();
		final IShortBound upperBound = _bridged.getUpperBound();
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

		short intervalStart = lowerBound.getValue();
		if((i<values.length) && (compare == 0))
		{
			if(!isIntervalStart[i])
			{
				r.add(DegenerateShortSet.makeSet(values[i]));
				intervalStart = (short) (intervalStart+1); // get successor
			}
			else
				r.add(empty);
			i++;
		}

		if(i<values.length)
		{
			short intervalEnd;
			short nextIntervalStart;
			if(isIntervalStart[i])
			{
				intervalEnd = (short) (values[i]-1); // get predecessor
				nextIntervalStart = values[i];
			}
			else
			{
				intervalEnd = values[i];
				nextIntervalStart = (short) (values[i]+1);
			}

			while((i<values.length) && ((compare = intervalEnd - upperBound.getValue()))<=0)
			{
				if((intervalEnd - intervalStart)>0)
				{
					if(_splitter != null)
						r.add(ShortInterval.makeInterval(intervalStart, intervalEnd));
					else
						r.add(ShortInterval.makeInterval(intervalStart, intervalEnd));
				}
				else
					r.add(DegenerateShortSet.makeSet(intervalEnd));
				intervalStart = nextIntervalStart;
				i++;
				if(i<values.length)
				{
					if(isIntervalStart[i])
					{
						intervalEnd = (short) (values[i]+1);
						nextIntervalStart = values[i];
					}
					else
					{
						intervalEnd = values[i];
						nextIntervalStart = (short) (values[i]+1);
					}
				}
			}

			if((compare = (intervalStart - upperBound.getValue()))<0)
			{
				final Splitter<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval> splitter = _splitter;
				if (splitter != null)
					r.add(ShortInterval.makeInterval(intervalStart, upperBound.getValue()));
				else
					r.add(ShortInterval.makeInterval(intervalStart, upperBound.getValue()));
			} else if(compare==0)
				r.add(DegenerateShortSet.makeSet(intervalStart));
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
					final Splitter<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval> splitter = _splitter;
					if (splitter != null)
						r.add(ShortInterval.makeInterval(intervalStart, upperBound.getValue()));
					else
						r.add(ShortInterval.makeInterval(intervalStart, upperBound.getValue()));
				} else if(compare==0)
					r.add(DegenerateShortSet.makeSet(intervalStart));
			}
			else //compare < 0
				r.add(_bridged);
		}

		return r;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IElementaryShortSet> splitOnRange(final IShortSet set)
	{
		final IShortBound lowerBound = set.getLowerBound();
		final IShortBound upperBound = set.getUpperBound();
		if((lowerBound != null) && (upperBound != null))
			return split( new short[]{lowerBound.getValue(), upperBound.getValue()}, new boolean[]{true, false});
		return Arrays.asList(_bridged.getAsElementarySet());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IElementaryShortSet> splitOnLowerBound(final IShortSet set)
	{
		final IShortBound lowerBound = set.getLowerBound();
		if(lowerBound!=null)
			return split(new short[]{lowerBound.getValue()}, new boolean[]{true});
		return Arrays.asList(_bridged.getAsElementarySet());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IElementaryShortSet> splitOnUpperBound(final IShortSet set)
	{
		final IShortBound upperBound = set.getUpperBound();
		if(upperBound != null)
			return split( new short[]{upperBound.getValue()}, new boolean[]{false});
		return Arrays.asList(_bridged.getAsElementarySet());
	}
}
