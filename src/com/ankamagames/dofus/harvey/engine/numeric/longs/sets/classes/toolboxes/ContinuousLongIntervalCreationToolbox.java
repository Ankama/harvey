/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.comparators.ContinuousLongSplitter;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.ContinuousLongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.ContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.DegenerateContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.EmptyContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongBound;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IEmptyContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleContinuousLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousLongIntervalCreationToolbox<BridgedType extends IContinuousLongInterval>
extends ContinuousLongSetCreationToolbox<BridgedType>
implements IIntervalCreationToolbox<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval, IEmptyContinuousLongSet, BridgedType>
{

	public ContinuousLongIntervalCreationToolbox(final BridgedType bridged)
	{
		super(bridged);
	}

	@Override
	public IContinuousLongInterval makeIntervalFromLowerBounds(final IContinuousLongSet lowerBoundSet, final IContinuousLongSet upperBoundSet)
	{
		final IContinuousLongBound lowerBound = lowerBoundSet.getLowerBound();
		final IContinuousLongBound upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return ContinuousLongInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(), upperBound.getValue(), upperBound.isClosed());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyContinuousLongSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public IContinuousLongInterval makeIntervalFromUpperBounds(final IContinuousLongSet lowerBoundSet, final IContinuousLongSet upperBoundSet)
	{
		final IContinuousLongBound lowerBound = lowerBoundSet.getUpperBound();
		final IContinuousLongBound upperBound = upperBoundSet.getUpperBound();
		if(lowerBound != null && upperBound != null)
			return ContinuousLongInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(), upperBound.getValue(), upperBound.isClosed());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyContinuousLongSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public IContinuousLongInterval makeInvertedInterval(final IContinuousLongSet lowerBoundSet,
			final IContinuousLongSet upperBoundSet)
	{
		final IContinuousLongBound lowerBound = lowerBoundSet.getUpperBound();
		final IContinuousLongBound upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return ContinuousLongInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(), upperBound.getValue(), upperBound.isClosed());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyContinuousLongSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public ISimpleContinuousLongSet makeSimpleCompositeSet(final IElementaryContinuousLongSet... parts)
	{
		return ContinuousLongSet.makeSet(parts);
	}

	@Override
	public List<? extends IElementaryContinuousLongSet> split(final int numberOfParts)
	{
		return Arrays.asList(ContinuousLongSplitter.getInstance().split(_bridged));
	}

	public List<IElementaryContinuousLongSet> split(final long[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IElementaryContinuousLongSet> r = new ArrayList<IElementaryContinuousLongSet>(values.length + 1);
		if(values.length == 0)
			r.add(_bridged);
		final EmptyContinuousLongSet empty = EmptyContinuousLongSet.getInstance();

		final IContinuousLongBound lowerBound = _bridged.getLowerBound();
		final IContinuousLongBound upperBound = _bridged.getUpperBound();
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
		long intervalStart;
		if(lowerBound==null)
			throw new NullPointerException();
		while((i<values.length) && (compare = values[i] - lowerBound.getValue())<0)
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
				r.add(DegenerateContinuousLongSet.makeSet(values[i]));
				isIntervalStartClosed = false;
			}
			else
				r.add(empty);
			i++;
		}
		if(i<values.length)
		{
			long intervalEnd;
			boolean isIntervalEndClosed;

			if(isUpperBoundInfinity)
			{
				while(i<values.length)
				{
					intervalEnd = values[i];
					isIntervalEndClosed = !isIntervalStart[i];
					if((intervalEnd - intervalStart)>0)
						r.add(ContinuousLongInterval.makeInterval(intervalStart, isIntervalStartClosed, intervalEnd, isIntervalEndClosed));
					else
						if(isIntervalStartClosed && isIntervalEndClosed)
							r.add(DegenerateContinuousLongSet.makeSet(intervalEnd));
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
			while((i<values.length) && ((compare = ((intervalEnd = values[i]) - upperBound.getValue()))<=0))
			{
				isIntervalEndClosed = !isIntervalStart[i];
				if((intervalEnd - intervalStart)>0)
				{
					r.add(ContinuousLongInterval.makeInterval(intervalStart, isIntervalStartClosed, intervalEnd, isIntervalEndClosed));
				}
				else
					if(isIntervalStartClosed && isIntervalEndClosed)
						r.add(DegenerateContinuousLongSet.makeSet(intervalEnd));
					else
						r.add(empty);
				intervalStart = intervalEnd;
				isIntervalStartClosed = !isIntervalEndClosed;
				i++;
			}

			if((compare = (intervalStart - upperBound.getValue()))<0)
			{
					r.add(ContinuousLongInterval.makeInterval(intervalStart, isIntervalStartClosed, upperBound.getValue(), _bridged.isUpperBoundClosed()));
			} else if((compare==0)&&isIntervalStartClosed&&_bridged.isUpperBoundClosed())
				r.add(DegenerateContinuousLongSet.makeSet(intervalStart));
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
				if((compare = (intervalStart - upperBound.getValue()))<0)
						r.add(ContinuousLongInterval.makeInterval(intervalStart, isIntervalStartClosed, upperBound.getValue(), upperBound.isClosed()));
				else if ((compare == 0) && (isIntervalStartClosed && upperBound.isClosed()))
					r.add(DegenerateContinuousLongSet.makeSet(intervalStart));
			}
			else //compare < 0
				r.add(_bridged);
		}
		return r;
	}

	@Override
	public List<? extends IContinuousLongSet> splitOnRange(final IContinuousLongSet set)
	{

		final IContinuousLongBound lowerBound = set.getLowerBound();
		final IContinuousLongBound upperBound = set.getUpperBound();
		if(lowerBound == null || upperBound == null)
		{
			@SuppressWarnings("unchecked")
			final List<BridgedType> asList = Arrays.asList(_bridged);
			return asList;
		}

		final long[] values =  new long[]{lowerBound.getValue(), upperBound.getValue()};
		return split(values, new boolean[]{lowerBound.isClosed(), !upperBound.isClosed()});
	}

	@Override
	public List<? extends IContinuousLongSet> splitOnLowerBound(final IContinuousLongSet set)
	{
		final IContinuousLongBound lowerBound = set.getLowerBound();
		if(lowerBound == null)
		{
			@SuppressWarnings("unchecked")
			final List<BridgedType> asList = Arrays.asList(_bridged);
			return asList;
		}
		final
		long[] values = new long[]{lowerBound.getValue()};
		return split(values, new boolean[]{lowerBound.isClosed()});
	}

	@Override
	public List<? extends IContinuousLongSet> splitOnUpperBound(final IContinuousLongSet set)
	{
		final IContinuousLongBound upperBound = set.getUpperBound();
		if(upperBound == null)
			return Arrays.asList(_bridged);
		@SuppressWarnings("unchecked")
		final
		long[] values = new long[]{upperBound.getValue()};
		return split(values, new boolean[]{!upperBound.isClosed()});
	}
}