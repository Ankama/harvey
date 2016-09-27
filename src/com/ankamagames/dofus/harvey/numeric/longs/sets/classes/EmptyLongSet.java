/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.sets.classes;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractEmptySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongBound;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IEmptyLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class EmptyLongSet
extends AbstractEmptySortedSet<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval, IEmptyLongSet>
implements IEmptyLongSet
{
	@SuppressWarnings("rawtypes")
	private static EmptyLongSet _instance = new EmptyLongSet();

	@SuppressWarnings("unchecked")
	public static EmptyLongSet getInstance()
	{
		return _instance;
	}

	private EmptyLongSet()
	{}

	@Override
	public EmptyLongSet getAsEmptySet()
	{
		return this;
	}

	@Override
	public EmptyLongSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public EmptyLongSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public EmptyLongSet getAsSet()
	{
		return this;
	}

	@Override
	public EmptyLongSet getAsInterval()
	{
		return this;
	}

	@Override
	public EmptyLongSet getSimpleSet()
	{
		return this;
	}

	@Override
	public boolean contains(final long value)
	{
		return false;
	}

	@Override
	public List<? extends ILongSet> split(final long[] values, final boolean[] isIntervalStart)
	{
		return split(values);
	}

	@Override
	public List<? extends ILongSet> split(final long... values)
	{
		return Collections.nCopies(values.length+1, this);
	}

	@Override
	public Iterator<? extends IEmptyLongSet> iterator() {
		return EmptyIterator.getInstance();
	}

	@Override
	public Iterator<Long> getDataIterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public Iterator<Long> getReverseDataIterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public @Nullable ILongBound getLowerBound()
	{
		return null;
	}

	@Override
	public @Nullable ILongBound getUpperBound()
	{
		return null;
	}

	@Override
	public List<Long> sample(final int numberOfSample)
	{
		return sample();
	}

	@Override
	public List<Long> sample()
	{
		return Arrays.asList();
	}
}