/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.engine.generic.comparators.DefaultComparableComparator;
import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.ChainedValue;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.DefaultChainedValueSurroundingProvider;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.SurroundingValuesProvider;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.GenericBound;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.GenericIntervalBridge;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementarySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISortedGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class GenericInterval<Data>
extends AbstractInterval<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>>
implements IGenericInterval<Data>
{
	private static final float MAX_SAMPLE = 10;
	protected Comparator<? super Data>			_comparator;
	protected SurroundingValuesProvider<Data>	_surroundingProvider;
	protected GenericBound<Data>				_lowerBound;
	protected GenericBound<Data>				_upperBound;

	static public <Data extends Comparable<? super Data>&ChainedValue<Data>> GenericInterval<Data> makeInterval(@Nullable final Data lowerBound, @Nullable final Data upperBound)
	{
		final DefaultComparableComparator<Data> comparator = DefaultComparableComparator.getInstance();
		final DefaultChainedValueSurroundingProvider<Data> surroundingProvider = DefaultChainedValueSurroundingProvider.getInstance();
		return new GenericInterval<Data>(lowerBound, upperBound, comparator, surroundingProvider, null);
	}

	static public <Data extends Comparable<? super Data>&ChainedValue<Data>> GenericInterval<Data> makeInterval(@Nullable final Data lowerBound, @Nullable final Data upperBound, final Splitter<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>> splitter)
	{
		final DefaultComparableComparator<Data> comparator = DefaultComparableComparator.getInstance();
		final DefaultChainedValueSurroundingProvider<Data> surroundingProvider = DefaultChainedValueSurroundingProvider.getInstance();
		return new GenericInterval<Data>(lowerBound, upperBound, comparator, surroundingProvider, splitter);
	}

	static public <Data extends ChainedValue<Data>> GenericInterval<Data> makeInterval(@Nullable final Data lowerBound, @Nullable final Data upperBound, final Comparator<? super Data> comparator)
	{
		final DefaultChainedValueSurroundingProvider<Data> surroundingProvider = DefaultChainedValueSurroundingProvider.getInstance();
		return new GenericInterval<Data>(lowerBound, upperBound, comparator, surroundingProvider, null);
	}

	static public <Data extends ChainedValue<Data>> GenericInterval<Data> makeInterval(@Nullable final Data lowerBound, @Nullable final Data upperBound, final Comparator<? super Data> comparator, final Splitter<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>> splitter)
	{
		final DefaultChainedValueSurroundingProvider<Data> surroundingProvider = DefaultChainedValueSurroundingProvider.getInstance();
		return new GenericInterval<Data>(lowerBound, upperBound, comparator, surroundingProvider, splitter);
	}

	static public <Data extends Comparable<? super Data>> GenericInterval<Data> makeInterval(@Nullable final Data lowerBound, @Nullable final Data upperBound, final SurroundingValuesProvider<Data> surroundingValuesProvider)
	{
		final DefaultComparableComparator<Data> comparator = DefaultComparableComparator.getInstance();
		return new GenericInterval<Data>(lowerBound, upperBound, comparator, surroundingValuesProvider, null);
	}

	static public <Data extends Comparable<? super Data>> GenericInterval<Data> makeInterval(@Nullable final Data lowerBound, @Nullable final Data upperBound, final SurroundingValuesProvider<Data> surroundingValuesProvider, final Splitter<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>> splitter)
	{
		final DefaultComparableComparator<Data> comparator = DefaultComparableComparator.getInstance();
		return new GenericInterval<Data>(lowerBound, upperBound, comparator, surroundingValuesProvider, splitter);
	}

	static public <Data> GenericInterval<Data> makeInterval(@Nullable final Data lowerBound, @Nullable final Data upperBound, final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingValuesProvider)
	{
		return new GenericInterval<Data>(lowerBound, upperBound, comparator, surroundingValuesProvider, null);
	}

	static public <Data> GenericInterval<Data> makeInterval(@Nullable final Data lowerBound, @Nullable final Data upperBound, final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingValuesProvider, final Splitter<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>> splitter)
	{
		return new GenericInterval<Data>(lowerBound, upperBound, comparator, surroundingValuesProvider, splitter);
	}

	GenericIntervalBridge<Data, GenericInterval<Data>> _bridge;

	AbstractIntervalBoundBridge<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>, GenericInterval<Data>> _boundBridge;

	private GenericInterval(@Nullable final Data lowerBound, @Nullable final Data upperBound, final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingValuesProvider, @Nullable final Splitter<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>> splitter)
	{
		_bridge =  new GenericIntervalBridge<Data, GenericInterval<Data>>(this, comparator, surroundingValuesProvider, splitter);
		_boundBridge = new AbstractIntervalBoundBridge<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>, GenericInterval<Data>>(this);
		_comparator = comparator;
		_surroundingProvider = surroundingValuesProvider;
		_lowerBound = GenericBound.makeBound(lowerBound, comparator, surroundingValuesProvider);
		_upperBound = GenericBound.makeBound(upperBound, comparator, surroundingValuesProvider);
	}

	@Override
	protected GenericIntervalBridge<Data, GenericInterval<Data>> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractIntervalBoundBridge<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>, ? extends AbstractInterval<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public ISortedGenericSet<Data> getAsSet()
	{
		return this;
	}

	@Override
	public ISimpleSortedGenericSet<Data> getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IElementarySortedGenericSet<Data> getAsElementarySet()
	{
		return this;
	}

	@Override
	public IGenericInterval<Data> getAsInterval()
	{
		return this;
	}

	@Override
	public boolean isEmpty()
	{
		return getBridge().isEmpty();
	}

	@Override
	public double size()
	{
		final IGenericBound<Data> lowerBound = getLowerBound();
		final IGenericBound<Data> upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return 0;
		return _comparator.compare(upperBound.getValue(), lowerBound.getValue()) + 1;
	}

	@Override
	public boolean isPreceding(final ISortedGenericSet<Data> set)
	{
		final IGenericBound<Data> upperBound = getUpperBound();
		final IGenericBound<Data> otherLowerBound = set.getLowerBound();
		if(upperBound == null || otherLowerBound == null)
			return false;
		final Data successor = _surroundingProvider.getSuccessor(upperBound.getValue());
		if(successor!=null)
		{
			return successor.equals(otherLowerBound.getValue());
		}
		return set.getLowerBound() == null;
	}

	@Override
	public boolean isSucceeding(final ISortedGenericSet<Data> set)
	{
		final IGenericBound<Data> lowerBound = getLowerBound();
		final IGenericBound<Data> otherUpperBound = set.getUpperBound();
		if(lowerBound == null || otherUpperBound == null)
			return false;
		final Data predecessor = _surroundingProvider.getPredecessor(lowerBound.getValue());
		if(predecessor!=null)
		{
			return predecessor.equals(otherUpperBound.getValue());
		}
		return set.getUpperBound() == null;
	}

	@Override
	public @Nullable IGenericBound<Data> getLowerBound()
	{
		final GenericBound<Data> lowerBound = _lowerBound;

		if(_comparator.compare(lowerBound.getValue(), _upperBound.getValue())<=0)
			return lowerBound;
		return null;
	}

	@Override
	public @Nullable IGenericBound<Data> getUpperBound()
	{
		final GenericBound<Data> upperBound = _upperBound;

		if(_comparator.compare(_lowerBound.getValue(), upperBound.getValue())<=0)
			return upperBound;
		return null;
	}

	@Override
	public boolean contains(@Nullable final Data value)
	{
		final IGenericBound<Data> lowerBound = getLowerBound();
		final IGenericBound<Data> upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return false;
		return (_comparator.compare(lowerBound.getValue(), value)<=0) && (_comparator.compare(upperBound.getValue(), value)>=0);
	}

	@Override
	public List<? extends ISortedGenericSet<Data>> split(final Data[] values, final boolean[] isIntervalStart)
	{
		return getBridge().split(values, isIntervalStart);
	}

	@Override
	public List<? extends ISortedGenericSet<Data>> split(final Data... values)
	{
		final boolean[] inNextInterval = new boolean[values.length];
		Arrays.fill(inNextInterval, true);
		return split(values, inNextInterval);
	}

	@Override
	public IGenericInterval<Data> getSimpleSet()
	{
		return this;
	}

	@Override
	public Iterator<Data> getDataIterator()
	{
		final IGenericBound<Data> lowerBound = getLowerBound();
		final IGenericBound<Data> upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return EmptyIterator.getInstance();
		return new Iterator<Data>()
		{
			@Nullable Data nextValue = lowerBound.getValue();

			@Override
			public boolean hasNext()
			{
				return _comparator.compare(nextValue, upperBound.getValue())<=0;
			}

			@Override
			public @Nullable Data next()
			{
				final Data r = nextValue;
				nextValue = _surroundingProvider.getSuccessor(nextValue);
				return r;
			}
		};
	}

	@Override
	public Iterator<Data> getReverseDataIterator()
	{
		final IGenericBound<Data> lowerBound = getLowerBound();
		final IGenericBound<Data> upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return EmptyIterator.getInstance();
		return new Iterator<Data>()
		{
			@Nullable Data nextValue = upperBound.getValue();

			@Override
			public boolean hasNext()
			{
				return _comparator.compare(nextValue, lowerBound.getValue())>=0;
			}

			@Override
			public @Nullable Data next()
			{
				final Data r = nextValue;
				nextValue = _surroundingProvider.getPredecessor(nextValue);
				return r;
			}
		};
	}

	@Override
	public String toString()
	{
		final IGenericBound<Data> lowerBound = getLowerBound();
		final IGenericBound<Data> upperBound = getUpperBound();
		if (lowerBound == null || upperBound == null)
			return "∅";
		if(isDegenerate())
			return "["+lowerBound.getValue()+"]";
		return "["+lowerBound.getValue()+";"+upperBound.getValue()+"]";
	}

	@Override
	public List<Data> sample(int numberOfSample)
	{
		if(numberOfSample<=2)
			return sample();
		if(isEmpty())
			return Arrays.asList(null);
		final List<Data> ret = new ArrayList<Data>(numberOfSample);
		numberOfSample -= 2;
		final IGenericBound<Data> lowerBound = getLowerBound();
		final IGenericBound<Data> upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			throw new NullPointerException();
		ret.add(lowerBound.getValue());

		final int chunk = (int)(size()/(numberOfSample+1));
		final Iterator<Data> it = getDataIterator();
		final Data next = it.next();
		for(int i = 1;i<=numberOfSample;i++)
		{
			ret.add(_surroundingProvider.getSuccessor(next, i*chunk));

		}
		ret.add(upperBound.getValue());
		return ret;
	}

	@Override
	public List<Data> sample()
	{
		// the formula of the number of samples
		// I wanted it to grow quickly for little sets and as they goes bigger and bigger the number of samples will be stabilized at MAX_SAMPLE
		// I've stated from the Sigmoïde formula and then stretched it as I wanted. --> http://fooplot.com/plot/qej5p2su4q
		final float maxSample = MAX_SAMPLE-2.f; // because we always want 2 Sample fot lower and upper bound
		final int numberOfSample = (int) Math.min((1.f/(1.f+(Math.exp(-size()/(maxSample*4.f))))*maxSample*2.f-maxSample+2.f), MAX_SAMPLE);
		return sample(numberOfSample);
	}
}