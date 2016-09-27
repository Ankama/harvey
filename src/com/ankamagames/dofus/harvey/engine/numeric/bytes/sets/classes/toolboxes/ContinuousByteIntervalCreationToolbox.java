/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.comparators.ContinuousByteSplitter;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.ContinuousByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.ContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.DegenerateContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.EmptyContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IEmptyContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleContinuousByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousByteIntervalCreationToolbox<BridgedType extends IContinuousByteInterval>
extends ContinuousByteSetCreationToolbox<BridgedType>
implements IIntervalCreationToolbox<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IContinuousByteInterval, IEmptyContinuousByteSet, BridgedType>
{

	public ContinuousByteIntervalCreationToolbox(final BridgedType bridged)
	{
		super(bridged);
	}

	@Override
	public IContinuousByteInterval makeIntervalFromLowerBounds(final IContinuousByteSet lowerBoundSet, final IContinuousByteSet upperBoundSet)
	{
		final IContinuousByteBound lowerBound = lowerBoundSet.getLowerBound();
		final IContinuousByteBound upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return ContinuousByteInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(), upperBound.getValue(), upperBound.isClosed());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyContinuousByteSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public IContinuousByteInterval makeIntervalFromUpperBounds(final IContinuousByteSet lowerBoundSet, final IContinuousByteSet upperBoundSet)
	{
		final IContinuousByteBound lowerBound = lowerBoundSet.getUpperBound();
		final IContinuousByteBound upperBound = upperBoundSet.getUpperBound();
		if(lowerBound != null && upperBound != null)
			return ContinuousByteInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(), upperBound.getValue(), upperBound.isClosed());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyContinuousByteSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public IContinuousByteInterval makeInvertedInterval(final IContinuousByteSet lowerBoundSet,
			final IContinuousByteSet upperBoundSet)
	{
		final IContinuousByteBound lowerBound = lowerBoundSet.getUpperBound();
		final IContinuousByteBound upperBound = upperBoundSet.getLowerBound();
		if(lowerBound != null && upperBound != null)
			return ContinuousByteInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(), upperBound.getValue(), upperBound.isClosed());
		if((upperBound==null)&&(lowerBound==null))
			return EmptyContinuousByteSet.getInstance();
		throw new NullPointerException();
	}

	@Override
	public ISimpleContinuousByteSet makeSimpleCompositeSet(final IElementaryContinuousByteSet... parts)
	{
		return ContinuousByteSet.makeSet(parts);
	}

	@Override
	public List<? extends IElementaryContinuousByteSet> split(final int numberOfParts)
	{
		return Arrays.asList(ContinuousByteSplitter.getInstance().split(_bridged));
	}

	public List<IElementaryContinuousByteSet> split(final byte[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IElementaryContinuousByteSet> r = new ArrayList<IElementaryContinuousByteSet>(values.length + 1);
		if(values.length == 0)
			r.add(_bridged);
		final EmptyContinuousByteSet empty = EmptyContinuousByteSet.getInstance();

		final IContinuousByteBound lowerBound = _bridged.getLowerBound();
		final IContinuousByteBound upperBound = _bridged.getUpperBound();
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
		byte intervalStart;
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
				r.add(DegenerateContinuousByteSet.makeSet(values[i]));
				isIntervalStartClosed = false;
			}
			else
				r.add(empty);
			i++;
		}
		if(i<values.length)
		{
			byte intervalEnd;
			boolean isIntervalEndClosed;

			if(isUpperBoundInfinity)
			{
				while(i<values.length)
				{
					intervalEnd = values[i];
					isIntervalEndClosed = !isIntervalStart[i];
					if((intervalEnd - intervalStart)>0)
						r.add(ContinuousByteInterval.makeInterval(intervalStart, isIntervalStartClosed, intervalEnd, isIntervalEndClosed));
					else
						if(isIntervalStartClosed && isIntervalEndClosed)
							r.add(DegenerateContinuousByteSet.makeSet(intervalEnd));
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
					r.add(ContinuousByteInterval.makeInterval(intervalStart, isIntervalStartClosed, intervalEnd, isIntervalEndClosed));
				}
				else
					if(isIntervalStartClosed && isIntervalEndClosed)
						r.add(DegenerateContinuousByteSet.makeSet(intervalEnd));
					else
						r.add(empty);
				intervalStart = intervalEnd;
				isIntervalStartClosed = !isIntervalEndClosed;
				i++;
			}

			if((compare = (intervalStart - upperBound.getValue()))<0)
			{
					r.add(ContinuousByteInterval.makeInterval(intervalStart, isIntervalStartClosed, upperBound.getValue(), _bridged.isUpperBoundClosed()));
			} else if((compare==0)&&isIntervalStartClosed&&_bridged.isUpperBoundClosed())
				r.add(DegenerateContinuousByteSet.makeSet(intervalStart));
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
						r.add(ContinuousByteInterval.makeInterval(intervalStart, isIntervalStartClosed, upperBound.getValue(), upperBound.isClosed()));
				else if ((compare == 0) && (isIntervalStartClosed && upperBound.isClosed()))
					r.add(DegenerateContinuousByteSet.makeSet(intervalStart));
			}
			else //compare < 0
				r.add(_bridged);
		}
		return r;
	}

	@Override
	public List<? extends IContinuousByteSet> splitOnRange(final IContinuousByteSet set)
	{

		final IContinuousByteBound lowerBound = set.getLowerBound();
		final IContinuousByteBound upperBound = set.getUpperBound();
		if(lowerBound == null || upperBound == null)
		{
			@SuppressWarnings("unchecked")
			final List<BridgedType> asList = Arrays.asList(_bridged);
			return asList;
		}

		final byte[] values =  new byte[]{lowerBound.getValue(), upperBound.getValue()};
		return split(values, new boolean[]{lowerBound.isClosed(), !upperBound.isClosed()});
	}

	@Override
	public List<? extends IContinuousByteSet> splitOnLowerBound(final IContinuousByteSet set)
	{
		final IContinuousByteBound lowerBound = set.getLowerBound();
		if(lowerBound == null)
		{
			@SuppressWarnings("unchecked")
			final List<BridgedType> asList = Arrays.asList(_bridged);
			return asList;
		}
		final
		byte[] values = new byte[]{lowerBound.getValue()};
		return split(values, new boolean[]{lowerBound.isClosed()});
	}

	@Override
	public List<? extends IContinuousByteSet> splitOnUpperBound(final IContinuousByteSet set)
	{
		final IContinuousByteBound upperBound = set.getUpperBound();
		if(upperBound == null)
			return Arrays.asList(_bridged);
		@SuppressWarnings("unchecked")
		final
		byte[] values = new byte[]{upperBound.getValue()};
		return split(values, new boolean[]{!upperBound.isClosed()});
	}
}