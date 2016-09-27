/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.comparators.ContinuousIntegerSplitter;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.ContinuousIntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.ContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.DegenerateContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.EmptyContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerBound;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IEmptyContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleContinuousIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousIntegerIntervalCreationToolbox<BridgedType extends IContinuousIntegerInterval>
extends ContinuousIntegerSetCreationToolbox<BridgedType>
implements IIntervalCreationToolbox<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, IContinuousIntegerInterval, IEmptyContinuousIntegerSet, BridgedType>
{

	public ContinuousIntegerIntervalCreationToolbox(final BridgedType bridged)
	{
		super(bridged);
	}

	@Override
	public IContinuousIntegerInterval makeIntervalFromLowerBounds(final IContinuousIntegerSet lowerBoundSet, final IContinuousIntegerSet upperBoundSet)
	{
		final IContinuousIntegerBound lowerBound = lowerBoundSet.getLowerBound();
		final IContinuousIntegerBound upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return ContinuousIntegerInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(), upperBound.getValue(), upperBound.isClosed());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyContinuousIntegerSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public IContinuousIntegerInterval makeIntervalFromUpperBounds(final IContinuousIntegerSet lowerBoundSet, final IContinuousIntegerSet upperBoundSet)
	{
		final IContinuousIntegerBound lowerBound = lowerBoundSet.getUpperBound();
		final IContinuousIntegerBound upperBound = upperBoundSet.getUpperBound();
		if(lowerBound != null && upperBound != null)
			return ContinuousIntegerInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(), upperBound.getValue(), upperBound.isClosed());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyContinuousIntegerSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public IContinuousIntegerInterval makeInvertedInterval(final IContinuousIntegerSet lowerBoundSet,
			final IContinuousIntegerSet upperBoundSet)
	{
		final IContinuousIntegerBound lowerBound = lowerBoundSet.getUpperBound();
		final IContinuousIntegerBound upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return ContinuousIntegerInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(), upperBound.getValue(), upperBound.isClosed());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyContinuousIntegerSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public ISimpleContinuousIntegerSet makeSimpleCompositeSet(final IElementaryContinuousIntegerSet... parts)
	{
		return ContinuousIntegerSet.makeSet(parts);
	}

	@Override
	public List<? extends IElementaryContinuousIntegerSet> split(final int numberOfParts)
	{
		return Arrays.asList(ContinuousIntegerSplitter.getInstance().split(_bridged));
	}

	public List<IElementaryContinuousIntegerSet> split(final int[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IElementaryContinuousIntegerSet> r = new ArrayList<IElementaryContinuousIntegerSet>(values.length + 1);
		if(values.length == 0)
			r.add(_bridged);
		final EmptyContinuousIntegerSet empty = EmptyContinuousIntegerSet.getInstance();

		final IContinuousIntegerBound lowerBound = _bridged.getLowerBound();
		final IContinuousIntegerBound upperBound = _bridged.getUpperBound();
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
		int intervalStart;
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
				r.add(DegenerateContinuousIntegerSet.makeSet(values[i]));
				isIntervalStartClosed = false;
			}
			else
				r.add(empty);
			i++;
		}
		if(i<values.length)
		{
			int intervalEnd;
			boolean isIntervalEndClosed;

			if(isUpperBoundInfinity)
			{
				while(i<values.length)
				{
					intervalEnd = values[i];
					isIntervalEndClosed = !isIntervalStart[i];
					if((intervalEnd - intervalStart)>0)
						r.add(ContinuousIntegerInterval.makeInterval(intervalStart, isIntervalStartClosed, intervalEnd, isIntervalEndClosed));
					else
						if(isIntervalStartClosed && isIntervalEndClosed)
							r.add(DegenerateContinuousIntegerSet.makeSet(intervalEnd));
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
					r.add(ContinuousIntegerInterval.makeInterval(intervalStart, isIntervalStartClosed, intervalEnd, isIntervalEndClosed));
				}
				else
					if(isIntervalStartClosed && isIntervalEndClosed)
						r.add(DegenerateContinuousIntegerSet.makeSet(intervalEnd));
					else
						r.add(empty);
				intervalStart = intervalEnd;
				isIntervalStartClosed = !isIntervalEndClosed;
				i++;
			}

			if((compare = (intervalStart - upperBound.getValue()))<0)
			{
					r.add(ContinuousIntegerInterval.makeInterval(intervalStart, isIntervalStartClosed, upperBound.getValue(), _bridged.isUpperBoundClosed()));
			} else if((compare==0)&&isIntervalStartClosed&&_bridged.isUpperBoundClosed())
				r.add(DegenerateContinuousIntegerSet.makeSet(intervalStart));
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
						r.add(ContinuousIntegerInterval.makeInterval(intervalStart, isIntervalStartClosed, upperBound.getValue(), upperBound.isClosed()));
				else if ((compare == 0) && (isIntervalStartClosed && upperBound.isClosed()))
					r.add(DegenerateContinuousIntegerSet.makeSet(intervalStart));
			}
			else //compare < 0
				r.add(_bridged);
		}
		return r;
	}

	@Override
	public List<? extends IContinuousIntegerSet> splitOnRange(final IContinuousIntegerSet set)
	{

		final IContinuousIntegerBound lowerBound = set.getLowerBound();
		final IContinuousIntegerBound upperBound = set.getUpperBound();
		if(lowerBound == null || upperBound == null)
		{
			@SuppressWarnings("unchecked")
			final List<BridgedType> asList = Arrays.asList(_bridged);
			return asList;
		}

		final int[] values =  new int[]{lowerBound.getValue(), upperBound.getValue()};
		return split(values, new boolean[]{lowerBound.isClosed(), !upperBound.isClosed()});
	}

	@Override
	public List<? extends IContinuousIntegerSet> splitOnLowerBound(final IContinuousIntegerSet set)
	{
		final IContinuousIntegerBound lowerBound = set.getLowerBound();
		if(lowerBound == null)
		{
			@SuppressWarnings("unchecked")
			final List<BridgedType> asList = Arrays.asList(_bridged);
			return asList;
		}
		final
		int[] values = new int[]{lowerBound.getValue()};
		return split(values, new boolean[]{lowerBound.isClosed()});
	}

	@Override
	public List<? extends IContinuousIntegerSet> splitOnUpperBound(final IContinuousIntegerSet set)
	{
		final IContinuousIntegerBound upperBound = set.getUpperBound();
		if(upperBound == null)
			return Arrays.asList(_bridged);
		@SuppressWarnings("unchecked")
		final
		int[] values = new int[]{upperBound.getValue()};
		return split(values, new boolean[]{!upperBound.isClosed()});
	}
}