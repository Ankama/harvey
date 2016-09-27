/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.doubles.sets.classes;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.CommonDegenerateDoubleSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.DegenerateDoubleSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.DoubleBound;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.interfaces.IDoubleBound;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDegenerateDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IElementaryDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleInterval;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.ISimpleDoubleSet;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class DegenerateDoubleSet
extends AbstractDegenerateContinuousSet<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, IDegenerateDoubleSet>
implements IDegenerateDoubleSet
{
	double _value;
	private final DegenerateDoubleSetBridge<DegenerateDoubleSet> _bridge = new DegenerateDoubleSetBridge<DegenerateDoubleSet>(this);
	private final IDoubleBound _lowerBound;
	private final IDoubleBound _upperBound;
	protected AbstractDegenerateBoundBridge<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, IDegenerateDoubleSet, DegenerateDoubleSet> _boundBridge;
	private final CommonDegenerateDoubleSetBridge<DegenerateDoubleSet> _degenerateBridge = new CommonDegenerateDoubleSetBridge<DegenerateDoubleSet>(this);

	public static DegenerateDoubleSet makeSet(final double value)
	{
		return new DegenerateDoubleSet(value);
	}

	private DegenerateDoubleSet(final double value)
	{
		_value = value;
		_lowerBound = DoubleBound.makeBound(true, true, _value);
		_upperBound = DoubleBound.makeBound(false, true, _value);
		_boundBridge = new AbstractDegenerateBoundBridge<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, IDegenerateDoubleSet, DegenerateDoubleSet> (this);

	}

	@Override
	public boolean equals(@Nullable final Object obj)
	{
		if(obj instanceof IDoubleSet)
		{
			final IDoubleSet set = (IDoubleSet)obj;
			if(set.isDegenerate())
			{
				final IDoubleBound lowerBound = set.getLowerBound();
				if(lowerBound != null)
					return getValue() == lowerBound.getValue();
				throw new NullPointerException();
			}
		}
		if(obj instanceof Double)
		{
			return getValue() == ((Double)obj);
		}
		return false;
	}

	@Override
	public boolean contains(final double value)
	{
		return getValue()==value;
	}

	@Override
	public double getValue()
	{
		return _value;
	}

	@Override
	public @NonNull IDoubleBound getLowerBound()
	{
		return _lowerBound;
	}

	@Override
	public @NonNull IDoubleBound getUpperBound()
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
	public boolean isInRange(final IDoubleSet set)
	{
		return _bridge.isInRange(set);
	}

	@Override
	public boolean isGreaterThan(final IDoubleSet set)
	{
		return _bridge.isGreaterThan(set);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final IDoubleSet set)
	{
		return _bridge.isGreaterThanOrEqualTo(set);
	}

	@Override
	public boolean isLowerThan(final IDoubleSet set)
	{
		return _bridge.isLowerThan(set);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final IDoubleSet set)
	{
		return _bridge.isLowerThanOrEqualTo(set);
	}

	@Override
	public List<? extends IDoubleSet> split(final double[] values, final boolean[] isIntervalStart)
	{
		return _degenerateBridge.split(values, isIntervalStart);
	}

	@Override
	public List<? extends IDoubleSet> split(final double... values)
	{
		return _degenerateBridge.split(values);
	}

	@Override
	public DegenerateDoubleSet getSimpleSet()
	{
		return this;
	}

	@Override
	public String toString()
	{
		return Double.toString(getValue());
	}

	@Override
	public IDegenerateDoubleSet getAsDegenerateSet()
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
	public IDoubleSet getAsSet()
	{
		return this;
	}

	@Override
	public IDoubleInterval getAsInterval()
	{
		return this;
	}

	@Override
	public boolean isPreceding(final IDoubleSet set)
	{
		final IDoubleBound lowerBound = set.getLowerBound();
		final IDoubleBound upperBound = getUpperBound();
		if(lowerBound != null && upperBound != null)
		{
			return (lowerBound.getValue() == upperBound.getValue())&& (upperBound.isClosed()!=lowerBound.isClosed());
		}
		return false;
	}

	@Override
	public boolean isSucceeding(final IDoubleSet set)
	{
		final IDoubleBound lowerBound = getLowerBound();
		final IDoubleBound upperBound = set.getUpperBound();
		if(lowerBound != null && upperBound != null)
		{
			return (lowerBound.getValue() == upperBound.getValue())&& (upperBound.isClosed()!=lowerBound.isClosed());
		}
		return false;
	}

	@Override
	protected AbstractDegenerateSortedSetBridge<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, IDegenerateDoubleSet, ? extends AbstractDegenerateSortedSet<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, IDegenerateDoubleSet>> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractDegenerateBoundBridge<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, IDegenerateDoubleSet, ? extends AbstractDegenerateSortedSet<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, IDegenerateDoubleSet>> getBoundBridge()
	{
		return _boundBridge;
	}
}