/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.sets.classes;

import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.TwoValueIterator;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.CommonDegenerateContinuousShortSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.ContinuousShortBound;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.DegenerateContinuousShortSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.interfaces.IIShortBoundFactory;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IDegenerateContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleContinuousShortSet;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class DegenerateContinuousShortSet
extends AbstractDegenerateContinuousSet<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval, IDegenerateContinuousShortSet>
implements IDegenerateContinuousShortSet
{
	protected DegenerateContinuousShortSetBridge< DegenerateContinuousShortSet> _bridge;
	protected AbstractDegenerateBoundBridge<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval, IDegenerateContinuousShortSet, DegenerateContinuousShortSet> _boundBridge;
	CommonDegenerateContinuousShortSetBridge<IContinuousShortBound, IContinuousShortSet> _degenerateBridge;

	public static  DegenerateContinuousShortSet makeSet(final short value)
	{
		return new DegenerateContinuousShortSet(value);
	}

	private DegenerateContinuousShortSet(final short value)
	{
		_bridge = new DegenerateContinuousShortSetBridge< DegenerateContinuousShortSet>(this);
		final EmptyContinuousShortSet empty = EmptyContinuousShortSet.getInstance();
		_degenerateBridge = new CommonDegenerateContinuousShortSetBridge<IContinuousShortBound, IContinuousShortSet>(
				value,
				new IIShortBoundFactory< IContinuousShortBound>()
				{
					@Override
					public ContinuousShortBound makeBound(final boolean isLowerBound, final short value)
					{
						return ContinuousShortBound.makeBound(isLowerBound, value);
					}
				},
				empty,
				this);
		_boundBridge = new AbstractDegenerateBoundBridge<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval, IDegenerateContinuousShortSet, DegenerateContinuousShortSet>(this);
	}

	@Override
	protected DegenerateContinuousShortSetBridge< DegenerateContinuousShortSet> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractDegenerateBoundBridge<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval, IDegenerateContinuousShortSet, ? extends AbstractDegenerateSortedSet<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval, IDegenerateContinuousShortSet>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public IContinuousShortSet getAsSet()
	{
		return this;
	}

	@Override
	public ISimpleContinuousShortSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IElementaryContinuousShortSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public IContinuousShortInterval getAsInterval()
	{
		return this;
	}

	@Override
	public IDegenerateContinuousShortSet getAsDegenerateSet()
	{
		return this;
	}

	@Override
	public short getValue()
	{
		return _degenerateBridge.getValue();
	}

	@Override
	public @NonNull IContinuousShortBound getLowerBound()
	{
		return _degenerateBridge.getLowerBound();
	}

	@Override
	public @NonNull IContinuousShortBound getUpperBound()
	{
		return _degenerateBridge.getUpperBound();
	}

	@Override
	public DegenerateContinuousShortSet getSimpleSet()
	{
		return this;
	}

	@Override
	public boolean contains(final short value)
	{
		return _degenerateBridge.contains(value);
	}

	@Override
	public List<? extends IContinuousShortSet> split(final short[] values, final boolean[] isIntervalStart)
	{
		return _degenerateBridge.split(values, isIntervalStart);
	}

	@Override
	public List<? extends IContinuousShortSet> split(final short... values)
	{
		return _degenerateBridge.split(values);
	}

	@Override
	public boolean isPreceding(final IContinuousShortSet set)
	{
		final IContinuousShortBound upperBound = getUpperBound();
		final IContinuousShortBound otherLowerBound = set.getLowerBound();
		if((upperBound == null) || (otherLowerBound == null))
			return false;
		final short upperBoundValue = upperBound.getValue();
		return upperBoundValue == (otherLowerBound.getValue()) && (set.isLowerBoundClosed()!=isUpperBoundClosed());
	}

	@Override
	public boolean isSucceeding(final IContinuousShortSet set)
	{
		final IContinuousShortBound lowerBound = getLowerBound();
		final IContinuousShortBound otherUpperBound = set.getUpperBound();
		if(lowerBound == null || otherUpperBound == null)
			return false;
		final short lowerBoundValue = lowerBound.getValue();
		return lowerBoundValue == (otherUpperBound.getValue()) && (set.isUpperBoundClosed()!=isLowerBoundClosed());
	}

	@Override
	public String toString()
	{
		return _degenerateBridge.toString();
	}

	@Override
	public Iterator<IContinuousShortBound> getBoundIterator()
	{
		return new TwoValueIterator<IContinuousShortBound>(getLowerBound(), getUpperBound());
	}
}