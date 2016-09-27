/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.sets.classes;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractEmptySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IEmptyShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class EmptyShortSet
extends AbstractEmptySortedSet<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval, IEmptyShortSet>
implements IEmptyShortSet
{
	@SuppressWarnings("rawtypes")
	private static EmptyShortSet _instance = new EmptyShortSet();

	@SuppressWarnings("unchecked")
	public static EmptyShortSet getInstance()
	{
		return _instance;
	}

	private EmptyShortSet()
	{}

	@Override
	public EmptyShortSet getAsEmptySet()
	{
		return this;
	}

	@Override
	public EmptyShortSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public EmptyShortSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public EmptyShortSet getAsSet()
	{
		return this;
	}

	@Override
	public EmptyShortSet getAsInterval()
	{
		return this;
	}

	@Override
	public EmptyShortSet getSimpleSet()
	{
		return this;
	}

	@Override
	public boolean contains(final short value)
	{
		return false;
	}

	@Override
	public List<? extends IShortSet> split(final short[] values, final boolean[] isIntervalStart)
	{
		return split(values);
	}

	@Override
	public List<? extends IShortSet> split(final short... values)
	{
		return Collections.nCopies(values.length+1, this);
	}

	@Override
	public Iterator<? extends IEmptyShortSet> iterator() {
		return EmptyIterator.getInstance();
	}

	@Override
	public Iterator<Short> getDataIterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public Iterator<Short> getReverseDataIterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public @Nullable IShortBound getLowerBound()
	{
		return null;
	}

	@Override
	public @Nullable IShortBound getUpperBound()
	{
		return null;
	}

	@Override
	public List<Short> sample(final int numberOfSample)
	{
		return sample();
	}

	@Override
	public List<Short> sample()
	{
		return Arrays.asList();
	}
}