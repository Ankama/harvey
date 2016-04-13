/**
 * 
 */
package com.ankamagames.dofus.harvey.generic.sets.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractOrderedEmpty;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.DefaultUncheckedIncrementableIncrementor;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.BaseOrderedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IOrderedGenericSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class EmptyOrderedGenericSet<Data>
	extends AbstractOrderedEmpty<IOrderedGenericSet<Data>>
	implements IOrderedGenericSet<Data>
{
	@SuppressWarnings("rawtypes")
	private static EmptyOrderedGenericSet _instance = new EmptyOrderedGenericSet();
	
	@SuppressWarnings("unchecked")
	public static <T> EmptyOrderedGenericSet<T> getInstance()
	{
		return _instance;
	}
	
	private EmptyOrderedGenericSet()
	{}
	
	@Override
	protected IOrderedGenericSet<Data> getThis()
	{
		return this;
	}

	@Override
	public @Nullable Data getLowerBound()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public @Nullable Data getUpperBound()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Comparator<? super Data> getComparator()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(@Nullable final Data value)
	{
		return false;
	}

	@Override
	public ArrayList<EmptyOrderedGenericSet<Data>> split(final Data[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<EmptyOrderedGenericSet<Data>> r = new ArrayList<EmptyOrderedGenericSet<Data>>(values.length+1);
		Collections.fill(r, this);
		return r;
	}

	@Override
	public ArrayList<EmptyOrderedGenericSet<Data>> split(final Data... values)
	{
		final ArrayList<EmptyOrderedGenericSet<Data>> r = new ArrayList<EmptyOrderedGenericSet<Data>>(values.length+1);
		Collections.fill(r, this);
		return r;
	}

	@Override
	public BaseOrderedGenericSet<Data, EmptyOrderedGenericSet<Data>> getMergedSet()
	{
		final DefaultUncheckedIncrementableIncrementor<Data> uncheckedIncrementor = DefaultUncheckedIncrementableIncrementor.getInstance();
		return BaseOrderedGenericSet.makeSet(getComparator(), uncheckedIncrementor);
	}
}