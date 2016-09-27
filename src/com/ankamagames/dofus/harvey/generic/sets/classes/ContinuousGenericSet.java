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
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleCompositeContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleContinuousGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public final class ContinuousGenericSet<Data, ChildType extends IElementaryContinuousGenericSet<Data>>
extends BaseCompositeContinuousGenericSet<Data, ChildType>
implements ISimpleCompositeContinuousGenericSet<Data, ChildType>
{

	public static <Data extends ContinuousComparable<Data>, ChildType extends IElementaryContinuousGenericSet<Data>> ContinuousGenericSet<Data, ChildType> makeSet(final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		final ContinuousComparator<Data> instance = DefaultContinuousComparableComparator.getInstance();
		return makeSet(instance, splitter);
	}

	public static <Data, ChildType extends IElementaryContinuousGenericSet<Data>> ContinuousGenericSet<Data, ChildType> makeSet(final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		return new ContinuousGenericSet<Data, ChildType>(comparator, splitter);
	}

	public static <Data extends ContinuousComparable<Data>, ChildType extends IElementaryContinuousGenericSet<Data>> ContinuousGenericSet<Data, ChildType> makeSet(final ChildType element, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		final ContinuousComparator<Data> instance = DefaultContinuousComparableComparator.getInstance();
		return new ContinuousGenericSet<Data, ChildType>(element, instance, splitter);
	}

	public static <Data, ChildType extends IElementaryContinuousGenericSet<Data>> ContinuousGenericSet<Data, ChildType> makeSet(final ChildType element, final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		return new ContinuousGenericSet<Data, ChildType>(element, comparator, splitter);
	}

	public static <Data extends ContinuousComparable<Data>, ChildType extends IElementaryContinuousGenericSet<Data>> ContinuousGenericSet<Data, ChildType> makeSet(final Iterable<ChildType> collection, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		final ContinuousComparator<Data> instance = DefaultContinuousComparableComparator.getInstance();
		return new ContinuousGenericSet<Data, ChildType>(collection, instance, splitter);
	}

	public static <Data, ChildType extends IElementaryContinuousGenericSet<Data>> ContinuousGenericSet<Data, ChildType> makeSet(final Iterable<ChildType> collection,
		final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		return new ContinuousGenericSet<Data, ChildType>(collection, comparator, splitter);
	}

	public static <Data extends ContinuousComparable<Data>, ChildType extends IElementaryContinuousGenericSet<Data>> ContinuousGenericSet<Data, ChildType> makeSet(final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter, final ChildType... children)
	{
		final ContinuousComparator<Data> instance = DefaultContinuousComparableComparator.getInstance();
		return new ContinuousGenericSet<Data, ChildType>(Arrays.asList(children), instance, splitter);
	}

	public static <Data, ChildType extends IElementaryContinuousGenericSet<Data>> ContinuousGenericSet<Data, ChildType> makeSet(final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter, final ChildType... children)
	{
		return new ContinuousGenericSet<Data, ChildType>(Arrays.asList(children), comparator, splitter);
	}

	public static <Data extends ContinuousComparable<Data>, ChildType extends IElementaryContinuousGenericSet<Data>> ContinuousGenericSet<Data, ChildType> makeSetFromElements(final List<Data> asList, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		final ContinuousComparator<Data> instance = DefaultContinuousComparableComparator.getInstance();
		final ArrayList<ChildType> ret = new ArrayList<ChildType>(asList.size());
		for(final Data elmt:asList)
		{
			ret.add((ChildType)DegenerateContinuousGenericSet.makeSet(elmt, splitter));
		}
		return new ContinuousGenericSet<Data, ChildType>(ret, instance, splitter);
	}

	public static <Data, ChildType extends IElementaryContinuousGenericSet<Data>> ContinuousGenericSet<Data, ChildType> makeSetFromElements(final List<Data> asList, final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		final ArrayList<ChildType> ret = new ArrayList<ChildType>(asList.size());
		for(final Data elmt:asList)
		{
			ret.add((ChildType)DegenerateContinuousGenericSet.makeSet(elmt, comparator, splitter));
		}
		return new ContinuousGenericSet<Data, ChildType>(ret, comparator, splitter);
	}

	private ContinuousGenericSet(final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		super(comparator, splitter);
	}

	private ContinuousGenericSet(final ChildType element, final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		super(element, comparator, splitter);
	}

	private ContinuousGenericSet(final Iterable<ChildType> collection, final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		super(collection, comparator, splitter);
	}

	@Override
	public ISimpleContinuousGenericSet<Data> getAsSimpleSet()
	{
		return this;
	}
}
