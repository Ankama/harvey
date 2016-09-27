/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.toolbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.DoubleSplitter;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.interfaces.IDoubleBound;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.classes.DegenerateDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.classes.EmptyDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.classes.DoubleInterval;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.classes.DoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IElementaryDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleInterval;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.ISimpleDoubleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class DoubleIntervalCreationToolbox<BridgedSet extends IDoubleInterval>
extends DoubleSetCreationToolbox<BridgedSet>
implements IIntervalCreationToolbox<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, EmptyDoubleSet, BridgedSet>
{

	DoubleSplitter _splitter;

	public DoubleIntervalCreationToolbox(final BridgedSet bridged)
	{
		super(bridged);
		_splitter = DoubleSplitter.getInstance();
	}

	@Override
	public IDoubleInterval makeIntervalFromLowerBounds(final IDoubleSet lowerBoundSet, final IDoubleSet upperBoundSet)
	{
		final IDoubleBound lowerBound = lowerBoundSet.getLowerBound();
		final IDoubleBound upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return DoubleInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(), upperBound.getValue(), upperBound.isClosed());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyDoubleSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public IDoubleInterval makeIntervalFromUpperBounds(final IDoubleSet lowerBoundSet, final IDoubleSet upperBoundSet)
	{
		final IDoubleBound lowerBound = lowerBoundSet.getUpperBound();
		final IDoubleBound upperBound = upperBoundSet.getUpperBound();
		if(lowerBound != null && upperBound != null)
			return DoubleInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(), upperBound.getValue(), upperBound.isClosed());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyDoubleSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public IDoubleInterval makeInvertedInterval(final IDoubleSet lowerBoundSet,
			final IDoubleSet upperBoundSet)
	{
		final IDoubleBound lowerBound = lowerBoundSet.getUpperBound();
		final IDoubleBound upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return DoubleInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(), upperBound.getValue(), upperBound.isClosed());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyDoubleSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public ISimpleDoubleSet makeSimpleCompositeSet(final IElementaryDoubleSet... parts)
	{
		return DoubleSet.makeSet(parts);
	}

	@Override
	public List<? extends IElementaryDoubleSet> split(final int numberOfParts)
	{
		return Arrays.asList(_splitter.split(_bridged));
	}

	public List<IElementaryDoubleSet> split(final double[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IElementaryDoubleSet> r = new ArrayList<IElementaryDoubleSet>(values.length + 1);
		if(values.length == 0)
			r.add(_bridged);
		final EmptyDoubleSet empty = EmptyDoubleSet.getInstance();

		final IDoubleBound lowerBound = _bridged.getLowerBound();
		final IDoubleBound upperBound = _bridged.getUpperBound();
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
		double intervalStart;
		if(!isLowerBoundInfinity)
		{
			if(lowerBound==null)
				throw new NullPointerException();
			while((i<values.length) && (compare = values[i]-lowerBound.getValue())<0)
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
					r.add(DegenerateDoubleSet.makeSet(values[i]));
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
			r.add(DoubleInterval.makeRightBoundedInterval(intervalStart, !isIntervalStartClosed));
		}

		if(i<values.length)
		{
			double intervalEnd;
			boolean isIntervalEndClosed;

			if(isUpperBoundInfinity)
			{
				while(i<values.length)
				{
					intervalEnd = values[i];
					isIntervalEndClosed = !isIntervalStart[i];
					if((intervalEnd - intervalStart)>0)
						r.add(DoubleInterval.makeInterval(intervalStart, isIntervalStartClosed, intervalEnd, isIntervalEndClosed));
					else
						if(isIntervalStartClosed && isIntervalEndClosed)
							r.add(DegenerateDoubleSet.makeSet(intervalEnd));
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
			while((i<values.length) && (compare = (intervalEnd = values[i])- upperBound.getValue())<=0)
			{
				isIntervalEndClosed = !isIntervalStart[i];
				if(intervalEnd - intervalStart>0)
				{
					r.add(DoubleInterval.makeInterval(intervalStart, isIntervalStartClosed, intervalEnd, isIntervalEndClosed));
				}
				else
					if(isIntervalStartClosed && isIntervalEndClosed)
						r.add(DegenerateDoubleSet.makeSet(intervalEnd));
					else
						r.add(empty);
				intervalStart = intervalEnd;
				isIntervalStartClosed = !isIntervalEndClosed;
				i++;
			}

			if((compare = (intervalStart - upperBound.getValue()))<0)
			{
					r.add(DoubleInterval.makeInterval(intervalStart, isIntervalStartClosed, upperBound.getValue(), _bridged.isUpperBoundClosed()));
			} else if((compare==0)&&isIntervalStartClosed&&_bridged.isUpperBoundClosed())
				r.add(DegenerateDoubleSet.makeSet(intervalStart));
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
						r.add(DoubleInterval.makeInterval(intervalStart, isIntervalStartClosed, upperBound.getValue(), upperBound.isClosed()));
				else if ((compare == 0) && (isIntervalStartClosed && upperBound.isClosed()))
					r.add(DegenerateDoubleSet.makeSet(intervalStart));
			}
			else //compare < 0
				r.add(_bridged);
		}
		return r;
	}

	@Override
	public List<? extends IDoubleSet> splitOnRange(final IDoubleSet set)
	{
		if(set.isLowerBoundInfinity())
			if(set.isUpperBoundInfinity())
			{
				@SuppressWarnings("unchecked")
				final List<BridgedSet> asList = Arrays.asList(_bridged);
				return asList;
			}
			else
				return splitOnUpperBound(set);
		if(set.isUpperBoundInfinity())
			return splitOnLowerBound(set);

		final IDoubleBound lowerBound = set.getLowerBound();
		final IDoubleBound upperBound = set.getUpperBound();
		if(lowerBound == null || upperBound == null)
		{
			@SuppressWarnings("unchecked")
			final List<BridgedSet> asList = Arrays.asList(_bridged);
			return asList;
		}

		@SuppressWarnings("unchecked")
		final double[] values = new double[]{lowerBound.getValue(), upperBound.getValue()};
		return split(values, new boolean[]{lowerBound.isClosed(), !upperBound.isClosed()});
	}

	@Override
	public List<? extends IDoubleSet> splitOnLowerBound(final IDoubleSet set)
	{
		final IDoubleBound lowerBound = set.getLowerBound();
		if(set.isLowerBoundInfinity() || lowerBound == null)
		{
			@SuppressWarnings("unchecked")
			final List<BridgedSet> asList = Arrays.asList(_bridged);
			return asList;
		}
		@SuppressWarnings("unchecked")
		final
		double[] values = new double[]{lowerBound.getValue()};
		return split(values, new boolean[]{lowerBound.isClosed()});
	}

	@Override
	public List<? extends IDoubleSet> splitOnUpperBound(final IDoubleSet set)
	{
		final IDoubleBound upperBound = set.getUpperBound();
		if(set.isUpperBoundInfinity() || upperBound == null)
			return Arrays.asList(_bridged);
		@SuppressWarnings("unchecked")
		final
		double[] values = new double[]{upperBound.getValue()};
		return split(values, new boolean[]{!upperBound.isClosed()});
	}

}
