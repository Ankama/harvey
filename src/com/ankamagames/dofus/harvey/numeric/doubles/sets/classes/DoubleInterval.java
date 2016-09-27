/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.doubles.sets.classes;

import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractContinuousInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBoundBridge;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.DoubleBound;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.DoubleIntervalBridge;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.interfaces.IDoubleBound;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IElementaryDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleInterval;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.ISimpleDoubleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class DoubleInterval
extends AbstractContinuousInterval<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval>
implements IDoubleInterval
{
	protected DoubleIntervalBridge<DoubleInterval>	_bridge;
	protected AbstractIntervalBoundBridge<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, IDoubleInterval> _boundBridge;
	protected DoubleBound									_lowerBound;
	protected DoubleBound									_upperBound;


		static public  DoubleInterval makeInterval(final double lowerBound, final double upperBound)
		{
			return new DoubleInterval(DoubleBound.makeBound(true, lowerBound), DoubleBound.makeBound(false, upperBound));
		}

		static public  DoubleInterval makeInterval(final double lowerBound, final boolean lowerBoundClosed,  final double upperBound,
			final boolean upperBoundClosed)
		{
			return new DoubleInterval(DoubleBound.makeBound(true, lowerBoundClosed, lowerBound), DoubleBound.makeBound(false, upperBoundClosed, upperBound));
		}

		static public  DoubleInterval makeLeftBoundedInterval( final double lowerBound)
		{
			return new DoubleInterval(DoubleBound.makeBound(true, lowerBound), DoubleBound.makeBound(false));
		}

		static public  DoubleInterval makeLeftBoundedInterval( final double lowerBound, final boolean lowerBoundClosed)
		{
			return new DoubleInterval(DoubleBound.makeBound(true, lowerBoundClosed, lowerBound), DoubleBound.makeBound(false));
		}

		static public  DoubleInterval makeRightBoundedInterval( final double upperBound)
		{
			return new DoubleInterval(DoubleBound.makeBound(true), DoubleBound.makeBound(false, upperBound));
		}

		static public  DoubleInterval makeRightBoundedInterval( final double upperBound, final boolean upperBoundClosed)
		{
			return new DoubleInterval(DoubleBound.makeBound(true), DoubleBound.makeBound(false, upperBoundClosed, upperBound));
		}

		static public  DoubleInterval makeUniverse()
		{
			return new DoubleInterval(DoubleBound.makeBound(true), DoubleBound.makeBound(false));
		}

	private DoubleInterval(final double lowerBound, final boolean isLowerBoudClosed, final double upperBound, final boolean isUpperBoundClosed, final double defaultStep)
	{
		_lowerBound = DoubleBound.makeBound(true, isLowerBoudClosed, lowerBound);
		_upperBound = DoubleBound.makeBound(false, isUpperBoundClosed, upperBound);
		_bridge = new DoubleIntervalBridge<DoubleInterval>(this);
		_boundBridge = new AbstractIntervalBoundBridge<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, IDoubleInterval>(this);
	}

	private DoubleInterval(final DoubleBound lowerBound, final DoubleBound upperBound)
	{
		_lowerBound = lowerBound;
		_upperBound = upperBound;
		_bridge = new DoubleIntervalBridge<DoubleInterval>(this);
		_boundBridge = new AbstractIntervalBoundBridge<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, IDoubleInterval>(this);
	}


	@Override
	protected DoubleIntervalBridge<DoubleInterval> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractIntervalBoundBridge<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, IDoubleInterval> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public IDoubleSet getAsSet()
	{
		return this;
	}

	@Override
	public IElementaryDoubleSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public ISimpleDoubleSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IDoubleInterval getAsInterval()
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
		final IDoubleBound lowerBound = getLowerBound();
		if (lowerBound != null)
			return _lowerBound.isClosed();
		return false;
	}

	@Override
	public boolean isUpperBoundClosed()
	{
		final IDoubleBound upperBound = getUpperBound();
		if (upperBound != null)
			return _upperBound.isClosed();
		return false;
	}


	@Override
	public boolean isPreceding(final IDoubleSet set)
	{
		if(isUpperBoundInfinity() || set.isLowerBoundInfinity())
			return false;
		final IDoubleBound upperBound = getUpperBound();
		final IDoubleBound otherLowerBound = set.getLowerBound();
		if(upperBound == null || otherLowerBound == null)
			return false;
		final double upperBoundValue = upperBound.getValue();
		return (otherLowerBound.getValue() == upperBoundValue && isUpperBoundClosed()!=set.isLowerBoundClosed());
	}

	@Override
	public boolean isSucceeding(final IDoubleSet set)
	{
		if(isLowerBoundInfinity() || set.isUpperBoundInfinity())
			return false;
		if(isEmpty()||set.isEmpty())
			return false;
		final IDoubleBound lowerBound = getLowerBound();
		final IDoubleBound otherUpperBound = set.getUpperBound();
		if(lowerBound == null || otherUpperBound == null)
			return false;
		final double lowerBoundValue = lowerBound.getValue();

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
		final IDoubleBound lowerBound = getLowerBound();
		final IDoubleBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return 0;
		if(isLowerBoundInfinity() || isUpperBoundInfinity())
			return Double.POSITIVE_INFINITY;
		return upperBound.getValue() - lowerBound.getValue();
	}

	@Override
	public @Nullable IDoubleBound getLowerBound()
	{
		final IDoubleBound lowerBound = _lowerBound;
		final IDoubleBound upperBound = _upperBound;

		if(isLowerBoundInfinity() || isUpperBoundInfinity())
			return lowerBound;
		final double compare = lowerBound.getValue() - upperBound.getValue();
		if((compare<0) || (compare == 0 && lowerBound.isClosed() && upperBound.isClosed()))
			return lowerBound;
		return null;
	}

	@Override
	public @Nullable IDoubleBound getUpperBound()
	{
		final IDoubleBound lowerBound = _lowerBound;
		final IDoubleBound upperBound = _upperBound;

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
		final IDoubleBound lowerBound = getLowerBound();
		final IDoubleBound upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return false;
		return (isLowerBoundClosed() && isUpperBoundClosed() && (lowerBound.getValue() == upperBound.getValue()));
	}

	@Override
	public boolean equals( final @Nullable Object obj)
	{
		if(obj instanceof IDoubleSet)
		{
			final IDoubleSet set = (IDoubleSet)obj;
			if(set.isInterval())
			{
				final IDoubleBound lowerBound = set.getLowerBound();
				final IDoubleBound upperBound = set.getUpperBound();
				if(lowerBound == null || upperBound == null)
					return isEmpty();
				final IDoubleBound myLowerBound = getLowerBound();
				final IDoubleBound myUpperBound = getUpperBound();
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
		if(isDegenerate()&&(obj instanceof Double))
		{
			final IDoubleBound lowerBound = getLowerBound();
			if(lowerBound == null)
				throw new NullPointerException();
			return lowerBound.getValue()==((Double)obj);
		}
		return false;
	}

	@Override
	public boolean contains(final double value)
	{
		final IDoubleBound lowerBound = getLowerBound();
		final IDoubleBound upperBound = getUpperBound();
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
	public List<? extends IDoubleSet> split(final double[] values, final boolean[] isIntervalStart)
	{
		return getBridge().split(values, isIntervalStart);
	}

	@Override
	public List<? extends IDoubleSet> split(final double... values)
	{
		final boolean[] inNextInterval = new boolean[values.length];
		Arrays.fill(inNextInterval, true);
		return split(values, inNextInterval);
	}

	@Override
	public DoubleInterval getSimpleSet()
	{
		return this;
	}

	@Override
	public String toString()
	{
		final IDoubleBound lowerBound = getLowerBound();
		final IDoubleBound upperBound = getUpperBound();
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
