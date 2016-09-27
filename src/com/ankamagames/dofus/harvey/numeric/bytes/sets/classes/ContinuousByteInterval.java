/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.classes;

import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractContinuousInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBoundBridge;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.ContinuousByteBound;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.ContinuousByteIntervalBridge;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleContinuousByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ContinuousByteInterval
extends AbstractContinuousInterval<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IContinuousByteInterval>
implements IContinuousByteInterval
{
	static public  ContinuousByteInterval makeInterval(final byte lowerBound, final byte upperBound)
	{
		return new ContinuousByteInterval(ContinuousByteBound.makeBound(true, lowerBound), ContinuousByteBound.makeBound(false, upperBound));
	}

	static public  ContinuousByteInterval makeInterval(final byte lowerBound, final boolean lowerBoundClosed, final byte upperBound, final boolean upperBoundClosed)
	{
		return new ContinuousByteInterval(ContinuousByteBound.makeBound(true, lowerBoundClosed, lowerBound), ContinuousByteBound.makeBound(false, upperBoundClosed, upperBound));
	}

	protected ContinuousByteIntervalBridge<ContinuousByteInterval> _bridge;
	protected AbstractIntervalBoundBridge<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IContinuousByteInterval, ContinuousByteInterval> _boundBridge;
	protected IContinuousByteBound																														_lowerBound;
	protected IContinuousByteBound																														_upperBound;

	private ContinuousByteInterval(final IContinuousByteBound lowerBound, final IContinuousByteBound upperBound)
	{
		_lowerBound = lowerBound;
		_upperBound = upperBound;
		_bridge = new ContinuousByteIntervalBridge<ContinuousByteInterval>(this);
		_boundBridge = new AbstractIntervalBoundBridge<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IContinuousByteInterval, ContinuousByteInterval>(this);
	}

	@Override
	protected ContinuousByteIntervalBridge<ContinuousByteInterval> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractIntervalBoundBridge<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IContinuousByteInterval, ? extends AbstractInterval<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IContinuousByteInterval>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public IContinuousByteSet getAsSet()
	{
		return this;
	}

	@Override
	public ISimpleContinuousByteSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IElementaryContinuousByteSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public IContinuousByteInterval getAsInterval()
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
		final IContinuousByteBound lowerBound = getLowerBound();
		if (lowerBound != null)
			return _lowerBound.isClosed();
		return false;
	}

	@Override
	public boolean isUpperBoundClosed()
	{
		final IContinuousByteBound upperBound = getUpperBound();
		if (upperBound != null)
			return _upperBound.isClosed();
		return false;
	}

	@Override
	public boolean isPreceding(final IContinuousByteSet set)
	{
		final IContinuousByteBound upperBound = getUpperBound();
		final IContinuousByteBound otherLowerBound = set.getLowerBound();
		if(upperBound == null || otherLowerBound == null)
			return false;
		final byte upperBoundValue = upperBound.getValue();
		return upperBoundValue == (otherLowerBound.getValue()) && isUpperBoundClosed()!=set.isLowerBoundClosed();
	}

	@Override
	public boolean isSucceeding(final IContinuousByteSet set)
	{
		if(isEmpty()||set.isEmpty())
			return false;
		final IContinuousByteBound lowerBound = getLowerBound();
		final IContinuousByteBound otherUpperBound = set.getUpperBound();
		if(lowerBound == null || otherUpperBound == null)
			return false;
		final byte lowerBoundValue = lowerBound.getValue();
		return lowerBoundValue == (otherUpperBound.getValue()) && isLowerBoundClosed()!=set.isUpperBoundClosed();
	}


	@Override
	public double size()
	{
		final IContinuousByteBound lowerBound = getLowerBound();
		final IContinuousByteBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return 0;
		return (upperBound.getValue() - lowerBound.getValue());
	}

	@Override
	public @Nullable IContinuousByteBound getLowerBound()
	{
		final IContinuousByteBound lowerBound = _lowerBound;
		final IContinuousByteBound upperBound = _upperBound;

		final double compare = (lowerBound.getValue() - upperBound.getValue());
		if((compare<0) || (compare == 0 && lowerBound.isClosed() && upperBound.isClosed()))
			return lowerBound;
		return null;
	}

	@Override
	public @Nullable IContinuousByteBound getUpperBound()
	{
		final IContinuousByteBound lowerBound = _lowerBound;
		final IContinuousByteBound upperBound = _upperBound;

		final double compare = (lowerBound.getValue() - upperBound.getValue());
		if((compare<0) || (compare == 0 && lowerBound.isClosed() && upperBound.isClosed()))
			return upperBound;
		return null;
	}

	@Override
	public boolean contains(final byte value)
	{
		final IContinuousByteBound lowerBound = getLowerBound();
		final IContinuousByteBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return false;
		final double compareLB = (lowerBound.getValue() - value);
		final double compareUB = (upperBound.getValue() - value);
		return (compareLB<0 || (isLowerBoundClosed() && compareLB == 0)) &&
				(compareUB>0 || (isUpperBoundClosed() && compareUB==0));
	}

	@Override
	public List<? extends IContinuousByteSet> split(final byte[] values, final boolean[] isIntervalStart)
	{
		return getBridge().split(values, isIntervalStart);
	}

	@Override
	public List<? extends IContinuousByteSet> split(final byte... values)
	{
		final boolean[] inNextInterval = new boolean[values.length];
		Arrays.fill(inNextInterval, true);
		return split(values, inNextInterval);
	}

	@Override
	public IContinuousByteInterval getSimpleSet()
	{
		return this;
	}

	@Override
	public String toString()
	{
		final IContinuousByteBound lowerBound = getLowerBound();
		final IContinuousByteBound upperBound = getUpperBound();
		if (lowerBound == null || upperBound == null)
			return "∅";
		if(isDegenerate())
			return "["+lowerBound.getValue()+"]";
		return (isLowerBoundClosed()?"[":"]")+lowerBound.getValue()+";"+upperBound.getValue()+(isUpperBoundClosed()?"]":"[");
	}
}