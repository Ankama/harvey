/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.classes;

import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractContinuousInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBoundBridge;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.ContinuousIntegerBound;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.ContinuousIntegerIntervalBridge;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerBound;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleContinuousIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ContinuousIntegerInterval
extends AbstractContinuousInterval<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, IContinuousIntegerInterval>
implements IContinuousIntegerInterval
{
	static public  ContinuousIntegerInterval makeInterval(final int lowerBound, final int upperBound)
	{
		return new ContinuousIntegerInterval(ContinuousIntegerBound.makeBound(true, lowerBound), ContinuousIntegerBound.makeBound(false, upperBound));
	}

	static public  ContinuousIntegerInterval makeInterval(final int lowerBound, final boolean lowerBoundClosed, final int upperBound, final boolean upperBoundClosed)
	{
		return new ContinuousIntegerInterval(ContinuousIntegerBound.makeBound(true, lowerBoundClosed, lowerBound), ContinuousIntegerBound.makeBound(false, upperBoundClosed, upperBound));
	}

	protected ContinuousIntegerIntervalBridge<ContinuousIntegerInterval> _bridge;
	protected AbstractIntervalBoundBridge<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, IContinuousIntegerInterval, ContinuousIntegerInterval> _boundBridge;
	protected IContinuousIntegerBound																														_lowerBound;
	protected IContinuousIntegerBound																														_upperBound;

	private ContinuousIntegerInterval(final IContinuousIntegerBound lowerBound, final IContinuousIntegerBound upperBound)
	{
		_lowerBound = lowerBound;
		_upperBound = upperBound;
		_bridge = new ContinuousIntegerIntervalBridge<ContinuousIntegerInterval>(this);
		_boundBridge = new AbstractIntervalBoundBridge<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, IContinuousIntegerInterval, ContinuousIntegerInterval>(this);
	}

	@Override
	protected ContinuousIntegerIntervalBridge<ContinuousIntegerInterval> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractIntervalBoundBridge<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, IContinuousIntegerInterval, ? extends AbstractInterval<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, IContinuousIntegerInterval>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public IContinuousIntegerSet getAsSet()
	{
		return this;
	}

	@Override
	public ISimpleContinuousIntegerSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IElementaryContinuousIntegerSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public IContinuousIntegerInterval getAsInterval()
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
		final IContinuousIntegerBound lowerBound = getLowerBound();
		if (lowerBound != null)
			return _lowerBound.isClosed();
		return false;
	}

	@Override
	public boolean isUpperBoundClosed()
	{
		final IContinuousIntegerBound upperBound = getUpperBound();
		if (upperBound != null)
			return _upperBound.isClosed();
		return false;
	}

	@Override
	public boolean isPreceding(final IContinuousIntegerSet set)
	{
		final IContinuousIntegerBound upperBound = getUpperBound();
		final IContinuousIntegerBound otherLowerBound = set.getLowerBound();
		if(upperBound == null || otherLowerBound == null)
			return false;
		final int upperBoundValue = upperBound.getValue();
		return upperBoundValue == (otherLowerBound.getValue()) && isUpperBoundClosed()!=set.isLowerBoundClosed();
	}

	@Override
	public boolean isSucceeding(final IContinuousIntegerSet set)
	{
		if(isEmpty()||set.isEmpty())
			return false;
		final IContinuousIntegerBound lowerBound = getLowerBound();
		final IContinuousIntegerBound otherUpperBound = set.getUpperBound();
		if(lowerBound == null || otherUpperBound == null)
			return false;
		final int lowerBoundValue = lowerBound.getValue();
		return lowerBoundValue == (otherUpperBound.getValue()) && isLowerBoundClosed()!=set.isUpperBoundClosed();
	}


	@Override
	public double size()
	{
		final IContinuousIntegerBound lowerBound = getLowerBound();
		final IContinuousIntegerBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return 0;
		return (upperBound.getValue() - lowerBound.getValue());
	}

	@Override
	public @Nullable IContinuousIntegerBound getLowerBound()
	{
		final IContinuousIntegerBound lowerBound = _lowerBound;
		final IContinuousIntegerBound upperBound = _upperBound;

		final double compare = (lowerBound.getValue() - upperBound.getValue());
		if((compare<0) || (compare == 0 && lowerBound.isClosed() && upperBound.isClosed()))
			return lowerBound;
		return null;
	}

	@Override
	public @Nullable IContinuousIntegerBound getUpperBound()
	{
		final IContinuousIntegerBound lowerBound = _lowerBound;
		final IContinuousIntegerBound upperBound = _upperBound;

		final double compare = (lowerBound.getValue() - upperBound.getValue());
		if((compare<0) || (compare == 0 && lowerBound.isClosed() && upperBound.isClosed()))
			return upperBound;
		return null;
	}

	@Override
	public boolean contains(final int value)
	{
		final IContinuousIntegerBound lowerBound = getLowerBound();
		final IContinuousIntegerBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return false;
		final double compareLB = (lowerBound.getValue() - value);
		final double compareUB = (upperBound.getValue() - value);
		return (compareLB<0 || (isLowerBoundClosed() && compareLB == 0)) &&
				(compareUB>0 || (isUpperBoundClosed() && compareUB==0));
	}

	@Override
	public List<? extends IContinuousIntegerSet> split(final int[] values, final boolean[] isIntervalStart)
	{
		return getBridge().split(values, isIntervalStart);
	}

	@Override
	public List<? extends IContinuousIntegerSet> split(final int... values)
	{
		final boolean[] inNextInterval = new boolean[values.length];
		Arrays.fill(inNextInterval, true);
		return split(values, inNextInterval);
	}

	@Override
	public IContinuousIntegerInterval getSimpleSet()
	{
		return this;
	}

	@Override
	public String toString()
	{
		final IContinuousIntegerBound lowerBound = getLowerBound();
		final IContinuousIntegerBound upperBound = getUpperBound();
		if (lowerBound == null || upperBound == null)
			return "∅";
		if(isDegenerate())
			return "["+lowerBound.getValue()+"]";
		return (isLowerBoundClosed()?"[":"]")+lowerBound.getValue()+";"+upperBound.getValue()+(isUpperBoundClosed()?"]":"[");
	}
}