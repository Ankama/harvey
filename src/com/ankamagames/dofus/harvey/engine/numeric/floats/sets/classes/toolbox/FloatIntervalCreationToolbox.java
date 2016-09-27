/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.toolbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.FloatSplitter;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IFloatBound;
import com.ankamagames.dofus.harvey.numeric.floats.sets.classes.DegenerateFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.classes.EmptyFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.classes.FloatInterval;
import com.ankamagames.dofus.harvey.numeric.floats.sets.classes.FloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IElementaryFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatInterval;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.ISimpleFloatSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class FloatIntervalCreationToolbox<BridgedSet extends IFloatInterval>
extends FloatSetCreationToolbox<BridgedSet>
implements IIntervalCreationToolbox<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, EmptyFloatSet, BridgedSet>
{

	FloatSplitter _splitter;

	public FloatIntervalCreationToolbox(final BridgedSet bridged)
	{
		super(bridged);
		_splitter = FloatSplitter.getInstance();
	}

	@Override
	public IFloatInterval makeIntervalFromLowerBounds(final IFloatSet lowerBoundSet, final IFloatSet upperBoundSet)
	{
		final IFloatBound lowerBound = lowerBoundSet.getLowerBound();
		final IFloatBound upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return FloatInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(), upperBound.getValue(), upperBound.isClosed());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyFloatSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public IFloatInterval makeIntervalFromUpperBounds(final IFloatSet lowerBoundSet, final IFloatSet upperBoundSet)
	{
		final IFloatBound lowerBound = lowerBoundSet.getUpperBound();
		final IFloatBound upperBound = upperBoundSet.getUpperBound();
		if(lowerBound != null && upperBound != null)
			return FloatInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(), upperBound.getValue(), upperBound.isClosed());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyFloatSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public IFloatInterval makeInvertedInterval(final IFloatSet lowerBoundSet,
			final IFloatSet upperBoundSet)
	{
		final IFloatBound lowerBound = lowerBoundSet.getUpperBound();
		final IFloatBound upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return FloatInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(), upperBound.getValue(), upperBound.isClosed());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyFloatSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public ISimpleFloatSet makeSimpleCompositeSet(final IElementaryFloatSet... parts)
	{
		return FloatSet.makeSet(parts);
	}

	@Override
	public List<? extends IElementaryFloatSet> split(final int numberOfParts)
	{
		return Arrays.asList(_splitter.split(_bridged));
	}

	public List<IElementaryFloatSet> split(final float[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IElementaryFloatSet> r = new ArrayList<IElementaryFloatSet>(values.length + 1);
		if(values.length == 0)
			r.add(_bridged);
		final EmptyFloatSet empty = EmptyFloatSet.getInstance();

		final IFloatBound lowerBound = _bridged.getLowerBound();
		final IFloatBound upperBound = _bridged.getUpperBound();
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
		float intervalStart;
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
					r.add(DegenerateFloatSet.makeSet(values[i]));
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
			r.add(FloatInterval.makeRightBoundedInterval(intervalStart, !isIntervalStartClosed));
		}

		if(i<values.length)
		{
			float intervalEnd;
			boolean isIntervalEndClosed;

			if(isUpperBoundInfinity)
			{
				while(i<values.length)
				{
					intervalEnd = values[i];
					isIntervalEndClosed = !isIntervalStart[i];
					if((intervalEnd - intervalStart)>0)
						r.add(FloatInterval.makeInterval(intervalStart, isIntervalStartClosed, intervalEnd, isIntervalEndClosed));
					else
						if(isIntervalStartClosed && isIntervalEndClosed)
							r.add(DegenerateFloatSet.makeSet(intervalEnd));
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
					r.add(FloatInterval.makeInterval(intervalStart, isIntervalStartClosed, intervalEnd, isIntervalEndClosed));
				}
				else
					if(isIntervalStartClosed && isIntervalEndClosed)
						r.add(DegenerateFloatSet.makeSet(intervalEnd));
					else
						r.add(empty);
				intervalStart = intervalEnd;
				isIntervalStartClosed = !isIntervalEndClosed;
				i++;
			}

			if((compare = (intervalStart - upperBound.getValue()))<0)
			{
					r.add(FloatInterval.makeInterval(intervalStart, isIntervalStartClosed, upperBound.getValue(), _bridged.isUpperBoundClosed()));
			} else if((compare==0)&&isIntervalStartClosed&&_bridged.isUpperBoundClosed())
				r.add(DegenerateFloatSet.makeSet(intervalStart));
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
						r.add(FloatInterval.makeInterval(intervalStart, isIntervalStartClosed, upperBound.getValue(), upperBound.isClosed()));
				else if ((compare == 0) && (isIntervalStartClosed && upperBound.isClosed()))
					r.add(DegenerateFloatSet.makeSet(intervalStart));
			}
			else //compare < 0
				r.add(_bridged);
		}
		return r;
	}

	@Override
	public List<? extends IFloatSet> splitOnRange(final IFloatSet set)
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

		final IFloatBound lowerBound = set.getLowerBound();
		final IFloatBound upperBound = set.getUpperBound();
		if(lowerBound == null || upperBound == null)
		{
			@SuppressWarnings("unchecked")
			final List<BridgedSet> asList = Arrays.asList(_bridged);
			return asList;
		}

		@SuppressWarnings("unchecked")
		final float[] values = new float[]{lowerBound.getValue(), upperBound.getValue()};
		return split(values, new boolean[]{lowerBound.isClosed(), !upperBound.isClosed()});
	}

	@Override
	public List<? extends IFloatSet> splitOnLowerBound(final IFloatSet set)
	{
		final IFloatBound lowerBound = set.getLowerBound();
		if(set.isLowerBoundInfinity() || lowerBound == null)
		{
			@SuppressWarnings("unchecked")
			final List<BridgedSet> asList = Arrays.asList(_bridged);
			return asList;
		}
		@SuppressWarnings("unchecked")
		final
		float[] values = new float[]{lowerBound.getValue()};
		return split(values, new boolean[]{lowerBound.isClosed()});
	}

	@Override
	public List<? extends IFloatSet> splitOnUpperBound(final IFloatSet set)
	{
		final IFloatBound upperBound = set.getUpperBound();
		if(set.isUpperBoundInfinity() || upperBound == null)
			return Arrays.asList(_bridged);
		@SuppressWarnings("unchecked")
		final
		float[] values = new float[]{upperBound.getValue()};
		return split(values, new boolean[]{!upperBound.isClosed()});
	}

}
