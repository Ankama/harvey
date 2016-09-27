/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.CommonDegenerateIntegerSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.DegenerateIntegerSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.IntegerBound;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.interfaces.IIIntegerBoundFactory;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IDegenerateIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerBound;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleIntegerSet;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.NonNullByDefault;



/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class DegenerateIntegerSet
extends AbstractDegenerateSortedSet<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval, IDegenerateIntegerSet>
implements IDegenerateIntegerSet
{
	protected DegenerateIntegerSetBridge<DegenerateIntegerSet> _bridge;
	protected AbstractDegenerateBoundBridge<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval, IDegenerateIntegerSet, DegenerateIntegerSet> _boundBridge;
	CommonDegenerateIntegerSetBridge<IIntegerBound, IIntegerSet> _degenerateBridge;

	public static DegenerateIntegerSet makeSet(final int value)
	{
		return new DegenerateIntegerSet(value);
	}

	private DegenerateIntegerSet(final int value)
	{
		_bridge = new DegenerateIntegerSetBridge<DegenerateIntegerSet>(this);
		final EmptyIntegerSet empty = EmptyIntegerSet.getInstance();
		_degenerateBridge = new CommonDegenerateIntegerSetBridge<IIntegerBound, IIntegerSet>(
				value,
				new IIIntegerBoundFactory<IIntegerBound>()
				{
					@Override
					public IIntegerBound makeBound(final boolean isLowerBound, final int value)
					{
						return IntegerBound.makeBound(value);
					}
				}, empty, this);
		_boundBridge = new AbstractDegenerateBoundBridge<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval, IDegenerateIntegerSet, DegenerateIntegerSet>(this);
	}

	@Override
	protected AbstractDegenerateSortedSetBridge<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval, IDegenerateIntegerSet, ? extends AbstractDegenerateSortedSet<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval, IDegenerateIntegerSet>> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractDegenerateBoundBridge<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval, IDegenerateIntegerSet, ? extends AbstractDegenerateSortedSet<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval, IDegenerateIntegerSet>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public IIntegerSet getAsSet()
	{
		return this;
	}

	@Override
	public IElementaryIntegerSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public ISimpleIntegerSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IDegenerateIntegerSet getAsDegenerateSet()
	{
		return this;
	}

	@Override
	public IIntegerInterval getAsInterval()
	{
		return this;
	}

	@Override
	public int getValue()
	{
		return _degenerateBridge.getValue();
	}

	@Override
	public @NonNull IIntegerBound getLowerBound()
	{
		return _degenerateBridge.getLowerBound();
	}

	@Override
	public @NonNull IIntegerBound getUpperBound()
	{
		return _degenerateBridge.getUpperBound();
	}

	@Override
	public DegenerateIntegerSet getSimpleSet()
	{
		return this;
	}

	@Override
	public boolean contains(final int value)
	{
		return _degenerateBridge.contains(value);
	}

	@Override
	public ArrayList<? extends IIntegerSet> split(final int[] values, final boolean[] isIntervalStart)
	{
		return _degenerateBridge.split(values, isIntervalStart);
	}

	@Override
	public ArrayList<? extends IIntegerSet> split(final int... values)
	{
		return _degenerateBridge.split(values);
	}

	@Override
	public Iterator<Integer> getDataIterator()
	{
		return _degenerateBridge.getDataIterator();
	}

	@Override
	public Iterator<Integer> getReverseDataIterator() {
		return _degenerateBridge.getReverseDataIterator();
	}

	@Override
	public boolean isPreceding(final IIntegerSet set)
	{
		final IIntegerBound lowerBound = set.getLowerBound();
		if(lowerBound == null)
			return false;
		final int predecessor = lowerBound.getValue()-1;
		return predecessor == (getValue());
	}

	@Override
	public boolean isSucceeding(final IIntegerSet set)
	{
		final IIntegerBound upperBound = set.getUpperBound();
		if(upperBound == null)
			return false;
		final int successor = upperBound.getValue()+1;
		return successor == (getValue());
	}

	@Override
	public List<Integer> sample(final int numberOfSample)
	{
		return sample();
	}

	@Override
	public List<Integer> sample()
	{
		return Arrays.asList(getValue());
	}

	@Override
	public String toString()
	{
		return _degenerateBridge.toString();
	}
}
