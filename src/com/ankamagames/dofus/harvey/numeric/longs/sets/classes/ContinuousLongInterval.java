/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.sets.classes;

import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractContinuousInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBoundBridge;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.ContinuousLongBound;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.ContinuousLongIntervalBridge;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongBound;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleContinuousLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ContinuousLongInterval
extends AbstractContinuousInterval<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval>
implements IContinuousLongInterval
{
	static public  ContinuousLongInterval makeInterval(final long lowerBound, final long upperBound)
	{
		return new ContinuousLongInterval(ContinuousLongBound.makeBound(true, lowerBound), ContinuousLongBound.makeBound(false, upperBound));
	}

	static public  ContinuousLongInterval makeInterval(final long lowerBound, final boolean lowerBoundClosed, final long upperBound, final boolean upperBoundClosed)
	{
		return new ContinuousLongInterval(ContinuousLongBound.makeBound(true, lowerBoundClosed, lowerBound), ContinuousLongBound.makeBound(false, upperBoundClosed, upperBound));
	}

	protected ContinuousLongIntervalBridge<ContinuousLongInterval> _bridge;
	protected AbstractIntervalBoundBridge<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval, ContinuousLongInterval> _boundBridge;
	protected IContinuousLongBound																														_lowerBound;
	protected IContinuousLongBound																														_upperBound;

	private ContinuousLongInterval(final IContinuousLongBound lowerBound, final IContinuousLongBound upperBound)
	{
		_lowerBound = lowerBound;
		_upperBound = upperBound;
		_bridge = new ContinuousLongIntervalBridge<ContinuousLongInterval>(this);
		_boundBridge = new AbstractIntervalBoundBridge<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval, ContinuousLongInterval>(this);
	}

	@Override
	protected ContinuousLongIntervalBridge<ContinuousLongInterval> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractIntervalBoundBridge<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval, ? extends AbstractInterval<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public IContinuousLongSet getAsSet()
	{
		return this;
	}

	@Override
	public ISimpleContinuousLongSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IElementaryContinuousLongSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public IContinuousLongInterval getAsInterval()
	{
		return this;
	}

	@Override
	public boolean isLowerBoundInfinity()
	{
		return false;
	}

	@Override
	public boolean isUpperBoundInfinity()
	{
		return false;
	}

	@Override
	public boolean isLowerBoundClosed()
	{
		final IContinuousLongBound lowerBound = getLowerBound();
		if (lowerBound != null)
			return _lowerBound.isClosed();
		return false;
	}

	@Override
	public boolean isUpperBoundClosed()
	{
		final IContinuousLongBound upperBound = getUpperBound();
		if (upperBound != null)
			return _upperBound.isClosed();
		return false;
	}

	@Override
	public boolean isPreceding(final IContinuousLongSet set)
	{
		final IContinuousLongBound upperBound = getUpperBound();
		final IContinuousLongBound otherLowerBound = set.getLowerBound();
		if(upperBound == null || otherLowerBound == null)
			return false;
		final long upperBoundValue = upperBound.getValue();
		return upperBoundValue == (otherLowerBound.getValue()) && isUpperBoundClosed()!=set.isLowerBoundClosed();
	}

	@Override
	public boolean isSucceeding(final IContinuousLongSet set)
	{
		if(isEmpty()||set.isEmpty())
			return false;
		final IContinuousLongBound lowerBound = getLowerBound();
		final IContinuousLongBound otherUpperBound = set.getUpperBound();
		if(lowerBound == null || otherUpperBound == null)
			return false;
		final long lowerBoundValue = lowerBound.getValue();
		return lowerBoundValue == (otherUpperBound.getValue()) && isLowerBoundClosed()!=set.isUpperBoundClosed();
	}


	@Override
	public double size()
	{
		final IContinuousLongBound lowerBound = getLowerBound();
		final IContinuousLongBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return 0;
		return (upperBound.getValue() - lowerBound.getValue());
	}

	@Override
	public @Nullable IContinuousLongBound getLowerBound()
	{
		final IContinuousLongBound lowerBound = _lowerBound;
		final IContinuousLongBound upperBound = _upperBound;

		final double compare = (lowerBound.getValue() - upperBound.getValue());
		if((compare<0) || (compare == 0 && lowerBound.isClosed() && upperBound.isClosed()))
			return lowerBound;
		return null;
	}

	@Override
	public @Nullable IContinuousLongBound getUpperBound()
	{
		final IContinuousLongBound lowerBound = _lowerBound;
		final IContinuousLongBound upperBound = _upperBound;

		final double compare = (lowerBound.getValue() - upperBound.getValue());
		if((compare<0) || (compare == 0 && lowerBound.isClosed() && upperBound.isClosed()))
			return upperBound;
		return null;
	}

	@Override
	public boolean contains(final long value)
	{
		final IContinuousLongBound lowerBound = getLowerBound();
		final IContinuousLongBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return false;
		final double compareLB = (lowerBound.getValue() - value);
		final double compareUB = (upperBound.getValue() - value);
		return (compareLB<0 || (isLowerBoundClosed() && compareLB == 0)) &&
				(compareUB>0 || (isUpperBoundClosed() && compareUB==0));
	}

	@Override
	public List<? extends IContinuousLongSet> split(final long[] values, final boolean[] isIntervalStart)
	{
		return getBridge().split(values, isIntervalStart);
	}

	@Override
	public List<? extends IContinuousLongSet> split(final long... values)
	{
		final boolean[] inNextInterval = new boolean[values.length];
		Arrays.fill(inNextInterval, true);
		return split(values, inNextInterval);
	}

	@Override
	public IContinuousLongInterval getSimpleSet()
	{
		return this;
	}

	@Override
	public String toString()
	{
		final IContinuousLongBound lowerBound = getLowerBound();
		final IContinuousLongBound upperBound = getUpperBound();
		if (lowerBound == null || upperBound == null)
			return "∅";
		if(isDegenerate())
			return "["+lowerBound.getValue()+"]";
		return (isLowerBoundClosed()?"[":"]")+lowerBound.getValue()+";"+upperBound.getValue()+(isUpperBoundClosed()?"]":"[");
	}
}