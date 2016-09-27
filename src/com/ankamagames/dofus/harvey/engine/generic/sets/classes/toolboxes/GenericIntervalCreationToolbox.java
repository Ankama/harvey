/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.SurroundingValuesProvider;
import com.ankamagames.dofus.harvey.generic.sets.classes.DegenerateSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.classes.EmptySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.classes.GenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.classes.SortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementarySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IEmptySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISortedGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class GenericIntervalCreationToolbox<Data, BridgedType extends IGenericInterval<Data>>
extends SortedGenericSetCreationToolbox<Data, BridgedType>
implements IIntervalCreationToolbox<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>, IEmptySortedGenericSet<Data>, BridgedType>
{
	protected final @Nullable Splitter<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>> _splitter;

	public GenericIntervalCreationToolbox(final BridgedType bridged, final Comparator<? super Data> comparator,
			final SurroundingValuesProvider<Data> surroundingProvider, @Nullable final Splitter<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>> splitter)
	{
		super(bridged, comparator, surroundingProvider);
		_splitter = splitter;
	}

	@Override
	public IGenericInterval<Data> makeIntervalFromLowerBounds(final ISortedGenericSet<Data> lowerBoundSet, final ISortedGenericSet<Data> upperBoundSet)
	{
		final IGenericBound<Data> lowerBound = lowerBoundSet.getLowerBound();
		final IGenericBound<Data> upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return GenericInterval.makeInterval(lowerBound.getValue(), upperBound.getValue(), _comparator, _surroundingProvider);
		if((upperBound==null)&&(lowerBound==null))
			return EmptySortedGenericSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public IGenericInterval<Data> makeIntervalFromUpperBounds(final ISortedGenericSet<Data> lowerBoundSet, final ISortedGenericSet<Data> upperBoundSet)
	{
		final IGenericBound<Data> lowerBound = lowerBoundSet.getUpperBound();
		final IGenericBound<Data> upperBound = upperBoundSet.getUpperBound();
		if(lowerBound != null && upperBound != null)
			return GenericInterval.makeInterval(lowerBound.getValue(), upperBound.getValue(), _comparator, _surroundingProvider);
		if((upperBound==null)&&(lowerBound==null))
			return EmptySortedGenericSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public IGenericInterval<Data> makeInvertedInterval(final ISortedGenericSet<Data> lowerBoundSet, final ISortedGenericSet<Data> upperBoundSet)
	{
		final IGenericBound<Data> lowerBound = lowerBoundSet.getUpperBound();
		final IGenericBound<Data> upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return GenericInterval.makeInterval(lowerBound.getValue(), upperBound.getValue(), _comparator, _surroundingProvider);
		if((upperBound==null)&&(lowerBound==null))
			return EmptySortedGenericSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public ISimpleSortedGenericSet<Data> makeSimpleCompositeSet(
		final IElementarySortedGenericSet<Data>... parts)
	{
		return SortedGenericSet.makeSet(_comparator, _surroundingProvider, parts);
	}

	@Override
	public List<? extends IElementarySortedGenericSet<Data>> split(final int numberOfParts)
	{
		final Splitter<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>> splitter = _splitter;
		if(splitter!=null)
		{
			return Arrays.asList(splitter.split(_bridged));
		}
		final IGenericBound<Data> lowerBound = _bridged.getLowerBound();
		final IGenericBound<Data> upperBound = _bridged.getUpperBound();
		if(lowerBound == null || upperBound == null)
			return Arrays.asList(_bridged);
		final int dist = _comparator.compare(upperBound.getValue(), lowerBound.getValue());
		if(dist<numberOfParts)
		{
			final ArrayList<DegenerateSortedGenericSet<Data>> r = new ArrayList<DegenerateSortedGenericSet<Data>>(dist);
			Data currentValue = lowerBound.getValue();
			r.add(DegenerateSortedGenericSet.makeSet(currentValue, _comparator, _surroundingProvider));
			for(int i = 0 ; i < dist ; i++)
			{
				currentValue = _surroundingProvider.getSuccessor(currentValue);
				r.add(DegenerateSortedGenericSet.makeSet(currentValue, _comparator, _surroundingProvider));
			}
			return r;
		}
		@Nullable Data nextSplit = lowerBound.getValue();
		@SuppressWarnings("unchecked")
		final
		Data[] splitArray = (Data[]) new Object[numberOfParts-1];
		int lastIndex = 0;
		for(int i = 1 ; i < numberOfParts ; i++)
		{
			final int index = (int)(i*dist/(float)numberOfParts+0.5f);
			for(int j = 0 ; j < index-lastIndex ; j++)
				nextSplit = _surroundingProvider.getSuccessor(nextSplit);
			splitArray[i-1] = nextSplit;
			lastIndex = index;
		}
		final boolean[] isIntervalStart = new boolean[numberOfParts-1];
		Arrays.fill(isIntervalStart, false);
		return split(splitArray, isIntervalStart);
	}

	public List<IElementarySortedGenericSet<Data>> split(final Data[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IElementarySortedGenericSet<Data>> r = new ArrayList<IElementarySortedGenericSet<Data>>(values.length + 1);
		if(values.length == 0)
		{
			r.add(_bridged);
			return r;
		}
		final EmptySortedGenericSet<Data> empty = EmptySortedGenericSet.getInstance();

		final IGenericBound<Data> lowerBound = _bridged.getLowerBound();
		final IGenericBound<Data> upperBound = _bridged.getUpperBound();
		if(lowerBound == null || upperBound == null)
		{
			for(int i = 0 ; i <= values.length ; i++)
				r.add(empty);
			return r;
		}

		int i = 0;
		int compare = 1;
		while((i<values.length) && ((compare = _comparator.compare(values[i], lowerBound.getValue()))<0))
		{
			r.add(empty);
			i++;
		}

		@Nullable Data intervalStart = lowerBound.getValue();
		if((i<values.length) && (compare == 0))
		{
			if(!isIntervalStart[i])
			{
				r.add(DegenerateSortedGenericSet.makeSet(values[i], _comparator, _surroundingProvider));
				intervalStart = _surroundingProvider.getSuccessor(intervalStart);
			}
			else
				r.add(empty);
			i++;
		}

		if(i<values.length)
		{
			@Nullable Data intervalEnd;
			@Nullable Data nextIntervalStart;
			if(isIntervalStart[i])
			{
				intervalEnd = _surroundingProvider.getPredecessor(values[i]);
				nextIntervalStart = values[i];
			}
			else
			{
				intervalEnd = values[i];
				nextIntervalStart = _surroundingProvider.getSuccessor(values[i]);
			}

			while((i<values.length) && ((compare = _comparator.compare(intervalEnd, upperBound.getValue()))<=0))
			{
				if(_comparator.compare(intervalEnd, intervalStart)>0)
				{
					if(_splitter != null)
						r.add(GenericInterval.makeInterval(intervalStart, intervalEnd, _comparator, _surroundingProvider, _splitter));
					else
						r.add(GenericInterval.makeInterval(intervalStart, intervalEnd, _comparator, _surroundingProvider));
				}
				else
					r.add(DegenerateSortedGenericSet.makeSet(intervalEnd, _comparator, _surroundingProvider));
				intervalStart = nextIntervalStart;
				i++;
				if(i<values.length)
				{
					if(isIntervalStart[i])
					{
						intervalEnd = _surroundingProvider.getPredecessor(values[i]);
						nextIntervalStart = values[i];
					}
					else
					{
						intervalEnd = values[i];
						nextIntervalStart = _surroundingProvider.getSuccessor(values[i]);
					}
				}
			}

			if((compare = _comparator.compare(intervalStart, upperBound.getValue()))<0)
			{
				final Splitter<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>> splitter = _splitter;
				if (splitter != null)
					r.add(GenericInterval.makeInterval(intervalStart, upperBound.getValue(), _comparator, _surroundingProvider, splitter));
				else
					r.add(GenericInterval.makeInterval(intervalStart, upperBound.getValue(), _comparator, _surroundingProvider));
			} else if(compare==0)
				r.add(DegenerateSortedGenericSet.makeSet(intervalStart, _comparator, _surroundingProvider));
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
				if((compare = _comparator.compare(intervalStart, upperBound.getValue()))<0)
				{
					final Splitter<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>> splitter = _splitter;
					if (splitter != null)
						r.add(GenericInterval.makeInterval(intervalStart, upperBound.getValue(), _comparator, _surroundingProvider, splitter));
					else
						r.add(GenericInterval.makeInterval(intervalStart, upperBound.getValue(), _comparator, _surroundingProvider));
				} else if(compare==0)
					r.add(DegenerateSortedGenericSet.makeSet(intervalStart, _comparator, _surroundingProvider));
			}
			else //compare < 0
				r.add(_bridged);
		}

		return r;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IElementarySortedGenericSet<Data>> splitOnRange(final ISortedGenericSet<Data> set)
	{
		final IGenericBound<Data> lowerBound = set.getLowerBound();
		final IGenericBound<Data> upperBound = set.getUpperBound();
		if((lowerBound != null) && (upperBound != null))
			return split((Data[]) new Object[]{lowerBound.getValue(), upperBound.getValue()}, new boolean[]{true, false});
		return Arrays.asList(_bridged.getAsElementarySet());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IElementarySortedGenericSet<Data>> splitOnLowerBound(final ISortedGenericSet<Data> set)
	{
		final IGenericBound<Data> lowerBound = set.getLowerBound();
		if(lowerBound!=null)
			return split((Data[]) new Object[]{lowerBound.getValue()}, new boolean[]{true});
		return Arrays.asList(_bridged.getAsElementarySet());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IElementarySortedGenericSet<Data>> splitOnUpperBound(final ISortedGenericSet<Data> set)
	{
		final IGenericBound<Data> upperBound = set.getUpperBound();
		if(upperBound != null)
			return split((Data[]) new Object[]{upperBound.getValue()}, new boolean[]{false});
		return Arrays.asList(_bridged.getAsElementarySet());
	}
}
