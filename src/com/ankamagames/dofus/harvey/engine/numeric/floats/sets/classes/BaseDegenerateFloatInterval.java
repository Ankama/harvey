/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes;

import java.util.ArrayList;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateContinuousInterval;
import com.ankamagames.dofus.harvey.numeric.floats.sets.classes.EmptyFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IDegenerateFloatInterval;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatSet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseDegenerateFloatInterval
	extends AbstractDegenerateContinuousInterval<IFloatSet, BridgedFloatBound<BaseDegenerateFloatInterval>>
	implements IDegenerateFloatInterval
{
	BridgedDegenerateFloatSetImplementation _bridgedImplementation = new BridgedDegenerateFloatSetImplementation(this);

	public static BaseDegenerateFloatInterval makeInterval(final float value)
	{
		return new BaseDegenerateFloatInterval(new BridgedFloatBound<BaseDegenerateFloatInterval>(value));
	}
	
	private BaseDegenerateFloatInterval(final BridgedFloatBound<BaseDegenerateFloatInterval> bound)
	{
		super(bound);
	}

	@Override
	protected IFloatSet getThis()
	{
		return this;
	}

	@Override
	protected IFloatSet getEmpty()
	{
		return EmptyFloatSet.getInstance();
	}
	
	@Override
	public boolean equals(@Nullable final Object obj)
	{
		if(obj instanceof IFloatSet)
		{
			final IFloatSet set = (IFloatSet)obj;
			if(set.isDegenerate())
				return getValue() == set.getLowerBound();
		}
		if(obj instanceof Float)
		{
			return getValue() == ((Float)obj);
		}
		return false;
	}

	@Override
	public boolean contains(final float value)
	{
		return getValue()==value;
	}

	@Override
	public float getValue()
	{
		return _bound.getValue();
	}

	@Override
	public float getLowerBound()
	{
		return _bound.getValue();
	}

	@Override
	public float getUpperBound()
	{
		return _bound.getValue();
	}

	@Override
	public boolean is(final SetCoveringMask mask, final IFloatSet set)
	{
		return _bridgedImplementation.is(mask, set);
	}

	@Override
	public boolean isInRange(final IFloatSet set)
	{
		return _bridgedImplementation.isInRange(set);
	}

	@Override
	public boolean isGreaterThan(final IFloatSet set)
	{
		return _bridgedImplementation.isGreaterThan(set);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final IFloatSet set)
	{
		return _bridgedImplementation.isGreaterThanOrEqualTo(set);
	}

	@Override
	public boolean isLowerThan(final IFloatSet set)
	{
		return _bridgedImplementation.isLowerThan(set);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final IFloatSet set)
	{
		return _bridgedImplementation.isLowerThanOrEqualTo(set);
	}

	@Override
	public boolean isLowerBoundClosed()
	{
		return true;
	}

	@Override
	public boolean isUpperBoundClosed()
	{
		return true;
	}
	
	@Override
	public ArrayList<IFloatSet> split(final float[] values, final boolean[] isIntervalStart)
	{
		return _bridgedImplementation.split(values, isIntervalStart);
	}

	@Override
	public ArrayList<IFloatSet> split(final float... values)
	{
		return _bridgedImplementation.split(values);
	}

	@Override
	public BaseFloatSet getMergedSet()
	{
		return _bridgedImplementation.getMergedSet();
	}
	
	@Override
	public String toString()
	{
		return Float.toString(getValue());
	}
}