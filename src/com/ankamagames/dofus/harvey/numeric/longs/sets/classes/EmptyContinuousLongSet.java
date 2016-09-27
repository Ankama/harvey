/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.sets.classes;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractEmptyContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongBound;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IEmptyContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleContinuousLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class EmptyContinuousLongSet
extends AbstractEmptyContinuousSet<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval, IEmptyContinuousLongSet>
implements IEmptyContinuousLongSet
{
	private static EmptyContinuousLongSet _instance = new EmptyContinuousLongSet();

	public static  EmptyContinuousLongSet getInstance()
	{
		return _instance;
	}

	private EmptyContinuousLongSet()
	{}

	@Override
	public EmptyContinuousLongSet getAsSet()
	{
		return this;
	}

	@Override
	public ISimpleContinuousLongSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IElementaryContinuousLongSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public IContinuousLongInterval getAsInterval()
	{
		return this;
	}

	@Override
	public IEmptyContinuousLongSet getAsEmptySet()
	{
		return this;
	}

	@Override
	public @Nullable IContinuousLongBound getLowerBound()
	{
		return null;
	}

	@Override
	public @Nullable IContinuousLongBound getUpperBound()
	{
		return null;
	}

	@Override
	public boolean contains(final long value)
	{
		return false;
	}

	@Override
	public Iterator<EmptyContinuousLongSet> iterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public List<? extends IContinuousLongSet> split(final long[] values, final boolean[] isIntervalStart)
	{
		return split(values);
	}

	@SuppressWarnings("null")
	@Override
	public List<? extends IContinuousLongSet> split(final long... values)
	{
		return Collections.nCopies(values.length+1, this);
	}

	@Override
	public IEmptyContinuousLongSet getSimpleSet()
	{
		return this;
	}

	@Override
	public Iterator<IContinuousLongBound> getBoundIterator()
	{
		return EmptyIterator.getInstance();
	}
}