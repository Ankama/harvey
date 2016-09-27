/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.floats.sets.classes;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.CommonDegenerateFloatSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.DegenerateFloatSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.FloatBound;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IFloatBound;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IDegenerateFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IElementaryFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatInterval;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.ISimpleFloatSet;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class DegenerateFloatSet
extends AbstractDegenerateContinuousSet<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, IDegenerateFloatSet>
implements IDegenerateFloatSet
{
	float _value;
	private final DegenerateFloatSetBridge<DegenerateFloatSet> _bridge = new DegenerateFloatSetBridge<DegenerateFloatSet>(this);
	private final IFloatBound _lowerBound;
	private final IFloatBound _upperBound;
	protected AbstractDegenerateBoundBridge<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, IDegenerateFloatSet, DegenerateFloatSet> _boundBridge;
	private final CommonDegenerateFloatSetBridge<DegenerateFloatSet> _degenerateBridge = new CommonDegenerateFloatSetBridge<DegenerateFloatSet>(this);

	public static DegenerateFloatSet makeSet(final float value)
	{
		return new DegenerateFloatSet(value);
	}

	private DegenerateFloatSet(final float value)
	{
		_value = value;
		_lowerBound = FloatBound.makeBound(true, true, _value);
		_upperBound = FloatBound.makeBound(false, true, _value);
		_boundBridge = new AbstractDegenerateBoundBridge<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, IDegenerateFloatSet, DegenerateFloatSet> (this);

	}

	@Override
	public boolean equals(@Nullable final Object obj)
	{
		if(obj instanceof IFloatSet)
		{
			final IFloatSet set = (IFloatSet)obj;
			if(set.isDegenerate())
			{
				final IFloatBound lowerBound = set.getLowerBound();
				if(lowerBound != null)
					return getValue() == lowerBound.getValue();
				throw new NullPointerException();
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
	public @NonNull IFloatBound getLowerBound()
	{
		return _lowerBound;
	}

	@Override
	public @NonNull IFloatBound getUpperBound()
	{
		return _upperBound;
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
	public boolean isInRange(final IFloatSet set)
	{
		return _bridge.isInRange(set);
	}

	@Override
	public boolean isGreaterThan(final IFloatSet set)
	{
		return _bridge.isGreaterThan(set);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final IFloatSet set)
	{
		return _bridge.isGreaterThanOrEqualTo(set);
	}

	@Override
	public boolean isLowerThan(final IFloatSet set)
	{
		return _bridge.isLowerThan(set);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final IFloatSet set)
	{
		return _bridge.isLowerThanOrEqualTo(set);
	}

	@Override
	public List<? extends IFloatSet> split(final float[] values, final boolean[] isIntervalStart)
	{
		return _degenerateBridge.split(values, isIntervalStart);
	}

	@Override
	public List<? extends IFloatSet> split(final float... values)
	{
		return _degenerateBridge.split(values);
	}

	@Override
	public DegenerateFloatSet getSimpleSet()
	{
		return this;
	}

	@Override
	public String toString()
	{
		return Float.toString(getValue());
	}

	@Override
	public IDegenerateFloatSet getAsDegenerateSet()
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
	public IFloatSet getAsSet()
	{
		return this;
	}

	@Override
	public IFloatInterval getAsInterval()
	{
		return this;
	}

	@Override
	public boolean isPreceding(final IFloatSet set)
	{
		final IFloatBound lowerBound = set.getLowerBound();
		final IFloatBound upperBound = getUpperBound();
		if(lowerBound != null && upperBound != null)
		{
			return (lowerBound.getValue() == upperBound.getValue())&& (upperBound.isClosed()!=lowerBound.isClosed());
		}
		return false;
	}

	@Override
	public boolean isSucceeding(final IFloatSet set)
	{
		final IFloatBound lowerBound = getLowerBound();
		final IFloatBound upperBound = set.getUpperBound();
		if(lowerBound != null && upperBound != null)
		{
			return (lowerBound.getValue() == upperBound.getValue())&& (upperBound.isClosed()!=lowerBound.isClosed());
		}
		return false;
	}

	@Override
	protected AbstractDegenerateSortedSetBridge<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, IDegenerateFloatSet, ? extends AbstractDegenerateSortedSet<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, IDegenerateFloatSet>> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractDegenerateBoundBridge<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, IDegenerateFloatSet, ? extends AbstractDegenerateSortedSet<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, IDegenerateFloatSet>> getBoundBridge()
	{
		return _boundBridge;
	}
}