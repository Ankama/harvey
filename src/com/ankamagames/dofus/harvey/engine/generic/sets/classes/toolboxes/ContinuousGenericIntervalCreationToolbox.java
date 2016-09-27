/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparator;
import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.generic.sets.classes.ContinuousGenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.classes.ContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.classes.DegenerateContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.classes.EmptyContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IEmptyContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleContinuousGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousGenericIntervalCreationToolbox<Data, BridgedType extends IContinuousGenericInterval<Data>>
extends ContinuousGenericSetCreationToolbox<Data, BridgedType>
implements IIntervalCreationToolbox<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>, IEmptyContinuousGenericSet<Data>, BridgedType>
{
	protected Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> _splitter;

	public ContinuousGenericIntervalCreationToolbox(final BridgedType bridged, final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		super(bridged, comparator, splitter);
		_splitter = splitter;
	}

	@Override
	public IContinuousGenericInterval<Data> makeIntervalFromLowerBounds(final IContinuousGenericSet<Data> lowerBoundSet, final IContinuousGenericSet<Data> upperBoundSet)
	{
		final IContinuousGenericBound<Data> lowerBound = lowerBoundSet.getLowerBound();
		final IContinuousGenericBound<Data> upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return ContinuousGenericInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(), upperBound.getValue(), upperBound.isClosed(), _comparator, _splitter);
		if((upperBound==null)&&(lowerBound==null))
			return EmptyContinuousGenericSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public IContinuousGenericInterval<Data> makeIntervalFromUpperBounds(final IContinuousGenericSet<Data> lowerBoundSet, final IContinuousGenericSet<Data> upperBoundSet)
	{
		final IContinuousGenericBound<Data> lowerBound = lowerBoundSet.getUpperBound();
		final IContinuousGenericBound<Data> upperBound = upperBoundSet.getUpperBound();
		if(lowerBound != null && upperBound != null)
			return ContinuousGenericInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(), upperBound.getValue(), upperBound.isClosed(), _comparator, _splitter);
		if((upperBound==null)&&(lowerBound==null))
			return EmptyContinuousGenericSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public IContinuousGenericInterval<Data> makeInvertedInterval(final IContinuousGenericSet<Data> lowerBoundSet,
			final IContinuousGenericSet<Data> upperBoundSet)
	{
		final IContinuousGenericBound<Data> lowerBound = lowerBoundSet.getUpperBound();
		final IContinuousGenericBound<Data> upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return ContinuousGenericInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(), upperBound.getValue(), upperBound.isClosed(), _comparator, _splitter);
		if((upperBound==null)&&(lowerBound==null))
			return EmptyContinuousGenericSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public ISimpleContinuousGenericSet<Data> makeSimpleCompositeSet(final IElementaryContinuousGenericSet<Data>... parts)
	{
		return ContinuousGenericSet.makeSet(_comparator, _splitter, parts);
	}

	@Override
	public List<? extends IElementaryContinuousGenericSet<Data>> split(final int numberOfParts)
	{
		return Arrays.asList(_splitter.split(_bridged));
	}

	public List<IElementaryContinuousGenericSet<Data>> split(final Data[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IElementaryContinuousGenericSet<Data>> r = new ArrayList<IElementaryContinuousGenericSet<Data>>(values.length + 1);
		if(values.length == 0)
			r.add(_bridged);
		final EmptyContinuousGenericSet<Data> empty = EmptyContinuousGenericSet.getInstance();

		final IContinuousGenericBound<Data> lowerBound = _bridged.getLowerBound();
		final IContinuousGenericBound<Data> upperBound = _bridged.getUpperBound();
		final boolean isLowerBoundInfinity = _bridged.isLowerBoundInfinity();
		final boolean isUpperBoundInfinity = _bridged.isUpperBoundInfinity();
		if((lowerBound == null) || (upperBound == null))
		{
			for(int i = 0 ; i <= values.length ; i++)
				r.add(empty);
			return r;
		}

		int i = 0;
		double compare = 1;
		boolean isIntervalStartClosed;
		@Nullable Data intervalStart;
		if(!isLowerBoundInfinity)
		{
			if(lowerBound==null)
				throw new NullPointerException();
			while((i<values.length) && ((compare = _comparator.compare(values[i], lowerBound.getValue()))<0))
			{
				r.add(empty);
				i++;
			}

			isIntervalStartClosed = lowerBound.isClosed();
			intervalStart = lowerBound.getValue();

			if((i<values.length) && (compare == 0))
			{
				if((!isIntervalStart[i])&&(lowerBound.isClosed()))
				{
					r.add(DegenerateContinuousGenericSet.makeSet(values[i], _comparator, _splitter));
					isIntervalStartClosed = false;
				}
				else
					r.add(empty);
				i++;
			}
		}
		else
		{
			intervalStart = values[i];
			isIntervalStartClosed = isIntervalStart[i++];
			r.add(ContinuousGenericInterval.makeRightBoundedInterval(intervalStart, !isIntervalStartClosed, _comparator, _splitter));
		}

		if(i<values.length)
		{
			@Nullable
			Data intervalEnd;
			boolean isIntervalEndClosed;

			if(isUpperBoundInfinity)
			{
				while(i<values.length)
				{
					intervalEnd = values[i];
					isIntervalEndClosed = !isIntervalStart[i];
					if(_comparator.compare(intervalEnd, intervalStart)>0)
						r.add(ContinuousGenericInterval.makeInterval(intervalStart, isIntervalStartClosed, intervalEnd, isIntervalEndClosed, _comparator, _splitter));
					else
						if(isIntervalStartClosed && isIntervalEndClosed)
							r.add(DegenerateContinuousGenericSet.makeSet(intervalEnd, _comparator, _splitter));
						else
							r.add(empty);
					intervalStart = intervalEnd;
					isIntervalStartClosed = !isIntervalEndClosed;
					i++;
				}

				return r;
			}

			if(upperBound==null)
				throw new NullPointerException();
			while((i<values.length) && ((compare = _comparator.compare((intervalEnd = values[i]), upperBound.getValue()))<=0))
			{
				isIntervalEndClosed = !isIntervalStart[i];
				if(_comparator.compare(intervalEnd, intervalStart)>0)
				{
					r.add(ContinuousGenericInterval.makeInterval(intervalStart, isIntervalStartClosed, intervalEnd, isIntervalEndClosed, _comparator, _splitter));
				}
				else
					if(isIntervalStartClosed && isIntervalEndClosed)
						r.add(DegenerateContinuousGenericSet.makeSet(intervalEnd, _comparator, _splitter));
					else
						r.add(empty);
				intervalStart = intervalEnd;
				isIntervalStartClosed = !isIntervalEndClosed;
				i++;
			}

			if((compare = _comparator.compare(intervalStart, upperBound.getValue()))<0)
			{
					r.add(ContinuousGenericInterval.makeInterval(intervalStart, isIntervalStartClosed, upperBound.getValue(), _bridged.isUpperBoundClosed(), _comparator, _splitter));
			} else if((compare==0)&&isIntervalStartClosed&&_bridged.isUpperBoundClosed())
				r.add(DegenerateContinuousGenericSet.makeSet(intervalStart, _comparator, _splitter));
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
			if(compare == 0)	//we added a degenerate to r with the only value we had
			{
				if((compare = _comparator.compare(intervalStart, upperBound.getValue()))<0)
						r.add(ContinuousGenericInterval.makeInterval(intervalStart, isIntervalStartClosed, upperBound.getValue(), upperBound.isClosed(), _comparator, _splitter));
				else if ((compare == 0) && (isIntervalStartClosed && upperBound.isClosed()))
					r.add(DegenerateContinuousGenericSet.makeSet(intervalStart, _comparator, _splitter));
			}
			else //compare < 0
				r.add(_bridged);
		}
		return r;
	}

	@Override
	public List<? extends IContinuousGenericSet<Data>> splitOnRange(final IContinuousGenericSet<Data> set)
	{
		if(set.isLowerBoundInfinity())
			if(set.isUpperBoundInfinity())
			{
				@SuppressWarnings("unchecked")
				final List<BridgedType> asList = Arrays.asList(_bridged);
				return asList;
			}
			else
				return splitOnUpperBound(set);
		if(set.isUpperBoundInfinity())
			return splitOnLowerBound(set);

		final IContinuousGenericBound<Data> lowerBound = set.getLowerBound();
		final IContinuousGenericBound<Data> upperBound = set.getUpperBound();
		if(lowerBound == null || upperBound == null)
		{
			@SuppressWarnings("unchecked")
			final List<BridgedType> asList = Arrays.asList(_bridged);
			return asList;
		}

		@SuppressWarnings("unchecked")
		final Data[] values = (Data[]) new Object[]{lowerBound.getValue(), upperBound.getValue()};
		return split(values, new boolean[]{lowerBound.isClosed(), !upperBound.isClosed()});
	}

	@Override
	public List<? extends IContinuousGenericSet<Data>> splitOnLowerBound(final IContinuousGenericSet<Data> set)
	{
		final IContinuousGenericBound<Data> lowerBound = set.getLowerBound();
		if(set.isLowerBoundInfinity() || lowerBound == null)
		{
			@SuppressWarnings("unchecked")
			final List<BridgedType> asList = Arrays.asList(_bridged);
			return asList;
		}
		@SuppressWarnings("unchecked")
		final
		Data[] values = (Data[]) new Object[]{lowerBound.getValue()};
		return split(values, new boolean[]{lowerBound.isClosed()});
	}

	@Override
	public List<? extends IContinuousGenericSet<Data>> splitOnUpperBound(final IContinuousGenericSet<Data> set)
	{
		final IContinuousGenericBound<Data> upperBound = set.getUpperBound();
		if(set.isUpperBoundInfinity() || upperBound == null)
			return Arrays.asList(_bridged);
		@SuppressWarnings("unchecked")
		final
		Data[] values = (Data[]) new Object[]{upperBound.getValue()};
		return split(values, new boolean[]{!upperBound.isClosed()});
	}
}