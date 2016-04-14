/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes;

import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateContinuousSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.classes.EmptyFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IDegenerateFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseDegenerateFloatSet
	extends AbstractDegenerateContinuousSet<IFloatSet>
	implements IDegenerateFloatSet
{
	float									_value;
	BridgedDegenerateFloatSetImplementation<BaseDegenerateFloatSet>	_bridgedImplementation	= new BridgedDegenerateFloatSetImplementation<BaseDegenerateFloatSet>(this);

	public static BaseDegenerateFloatSet makeSet(final float value)
	{
		return new BaseDegenerateFloatSet(value);
	}
	
	private BaseDegenerateFloatSet(final float value)
	{
		_value = value;
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
			{
				return getValue() == set.getLowerBound();
			}
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
		return _value;
	}

	@Override
	public float getLowerBound()
	{
		return getValue();
	}

	@Override
	public float getUpperBound()
	{
		return getValue();
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
	public List<? extends IFloatSet> split(final float[] values, final boolean[] isIntervalStart)
	{
		return _bridgedImplementation.split(values, isIntervalStart);
	}

	@Override
	public List<? extends IFloatSet> split(final float... values)
	{
		return _bridgedImplementation.split(values);
	}

	@Override
	public BaseFloatSet<BaseDegenerateFloatSet> getMergedSet()
	{
		return _bridgedImplementation.getMergedSet();
	}
	
	@Override
	public String toString()
	{
		return Float.toString(getValue());
	}
}