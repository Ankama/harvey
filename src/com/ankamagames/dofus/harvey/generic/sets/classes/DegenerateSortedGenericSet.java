/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSetBridge;
import com.ankamagames.dofus.harvey.engine.generic.comparators.DefaultComparableComparator;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.ChainedValue;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.DefaultChainedValueSurroundingProvider;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.SurroundingValuesProvider;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.CommonDegenerateSortedGenericSetBridge;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.DegenerateSortedGenericSetBridge;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.GenericBound;
import com.ankamagames.dofus.harvey.engine.generic.sets.interfaces.IIGenericBoundFactory;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IDegenerateSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementarySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISortedGenericSet;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;



/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class DegenerateSortedGenericSet<Data>
extends AbstractDegenerateSortedSet<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>, IDegenerateSortedGenericSet<Data>>
implements IDegenerateSortedGenericSet<Data>
{
	protected SurroundingValuesProvider<Data> _surroundingProvider;
	protected DegenerateSortedGenericSetBridge<Data, DegenerateSortedGenericSet<Data>> _bridge;
	protected AbstractDegenerateBoundBridge<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>, IDegenerateSortedGenericSet<Data>, DegenerateSortedGenericSet<Data>> _boundBridge;
	CommonDegenerateSortedGenericSetBridge<Data, IGenericBound<Data>, ISortedGenericSet<Data>> _degenerateBridge;

	public static <Data extends Comparable<? super Data>&ChainedValue<Data>> DegenerateSortedGenericSet<Data> makeSet(@Nullable final Data value)
	{
		final DefaultComparableComparator<Data> comparator = DefaultComparableComparator.getInstance();
		final DefaultChainedValueSurroundingProvider<Data> surroundingProvider = DefaultChainedValueSurroundingProvider.getInstance();
		return new DegenerateSortedGenericSet<Data>(value, comparator, surroundingProvider);
	}

	public static <Data extends ChainedValue<Data>> DegenerateSortedGenericSet<Data> makeSet(@Nullable final Data value, final Comparator<? super Data> comparator)
	{
		final DefaultChainedValueSurroundingProvider<Data> surroundingProvider = DefaultChainedValueSurroundingProvider.getInstance();
		return new DegenerateSortedGenericSet<Data>(value, comparator, surroundingProvider);
	}

	public static <Data extends  Comparable<? super Data>> DegenerateSortedGenericSet<Data> makeSet(@Nullable final Data value, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		final DefaultComparableComparator<Data> comparator = DefaultComparableComparator.getInstance();
		return new DegenerateSortedGenericSet<Data>(value, comparator, surroundingProvider);
	}

	public static <Data> DegenerateSortedGenericSet<Data> makeSet(@Nullable final Data value, final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		return new DegenerateSortedGenericSet<Data>(value, comparator, surroundingProvider);
	}

	private DegenerateSortedGenericSet(@Nullable final Data value, final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		_surroundingProvider = surroundingProvider;
		_bridge = new DegenerateSortedGenericSetBridge<Data, DegenerateSortedGenericSet<Data>>(this, comparator, surroundingProvider);
		final EmptySortedGenericSet<Data> empty = EmptySortedGenericSet.getInstance();
		_degenerateBridge = new CommonDegenerateSortedGenericSetBridge<Data, IGenericBound<Data>, ISortedGenericSet<Data>>(
				value,
				new IIGenericBoundFactory<Data, IGenericBound<Data>>()
				{
					@Override
					public IGenericBound<Data> makeBound(final boolean isLowerBound, final @Nullable Data value)
					{
						return GenericBound.makeBound(value, comparator, surroundingProvider);
					}
				},
				comparator, empty, this);
		_boundBridge = new AbstractDegenerateBoundBridge<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>, IDegenerateSortedGenericSet<Data>, DegenerateSortedGenericSet<Data>>(this);
	}

	@Override
	protected AbstractDegenerateSortedSetBridge<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>, IDegenerateSortedGenericSet<Data>, ? extends AbstractDegenerateSortedSet<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>, IDegenerateSortedGenericSet<Data>>> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractDegenerateBoundBridge<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>, IDegenerateSortedGenericSet<Data>, ? extends AbstractDegenerateSortedSet<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>, IDegenerateSortedGenericSet<Data>>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public ISortedGenericSet<Data> getAsSet()
	{
		return this;
	}

	@Override
	public IElementarySortedGenericSet<Data> getAsElementarySet()
	{
		return this;
	}

	@Override
	public ISimpleSortedGenericSet<Data> getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IDegenerateSortedGenericSet<Data> getAsDegenerateSet()
	{
		return this;
	}

	@Override
	public IGenericInterval<Data> getAsInterval()
	{
		return this;
	}

	@Override
	@Nullable
	public Data getValue()
	{
		return _degenerateBridge.getValue();
	}

	@Override
	public @NonNull IGenericBound<Data> getLowerBound()
	{
		return _degenerateBridge.getLowerBound();
	}

	@Override
	public @NonNull IGenericBound<Data> getUpperBound()
	{
		return _degenerateBridge.getUpperBound();
	}

	@Override
	public DegenerateSortedGenericSet<Data> getSimpleSet()
	{
		return this;
	}

	@Override
	public boolean contains(final @Nullable Data value)
	{
		return _degenerateBridge.contains(value);
	}

	@Override
	public ArrayList<? extends ISortedGenericSet<Data>> split(final Data[] values, final boolean[] isIntervalStart)
	{
		return _degenerateBridge.split(values, isIntervalStart);
	}

	@Override
	public ArrayList<? extends ISortedGenericSet<Data>> split(final Data... values)
	{
		return _degenerateBridge.split(values);
	}

	@Override
	public Iterator<Data> getDataIterator()
	{
		return _degenerateBridge.getDataIterator();
	}

	@Override
	public Iterator<Data> getReverseDataIterator() {
		return _degenerateBridge.getReverseDataIterator();
	}

	@Override
	public boolean isPreceding(final ISortedGenericSet<Data> set)
	{
		final IGenericBound<Data> lowerBound = set.getLowerBound();
		if(lowerBound == null)
			return false;
		final Data predecessor = _surroundingProvider.getPredecessor(lowerBound.getValue());
		if(predecessor!=null)
			return predecessor.equals(getValue());
		return getValue()==null;
	}

	@Override
	public boolean isSucceeding(final ISortedGenericSet<Data> set)
	{
		final IGenericBound<Data> upperBound = set.getUpperBound();
		if(upperBound == null)
			return false;
		final Data successor = _surroundingProvider.getSuccessor(upperBound.getValue());
		if(successor!=null)
			return successor.equals(getValue());
		return getValue()==null;
	}

	@Override
	public String toString()
	{
		return _degenerateBridge.toString();
	}

	@Override
	public List<Data> sample(final int numberOfSample)
	{
		return sample();
	}

	@Override
	public List<Data> sample()
	{
		return Arrays.asList(getValue());
	}
}