/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.classes;

import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.TwoValueIterator;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.CommonDegenerateContinuousIntegerSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.ContinuousIntegerBound;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.DegenerateContinuousIntegerSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.interfaces.IIIntegerBoundFactory;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerBound;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IDegenerateContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleContinuousIntegerSet;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class DegenerateContinuousIntegerSet
extends AbstractDegenerateContinuousSet<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, IContinuousIntegerInterval, IDegenerateContinuousIntegerSet>
implements IDegenerateContinuousIntegerSet
{
	protected DegenerateContinuousIntegerSetBridge< DegenerateContinuousIntegerSet> _bridge;
	protected AbstractDegenerateBoundBridge<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, IContinuousIntegerInterval, IDegenerateContinuousIntegerSet, DegenerateContinuousIntegerSet> _boundBridge;
	CommonDegenerateContinuousIntegerSetBridge<IContinuousIntegerBound, IContinuousIntegerSet> _degenerateBridge;

	public static  DegenerateContinuousIntegerSet makeSet(final int value)
	{
		return new DegenerateContinuousIntegerSet(value);
	}

	private DegenerateContinuousIntegerSet(final int value)
	{
		_bridge = new DegenerateContinuousIntegerSetBridge< DegenerateContinuousIntegerSet>(this);
		final EmptyContinuousIntegerSet empty = EmptyContinuousIntegerSet.getInstance();
		_degenerateBridge = new CommonDegenerateContinuousIntegerSetBridge<IContinuousIntegerBound, IContinuousIntegerSet>(
				value,
				new IIIntegerBoundFactory< IContinuousIntegerBound>()
				{
					@Override
					public ContinuousIntegerBound makeBound(final boolean isLowerBound, final int value)
					{
						return ContinuousIntegerBound.makeBound(isLowerBound, value);
					}
				},
				empty,
				this);
		_boundBridge = new AbstractDegenerateBoundBridge<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, IContinuousIntegerInterval, IDegenerateContinuousIntegerSet, DegenerateContinuousIntegerSet>(this);
	}

	@Override
	protected DegenerateContinuousIntegerSetBridge< DegenerateContinuousIntegerSet> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractDegenerateBoundBridge<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, IContinuousIntegerInterval, IDegenerateContinuousIntegerSet, ? extends AbstractDegenerateSortedSet<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, IContinuousIntegerInterval, IDegenerateContinuousIntegerSet>> getBoundBridge()
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
	public IDegenerateContinuousIntegerSet getAsDegenerateSet()
	{
		return this;
	}

	@Override
	public int getValue()
	{
		return _degenerateBridge.getValue();
	}

	@Override
	public @NonNull IContinuousIntegerBound getLowerBound()
	{
		return _degenerateBridge.getLowerBound();
	}

	@Override
	public @NonNull IContinuousIntegerBound getUpperBound()
	{
		return _degenerateBridge.getUpperBound();
	}

	@Override
	public DegenerateContinuousIntegerSet getSimpleSet()
	{
		return this;
	}

	@Override
	public boolean contains(final int value)
	{
		return _degenerateBridge.contains(value);
	}

	@Override
	public List<? extends IContinuousIntegerSet> split(final int[] values, final boolean[] isIntervalStart)
	{
		return _degenerateBridge.split(values, isIntervalStart);
	}

	@Override
	public List<? extends IContinuousIntegerSet> split(final int... values)
	{
		return _degenerateBridge.split(values);
	}

	@Override
	public boolean isPreceding(final IContinuousIntegerSet set)
	{
		final IContinuousIntegerBound upperBound = getUpperBound();
		final IContinuousIntegerBound otherLowerBound = set.getLowerBound();
		if((upperBound == null) || (otherLowerBound == null))
			return false;
		final int upperBoundValue = upperBound.getValue();
		return upperBoundValue == (otherLowerBound.getValue()) && (set.isLowerBoundClosed()!=isUpperBoundClosed());
	}

	@Override
	public boolean isSucceeding(final IContinuousIntegerSet set)
	{
		final IContinuousIntegerBound lowerBound = getLowerBound();
		final IContinuousIntegerBound otherUpperBound = set.getUpperBound();
		if(lowerBound == null || otherUpperBound == null)
			return false;
		final int lowerBoundValue = lowerBound.getValue();
		return lowerBoundValue == (otherUpperBound.getValue()) && (set.isUpperBoundClosed()!=isLowerBoundClosed());
	}

	@Override
	public String toString()
	{
		return _degenerateBridge.toString();
	}

	@Override
	public Iterator<IContinuousIntegerBound> getBoundIterator()
	{
		return new TwoValueIterator<IContinuousIntegerBound>(getLowerBound(), getUpperBound());
	}
}