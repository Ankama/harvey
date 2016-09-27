/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.comparators.IntegerSplitter;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.DegenerateIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.EmptyIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.IntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.IntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IEmptyIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerBound;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class IntegerIntervalCreationToolbox<BridgedType extends IIntegerInterval>
extends IntegerSetCreationToolbox<BridgedType>
implements IIntervalCreationToolbox<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval, IEmptyIntegerSet, BridgedType>
{
	protected final @Nullable Splitter<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval> _splitter;

	public IntegerIntervalCreationToolbox(final BridgedType bridged)
	{
		super(bridged);
		_splitter = IntegerSplitter.getInstance();
	}

	@Override
	public IIntegerInterval makeIntervalFromLowerBounds(final IIntegerSet lowerBoundSet, final IIntegerSet upperBoundSet)
	{
		final IIntegerBound lowerBound = lowerBoundSet.getLowerBound();
		final IIntegerBound upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return IntegerInterval.makeInterval(lowerBound.getValue(), upperBound.getValue());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyIntegerSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public IIntegerInterval makeIntervalFromUpperBounds(final IIntegerSet lowerBoundSet, final IIntegerSet upperBoundSet)
	{
		final IIntegerBound lowerBound = lowerBoundSet.getUpperBound();
		final IIntegerBound upperBound = upperBoundSet.getUpperBound();
		if(lowerBound != null && upperBound != null)
			return IntegerInterval.makeInterval(lowerBound.getValue(), upperBound.getValue());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyIntegerSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public IIntegerInterval makeInvertedInterval(final IIntegerSet lowerBoundSet, final IIntegerSet upperBoundSet)
	{
		final IIntegerBound lowerBound = lowerBoundSet.getUpperBound();
		final IIntegerBound upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return IntegerInterval.makeInterval(lowerBound.getValue(), upperBound.getValue());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyIntegerSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public ISimpleIntegerSet makeSimpleCompositeSet(
		final IElementaryIntegerSet... parts)
	{
		return IntegerSet.makeSet(parts);
	}

	@Override
	public List<? extends IElementaryIntegerSet> split(final int numberOfParts)
	{
		final Splitter<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval> splitter = _splitter;
		if(splitter!=null)
		{
			return Arrays.asList(splitter.split(_bridged));
		}
		final IIntegerBound lowerBound = _bridged.getLowerBound();
		final IIntegerBound upperBound = _bridged.getUpperBound();
		if(lowerBound == null || upperBound == null)
			return Arrays.asList(_bridged);
		final int dist = upperBound.getValue() - lowerBound.getValue();
		if(dist<numberOfParts)
		{
			final ArrayList<DegenerateIntegerSet> r = new ArrayList<DegenerateIntegerSet>(dist);
			int currentValue = lowerBound.getValue();
			r.add(DegenerateIntegerSet.makeSet(currentValue));
			for(int i = 0 ; i < dist ; i++)
			{
				currentValue = (int) (currentValue+1);
				r.add(DegenerateIntegerSet.makeSet(currentValue));
			}
			return r;
		}
		int nextSplit = lowerBound.getValue();
		@SuppressWarnings("unchecked")
		final
		int[] splitArray = new int[numberOfParts-1];
		int lastIndex = 0;
		for(int i = 1 ; i < numberOfParts ; i++)
		{
			final int index = (int)(i*dist/(float)numberOfParts+0.5f);
			for(int j = 0 ; j < index-lastIndex ; j++)
				nextSplit = (int) (nextSplit+1);
			splitArray[i-1] = nextSplit;
			lastIndex = index;
		}
		final boolean[] isIntervalStart = new boolean[numberOfParts-1];
		Arrays.fill(isIntervalStart, false);
		return split(splitArray, isIntervalStart);
	}

	public List<IElementaryIntegerSet> split(final int[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IElementaryIntegerSet> r = new ArrayList<IElementaryIntegerSet>(values.length + 1);
		if(values.length == 0)
		{
			r.add(_bridged);
			return r;
		}
		final EmptyIntegerSet empty = EmptyIntegerSet.getInstance();

		final IIntegerBound lowerBound = _bridged.getLowerBound();
		final IIntegerBound upperBound = _bridged.getUpperBound();
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

		int intervalStart = lowerBound.getValue();
		if((i<values.length) && (compare == 0))
		{
			if(!isIntervalStart[i])
			{
				r.add(DegenerateIntegerSet.makeSet(values[i]));
				intervalStart = (int) (intervalStart+1); // get successor
			}
			else
				r.add(empty);
			i++;
		}

		if(i<values.length)
		{
			int intervalEnd;
			int nextIntervalStart;
			if(isIntervalStart[i])
			{
				intervalEnd = (int) (values[i]-1); // get predecessor
				nextIntervalStart = values[i];
			}
			else
			{
				intervalEnd = values[i];
				nextIntervalStart = (int) (values[i]+1);
			}

			while((i<values.length) && ((compare = intervalEnd - upperBound.getValue()))<=0)
			{
				if((intervalEnd - intervalStart)>0)
				{
					if(_splitter != null)
						r.add(IntegerInterval.makeInterval(intervalStart, intervalEnd));
					else
						r.add(IntegerInterval.makeInterval(intervalStart, intervalEnd));
				}
				else
					r.add(DegenerateIntegerSet.makeSet(intervalEnd));
				intervalStart = nextIntervalStart;
				i++;
				if(i<values.length)
				{
					if(isIntervalStart[i])
					{
						intervalEnd = (int) (values[i]+1);
						nextIntervalStart = values[i];
					}
					else
					{
						intervalEnd = values[i];
						nextIntervalStart = (int) (values[i]+1);
					}
				}
			}

			if((compare = (intervalStart - upperBound.getValue()))<0)
			{
				final Splitter<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval> splitter = _splitter;
				if (splitter != null)
					r.add(IntegerInterval.makeInterval(intervalStart, upperBound.getValue()));
				else
					r.add(IntegerInterval.makeInterval(intervalStart, upperBound.getValue()));
			} else if(compare==0)
				r.add(DegenerateIntegerSet.makeSet(intervalStart));
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
					final Splitter<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval> splitter = _splitter;
					if (splitter != null)
						r.add(IntegerInterval.makeInterval(intervalStart, upperBound.getValue()));
					else
						r.add(IntegerInterval.makeInterval(intervalStart, upperBound.getValue()));
				} else if(compare==0)
					r.add(DegenerateIntegerSet.makeSet(intervalStart));
			}
			else //compare < 0
				r.add(_bridged);
		}

		return r;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IElementaryIntegerSet> splitOnRange(final IIntegerSet set)
	{
		final IIntegerBound lowerBound = set.getLowerBound();
		final IIntegerBound upperBound = set.getUpperBound();
		if((lowerBound != null) && (upperBound != null))
			return split( new int[]{lowerBound.getValue(), upperBound.getValue()}, new boolean[]{true, false});
		return Arrays.asList(_bridged.getAsElementarySet());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IElementaryIntegerSet> splitOnLowerBound(final IIntegerSet set)
	{
		final IIntegerBound lowerBound = set.getLowerBound();
		if(lowerBound!=null)
			return split(new int[]{lowerBound.getValue()}, new boolean[]{true});
		return Arrays.asList(_bridged.getAsElementarySet());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IElementaryIntegerSet> splitOnUpperBound(final IIntegerSet set)
	{
		final IIntegerBound upperBound = set.getUpperBound();
		if(upperBound != null)
			return split( new int[]{upperBound.getValue()}, new boolean[]{false});
		return Arrays.asList(_bridged.getAsElementarySet());
	}
}
