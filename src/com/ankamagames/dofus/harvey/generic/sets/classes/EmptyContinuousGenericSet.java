/**
 * 
 */
package com.ankamagames.dofus.harvey.generic.sets.classes;

import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractContinuousEmpty;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparator;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.BaseContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class EmptyContinuousGenericSet<Data>
extends AbstractContinuousEmpty<IContinuousGenericSet<Data>>
implements IContinuousGenericSet<Data>
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
	protected IContinuousGenericSet<Data> getThis()
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
	public ContinuousComparator<? super Data> getComparator()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(@Nullable final Data value)
	{
		return false;
	}

	@Override
	public ArrayList<EmptyContinuousGenericSet<Data>> split(final Data[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<EmptyContinuousGenericSet<Data>> r = new ArrayList<EmptyContinuousGenericSet<Data>>(values.length+1);
		Collections.fill(r, this);
		return r;
	}

	@Override
	public ArrayList<EmptyContinuousGenericSet<Data>> split(final Data... values)
	{
		final ArrayList<EmptyContinuousGenericSet<Data>> r = new ArrayList<EmptyContinuousGenericSet<Data>>(values.length+1);
		Collections.fill(r, this);
		return r;
	}

	@Override
	public BaseContinuousGenericSet<Data, EmptyContinuousGenericSet<Data>> getMergedSet()
	{
		return BaseContinuousGenericSet.makeSet(getComparator());
	}
}