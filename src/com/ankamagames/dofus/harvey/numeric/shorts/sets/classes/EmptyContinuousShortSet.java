/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.sets.classes;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractEmptyContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IEmptyContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleContinuousShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class EmptyContinuousShortSet
extends AbstractEmptyContinuousSet<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval, IEmptyContinuousShortSet>
implements IEmptyContinuousShortSet
{
	private static EmptyContinuousShortSet _instance = new EmptyContinuousShortSet();

	public static  EmptyContinuousShortSet getInstance()
	{
		return _instance;
	}

	private EmptyContinuousShortSet()
	{}

	@Override
	public EmptyContinuousShortSet getAsSet()
	{
		return this;
	}

	@Override
	public ISimpleContinuousShortSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IElementaryContinuousShortSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public IContinuousShortInterval getAsInterval()
	{
		return this;
	}

	@Override
	public IEmptyContinuousShortSet getAsEmptySet()
	{
		return this;
	}

	@Override
	public @Nullable IContinuousShortBound getLowerBound()
	{
		return null;
	}

	@Override
	public @Nullable IContinuousShortBound getUpperBound()
	{
		return null;
	}

	@Override
	public boolean contains(final short value)
	{
		return false;
	}

	@Override
	public Iterator<EmptyContinuousShortSet> iterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public List<? extends IContinuousShortSet> split(final short[] values, final boolean[] isIntervalStart)
	{
		return split(values);
	}

	@SuppressWarnings("null")
	@Override
	public List<? extends IContinuousShortSet> split(final short... values)
	{
		return Collections.nCopies(values.length+1, this);
	}

	@Override
	public IEmptyContinuousShortSet getSimpleSet()
	{
		return this;
	}

	@Override
	public Iterator<IContinuousShortBound> getBoundIterator()
	{
		return EmptyIterator.getInstance();
	}
}