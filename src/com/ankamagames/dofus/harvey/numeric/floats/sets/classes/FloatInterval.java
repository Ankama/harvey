/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.floats.sets.classes;

import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractContinuousInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBoundBridge;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.FloatBound;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.FloatIntervalBridge;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IFloatBound;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IElementaryFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatInterval;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.ISimpleFloatSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FloatInterval
extends AbstractContinuousInterval<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval>
implements IFloatInterval
{
	protected FloatIntervalBridge<FloatInterval>	_bridge;
	protected AbstractIntervalBoundBridge<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, IFloatInterval> _boundBridge;
	protected FloatBound									_lowerBound;
	protected FloatBound									_upperBound;


		static public  FloatInterval makeInterval(final float lowerBound, final float upperBound)
		{
			return new FloatInterval(FloatBound.makeBound(true, lowerBound), FloatBound.makeBound(false, upperBound));
		}

		static public  FloatInterval makeInterval(final float lowerBound, final boolean lowerBoundClosed,  final float upperBound,
			final boolean upperBoundClosed)
		{
			return new FloatInterval(FloatBound.makeBound(true, lowerBoundClosed, lowerBound), FloatBound.makeBound(false, upperBoundClosed, upperBound));
		}

		static public  FloatInterval makeLeftBoundedInterval( final float lowerBound)
		{
			return new FloatInterval(FloatBound.makeBound(true, lowerBound), FloatBound.makeBound(false));
		}

		static public  FloatInterval makeLeftBoundedInterval( final float lowerBound, final boolean lowerBoundClosed)
		{
			return new FloatInterval(FloatBound.makeBound(true, lowerBoundClosed, lowerBound), FloatBound.makeBound(false));
		}

		static public  FloatInterval makeRightBoundedInterval( final float upperBound)
		{
			return new FloatInterval(FloatBound.makeBound(true), FloatBound.makeBound(false, upperBound));
		}

		static public  FloatInterval makeRightBoundedInterval( final float upperBound, final boolean upperBoundClosed)
		{
			return new FloatInterval(FloatBound.makeBound(true), FloatBound.makeBound(false, upperBoundClosed, upperBound));
		}

		static public  FloatInterval makeUniverse()
		{
			return new FloatInterval(FloatBound.makeBound(true), FloatBound.makeBound(false));
		}

	private FloatInterval(final float lowerBound, final boolean isLowerBoudClosed, final float upperBound, final boolean isUpperBoundClosed, final float defaultStep)
	{
		_lowerBound = FloatBound.makeBound(true, isLowerBoudClosed, lowerBound);
		_upperBound = FloatBound.makeBound(false, isUpperBoundClosed, upperBound);
		_bridge = new FloatIntervalBridge<FloatInterval>(this);
		_boundBridge = new AbstractIntervalBoundBridge<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, IFloatInterval>(this);
	}

	private FloatInterval(final FloatBound lowerBound, final FloatBound upperBound)
	{
		_lowerBound = lowerBound;
		_upperBound = upperBound;
		_bridge = new FloatIntervalBridge<FloatInterval>(this);
		_boundBridge = new AbstractIntervalBoundBridge<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, IFloatInterval>(this);
	}


	@Override
	protected FloatIntervalBridge<FloatInterval> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractIntervalBoundBridge<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, IFloatInterval> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public IFloatSet getAsSet()
	{
		return this;
	}

	@Override
	public IElementaryFloatSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public ISimpleFloatSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IFloatInterval getAsInterval()
	{
		return this;
	}

	@Override
	public boolean isLowerBoundInfinity()
	{
		return _lowerBound.isInfinity();
	}

	@Override
	public boolean isUpperBoundInfinity()
	{
		return _upperBound.isInfinity();
	}


	@Override
	public boolean isLowerBoundClosed()
	{
		final IFloatBound lowerBound = getLowerBound();
		if (lowerBound != null)
			return _lowerBound.isClosed();
		return false;
	}

	@Override
	public boolean isUpperBoundClosed()
	{
		final IFloatBound upperBound = getUpperBound();
		if (upperBound != null)
			return _upperBound.isClosed();
		return false;
	}


	@Override
	public boolean isPreceding(final IFloatSet set)
	{
		if(isUpperBoundInfinity() || set.isLowerBoundInfinity())
			return false;
		final IFloatBound upperBound = getUpperBound();
		final IFloatBound otherLowerBound = set.getLowerBound();
		if(upperBound == null || otherLowerBound == null)
			return false;
		final float upperBoundValue = upperBound.getValue();
		return (otherLowerBound.getValue() == upperBoundValue && isUpperBoundClosed()!=set.isLowerBoundClosed());
	}

	@Override
	public boolean isSucceeding(final IFloatSet set)
	{
		if(isLowerBoundInfinity() || set.isUpperBoundInfinity())
			return false;
		if(isEmpty()||set.isEmpty())
			return false;
		final IFloatBound lowerBound = getLowerBound();
		final IFloatBound otherUpperBound = set.getUpperBound();
		if(lowerBound == null || otherUpperBound == null)
			return false;
		final float lowerBoundValue = lowerBound.getValue();

		return (lowerBoundValue == otherUpperBound.getValue() && isLowerBoundClosed()!=set.isUpperBoundClosed());
	}

	@Override
	public boolean isEmpty()
	{
		if(isLowerBoundInfinity() || isUpperBoundInfinity()) // case where the interval is Universe/LeftBounded/RightBounded
			return false;
		return super.isEmpty();
	}

	@Override
	public double size()
	{
		final IFloatBound lowerBound = getLowerBound();
		final IFloatBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return 0;
		if(isLowerBoundInfinity() || isUpperBoundInfinity())
			return Double.POSITIVE_INFINITY;
		return upperBound.getValue() - lowerBound.getValue();
	}

	@Override
	public @Nullable IFloatBound getLowerBound()
	{
		final IFloatBound lowerBound = _lowerBound;
		final IFloatBound upperBound = _upperBound;

		if(isLowerBoundInfinity() || isUpperBoundInfinity())
			return lowerBound;
		final double compare = lowerBound.getValue() - upperBound.getValue();
		if((compare<0) || (compare == 0 && lowerBound.isClosed() && upperBound.isClosed()))
			return lowerBound;
		return null;
	}

	@Override
	public @Nullable IFloatBound getUpperBound()
	{
		final IFloatBound lowerBound = _lowerBound;
		final IFloatBound upperBound = _upperBound;

		if((isUpperBoundInfinity() || isLowerBoundInfinity()))
			return upperBound;
		final double compare = lowerBound.getValue() - upperBound.getValue();
		if((compare<0) || (compare == 0 && lowerBound.isClosed() && upperBound.isClosed()))
			return upperBound;
		return null;
	}

	@Override
	public boolean isDegenerate()
	{
		final IFloatBound lowerBound = getLowerBound();
		final IFloatBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return false;
		return (isLowerBoundClosed() && isUpperBoundClosed() && (lowerBound.getValue() == upperBound.getValue()));
	}

	@Override
	public boolean equals( final @Nullable Object obj)
	{
		if(obj instanceof IFloatSet)
		{
			final IFloatSet set = (IFloatSet)obj;
			if(set.isInterval())
			{
				final IFloatBound lowerBound = set.getLowerBound();
				final IFloatBound upperBound = set.getUpperBound();
				if(lowerBound == null || upperBound == null)
					return isEmpty();
				final IFloatBound myLowerBound = getLowerBound();
				final IFloatBound myUpperBound = getUpperBound();
				if(myLowerBound == null || myUpperBound == null)
					return false;
				if(lowerBound.isInfinity())
				{
					if(myLowerBound.isInfinity())
						return upperBound.compareTo(myUpperBound)==0;
					return false;
				}
				else if(upperBound.isInfinity())
				{
					if(myUpperBound.isInfinity())
						return lowerBound.compareTo(myLowerBound) == 0;
					return false;
				}
				return (upperBound.compareTo(myUpperBound)==0 && lowerBound.compareTo(myLowerBound)==0);
			}
		}
		if(isDegenerate()&&(obj instanceof Float))
		{
			final IFloatBound lowerBound = getLowerBound();
			if(lowerBound == null)
				throw new NullPointerException();
			return lowerBound.getValue()==((Float)obj);
		}
		return false;
	}

	@Override
	public boolean contains(final float value)
	{
		final IFloatBound lowerBound = getLowerBound();
		final IFloatBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return false;
		if(!isLowerBoundInfinity())
			if(!isUpperBoundInfinity())
			{
				final double compareLB = lowerBound.getValue() - value;
				final double compareUB = upperBound.getValue() - value;
				return (compareLB<0 || (isLowerBoundClosed() && compareLB == 0)) &&
						(compareUB>0 || (isUpperBoundClosed() && compareUB==0));
			}
			else
			{
				final double compare = lowerBound.getValue() - value;
				return compare < 0 || (isLowerBoundClosed() && compare == 0);
			}
		if(!isUpperBoundInfinity())
		{
			final double compare = upperBound.getValue() - value;
			return compare > 0 || (isUpperBoundClosed() && compare == 0);
		}
		return true;
	}

	@Override
	public List<? extends IFloatSet> split(final float[] values, final boolean[] isIntervalStart)
	{
		return getBridge().split(values, isIntervalStart);
	}

	@Override
	public List<? extends IFloatSet> split(final float... values)
	{
		final boolean[] inNextInterval = new boolean[values.length];
		Arrays.fill(inNextInterval, true);
		return split(values, inNextInterval);
	}

	@Override
	public FloatInterval getSimpleSet()
	{
		return this;
	}

	@Override
	public String toString()
	{
		final IFloatBound lowerBound = getLowerBound();
		final IFloatBound upperBound = getUpperBound();
		if (lowerBound == null || upperBound == null)
			return "∅";
		if(!isLowerBoundInfinity())
			if(!isUpperBoundInfinity())
			{
				if(isDegenerate())
					return "["+lowerBound.getValue()+"]";
				return (isLowerBoundClosed()?"[":"]")+lowerBound.getValue()+";"+upperBound.getValue()+(isUpperBoundClosed()?"]":"[");
			}
			else
				return  (isLowerBoundClosed()?"[":"]")+lowerBound.getValue()+";+∞[";
		if(!isUpperBoundInfinity())
			return "]-∞;"+upperBound.getValue()+(isUpperBoundClosed()?"]":"[");
		return "Ω";
	}
}
