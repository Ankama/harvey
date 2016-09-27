/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.classes;

import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.TwoValueIterator;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.CommonDegenerateContinuousByteSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.ContinuousByteBound;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.DegenerateContinuousByteSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.interfaces.IIByteBoundFactory;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IDegenerateContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleContinuousByteSet;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class DegenerateContinuousByteSet
extends AbstractDegenerateContinuousSet<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IContinuousByteInterval, IDegenerateContinuousByteSet>
implements IDegenerateContinuousByteSet
{
	protected DegenerateContinuousByteSetBridge< DegenerateContinuousByteSet> _bridge;
	protected AbstractDegenerateBoundBridge<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IContinuousByteInterval, IDegenerateContinuousByteSet, DegenerateContinuousByteSet> _boundBridge;
	CommonDegenerateContinuousByteSetBridge<IContinuousByteBound, IContinuousByteSet> _degenerateBridge;

	public static  DegenerateContinuousByteSet makeSet(final byte value)
	{
		return new DegenerateContinuousByteSet(value);
	}

	private DegenerateContinuousByteSet(final byte value)
	{
		_bridge = new DegenerateContinuousByteSetBridge< DegenerateContinuousByteSet>(this);
		final EmptyContinuousByteSet empty = EmptyContinuousByteSet.getInstance();
		_degenerateBridge = new CommonDegenerateContinuousByteSetBridge<IContinuousByteBound, IContinuousByteSet>(
				value,
				new IIByteBoundFactory< IContinuousByteBound>()
				{
					@Override
					public ContinuousByteBound makeBound(final boolean isLowerBound, final byte value)
					{
						return ContinuousByteBound.makeBound(isLowerBound, value);
					}
				},
				empty,
				this);
		_boundBridge = new AbstractDegenerateBoundBridge<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IContinuousByteInterval, IDegenerateContinuousByteSet, DegenerateContinuousByteSet>(this);
	}

	@Override
	protected DegenerateContinuousByteSetBridge< DegenerateContinuousByteSet> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractDegenerateBoundBridge<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IContinuousByteInterval, IDegenerateContinuousByteSet, ? extends AbstractDegenerateSortedSet<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IContinuousByteInterval, IDegenerateContinuousByteSet>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public IContinuousByteSet getAsSet()
	{
		return this;
	}

	@Override
	public ISimpleContinuousByteSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IElementaryContinuousByteSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public IContinuousByteInterval getAsInterval()
	{
		return this;
	}

	@Override
	public IDegenerateContinuousByteSet getAsDegenerateSet()
	{
		return this;
	}

	@Override
	public byte getValue()
	{
		return _degenerateBridge.getValue();
	}

	@Override
	public @NonNull IContinuousByteBound getLowerBound()
	{
		return _degenerateBridge.getLowerBound();
	}

	@Override
	public @NonNull IContinuousByteBound getUpperBound()
	{
		return _degenerateBridge.getUpperBound();
	}

	@Override
	public DegenerateContinuousByteSet getSimpleSet()
	{
		return this;
	}

	@Override
	public boolean contains(final byte value)
	{
		return _degenerateBridge.contains(value);
	}

	@Override
	public List<? extends IContinuousByteSet> split(final byte[] values, final boolean[] isIntervalStart)
	{
		return _degenerateBridge.split(values, isIntervalStart);
	}

	@Override
	public List<? extends IContinuousByteSet> split(final byte... values)
	{
		return _degenerateBridge.split(values);
	}

	@Override
	public boolean isPreceding(final IContinuousByteSet set)
	{
		final IContinuousByteBound upperBound = getUpperBound();
		final IContinuousByteBound otherLowerBound = set.getLowerBound();
		if((upperBound == null) || (otherLowerBound == null))
			return false;
		final byte upperBoundValue = upperBound.getValue();
		return upperBoundValue == (otherLowerBound.getValue()) && (set.isLowerBoundClosed()!=isUpperBoundClosed());
	}

	@Override
	public boolean isSucceeding(final IContinuousByteSet set)
	{
		final IContinuousByteBound lowerBound = getLowerBound();
		final IContinuousByteBound otherUpperBound = set.getUpperBound();
		if(lowerBound == null || otherUpperBound == null)
			return false;
		final byte lowerBoundValue = lowerBound.getValue();
		return lowerBoundValue == (otherUpperBound.getValue()) && (set.isUpperBoundClosed()!=isLowerBoundClosed());
	}

	@Override
	public String toString()
	{
		return _degenerateBridge.toString();
	}

	@Override
	public Iterator<IContinuousByteBound> getBoundIterator()
	{
		return new TwoValueIterator<IContinuousByteBound>(getLowerBound(), getUpperBound());
	}
}