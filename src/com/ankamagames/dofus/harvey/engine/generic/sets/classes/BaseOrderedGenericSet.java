/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeOrderedSet;
import com.ankamagames.dofus.harvey.engine.generic.comparators.DefaultUncheckedComparableComparator;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.DefaultIncrementableIncrementor;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.Incrementable;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.Incrementor;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ICompositeOrderedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IOrderedGenericSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedGenericSet<Data, ChildType extends IOrderedGenericSet<Data>>
extends	AbstractCompositeOrderedSet
<
	IOrderedGenericSet<Data>,
	ChildType,
	BridgedCompositeOrderedGenericSet<Data, ChildType, BaseOrderedGenericSet<Data, ChildType>>
>
implements ICompositeOrderedGenericSet<Data, ChildType>
{
	protected BridgedCompositeOrderedGenericSet<Data, ChildType, BaseOrderedGenericSet<Data, ChildType>>	_bridgedComposite;

	protected Comparator<? super Data>																		_comparator;

	protected static <Data extends Incrementable<Data>, ChildType extends IOrderedGenericSet<Data>> BaseOrderedGenericSet<Data, ChildType> makeSet()
	{
		final DefaultIncrementableIncrementor<Data> incrementor = DefaultIncrementableIncrementor.getInstance();
		return makeSet(DefaultUncheckedComparableComparator.getInstance(), incrementor);
	}

	protected static <Data extends Incrementable<Data>, ChildType extends IOrderedGenericSet<Data>> BaseOrderedGenericSet<Data, ChildType> makeSet(final Comparator<? super Data> comparator)
	{
		final DefaultIncrementableIncrementor<Data> incrementor = DefaultIncrementableIncrementor.getInstance();
		return new BaseOrderedGenericSet<Data, ChildType>(comparator, incrementor);
	}

	protected static <Data, ChildType extends IOrderedGenericSet<Data>> BaseOrderedGenericSet<Data, ChildType> makeSet(final Incrementor<Data> incrementor)
	{
		return new BaseOrderedGenericSet<Data, ChildType>(DefaultUncheckedComparableComparator.getInstance(), incrementor);
	}

	public static <Data, ChildType extends IOrderedGenericSet<Data>> BaseOrderedGenericSet<Data, ChildType> makeSet(final Comparator<? super Data> comparator, final Incrementor<Data> incrementor)
	{
		return new BaseOrderedGenericSet<Data, ChildType>(comparator, incrementor);
	}

	public static <Data extends Incrementable<Data>, ChildType extends IOrderedGenericSet<Data>> BaseOrderedGenericSet<Data, ChildType> makeSet(final ChildType element)
	{
		final DefaultIncrementableIncrementor<Data> incrementor = DefaultIncrementableIncrementor.getInstance();
		return new BaseOrderedGenericSet<Data, ChildType>(element, DefaultUncheckedComparableComparator.getInstance(), incrementor);
	}

	public static <Data extends Incrementable<Data>, ChildType extends IOrderedGenericSet<Data>> BaseOrderedGenericSet<Data, ChildType> makeSet(final ChildType element, final Comparator<? super Data> comparator)
	{
		final DefaultIncrementableIncrementor<Data> incrementor = DefaultIncrementableIncrementor.getInstance();
		return new BaseOrderedGenericSet<Data, ChildType>(element, comparator, incrementor);
	}

	public static <Data, ChildType extends IOrderedGenericSet<Data>> BaseOrderedGenericSet<Data, ChildType> makeSet(final ChildType element, final Incrementor<Data> incrementor)
	{
		return new BaseOrderedGenericSet<Data, ChildType>(element, DefaultUncheckedComparableComparator.getInstance(), incrementor);
	}

	public static <Data, ChildType extends IOrderedGenericSet<Data>> BaseOrderedGenericSet<Data, ChildType> makeSet(final ChildType element, final Comparator<? super Data> comparator,
		final Incrementor<Data> incrementor)
	{
		return new BaseOrderedGenericSet<Data, ChildType>(element, comparator, incrementor);
	}

	public static <Data extends Incrementable<Data>, ChildType extends IOrderedGenericSet<Data>> BaseOrderedGenericSet<Data, ChildType> makeSet(final Collection<? extends ChildType> collection)
	{
		final DefaultIncrementableIncrementor<Data> incrementor = DefaultIncrementableIncrementor.getInstance();
		return new BaseOrderedGenericSet<Data, ChildType>(collection, DefaultUncheckedComparableComparator.getInstance(), incrementor);
	}

	public static <Data extends Incrementable<Data>, ChildType extends IOrderedGenericSet<Data>> BaseOrderedGenericSet<Data, ChildType> makeSet(final Collection<? extends ChildType> collection,
		final Comparator<? super Data> comparator)
	{
		final DefaultIncrementableIncrementor<Data> incrementor = DefaultIncrementableIncrementor.getInstance();
		return new BaseOrderedGenericSet<Data, ChildType>(collection, comparator, incrementor);
	}

	public static <Data, ChildType extends IOrderedGenericSet<Data>> BaseOrderedGenericSet<Data, ChildType> makeSet(final Collection<? extends ChildType> collection,
		final Incrementor<Data> incrementor)
	{
		return new BaseOrderedGenericSet<Data, ChildType>(collection, DefaultUncheckedComparableComparator.getInstance(), incrementor);
	}

	public static <Data, ChildType extends IOrderedGenericSet<Data>> BaseOrderedGenericSet<Data, ChildType> makeSet(final Collection<? extends ChildType> collection,
		final Comparator<? super Data> comparator, final Incrementor<Data> incrementor)
	{
		return new BaseOrderedGenericSet<Data, ChildType>(collection, comparator, incrementor);
	}

	private BaseOrderedGenericSet(final Comparator<? super Data> comparator, final Incrementor<Data> incrementor)
	{
		_comparator = comparator;
		_bridgedComposite = new BridgedCompositeOrderedGenericSet<Data, ChildType, BaseOrderedGenericSet<Data, ChildType>>(this, incrementor);
	}

	private BaseOrderedGenericSet(final ChildType element, final Comparator<? super Data> comparator,
		final Incrementor<Data> incrementor)
	{
		_comparator = comparator;
		_bridgedComposite = new BridgedCompositeOrderedGenericSet<Data, ChildType, BaseOrderedGenericSet<Data, ChildType>>(this, element, incrementor);
	}

	private BaseOrderedGenericSet(final Collection<? extends ChildType> collection, final Comparator<? super Data> comparator,
		final Incrementor<Data> incrementor)
	{
		_comparator = comparator;
		_bridgedComposite = new BridgedCompositeOrderedGenericSet<Data, ChildType, BaseOrderedGenericSet<Data, ChildType>>(this, collection, incrementor);
	}

	@Override
	protected IOrderedGenericSet<Data> getThis()
	{
		return this;
	}

	@Override
	protected BridgedCompositeOrderedGenericSet<Data, ChildType, BaseOrderedGenericSet<Data, ChildType>> getBridgedComposite()
	{
		return _bridgedComposite;
	}

	@Override
	public List<? extends IOrderedGenericSet<Data>> split(final Data[] datas, final boolean[] isIntervalStart)
	{
		return _bridgedComposite.split(datas, isIntervalStart);
	}

	@Override
	public List<? extends IOrderedGenericSet<Data>> split(final Data... datas)
	{
		final boolean[] inNextInterval = new boolean[datas.length];
		Arrays.fill(inNextInterval, true);
		return split(datas, inNextInterval);
	}

	@Override
	public List<? extends IOrderedGenericSet<Data>> splitOnRange(final IOrderedGenericSet<Data> set)
	{
		return _bridgedComposite.splitOnRange(set);
	}

	@Override
	public Comparator<? super Data> getComparator()
	{
		return _comparator;
	}

	@Override
	public @Nullable Data getLowerBound()
	{
		return _bridgedComposite.getLowerBound();
	}

	@Override
	public @Nullable Data getUpperBound()
	{
		return _bridgedComposite.getUpperBound();
	}

	@Override
	public boolean contains(final @Nullable Data value)
	{
		return _bridgedComposite.contains(value);
	}

	@Override
	public boolean isInterval()
	{
		return _bridgedComposite.isInterval();
	}

	@Override
	public boolean containsAllValuesInRange(final IOrderedGenericSet<Data> set)
	{
		return _bridgedComposite.containsAllValuesInRange(set);
	}
	
	@Override
	public BaseOrderedGenericSet<Data, ?> getMergedSet()
	{
		return _bridgedComposite.getMergedSet();
	}
	
	@Override
	public String toString()
	{
		return _bridgedComposite.toString();
	}
}