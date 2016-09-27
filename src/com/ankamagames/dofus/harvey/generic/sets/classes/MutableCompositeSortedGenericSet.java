/**
 *
 */
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
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISortedGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class MutableCompositeSortedGenericSet<Data, ChildType extends ISortedGenericSet<Data>>
extends BaseCompositeSortedGenericSet<Data, ChildType>
{
	protected static <Data extends ChainedValue<Data>&Comparable<? super Data>, ChildType extends ISortedGenericSet<Data>> MutableCompositeSortedGenericSet<Data, ChildType> makeSet()
	{
		final DefaultComparableComparator<Data> comparator = DefaultComparableComparator.getInstance();
		final DefaultChainedValueSurroundingProvider<Data> surroundingProvider = DefaultChainedValueSurroundingProvider.getInstance();
		return makeSet(comparator, surroundingProvider);
	}

	public static <Data extends ChainedValue<Data>, ChildType extends ISortedGenericSet<Data>> MutableCompositeSortedGenericSet<Data, ChildType> makeSet(final Comparator<? super Data> comparator)
	{
		final DefaultChainedValueSurroundingProvider<Data> surroundingProvider = DefaultChainedValueSurroundingProvider.getInstance();
		return new MutableCompositeSortedGenericSet<Data, ChildType>(comparator, surroundingProvider);
	}

	public static <Data extends Comparable<? super Data>, ChildType extends ISortedGenericSet<Data>> MutableCompositeSortedGenericSet<Data, ChildType> makeSet(final SurroundingValuesProvider<Data> surroundingProvider)
	{
		final DefaultComparableComparator<Data> comparartor = DefaultComparableComparator.getInstance();
		return new MutableCompositeSortedGenericSet<Data, ChildType>(comparartor, surroundingProvider);
	}

	public static <Data, ChildType extends ISortedGenericSet<Data>> MutableCompositeSortedGenericSet<Data, ChildType> makeSet(final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		return new MutableCompositeSortedGenericSet<Data, ChildType>(comparator, surroundingProvider);
	}

	public static <Data extends ChainedValue<Data>&Comparable<? super Data>, ChildType extends ISortedGenericSet<Data>> MutableCompositeSortedGenericSet<Data, ChildType> makeSet(final ChildType element)
	{
		final DefaultComparableComparator<Data> comparator = DefaultComparableComparator.getInstance();
		final DefaultChainedValueSurroundingProvider<Data> surroundingProvider = DefaultChainedValueSurroundingProvider.getInstance();
		return new MutableCompositeSortedGenericSet<Data, ChildType>(element, comparator, surroundingProvider);
	}

	public static <Data extends ChainedValue<Data>, ChildType extends ISortedGenericSet<Data>> MutableCompositeSortedGenericSet<Data, ChildType> makeSet(final ChildType element, final Comparator<? super Data> comparator)
	{
		final DefaultChainedValueSurroundingProvider<Data> surroundingProvider = DefaultChainedValueSurroundingProvider.getInstance();
		return new MutableCompositeSortedGenericSet<Data, ChildType>(element, comparator, surroundingProvider);
	}

	public static <Data extends Comparable<? super Data>, ChildType extends ISortedGenericSet<Data>> MutableCompositeSortedGenericSet<Data, ChildType> makeSet(final ChildType element, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		final DefaultComparableComparator<Data> comparator = DefaultComparableComparator.getInstance();
		return new MutableCompositeSortedGenericSet<Data, ChildType>(element, comparator, surroundingProvider);
	}

	public static <Data, ChildType extends ISortedGenericSet<Data>> MutableCompositeSortedGenericSet<Data, ChildType> makeSet(final ChildType element, final Comparator<? super Data> comparator,
		final SurroundingValuesProvider<Data> surroundingProvider)
	{
		return new MutableCompositeSortedGenericSet<Data, ChildType>(element, comparator, surroundingProvider);
	}

	public static <Data extends ChainedValue<Data>&Comparable<? super Data>, ChildType extends ISortedGenericSet<Data>> MutableCompositeSortedGenericSet<Data, ChildType> makeSet(final Collection<ChildType> collection)
	{
		final DefaultComparableComparator<Data> comparator = DefaultComparableComparator.getInstance();
		final DefaultChainedValueSurroundingProvider<Data> surroundingProvider = DefaultChainedValueSurroundingProvider.getInstance();
		return new MutableCompositeSortedGenericSet<Data, ChildType>(collection, comparator, surroundingProvider);
	}

	public static <Data extends ChainedValue<Data>, ChildType extends ISortedGenericSet<Data>> MutableCompositeSortedGenericSet<Data, ChildType> makeSet(final Collection<ChildType> collection,
		final Comparator<? super Data> comparator)
	{
		final DefaultChainedValueSurroundingProvider<Data> surroundingProvider = DefaultChainedValueSurroundingProvider.getInstance();
		return new MutableCompositeSortedGenericSet<Data, ChildType>(collection, comparator, surroundingProvider);
	}

	public static <Data extends ChainedValue<Data>, ChildType extends ISortedGenericSet<Data>> MutableCompositeSortedGenericSet<Data, ChildType> makeSet(
			final Comparator<? super Data> comparator, final ChildType... collection)
	{
		final DefaultChainedValueSurroundingProvider<Data> surroundingProvider = DefaultChainedValueSurroundingProvider.getInstance();
		return new MutableCompositeSortedGenericSet<Data, ChildType>(Arrays.asList(collection), comparator, surroundingProvider);
	}

	public static <Data extends Comparable<? super Data>, ChildType extends ISortedGenericSet<Data>> MutableCompositeSortedGenericSet<Data, ChildType> makeSet(final Collection<ChildType> collection,
		final SurroundingValuesProvider<Data> surroundingProvider)
	{
		final DefaultComparableComparator<Data> comparator = DefaultComparableComparator.getInstance();
		return new MutableCompositeSortedGenericSet<Data, ChildType>(collection, comparator, surroundingProvider);
	}

	public static <Data extends Comparable<? super Data>, ChildType extends ISortedGenericSet<Data>> MutableCompositeSortedGenericSet<Data, ChildType> makeSet(
			final SurroundingValuesProvider<Data> surroundingProvider, final ChildType... collection)
	{
		final DefaultComparableComparator<Data> comparator = DefaultComparableComparator.getInstance();
		return new MutableCompositeSortedGenericSet<Data, ChildType>(Arrays.asList(collection), comparator, surroundingProvider);
	}

	public static <Data, ChildType extends ISortedGenericSet<Data>> MutableCompositeSortedGenericSet<Data, ChildType> makeSet(final Collection<ChildType> collection,
		final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		return new MutableCompositeSortedGenericSet<Data, ChildType>(collection, comparator, surroundingProvider);
	}

	public static <Data, ChildType extends ISortedGenericSet<Data>> MutableCompositeSortedGenericSet<Data, ChildType> makeSet(
			final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider, final ChildType... collection)
	{
		return new MutableCompositeSortedGenericSet<Data, ChildType>(Arrays.asList(collection), comparator, surroundingProvider);
	}

	public static <Data extends ChainedValue<Data>&Comparable<? super Data>> MutableCompositeSortedGenericSet<Data, ? extends IElementarySortedGenericSet<Data>> makeSetFromElements(final List<Data> asList)
	{
		final ArrayList<IElementarySortedGenericSet<Data>> children = new ArrayList<IElementarySortedGenericSet<Data>>(asList.size());
		for(final Data data:asList)
		{
			children.add(DegenerateSortedGenericSet.makeSet(data));
		}
		return makeSet(children);
	}

	public static <Data extends ChainedValue<Data>> MutableCompositeSortedGenericSet<Data, ? extends IElementarySortedGenericSet<Data>> makeSetFromElements(final List<Data> asList, final Comparator<Data> comparator)
	{
		final ArrayList<IElementarySortedGenericSet<Data>> children = new ArrayList<IElementarySortedGenericSet<Data>>(asList.size());
		for(final Data data:asList)
		{
			children.add(DegenerateSortedGenericSet.makeSet(data, comparator));
		}
		return makeSet(children, comparator);
	}

	public static <Data extends Comparable<? super Data>> MutableCompositeSortedGenericSet<Data, ? extends IElementarySortedGenericSet<Data>> makeSetFromElements(final List<Data> asList, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		final ArrayList<IElementarySortedGenericSet<Data>> children = new ArrayList<IElementarySortedGenericSet<Data>>(asList.size());
		for(final Data data:asList)
		{
			children.add(DegenerateSortedGenericSet.makeSet(data, surroundingProvider));
		}
		return makeSet(children, surroundingProvider);
	}

	public static <Data> MutableCompositeSortedGenericSet<Data, ? extends IElementarySortedGenericSet<Data>> makeSetFromElements(final List<Data> asList, final Comparator<Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		final ArrayList<IElementarySortedGenericSet<Data>> children = new ArrayList<IElementarySortedGenericSet<Data>>(asList.size());
		for(final Data data:asList)
		{
			children.add(DegenerateSortedGenericSet.makeSet(data, comparator, surroundingProvider));
		}
		return makeSet(children, comparator, surroundingProvider);
	}

	protected MutableCompositeSortedGenericSet(final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		super(comparator, surroundingProvider);
	}

	@SuppressWarnings("unchecked")
	protected MutableCompositeSortedGenericSet(final ChildType element, final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		super(element, comparator, surroundingProvider);
	}

	protected MutableCompositeSortedGenericSet(final Collection<ChildType> collection, final Comparator<? super Data> comparator,
		final SurroundingValuesProvider<Data> surroundingProvider)
	{
		super(collection, comparator, surroundingProvider);
	}
}
