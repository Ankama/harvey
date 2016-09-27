/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.sets.classes;

import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.TwoValueIterator;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.CommonDegenerateContinuousLongSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.ContinuousLongBound;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.DegenerateContinuousLongSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.interfaces.IILongBoundFactory;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongBound;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IDegenerateContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleContinuousLongSet;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class DegenerateContinuousLongSet
extends AbstractDegenerateContinuousSet<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval, IDegenerateContinuousLongSet>
implements IDegenerateContinuousLongSet
{
	protected DegenerateContinuousLongSetBridge< DegenerateContinuousLongSet> _bridge;
	protected AbstractDegenerateBoundBridge<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval, IDegenerateContinuousLongSet, DegenerateContinuousLongSet> _boundBridge;
	CommonDegenerateContinuousLongSetBridge<IContinuousLongBound, IContinuousLongSet> _degenerateBridge;

	public static  DegenerateContinuousLongSet makeSet(final long value)
	{
		return new DegenerateContinuousLongSet(value);
	}

	private DegenerateContinuousLongSet(final long value)
	{
		_bridge = new DegenerateContinuousLongSetBridge< DegenerateContinuousLongSet>(this);
		final EmptyContinuousLongSet empty = EmptyContinuousLongSet.getInstance();
		_degenerateBridge = new CommonDegenerateContinuousLongSetBridge<IContinuousLongBound, IContinuousLongSet>(
				value,
				new IILongBoundFactory< IContinuousLongBound>()
				{
					@Override
					public ContinuousLongBound makeBound(final boolean isLowerBound, final long value)
					{
						return ContinuousLongBound.makeBound(isLowerBound, value);
					}
				},
				empty,
				this);
		_boundBridge = new AbstractDegenerateBoundBridge<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval, IDegenerateContinuousLongSet, DegenerateContinuousLongSet>(this);
	}

	@Override
	protected DegenerateContinuousLongSetBridge< DegenerateContinuousLongSet> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractDegenerateBoundBridge<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval, IDegenerateContinuousLongSet, ? extends AbstractDegenerateSortedSet<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval, IDegenerateContinuousLongSet>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public IContinuousLongSet getAsSet()
	{
		return this;
	}

	@Override
	public ISimpleContinuousLongSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IElementaryContinuousLongSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public IContinuousLongInterval getAsInterval()
	{
		return this;
	}

	@Override
	public IDegenerateContinuousLongSet getAsDegenerateSet()
	{
		return this;
	}

	@Override
	public long getValue()
	{
		return _degenerateBridge.getValue();
	}

	@Override
	public @NonNull IContinuousLongBound getLowerBound()
	{
		return _degenerateBridge.getLowerBound();
	}

	@Override
	public @NonNull IContinuousLongBound getUpperBound()
	{
		return _degenerateBridge.getUpperBound();
	}

	@Override
	public DegenerateContinuousLongSet getSimpleSet()
	{
		return this;
	}

	@Override
	public boolean contains(final long value)
	{
		return _degenerateBridge.contains(value);
	}

	@Override
	public List<? extends IContinuousLongSet> split(final long[] values, final boolean[] isIntervalStart)
	{
		return _degenerateBridge.split(values, isIntervalStart);
	}

	@Override
	public List<? extends IContinuousLongSet> split(final long... values)
	{
		return _degenerateBridge.split(values);
	}

	@Override
	public boolean isPreceding(final IContinuousLongSet set)
	{
		final IContinuousLongBound upperBound = getUpperBound();
		final IContinuousLongBound otherLowerBound = set.getLowerBound();
		if((upperBound == null) || (otherLowerBound == null))
			return false;
		final long upperBoundValue = upperBound.getValue();
		return upperBoundValue == (otherLowerBound.getValue()) && (set.isLowerBoundClosed()!=isUpperBoundClosed());
	}

	@Override
	public boolean isSucceeding(final IContinuousLongSet set)
	{
		final IContinuousLongBound lowerBound = getLowerBound();
		final IContinuousLongBound otherUpperBound = set.getUpperBound();
		if(lowerBound == null || otherUpperBound == null)
			return false;
		final long lowerBoundValue = lowerBound.getValue();
		return lowerBoundValue == (otherUpperBound.getValue()) && (set.isUpperBoundClosed()!=isLowerBoundClosed());
	}

	@Override
	public String toString()
	{
		return _degenerateBridge.toString();
	}

	@Override
	public Iterator<IContinuousLongBound> getBoundIterator()
	{
		return new TwoValueIterator<IContinuousLongBound>(getLowerBound(), getUpperBound());
	}
}