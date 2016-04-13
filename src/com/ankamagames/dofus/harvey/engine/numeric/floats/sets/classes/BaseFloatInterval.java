/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.BridgedOrderedSetIsAndHasImplementation;
import com.ankamagames.dofus.harvey.numeric.floats.sets.classes.EmptyFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatInterval;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseFloatInterval
	extends AbstractSet<IFloatSet>
	implements IFloatInterval
{
	protected BridgedOrderedSetIsAndHasImplementation<IFloatSet, IFloatInterval>	_bridgedIsAndHasImplementation	= new BridgedOrderedSetIsAndHasImplementation<IFloatSet, IFloatInterval>(
		this);

	protected float																	_defaultStep;
	protected BridgedFloatBound<BaseFloatInterval>									_lowerBound;
	protected BridgedFloatBound<BaseFloatInterval>									_upperBound;

	static public BaseFloatInterval makeInterval(final float lowerBound, final float upperBound)
	{
		return new BaseFloatInterval(lowerBound, true, upperBound, true, 1.0f);
	}

	static public BaseFloatInterval makeInterval(final float lowerBound, final float upperBound, final float defaultStep)
	{
		return new BaseFloatInterval(lowerBound, true, upperBound, true, defaultStep);
	}

	static public BaseFloatInterval makeInterval(final float lowerBound, final boolean isLowerBoudClosed, final float upperBound, final boolean isUpperBoundClosed)
	{
		return new BaseFloatInterval(lowerBound, isLowerBoudClosed, upperBound, isUpperBoundClosed, 1.0f);
	}

	static public BaseFloatInterval makeInterval(final float lowerBound, final boolean isLowerBoudClosed, final float upperBound, final boolean isUpperBoundClosed,
		final float defaultStep)
	{
		return new BaseFloatInterval(lowerBound, isLowerBoudClosed, upperBound, isUpperBoundClosed, defaultStep);
	}

	private BaseFloatInterval(final float lowerBound, final boolean isLowerBoudClosed, final float upperBound, final boolean isUpperBoundClosed, final float defaultStep)
	{
		_defaultStep = defaultStep;
		_lowerBound = new BridgedFloatBound<BaseFloatInterval>(lowerBound, isLowerBoudClosed);
		_upperBound = new BridgedFloatBound<BaseFloatInterval>(upperBound, isUpperBoundClosed);
	}

	@Override
	protected IFloatSet getThis()
	{
		return this;
	}

	@Override
	public boolean equals(@Nullable final Object obj)
	{
		if(obj instanceof IFloatSet)
		{
			final IFloatSet set = (IFloatSet)obj;
			if(set.isInterval())
			{
				if((set.getLowerBound() == getLowerBound()) && (set.getUpperBound() == getUpperBound()) &&
						(set.isLowerBoundClosed() == isLowerBoundClosed()) && (set.isUpperBoundClosed() == isUpperBoundClosed()))
					return true;
			}
		}
		if(isDegenerate()&&(obj instanceof Float))
		{
			return getLowerBound()==((Float)obj);
		}
		return false;
	}

	@Override
	public float getLowerBound()
	{
		return _lowerBound.getValue();
	}

	@Override
	public float getUpperBound()
	{
		return _upperBound.getValue();
	}

	@Override
	public boolean isLowerBoundClosed()
	{
		return _lowerBound.isClosed();
	}

	@Override
	public boolean isUpperBoundClosed()
	{
		return _upperBound.isClosed();
	}

	@Override
	public boolean isEmpty()
	{
		return (getLowerBound() > getUpperBound()) || ((getLowerBound() == getUpperBound()) && (!isLowerBoundClosed() || !isUpperBoundClosed()));
	}
	
	@Override
	public double size()
	{
		return Math.max(0, getUpperBound() - getLowerBound());
	}

	@Override
	public boolean isDegenerate()
	{
		return (isLowerBoundClosed() && isUpperBoundClosed() && (getLowerBound() == getUpperBound()));
	}

	@Override
	public boolean isInterval()
	{
		return true;
	}

	@Override
	public boolean containsAllValuesInRange(final IFloatSet set)
	{
		return contains(set);
	}

	@Override
	public boolean contains(final IFloatSet set)
	{
		if(set.isEmpty())
			return true;
		return ((contains(set.getLowerBound()) || (!set.isLowerBoundClosed() && !isLowerBoundClosed() && (set.getLowerBound() == getLowerBound())))
			&& (contains(set.getUpperBound()) || (!set.isUpperBoundClosed() && !isUpperBoundClosed() && (set.getUpperBound() == getUpperBound()))));
	}

	@Override
	public boolean isContainedBy(final IFloatSet set)
	{
		if(isEmpty())
			return false;
		if(set.isEmpty())
			return false;
		if(set.isDegenerate())
			return isDegenerate() && (getLowerBound() == set.getLowerBound());
		if(set.isInterval())
		{
			return (((getLowerBound() > set.getLowerBound()) || ((getLowerBound() == set.getLowerBound()) && (set.isLowerBoundClosed() || !isLowerBoundClosed())))
				&& ((getUpperBound() < set.getUpperBound()) || ((getUpperBound() == set.getUpperBound()) && (set.isUpperBoundClosed() || !isUpperBoundClosed()))));
		}
		
		return set.contains(this);
	}

	@Override
	public boolean contains(final float value)
	{
		return ((((value > getLowerBound()) || (isLowerBoundClosed() && (value == getLowerBound()))) &&
				((value < getUpperBound()))) || (isUpperBoundClosed() && (value == getUpperBound())));
	}

	@Override
	public boolean is(final SetCoveringMask mask, final IFloatSet set)
	{
		return _bridgedIsAndHasImplementation.is(mask, set);
	}

	@Override
	public boolean has(final SetCoveringMask mask, final IFloatSet set)
	{
		return _bridgedIsAndHasImplementation.has(mask, set);
	}

	@Override
	public boolean intersects(final IFloatSet set)
	{
		if((set.isEmpty()) || isEmpty())
			return false;

		if(contains(set.getLowerBound()))
			if((set.isLowerBoundClosed()) || (set.getLowerBound() != getUpperBound()))
				return true;

		if(set.isDegenerate())
			return false;

		if(contains(set.getUpperBound()))
			if((set.isUpperBoundClosed()) || (set.getUpperBound() != getLowerBound()))
				return true;

		if(set.isInterval())
		{
			return ((set.getLowerBound() < getLowerBound()) && (set.getUpperBound() > getUpperBound()));
		}

		return set.intersects(this);
	}

	@Override
	public boolean isInRange(final IFloatSet set)
	{
		if(set.isEmpty())
			return isEmpty();
		if(isEmpty())
			return false;
		return ((getLowerBound() > set.getLowerBound()) || ((isLowerBoundClosed() == set.isLowerBoundClosed()) && (getLowerBound() == set.getLowerBound()))) &&
			((getUpperBound() < set.getUpperBound()) || ((isUpperBoundClosed() == set.isUpperBoundClosed()) && (getUpperBound() == set.getUpperBound())));
	}

	@Override
	public boolean hasValueInRange(final IFloatSet set)
	{
		if(set.isEmpty())
			return isEmpty();
		if(isEmpty())
			return false;
		return ((getLowerBound() < set.getUpperBound()) || ((getLowerBound() == getUpperBound()) && isLowerBoundClosed() && set.isUpperBoundClosed())) &&
			((getUpperBound() > set.getLowerBound()) || ((getUpperBound() == getLowerBound()) && isUpperBoundClosed() && set.isLowerBoundClosed()));
	}

	@Override
	public boolean isGreaterThan(final IFloatSet set)
	{
		if(set.isEmpty() || isEmpty())
			return false;
		return ((getLowerBound() > set.getUpperBound()) || ((getLowerBound() == set.getUpperBound()) && (!isLowerBoundClosed() || !set.isUpperBoundClosed())));
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final IFloatSet set)
	{
		if(set.isEmpty())
			return isEmpty();
		if(isEmpty())
			return false;
		return (getLowerBound() >= set.getUpperBound());
	}

	@Override
	public boolean hasValueLowerThan(final IFloatSet set)
	{
		if(set.isEmpty() || isEmpty())
			return false;
		return ((getLowerBound() < set.getLowerBound()) || ((getLowerBound() == set.getLowerBound()) && (!set.isLowerBoundClosed() && isLowerBoundClosed())));
	}

	@Override
	public boolean isLowerThan(final IFloatSet set)
	{
		if(set.isEmpty() || isEmpty())
			return false;
		return ((getUpperBound() < set.getLowerBound()) || ((getUpperBound() == set.getLowerBound()) && (!isUpperBoundClosed() || !set.isLowerBoundClosed())));
	}

	@Override
	public boolean isLowerThanOrEqualTo(final IFloatSet set)
	{
		if(set.isEmpty())
			return isEmpty();
		if(isEmpty())
			return false;
		return (getUpperBound() <= set.getLowerBound());
	}

	@Override
	public boolean hasValueGreaterThan(final IFloatSet set)
	{
		if(set.isEmpty() || isEmpty())
			return false;
		return ((getUpperBound() > set.getUpperBound()) || ((getUpperBound() == set.getUpperBound()) && (!set.isUpperBoundClosed() && isUpperBoundClosed())));
	}

	@Override
	public List<? extends IFloatSet> splitOnRange(final IFloatSet set)
	{
		return split(new float[]{set.getLowerBound(), set.getUpperBound()}, new boolean[]{set.isLowerBoundClosed(), !set.isUpperBoundClosed()});
	}

	@Override
	public ArrayList<IFloatSet> split(final float[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IFloatSet> r = new ArrayList<IFloatSet>(values.length+1);

		if(values.length==0)
		{
			r.add(0, this);
			return r;
		}
		
		final EmptyFloatSet empty = EmptyFloatSet.getInstance();
		int i = 0;
		float value = values[0];
		boolean intervalStart = isIntervalStart[0];
		if((value < getLowerBound()) || ((value == getLowerBound()) && (intervalStart || !isLowerBoundClosed())))
		{
			r.add(0, empty);
			float formerValue;
			boolean formerIntervalStart;
			for(i++ ; i < values.length ; i++)
			{
				formerValue = value;
				formerIntervalStart = intervalStart;
				value = values[i];
				intervalStart = isIntervalStart[i];
				if((value > getLowerBound()) || ((value == getLowerBound()) && (!intervalStart) && isLowerBoundClosed()))
					break;
				if((formerValue < value) || ((formerValue == value) && (formerIntervalStart) && (!intervalStart)))
					r.add(i, empty);
				else
					throw new UnsupportedOperationException("The values given to split must be sorted");
			}
		}
		
		if(i == (values.length))
		{
			r.add(values.length, this);
			return r;
		}
		
		float startValue = getLowerBound();
		boolean closedStart = isLowerBoundClosed();
		final int formerI = i;
		for(; i < values.length ; i++)
		{
			final float intervalEnd = values[i];
			final boolean closedEnd = !isIntervalStart[i];
			if((intervalEnd > getUpperBound()) || ((intervalEnd == getUpperBound()) && (closedEnd)))
				break;

			if((startValue > intervalEnd) || ((startValue == intervalEnd) && ((!closedEnd) || (!closedStart))))
				throw new UnsupportedOperationException("The values given to split must be sorted");
			
			if(startValue==intervalEnd)
				r.add(i, BaseDegenerateFloatInterval.makeInterval(startValue));
			else
				r.add(i, BaseFloatInterval.makeInterval(startValue, closedStart, intervalEnd, closedEnd, _defaultStep));
			
			startValue = intervalEnd;
			closedStart = !closedEnd;
		}
		if(i == formerI)
		{
			r.add(i, this);
		}
		else
		{
			if(startValue==getUpperBound())
				if(isUpperBoundClosed())
					r.add(i, BaseDegenerateFloatInterval.makeInterval(getUpperBound()));
				else
					r.add(i, empty);
			else if(startValue < getUpperBound())
				r.add(i, BaseFloatInterval.makeInterval(startValue, closedStart, getUpperBound(), isUpperBoundClosed(), _defaultStep));
		}

		final float formerValue = getUpperBound();
		while( (i++) < values.length )
		{
			if((formerValue < values[i-1]) || ((formerValue == values[i-1]) && (!isIntervalStart[i-1])))
				r.add(i, empty);
			else
				throw new UnsupportedOperationException("The values given to split must be sorted");
		}
	
		return r;
	}
	
	@Override
	public ArrayList<IFloatSet> split(final float... values)
	{
		final boolean[] inNextInterval = new boolean[values.length];
		Arrays.fill(inNextInterval, true);
		return split(values, inNextInterval);
	}

	@Override
	public BaseFloatSet getMergedSet()
	{
		return BaseFloatSet.makeSet(this);
	}

	@Override
	public String toString()
	{
		if(isEmpty())
			return "∅";
		if(isDegenerate())
			return Float.toString(getLowerBound());
		String r;
		if(isLowerBoundClosed())
			r = "[";
		else
			r = "(";
		r += Float.toString(getLowerBound()) + " ; " + Float.toString(getUpperBound());
		if(isUpperBoundClosed())
			r += "]";
		else
			r += ")";
		return r;
	}
}
