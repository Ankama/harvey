/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.classes;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractEmptySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IEmptyIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerBound;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class EmptyIntegerSet
extends AbstractEmptySortedSet<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval, IEmptyIntegerSet>
implements IEmptyIntegerSet
{
	@SuppressWarnings("rawtypes")
	private static EmptyIntegerSet _instance = new EmptyIntegerSet();

	@SuppressWarnings("unchecked")
	public static EmptyIntegerSet getInstance()
	{
		return _instance;
	}

	private EmptyIntegerSet()
	{}

	@Override
	public EmptyIntegerSet getAsEmptySet()
	{
		return this;
	}

	@Override
	public EmptyIntegerSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public EmptyIntegerSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public EmptyIntegerSet getAsSet()
	{
		return this;
	}

	@Override
	public EmptyIntegerSet getAsInterval()
	{
		return this;
	}

	@Override
	public EmptyIntegerSet getSimpleSet()
	{
		return this;
	}

	@Override
	public boolean contains(final int value)
	{
		return false;
	}

	@Override
	public List<? extends IIntegerSet> split(final int[] values, final boolean[] isIntervalStart)
	{
		return split(values);
	}

	@Override
	public List<? extends IIntegerSet> split(final int... values)
	{
		return Collections.nCopies(values.length+1, this);
	}

	@Override
	public Iterator<? extends IEmptyIntegerSet> iterator() {
		return EmptyIterator.getInstance();
	}

	@Override
	public Iterator<Integer> getDataIterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public Iterator<Integer> getReverseDataIterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public @Nullable IIntegerBound getLowerBound()
	{
		return null;
	}

	@Override
	public @Nullable IIntegerBound getUpperBound()
	{
		return null;
	}

	@Override
	public List<Integer> sample(final int numberOfSample)
	{
		return sample();
	}

	@Override
	public List<Integer> sample()
	{
		return Arrays.asList();
	}
}