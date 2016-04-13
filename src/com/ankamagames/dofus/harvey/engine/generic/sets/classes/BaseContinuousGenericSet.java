/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeContinuousSet;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparable;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparator;
import com.ankamagames.dofus.harvey.engine.generic.comparators.DefaultUncheckedContinuousComparableComparator;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ICompositeContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseContinuousGenericSet<Data, ChildType extends IContinuousGenericSet<Data>>
extends	AbstractCompositeContinuousSet
<
	IContinuousGenericSet<Data>,
	ChildType,
	BridgedCompositeContinuousGenericSet<Data, ChildType, BaseContinuousGenericSet<Data, ChildType>>
>
implements ICompositeContinuousGenericSet<Data, ChildType>, IContinuousGenericSet<Data>
{
	protected BridgedCompositeContinuousGenericSet<Data, ChildType, BaseContinuousGenericSet<Data, ChildType>>	_bridgedComposite;

	protected ContinuousComparator<? super Data>												_comparator;

	public static <Data extends ContinuousComparable<Data>, ChildType extends IContinuousGenericSet<Data>> BaseContinuousGenericSet<Data, ChildType> makeSet()
	{
		return makeSet(DefaultUncheckedContinuousComparableComparator.getInstance());
	}

	public static <Data, ChildType extends IContinuousGenericSet<Data>> BaseContinuousGenericSet<Data, ChildType> makeSet(final ContinuousComparator<? super Data> comparator)
	{
		return new BaseContinuousGenericSet<Data, ChildType>(comparator);
	}

	public static <Data extends ContinuousComparable<Data>, ChildType extends IContinuousGenericSet<Data>> BaseContinuousGenericSet<Data, ChildType> makeSet(final ChildType element)
	{
		return new BaseContinuousGenericSet<Data, ChildType>(element, DefaultUncheckedContinuousComparableComparator.getInstance());
	}

	public static <Data, ChildType extends IContinuousGenericSet<Data>> BaseContinuousGenericSet<Data, ChildType> makeSet(final ChildType element, final ContinuousComparator<? super Data> comparator)
	{
		return new BaseContinuousGenericSet<Data, ChildType>(element, comparator);
	}

	public static <Data extends ContinuousComparable<Data>, ChildType extends IContinuousGenericSet<Data>> BaseContinuousGenericSet<Data, ChildType> makeSet(final Collection<? extends ChildType> collection)
	{
		return new BaseContinuousGenericSet<Data, ChildType>(collection, DefaultUncheckedContinuousComparableComparator.getInstance());
	}

	public static <Data, ChildType extends IContinuousGenericSet<Data>> BaseContinuousGenericSet<Data, ChildType> makeSet(final Collection<? extends ChildType> collection,
		final ContinuousComparator<? super Data> comparator)
	{
		return new BaseContinuousGenericSet<Data, ChildType>(collection, comparator);
	}

	private BaseContinuousGenericSet(final ContinuousComparator<? super Data> comparator)
	{
		_comparator = comparator;
		_bridgedComposite = new BridgedCompositeContinuousGenericSet<Data, ChildType, BaseContinuousGenericSet<Data, ChildType>>(
			this);
	}

	private BaseContinuousGenericSet(final ChildType element, final ContinuousComparator<? super Data> comparator)
	{
		_comparator = comparator;
		_bridgedComposite = new BridgedCompositeContinuousGenericSet<Data, ChildType, BaseContinuousGenericSet<Data, ChildType>>(
			this, element);
	}

	private BaseContinuousGenericSet(final Collection<? extends ChildType> collection, final ContinuousComparator<? super Data> comparator)
	{
		_comparator = comparator;
		_bridgedComposite = new BridgedCompositeContinuousGenericSet<Data, ChildType, BaseContinuousGenericSet<Data, ChildType>>(
			this, collection);
	}

	@Override
	protected IContinuousGenericSet<Data> getThis()
	{
		return this;
	}

	@Override
	protected BridgedCompositeContinuousGenericSet<Data, ChildType, BaseContinuousGenericSet<Data, ChildType>> getBridgedComposite()
	{
		return _bridgedComposite;
	}
	
	@Override
	public boolean isLowerBoundClosed()
	{
		return _bridgedComposite.isLowerBoundClosed();
	}
	
	@Override
	public boolean isUpperBoundClosed()
	{
		return _bridgedComposite.isUpperBoundClosed();
	}

	@Override
	public List<? extends IContinuousGenericSet<Data>> split(final Data[] datas, final boolean[] isIntervalStart)
	{
		return _bridgedComposite.split(datas, isIntervalStart);
	}

	@Override
	public List<? extends IContinuousGenericSet<Data>> split(final Data... datas)
	{
		final boolean[] inNextInterval = new boolean[datas.length];
		Arrays.fill(inNextInterval, true);
		return split(datas, inNextInterval);
	}

	@Override
	public List<? extends IContinuousGenericSet<Data>> splitOnRange(final IContinuousGenericSet<Data> set)
	{
		return _bridgedComposite.splitOnRange(set);
	}

	@Override
	public ContinuousComparator<? super Data> getComparator()
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
	public boolean containsAllValuesInRange(final IContinuousGenericSet<Data> set)
	{
		return _bridgedComposite.containsAllValuesInRange(set);
	}
	
	@Override
	public BaseContinuousGenericSet<Data, ?> getMergedSet()
	{
		return _bridgedComposite.getMergedSet();
	}
	
	@Override
	public String toString()
	{
		return _bridgedComposite.toString();
	}
}