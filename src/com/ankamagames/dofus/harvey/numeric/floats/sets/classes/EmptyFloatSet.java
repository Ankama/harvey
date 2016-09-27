/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.floats.sets.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractEmptyContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IFloatBound;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IElementaryFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatInterval;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.ISimpleFloatSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.sun.xml.internal.fastinfoset.stax.events.EmptyIterator;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class EmptyFloatSet
extends AbstractEmptyContinuousSet<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, EmptyFloatSet>
implements IElementaryFloatSet, IFloatInterval
{
	private static EmptyFloatSet _instance = new EmptyFloatSet();

	public static EmptyFloatSet getInstance()
	{
		return _instance;
	}

	private EmptyFloatSet()
	{}

	@Override
	public IFloatSet getAsSet()
	{
		return this;
	}

	@Override
	public IElementaryFloatSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public ISimpleFloatSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public EmptyFloatSet getAsEmptySet()
	{
		return this;
	}

	@Override
	public IFloatInterval getAsInterval()
	{
		return this;
	}

	@Override
	public @Nullable IFloatBound getLowerBound()
	{
		return null;
	}

	@Override
	public @Nullable IFloatBound getUpperBound()
	{
		return null;
	}

	@Override
	public Iterator<EmptyFloatSet> iterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public Iterator<IFloatBound> getBoundIterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public boolean contains(final float value)
	{
		return false;
	}

	@Override
	public List<? extends IFloatSet> split(final float[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<EmptyFloatSet> r = new ArrayList<EmptyFloatSet>(values.length+1);
		Collections.fill(r, this);
		return r;
	}

	@Override
	public List<? extends IFloatSet> split(final float... values)
	{
		final ArrayList<EmptyFloatSet> r = new ArrayList<EmptyFloatSet>(values.length+1);
		Collections.fill(r, this);
		return r;
	}

	@Override
	public EmptyFloatSet getSimpleSet()
	{
		return this;
	}
}