/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.ShortBound;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.CommonDegenerateShortSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.DegenerateShortSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.interfaces.IIShortBoundFactory;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IDegenerateShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleShortSet;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.NonNullByDefault;



/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class DegenerateShortSet
extends AbstractDegenerateSortedSet<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval, IDegenerateShortSet>
implements IDegenerateShortSet
{
	protected DegenerateShortSetBridge<DegenerateShortSet> _bridge;
	protected AbstractDegenerateBoundBridge<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval, IDegenerateShortSet, DegenerateShortSet> _boundBridge;
	CommonDegenerateShortSetBridge<IShortBound, IShortSet> _degenerateBridge;

	public static DegenerateShortSet makeSet(final short value)
	{
		return new DegenerateShortSet(value);
	}

	private DegenerateShortSet(final short value)
	{
		_bridge = new DegenerateShortSetBridge<DegenerateShortSet>(this);
		final EmptyShortSet empty = EmptyShortSet.getInstance();
		_degenerateBridge = new CommonDegenerateShortSetBridge<IShortBound, IShortSet>(
				value,
				new IIShortBoundFactory<IShortBound>()
				{
					@Override
					public IShortBound makeBound(final boolean isLowerBound, final short value)
					{
						return ShortBound.makeBound(value);
					}
				}, empty, this);
		_boundBridge = new AbstractDegenerateBoundBridge<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval, IDegenerateShortSet, DegenerateShortSet>(this);
	}

	@Override
	protected AbstractDegenerateSortedSetBridge<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval, IDegenerateShortSet, ? extends AbstractDegenerateSortedSet<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval, IDegenerateShortSet>> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractDegenerateBoundBridge<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval, IDegenerateShortSet, ? extends AbstractDegenerateSortedSet<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval, IDegenerateShortSet>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public IShortSet getAsSet()
	{
		return this;
	}

	@Override
	public IElementaryShortSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public ISimpleShortSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IDegenerateShortSet getAsDegenerateSet()
	{
		return this;
	}

	@Override
	public IShortInterval getAsInterval()
	{
		return this;
	}

	@Override
	public short getValue()
	{
		return _degenerateBridge.getValue();
	}

	@Override
	public @NonNull IShortBound getLowerBound()
	{
		return _degenerateBridge.getLowerBound();
	}

	@Override
	public @NonNull IShortBound getUpperBound()
	{
		return _degenerateBridge.getUpperBound();
	}

	@Override
	public DegenerateShortSet getSimpleSet()
	{
		return this;
	}

	@Override
	public boolean contains(final short value)
	{
		return _degenerateBridge.contains(value);
	}

	@Override
	public ArrayList<? extends IShortSet> split(final short[] values, final boolean[] isIntervalStart)
	{
		return _degenerateBridge.split(values, isIntervalStart);
	}

	@Override
	public ArrayList<? extends IShortSet> split(final short... values)
	{
		return _degenerateBridge.split(values);
	}

	@Override
	public Iterator<Short> getDataIterator()
	{
		return _degenerateBridge.getDataIterator();
	}

	@Override
	public Iterator<Short> getReverseDataIterator() {
		return _degenerateBridge.getReverseDataIterator();
	}

	@Override
	public boolean isPreceding(final IShortSet set)
	{
		final IShortBound lowerBound = set.getLowerBound();
		if(lowerBound == null)
			return false;
		final short predecessor = (short) (lowerBound.getValue()-1);
		return predecessor == (getValue());
	}

	@Override
	public boolean isSucceeding(final IShortSet set)
	{
		final IShortBound upperBound = set.getUpperBound();
		if(upperBound == null)
			return false;
		final short successor = (short) (upperBound.getValue()+1);
		return successor == (getValue());
	}

	@Override
	public List<Short> sample(final int numberOfSample)
	{
		return sample();
	}

	@Override
	public List<Short> sample()
	{
		return Arrays.asList(getValue());
	}

	@Override
	public String toString()
	{
		return _degenerateBridge.toString();
	}
}
