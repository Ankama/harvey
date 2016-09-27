/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.sets.classes;

import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractContinuousInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBoundBridge;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.ContinuousShortBound;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.ContinuousShortIntervalBridge;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleContinuousShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ContinuousShortInterval
extends AbstractContinuousInterval<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval>
implements IContinuousShortInterval
{
	static public  ContinuousShortInterval makeInterval(final short lowerBound, final short upperBound)
	{
		return new ContinuousShortInterval(ContinuousShortBound.makeBound(true, lowerBound), ContinuousShortBound.makeBound(false, upperBound));
	}

	static public  ContinuousShortInterval makeInterval(final short lowerBound, final boolean lowerBoundClosed, final short upperBound, final boolean upperBoundClosed)
	{
		return new ContinuousShortInterval(ContinuousShortBound.makeBound(true, lowerBoundClosed, lowerBound), ContinuousShortBound.makeBound(false, upperBoundClosed, upperBound));
	}

	protected ContinuousShortIntervalBridge<ContinuousShortInterval> _bridge;
	protected AbstractIntervalBoundBridge<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval, ContinuousShortInterval> _boundBridge;
	protected IContinuousShortBound																														_lowerBound;
	protected IContinuousShortBound																														_upperBound;

	private ContinuousShortInterval(final IContinuousShortBound lowerBound, final IContinuousShortBound upperBound)
	{
		_lowerBound = lowerBound;
		_upperBound = upperBound;
		_bridge = new ContinuousShortIntervalBridge<ContinuousShortInterval>(this);
		_boundBridge = new AbstractIntervalBoundBridge<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval, ContinuousShortInterval>(this);
	}

	@Override
	protected ContinuousShortIntervalBridge<ContinuousShortInterval> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractIntervalBoundBridge<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval, ? extends AbstractInterval<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public IContinuousShortSet getAsSet()
	{
		return this;
	}

	@Override
	public ISimpleContinuousShortSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IElementaryContinuousShortSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public IContinuousShortInterval getAsInterval()
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
		final IContinuousShortBound lowerBound = getLowerBound();
		if (lowerBound != null)
			return _lowerBound.isClosed();
		return false;
	}

	@Override
	public boolean isUpperBoundClosed()
	{
		final IContinuousShortBound upperBound = getUpperBound();
		if (upperBound != null)
			return _upperBound.isClosed();
		return false;
	}

	@Override
	public boolean isPreceding(final IContinuousShortSet set)
	{
		final IContinuousShortBound upperBound = getUpperBound();
		final IContinuousShortBound otherLowerBound = set.getLowerBound();
		if(upperBound == null || otherLowerBound == null)
			return false;
		final short upperBoundValue = upperBound.getValue();
		return upperBoundValue == (otherLowerBound.getValue()) && isUpperBoundClosed()!=set.isLowerBoundClosed();
	}

	@Override
	public boolean isSucceeding(final IContinuousShortSet set)
	{
		if(isEmpty()||set.isEmpty())
			return false;
		final IContinuousShortBound lowerBound = getLowerBound();
		final IContinuousShortBound otherUpperBound = set.getUpperBound();
		if(lowerBound == null || otherUpperBound == null)
			return false;
		final short lowerBoundValue = lowerBound.getValue();
		return lowerBoundValue == (otherUpperBound.getValue()) && isLowerBoundClosed()!=set.isUpperBoundClosed();
	}


	@Override
	public double size()
	{
		final IContinuousShortBound lowerBound = getLowerBound();
		final IContinuousShortBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return 0;
		return (upperBound.getValue() - lowerBound.getValue());
	}

	@Override
	public @Nullable IContinuousShortBound getLowerBound()
	{
		final IContinuousShortBound lowerBound = _lowerBound;
		final IContinuousShortBound upperBound = _upperBound;

		final double compare = (lowerBound.getValue() - upperBound.getValue());
		if((compare<0) || (compare == 0 && lowerBound.isClosed() && upperBound.isClosed()))
			return lowerBound;
		return null;
	}

	@Override
	public @Nullable IContinuousShortBound getUpperBound()
	{
		final IContinuousShortBound lowerBound = _lowerBound;
		final IContinuousShortBound upperBound = _upperBound;

		final double compare = (lowerBound.getValue() - upperBound.getValue());
		if((compare<0) || (compare == 0 && lowerBound.isClosed() && upperBound.isClosed()))
			return upperBound;
		return null;
	}

	@Override
	public boolean contains(final short value)
	{
		final IContinuousShortBound lowerBound = getLowerBound();
		final IContinuousShortBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return false;
		final double compareLB = (lowerBound.getValue() - value);
		final double compareUB = (upperBound.getValue() - value);
		return (compareLB<0 || (isLowerBoundClosed() && compareLB == 0)) &&
				(compareUB>0 || (isUpperBoundClosed() && compareUB==0));
	}

	@Override
	public List<? extends IContinuousShortSet> split(final short[] values, final boolean[] isIntervalStart)
	{
		return getBridge().split(values, isIntervalStart);
	}

	@Override
	public List<? extends IContinuousShortSet> split(final short... values)
	{
		final boolean[] inNextInterval = new boolean[values.length];
		Arrays.fill(inNextInterval, true);
		return split(values, inNextInterval);
	}

	@Override
	public IContinuousShortInterval getSimpleSet()
	{
		return this;
	}

	@Override
	public String toString()
	{
		final IContinuousShortBound lowerBound = getLowerBound();
		final IContinuousShortBound upperBound = getUpperBound();
		if (lowerBound == null || upperBound == null)
			return "∅";
		if(isDegenerate())
			return "["+lowerBound.getValue()+"]";
		return (isLowerBoundClosed()?"[":"]")+lowerBound.getValue()+";"+upperBound.getValue()+(isUpperBoundClosed()?"]":"[");
	}
}