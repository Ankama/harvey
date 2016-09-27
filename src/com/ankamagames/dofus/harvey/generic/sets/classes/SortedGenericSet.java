package com.ankamagames.dofus.harvey.generic.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.generic.comparators.DefaultComparableComparator;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.ChainedValue;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.DefaultChainedValueSurroundingProvider;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.SurroundingValuesProvider;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.BaseCompositeSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementarySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleCompositeSortedGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public final class SortedGenericSet<Data, ChildType extends IElementarySortedGenericSet<Data>>
extends BaseCompositeSortedGenericSet<Data, ChildType>
implements ISimpleCompositeSortedGenericSet<Data, ChildType>
{
	public static <Data extends ChainedValue<Data>&Comparable<? super Data>, ChildType extends IElementarySortedGenericSet<Data>> SortedGenericSet<Data, ChildType> makeSet()
	{
		final DefaultComparableComparator<Data> comparator = DefaultComparableComparator.getInstance();
		final DefaultChainedValueSurroundingProvider<Data> surroundingProvider = DefaultChainedValueSurroundingProvider.getInstance();
		return makeSet(comparator, surroundingProvider);
	}

	public static <Data extends ChainedValue<Data>, ChildType extends IElementarySortedGenericSet<Data>> SortedGenericSet<Data, ChildType> makeSet(final Comparator<? super Data> comparator)
	{
		final DefaultChainedValueSurroundingProvider<Data> surroundingProvider = DefaultChainedValueSurroundingProvider.getInstance();
		return new SortedGenericSet<Data, ChildType>(comparator, surroundingProvider);
	}

	public static <Data extends Comparable<? super Data>, ChildType extends IElementarySortedGenericSet<Data>> SortedGenericSet<Data, ChildType> makeSet(final SurroundingValuesProvider<Data> surroundingProvider)
	{
		final DefaultComparableComparator<Data> comparartor = DefaultComparableComparator.getInstance();
		return new SortedGenericSet<Data, ChildType>(comparartor, surroundingProvider);
	}

	public static <Data, ChildType extends IElementarySortedGenericSet<Data>> SortedGenericSet<Data, ChildType> makeSet(final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		return new SortedGenericSet<Data, ChildType>(comparator, surroundingProvider);
	}

	public static <Data extends ChainedValue<Data>&Comparable<? super Data>, ChildType extends IElementarySortedGenericSet<Data>> SortedGenericSet<Data, ChildType> makeSet(final ChildType element)
	{
		final DefaultComparableComparator<Data> comparator = DefaultComparableComparator.getInstance();
		final DefaultChainedValueSurroundingProvider<Data> surroundingProvider = DefaultChainedValueSurroundingProvider.getInstance();
		return new SortedGenericSet<Data, ChildType>(element, comparator, surroundingProvider);
	}

	public static <Data extends ChainedValue<Data>, ChildType extends IElementarySortedGenericSet<Data>> SortedGenericSet<Data, ChildType> makeSet(final ChildType element, final Comparator<? super Data> comparator)
	{
		final DefaultChainedValueSurroundingProvider<Data> surroundingProvider = DefaultChainedValueSurroundingProvider.getInstance();
		return new SortedGenericSet<Data, ChildType>(element, comparator, surroundingProvider);
	}

	public static <Data extends Comparable<? super Data>, ChildType extends IElementarySortedGenericSet<Data>> SortedGenericSet<Data, ChildType> makeSet(final ChildType element, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		final DefaultComparableComparator<Data> comparator = DefaultComparableComparator.getInstance();
		return new SortedGenericSet<Data, ChildType>(element, comparator, surroundingProvider);
	}

	public static <Data, ChildType extends IElementarySortedGenericSet<Data>> SortedGenericSet<Data, ChildType> makeSet(final ChildType element, final Comparator<? super Data> comparator,
		final SurroundingValuesProvider<Data> surroundingProvider)
	{
		return new SortedGenericSet<Data, ChildType>(element, comparator, surroundingProvider);
	}

	public static <Data extends ChainedValue<Data>&Comparable<? super Data>, ChildType extends IElementarySortedGenericSet<Data>> SortedGenericSet<Data, ChildType> makeSet(final Collection<ChildType> collection)
	{
		final DefaultComparableComparator<Data> comparator = DefaultComparableComparator.getInstance();
		final DefaultChainedValueSurroundingProvider<Data> surroundingProvider = DefaultChainedValueSurroundingProvider.getInstance();
		return new SortedGenericSet<Data, ChildType>(collection, comparator, surroundingProvider);
	}

	public static <Data extends ChainedValue<Data>&Comparable<? super Data>, ChildType extends IElementarySortedGenericSet<Data>> SortedGenericSet<Data, ChildType> makeSet(final ChildType... elements)
	{
		return makeSet(Arrays.asList(elements));
	}

	public static <Data extends ChainedValue<Data>, ChildType extends IElementarySortedGenericSet<Data>> SortedGenericSet<Data, ChildType> makeSet(final Collection<ChildType> collection,
		final Comparator<? super Data> comparator)
	{
		final DefaultChainedValueSurroundingProvider<Data> surroundingProvider = DefaultChainedValueSurroundingProvider.getInstance();
		return new SortedGenericSet<Data, ChildType>(collection, comparator, surroundingProvider);
	}

	public static <Data extends ChainedValue<Data>, ChildType extends IElementarySortedGenericSet<Data>> SortedGenericSet<Data, ChildType> makeSet(final Comparator<? super Data> comparator,
			final ChildType... elements)
	{
		return makeSet(Arrays.asList(elements), comparator);
	}

	public static <Data extends Comparable<? super Data>, ChildType extends IElementarySortedGenericSet<Data>> SortedGenericSet<Data, ChildType> makeSet(final Collection<ChildType> collection,
		final SurroundingValuesProvider<Data> surroundingProvider)
	{
		final DefaultComparableComparator<Data> comparator = DefaultComparableComparator.getInstance();
		return new SortedGenericSet<Data, ChildType>(collection, comparator, surroundingProvider);
	}

	public static <Data extends Comparable<? super Data>, ChildType extends IElementarySortedGenericSet<Data>> SortedGenericSet<Data, ChildType> makeSet(final SurroundingValuesProvider<Data> surroundingProvider,
			final ChildType... elements)
	{
		return makeSet(Arrays.asList(elements), surroundingProvider);
	}

	public static <Data extends ChainedValue<Data>&Comparable<? super Data>> SortedGenericSet<Data, DegenerateSortedGenericSet<Data>> makeSetFromElements(final List<Data> asList)
	{
		final ArrayList<DegenerateSortedGenericSet<Data>> children = new ArrayList<DegenerateSortedGenericSet<Data>>(asList.size());
		for(final Data data:asList)
		{
			children.add(DegenerateSortedGenericSet.makeSet(data));
		}
		return makeSet(children);
	}

	public static <Data extends ChainedValue<Data>> SortedGenericSet<Data, DegenerateSortedGenericSet<Data>> makeSetFromElements(final List<Data> asList, final Comparator<Data> comparator)
	{
		final ArrayList<DegenerateSortedGenericSet<Data>> children = new ArrayList<DegenerateSortedGenericSet<Data>>(asList.size());
		for(final Data data:asList)
		{
			children.add(DegenerateSortedGenericSet.makeSet(data, comparator));
		}
		return makeSet(children, comparator);
	}

	public static <Data extends Comparable<? super Data>> SortedGenericSet<Data, DegenerateSortedGenericSet<Data>> makeSetFromElements(final List<Data> asList, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		final ArrayList<DegenerateSortedGenericSet<Data>> children = new ArrayList<DegenerateSortedGenericSet<Data>>(asList.size());
		for(final Data data:asList)
		{
			children.add(DegenerateSortedGenericSet.makeSet(data, surroundingProvider));
		}
		return makeSet(children, surroundingProvider);
	}

	public static <Data> SortedGenericSet<Data, DegenerateSortedGenericSet<Data>> makeSetFromElements(final List<Data> asList, final Comparator<Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		final ArrayList<DegenerateSortedGenericSet<Data>> children = new ArrayList<DegenerateSortedGenericSet<Data>>(asList.size());
		for(final Data data:asList)
		{
			children.add(DegenerateSortedGenericSet.makeSet(data, comparator, surroundingProvider));
		}
		return makeSet(children, comparator, surroundingProvider);
	}

	public static <Data, ChildType extends IElementarySortedGenericSet<Data>> SortedGenericSet<Data, ChildType> makeSet(final Collection<ChildType> collection,
			final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider)
		{
			return new SortedGenericSet<Data, ChildType>(collection, comparator, surroundingProvider);
		}

	public static <Data, ChildType extends IElementarySortedGenericSet<Data>> SortedGenericSet<Data, ChildType> makeSet(final Comparator<? super Data> comparator,
			final SurroundingValuesProvider<Data> surroundingProvider, final ChildType... elements)
	{
		return makeSet(Arrays.asList(elements), comparator, surroundingProvider);
	}

	private SortedGenericSet(final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		super(comparator, surroundingProvider);
	}

	private SortedGenericSet(final ChildType element, final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		super(element, comparator, surroundingProvider);
	}

	private SortedGenericSet(final Collection<ChildType> collection, final Comparator<? super Data> comparator,
		final SurroundingValuesProvider<Data> surroundingProvider)
	{
		super(collection, comparator, surroundingProvider);
	}

	@Override
	public SortedGenericSet<Data, ChildType> getAsSimpleSet() {
		return this;
	}

	/*
	@Override
	public Iterator<IGenericBound<Data>> getBoundIterator() {
		if(isEmpty())
			return EmptyIterator.getInstance();
		final IGenericBound<Data> lowerBound = getLowerBound();
		final IGenericBound<Data> upperBound = getUpperBound();
		if(isDegenerate() || isInterval()) {
			if(lowerBound == null || upperBound == null)
				throw new NullPointerException();// this should never happen because interval must avec Lower and Upper bound
			return new TwoValueIterator<IGenericBound<Data>>(lowerBound, upperBound);
		}
		return new Iterator<IGenericBound<Data>>() {
			Iterator<ChildType> childIt = getChildren().iterator();
			ChildType lastChild = childIt.next();
			@Nullable IGenericBound<Data> currentBound = lastChild.getLowerBound();
			ChildType nextChild = lastChild;
			ChildType prevChild = lastChild;
			Iterator<IGenericBound<Data>> currentBoundIt = lastChild.getBoundIterator();
			boolean firstTime = true;
			boolean isLowerBound = true;
			boolean needNextChild = true;
			@Override
			public boolean hasNext() {

				while(!firstTime)
				{
					if(isLowerBound)
					{
						if(needNextChild && childIt.hasNext())
						{
							prevChild = nextChild;
							nextChild = childIt.next();
							if(nextChild.isSucceeding(prevChild))
								continue;
							needNextChild = false;
						}
						else
						{
							isLowerBound = false;
							currentBound = lastChild.getUpperBound();
							needNextChild = false;
							return true;
						}
					}
					else
					{
						if(needNextChild && childIt.hasNext())
						{
							prevChild = nextChild;
							nextChild = childIt.next();
						}
						else if(!childIt.hasNext() && lastChild == nextChild)
							return false;
						lastChild = nextChild;
						currentBound = lastChild.getLowerBound();
						isLowerBound = true;
						needNextChild = true;
						return true;
					}
				}
				firstTime = false;
				return true;
			}

			@Override
			public IGenericBound<Data> next()
			{
				final IGenericBound<Data> tmp = currentBound;
				if(tmp == null)
					throw new NoSuchElementException();
				return tmp;
			}
		};
	}*/

}