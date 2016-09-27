/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.classes;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractEmptyContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IEmptyContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleContinuousGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class EmptyContinuousGenericSet<Data>
extends AbstractEmptyContinuousSet<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>, IEmptyContinuousGenericSet<Data>>
implements IEmptyContinuousGenericSet<Data>
{
	@SuppressWarnings("rawtypes")
	private static EmptyContinuousGenericSet _instance = new EmptyContinuousGenericSet();

	@SuppressWarnings("unchecked")
	public static <T> EmptyContinuousGenericSet<T> getInstance()
	{
		return _instance;
	}

	private EmptyContinuousGenericSet()
	{}

	@Override
	public EmptyContinuousGenericSet<Data> getAsSet()
	{
		return this;
	}

	@Override
	public ISimpleContinuousGenericSet<Data> getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IElementaryContinuousGenericSet<Data> getAsElementarySet()
	{
		return this;
	}

	@Override
	public IContinuousGenericInterval<Data> getAsInterval()
	{
		return this;
	}

	@Override
	public IEmptyContinuousGenericSet<Data> getAsEmptySet()
	{
		return this;
	}

	@Override
	public @Nullable IContinuousGenericBound<Data> getLowerBound()
	{
		return null;
	}

	@Override
	public @Nullable IContinuousGenericBound<Data> getUpperBound()
	{
		return null;
	}

	@Override
	public boolean contains(final @Nullable Data value)
	{
		return false;
	}

	@Override
	public Iterator<EmptyContinuousGenericSet<Data>> iterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public List<? extends IContinuousGenericSet<Data>> split(final Data[] values, final boolean[] isIntervalStart)
	{
		return split(values);
	}

	@SuppressWarnings("null")
	@Override
	public List<? extends IContinuousGenericSet<Data>> split(final Data... values)
	{
		return Collections.nCopies(values.length+1, this);
	}

	@Override
	public IEmptyContinuousGenericSet<Data> getSimpleSet()
	{
		return this;
	}

	@Override
	public Iterator<IContinuousGenericBound<Data>> getBoundIterator()
	{
		return EmptyIterator.getInstance();
	}
}