/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.ByteBound;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.CommonDegenerateByteSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.DegenerateByteSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.interfaces.IIByteBoundFactory;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IDegenerateByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleByteSet;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.NonNullByDefault;



/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class DegenerateByteSet
extends AbstractDegenerateSortedSet<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval, IDegenerateByteSet>
implements IDegenerateByteSet
{
	protected DegenerateByteSetBridge<DegenerateByteSet> _bridge;
	protected AbstractDegenerateBoundBridge<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval, IDegenerateByteSet, DegenerateByteSet> _boundBridge;
	CommonDegenerateByteSetBridge<IByteBound, IByteSet> _degenerateBridge;

	public static DegenerateByteSet makeSet(final byte value)
	{
		return new DegenerateByteSet(value);
	}

	private DegenerateByteSet(final byte value)
	{
		_bridge = new DegenerateByteSetBridge<DegenerateByteSet>(this);
		final EmptyByteSet empty = EmptyByteSet.getInstance();
		_degenerateBridge = new CommonDegenerateByteSetBridge<IByteBound, IByteSet>(
				value,
				new IIByteBoundFactory<IByteBound>()
				{
					@Override
					public IByteBound makeBound(final boolean isLowerBound, final byte value)
					{
						return ByteBound.makeBound(value);
					}
				}, empty, this);
		_boundBridge = new AbstractDegenerateBoundBridge<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval, IDegenerateByteSet, DegenerateByteSet>(this);
	}

	@Override
	protected AbstractDegenerateSortedSetBridge<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval, IDegenerateByteSet, ? extends AbstractDegenerateSortedSet<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval, IDegenerateByteSet>> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractDegenerateBoundBridge<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval, IDegenerateByteSet, ? extends AbstractDegenerateSortedSet<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval, IDegenerateByteSet>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public IByteSet getAsSet()
	{
		return this;
	}

	@Override
	public IElementaryByteSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public ISimpleByteSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IDegenerateByteSet getAsDegenerateSet()
	{
		return this;
	}

	@Override
	public IByteInterval getAsInterval()
	{
		return this;
	}

	@Override
	public byte getValue()
	{
		return _degenerateBridge.getValue();
	}

	@Override
	public @NonNull IByteBound getLowerBound()
	{
		return _degenerateBridge.getLowerBound();
	}

	@Override
	public @NonNull IByteBound getUpperBound()
	{
		return _degenerateBridge.getUpperBound();
	}

	@Override
	public DegenerateByteSet getSimpleSet()
	{
		return this;
	}

	@Override
	public boolean contains(final byte value)
	{
		return _degenerateBridge.contains(value);
	}

	@Override
	public ArrayList<? extends IByteSet> split(final byte[] values, final boolean[] isIntervalStart)
	{
		return _degenerateBridge.split(values, isIntervalStart);
	}

	@Override
	public ArrayList<? extends IByteSet> split(final byte... values)
	{
		return _degenerateBridge.split(values);
	}

	@Override
	public Iterator<Byte> getDataIterator()
	{
		return _degenerateBridge.getDataIterator();
	}

	@Override
	public Iterator<Byte> getReverseDataIterator() {
		return _degenerateBridge.getReverseDataIterator();
	}

	@Override
	public boolean isPreceding(final IByteSet set)
	{
		final IByteBound lowerBound = set.getLowerBound();
		if(lowerBound == null)
			return false;
		final byte predecessor = (byte) (lowerBound.getValue()-1);
		return predecessor == (getValue());
	}

	@Override
	public boolean isSucceeding(final IByteSet set)
	{
		final IByteBound upperBound = set.getUpperBound();
		if(upperBound == null)
			return false;
		final byte successor = (byte) (upperBound.getValue()+1);
		return successor == (getValue());
	}

	@Override
	public List<Byte> sample(final int numberOfSample)
	{
		return sample();
	}

	@Override
	public List<Byte> sample()
	{
		return Arrays.asList(getValue());
	}

	@Override
	public String toString()
	{
		return _degenerateBridge.toString();
	}
}
