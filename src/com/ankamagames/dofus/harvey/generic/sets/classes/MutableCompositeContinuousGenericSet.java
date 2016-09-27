/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparable;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparator;
import com.ankamagames.dofus.harvey.engine.generic.comparators.DefaultContinuousComparableComparator;
import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.BaseCompositeContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleContinuousGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class MutableCompositeContinuousGenericSet<Data, ChildType extends IContinuousGenericSet<Data>>
extends BaseCompositeContinuousGenericSet<Data, ChildType> {

	public static <Data extends ContinuousComparable<Data>, ChildType extends IContinuousGenericSet<Data>> MutableCompositeContinuousGenericSet<Data, ChildType> makeSet(final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		final ContinuousComparator<Data> instance = DefaultContinuousComparableComparator.getInstance();
		return makeSet(instance, splitter);
	}

	public static <Data, ChildType extends IContinuousGenericSet<Data>> MutableCompositeContinuousGenericSet<Data, ChildType> makeSet(final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		return new MutableCompositeContinuousGenericSet<Data, ChildType>(comparator, splitter);
	}

	public static <Data extends ContinuousComparable<Data>, ChildType extends IContinuousGenericSet<Data>> MutableCompositeContinuousGenericSet<Data, ChildType> makeSet(final ChildType element, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		final ContinuousComparator<Data> instance = DefaultContinuousComparableComparator.getInstance();
		return new MutableCompositeContinuousGenericSet<Data, ChildType>(element, instance, splitter);
	}

	public static <Data, ChildType extends IContinuousGenericSet<Data>> MutableCompositeContinuousGenericSet<Data, ChildType> makeSet(final ChildType element, final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		return new MutableCompositeContinuousGenericSet<Data, ChildType>(element, comparator, splitter);
	}

	public static <Data extends ContinuousComparable<Data>, ChildType extends IContinuousGenericSet<Data>> MutableCompositeContinuousGenericSet<Data, ChildType> makeSet(final Iterable<ChildType> collection, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		final ContinuousComparator<Data> instance = DefaultContinuousComparableComparator.getInstance();
		return new MutableCompositeContinuousGenericSet<Data, ChildType>(collection, instance, splitter);
	}

	public static <Data extends ContinuousComparable<Data>, ChildType extends IContinuousGenericSet<Data>> MutableCompositeContinuousGenericSet<Data, ChildType> makeSet(final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter, final ChildType... collection)
	{
		final ContinuousComparator<Data> instance = DefaultContinuousComparableComparator.getInstance();
		return new MutableCompositeContinuousGenericSet<Data, ChildType>(Arrays.asList(collection), instance, splitter);
	}

	public static <Data, ChildType extends IContinuousGenericSet<Data>> MutableCompositeContinuousGenericSet<Data, ChildType> makeSet(final Iterable<ChildType> collection,
		final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		return new MutableCompositeContinuousGenericSet<Data, ChildType>(collection, comparator, splitter);
	}

	public static <Data, ChildType extends IContinuousGenericSet<Data>> MutableCompositeContinuousGenericSet<Data, ChildType> makeSet(final ContinuousComparator<? super Data> comparator,
			final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter, final ChildType... collection)
	{
		return new MutableCompositeContinuousGenericSet<Data, ChildType>(Arrays.asList(collection), comparator, splitter);
	}

	public static <Data, ChildType extends IContinuousGenericSet<Data>> MutableCompositeContinuousGenericSet<Data, ChildType> makeSetFromElements(final List<Data> datas, final ContinuousComparator<? super Data> comparator,
			final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		final List<ChildType> ret = new ArrayList<ChildType>(datas.size());
		for(final Data elmt:datas)
		{
			ret.add((ChildType) DegenerateContinuousGenericSet.makeSet(elmt, comparator, splitter));
		}
		return new MutableCompositeContinuousGenericSet<Data, ChildType>(ret, comparator, splitter);
	}

	protected MutableCompositeContinuousGenericSet(final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		super(comparator, splitter);
	}

	protected MutableCompositeContinuousGenericSet(final ChildType element, final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		super(element, comparator, splitter);
	}

	protected MutableCompositeContinuousGenericSet(final Iterable<ChildType> collection, final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		super(collection, comparator, splitter);
	}
}
