/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.LongBound;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.CommonDegenerateLongSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.DegenerateLongSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.interfaces.IILongBoundFactory;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongBound;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IDegenerateLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleLongSet;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.NonNullByDefault;



/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class DegenerateLongSet
extends AbstractDegenerateSortedSet<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval, IDegenerateLongSet>
implements IDegenerateLongSet
{
	protected DegenerateLongSetBridge<DegenerateLongSet> _bridge;
	protected AbstractDegenerateBoundBridge<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval, IDegenerateLongSet, DegenerateLongSet> _boundBridge;
	CommonDegenerateLongSetBridge<ILongBound, ILongSet> _degenerateBridge;

	public static DegenerateLongSet makeSet(final long value)
	{
		return new DegenerateLongSet(value);
	}

	private DegenerateLongSet(final long value)
	{
		_bridge = new DegenerateLongSetBridge<DegenerateLongSet>(this);
		final EmptyLongSet empty = EmptyLongSet.getInstance();
		_degenerateBridge = new CommonDegenerateLongSetBridge<ILongBound, ILongSet>(
				value,
				new IILongBoundFactory<ILongBound>()
				{
					@Override
					public ILongBound makeBound(final boolean isLowerBound, final long value)
					{
						return LongBound.makeBound(value);
					}
				}, empty, this);
		_boundBridge = new AbstractDegenerateBoundBridge<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval, IDegenerateLongSet, DegenerateLongSet>(this);
	}

	@Override
	protected AbstractDegenerateSortedSetBridge<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval, IDegenerateLongSet, ? extends AbstractDegenerateSortedSet<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval, IDegenerateLongSet>> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractDegenerateBoundBridge<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval, IDegenerateLongSet, ? extends AbstractDegenerateSortedSet<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval, IDegenerateLongSet>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public ILongSet getAsSet()
	{
		return this;
	}

	@Override
	public IElementaryLongSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public ISimpleLongSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IDegenerateLongSet getAsDegenerateSet()
	{
		return this;
	}

	@Override
	public ILongInterval getAsInterval()
	{
		return this;
	}

	@Override
	public long getValue()
	{
		return _degenerateBridge.getValue();
	}

	@Override
	public @NonNull ILongBound getLowerBound()
	{
		return _degenerateBridge.getLowerBound();
	}

	@Override
	public @NonNull ILongBound getUpperBound()
	{
		return _degenerateBridge.getUpperBound();
	}

	@Override
	public DegenerateLongSet getSimpleSet()
	{
		return this;
	}

	@Override
	public boolean contains(final long value)
	{
		return _degenerateBridge.contains(value);
	}

	@Override
	public ArrayList<? extends ILongSet> split(final long[] values, final boolean[] isIntervalStart)
	{
		return _degenerateBridge.split(values, isIntervalStart);
	}

	@Override
	public ArrayList<? extends ILongSet> split(final long... values)
	{
		return _degenerateBridge.split(values);
	}

	@Override
	public Iterator<Long> getDataIterator()
	{
		return _degenerateBridge.getDataIterator();
	}

	@Override
	public Iterator<Long> getReverseDataIterator() {
		return _degenerateBridge.getReverseDataIterator();
	}

	@Override
	public boolean isPreceding(final ILongSet set)
	{
		final ILongBound lowerBound = set.getLowerBound();
		if(lowerBound == null)
			return false;
		final long predecessor = (long) (lowerBound.getValue()-1);
		return predecessor == (getValue());
	}

	@Override
	public boolean isSucceeding(final ILongSet set)
	{
		final ILongBound upperBound = set.getUpperBound();
		if(upperBound == null)
			return false;
		final long successor = (long) (upperBound.getValue()+1);
		return successor == (getValue());
	}

	@Override
	public List<Long> sample(final int numberOfSample)
	{
		return sample();
	}

	@Override
	public List<Long> sample()
	{
		return Arrays.asList(getValue());
	}

	@Override
	public String toString()
	{
		return _degenerateBridge.toString();
	}
}
