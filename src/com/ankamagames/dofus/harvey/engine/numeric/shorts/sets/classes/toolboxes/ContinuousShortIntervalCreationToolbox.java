/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.comparators.ContinuousShortSplitter;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.ContinuousShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.ContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.DegenerateContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.EmptyContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IEmptyContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleContinuousShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousShortIntervalCreationToolbox<BridgedType extends IContinuousShortInterval>
extends ContinuousShortSetCreationToolbox<BridgedType>
implements IIntervalCreationToolbox<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval, IEmptyContinuousShortSet, BridgedType>
{

	public ContinuousShortIntervalCreationToolbox(final BridgedType bridged)
	{
		super(bridged);
	}

	@Override
	public IContinuousShortInterval makeIntervalFromLowerBounds(final IContinuousShortSet lowerBoundSet, final IContinuousShortSet upperBoundSet)
	{
		final IContinuousShortBound lowerBound = lowerBoundSet.getLowerBound();
		final IContinuousShortBound upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return ContinuousShortInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(), upperBound.getValue(), upperBound.isClosed());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyContinuousShortSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public IContinuousShortInterval makeIntervalFromUpperBounds(final IContinuousShortSet lowerBoundSet, final IContinuousShortSet upperBoundSet)
	{
		final IContinuousShortBound lowerBound = lowerBoundSet.getUpperBound();
		final IContinuousShortBound upperBound = upperBoundSet.getUpperBound();
		if(lowerBound != null && upperBound != null)
			return ContinuousShortInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(), upperBound.getValue(), upperBound.isClosed());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyContinuousShortSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public IContinuousShortInterval makeInvertedInterval(final IContinuousShortSet lowerBoundSet,
			final IContinuousShortSet upperBoundSet)
	{
		final IContinuousShortBound lowerBound = lowerBoundSet.getUpperBound();
		final IContinuousShortBound upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return ContinuousShortInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(), upperBound.getValue(), upperBound.isClosed());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyContinuousShortSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public ISimpleContinuousShortSet makeSimpleCompositeSet(final IElementaryContinuousShortSet... parts)
	{
		return ContinuousShortSet.makeSet(parts);
	}

	@Override
	public List<? extends IElementaryContinuousShortSet> split(final int numberOfParts)
	{
		return Arrays.asList(ContinuousShortSplitter.getInstance().split(_bridged));
	}

	public List<IElementaryContinuousShortSet> split(final short[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IElementaryContinuousShortSet> r = new ArrayList<IElementaryContinuousShortSet>(values.length + 1);
		if(values.length == 0)
			r.add(_bridged);
		final EmptyContinuousShortSet empty = EmptyContinuousShortSet.getInstance();

		final IContinuousShortBound lowerBound = _bridged.getLowerBound();
		final IContinuousShortBound upperBound = _bridged.getUpperBound();
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
		short intervalStart;
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
				r.add(DegenerateContinuousShortSet.makeSet(values[i]));
				isIntervalStartClosed = false;
			}
			else
				r.add(empty);
			i++;
		}
		if(i<values.length)
		{
			short intervalEnd;
			boolean isIntervalEndClosed;

			if(isUpperBoundInfinity)
			{
				while(i<values.length)
				{
					intervalEnd = values[i];
					isIntervalEndClosed = !isIntervalStart[i];
					if((intervalEnd - intervalStart)>0)
						r.add(ContinuousShortInterval.makeInterval(intervalStart, isIntervalStartClosed, intervalEnd, isIntervalEndClosed));
					else
						if(isIntervalStartClosed && isIntervalEndClosed)
							r.add(DegenerateContinuousShortSet.makeSet(intervalEnd));
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
					r.add(ContinuousShortInterval.makeInterval(intervalStart, isIntervalStartClosed, intervalEnd, isIntervalEndClosed));
				}
				else
					if(isIntervalStartClosed && isIntervalEndClosed)
						r.add(DegenerateContinuousShortSet.makeSet(intervalEnd));
					else
						r.add(empty);
				intervalStart = intervalEnd;
				isIntervalStartClosed = !isIntervalEndClosed;
				i++;
			}

			if((compare = (intervalStart - upperBound.getValue()))<0)
			{
					r.add(ContinuousShortInterval.makeInterval(intervalStart, isIntervalStartClosed, upperBound.getValue(), _bridged.isUpperBoundClosed()));
			} else if((compare==0)&&isIntervalStartClosed&&_bridged.isUpperBoundClosed())
				r.add(DegenerateContinuousShortSet.makeSet(intervalStart));
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
						r.add(ContinuousShortInterval.makeInterval(intervalStart, isIntervalStartClosed, upperBound.getValue(), upperBound.isClosed()));
				else if ((compare == 0) && (isIntervalStartClosed && upperBound.isClosed()))
					r.add(DegenerateContinuousShortSet.makeSet(intervalStart));
			}
			else //compare < 0
				r.add(_bridged);
		}
		return r;
	}

	@Override
	public List<? extends IContinuousShortSet> splitOnRange(final IContinuousShortSet set)
	{

		final IContinuousShortBound lowerBound = set.getLowerBound();
		final IContinuousShortBound upperBound = set.getUpperBound();
		if(lowerBound == null || upperBound == null)
		{
			@SuppressWarnings("unchecked")
			final List<BridgedType> asList = Arrays.asList(_bridged);
			return asList;
		}

		final short[] values =  new short[]{lowerBound.getValue(), upperBound.getValue()};
		return split(values, new boolean[]{lowerBound.isClosed(), !upperBound.isClosed()});
	}

	@Override
	public List<? extends IContinuousShortSet> splitOnLowerBound(final IContinuousShortSet set)
	{
		final IContinuousShortBound lowerBound = set.getLowerBound();
		if(lowerBound == null)
		{
			@SuppressWarnings("unchecked")
			final List<BridgedType> asList = Arrays.asList(_bridged);
			return asList;
		}
		final
		short[] values = new short[]{lowerBound.getValue()};
		return split(values, new boolean[]{lowerBound.isClosed()});
	}

	@Override
	public List<? extends IContinuousShortSet> splitOnUpperBound(final IContinuousShortSet set)
	{
		final IContinuousShortBound upperBound = set.getUpperBound();
		if(upperBound == null)
			return Arrays.asList(_bridged);
		@SuppressWarnings("unchecked")
		final
		short[] values = new short[]{upperBound.getValue()};
		return split(values, new boolean[]{!upperBound.isClosed()});
	}
}