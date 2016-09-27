/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.classes;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractEmptySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementarySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IEmptySortedGenericSet;
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
public final class EmptySortedGenericSet<Data>
extends AbstractEmptySortedSet<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>, IEmptySortedGenericSet<Data>>
implements IEmptySortedGenericSet<Data>
{
	@SuppressWarnings("rawtypes")
	private static EmptySortedGenericSet _instance = new EmptySortedGenericSet();

	@SuppressWarnings("unchecked")
	public static <T> EmptySortedGenericSet<T> getInstance()
	{
		return _instance;
	}

	private EmptySortedGenericSet()
	{}

	@Override
	public EmptySortedGenericSet<Data> getAsEmptySet()
	{
		return this;
	}

	@Override
	public EmptySortedGenericSet<Data> getAsElementarySet()
	{
		return this;
	}

	@Override
	public EmptySortedGenericSet<Data> getAsSimpleSet()
	{
		return this;
	}

	@Override
	public EmptySortedGenericSet<Data> getAsSet()
	{
		return this;
	}

	@Override
	public EmptySortedGenericSet<Data> getAsInterval()
	{
		return this;
	}

	@Override
	public EmptySortedGenericSet<Data> getSimpleSet()
	{
		return this;
	}

	@Override
	public boolean contains(@Nullable final Data value)
	{
		return false;
	}

	@Override
	public List<? extends ISortedGenericSet<Data>> split(final Data[] values, final boolean[] isIntervalStart)
	{
		return split(values);
	}

	@Override
	public List<? extends ISortedGenericSet<Data>> split(final Data... values)
	{
		return Collections.nCopies(values.length+1, this);
	}

	@Override
	public Iterator<? extends IEmptySortedGenericSet<Data>> iterator() {
		return EmptyIterator.getInstance();
	}

	@Override
	public Iterator<Data> getDataIterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public Iterator<Data> getReverseDataIterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public @Nullable IGenericBound<Data> getLowerBound()
	{
		return null;
	}

	@Override
	public @Nullable IGenericBound<Data> getUpperBound()
	{
		return null;
	}

	@Override
	public List<Data> sample(final int numberOfSample)
	{
		return sample();
	}

	@Override
	public List<Data> sample()
	{
		return Arrays.asList();
	}
}