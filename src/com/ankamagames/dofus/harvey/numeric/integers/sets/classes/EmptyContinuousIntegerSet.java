/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.classes;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractEmptyContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerBound;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IEmptyContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleContinuousIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class EmptyContinuousIntegerSet
extends AbstractEmptyContinuousSet<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, IContinuousIntegerInterval, IEmptyContinuousIntegerSet>
implements IEmptyContinuousIntegerSet
{
	private static EmptyContinuousIntegerSet _instance = new EmptyContinuousIntegerSet();

	public static  EmptyContinuousIntegerSet getInstance()
	{
		return _instance;
	}

	private EmptyContinuousIntegerSet()
	{}

	@Override
	public EmptyContinuousIntegerSet getAsSet()
	{
		return this;
	}

	@Override
	public ISimpleContinuousIntegerSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IElementaryContinuousIntegerSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public IContinuousIntegerInterval getAsInterval()
	{
		return this;
	}

	@Override
	public IEmptyContinuousIntegerSet getAsEmptySet()
	{
		return this;
	}

	@Override
	public @Nullable IContinuousIntegerBound getLowerBound()
	{
		return null;
	}

	@Override
	public @Nullable IContinuousIntegerBound getUpperBound()
	{
		return null;
	}

	@Override
	public boolean contains(final int value)
	{
		return false;
	}

	@Override
	public Iterator<EmptyContinuousIntegerSet> iterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public List<? extends IContinuousIntegerSet> split(final int[] values, final boolean[] isIntervalStart)
	{
		return split(values);
	}

	@SuppressWarnings("null")
	@Override
	public List<? extends IContinuousIntegerSet> split(final int... values)
	{
		return Collections.nCopies(values.length+1, this);
	}

	@Override
	public IEmptyContinuousIntegerSet getSimpleSet()
	{
		return this;
	}

	@Override
	public Iterator<IContinuousIntegerBound> getBoundIterator()
	{
		return EmptyIterator.getInstance();
	}
}