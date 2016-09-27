/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.doubles.sets.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractEmptyContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.interfaces.IDoubleBound;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IElementaryDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleInterval;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.ISimpleDoubleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.sun.xml.internal.fastinfoset.stax.events.EmptyIterator;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class EmptyDoubleSet
extends AbstractEmptyContinuousSet<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, EmptyDoubleSet>
implements IElementaryDoubleSet, IDoubleInterval
{
	private static EmptyDoubleSet _instance = new EmptyDoubleSet();

	public static EmptyDoubleSet getInstance()
	{
		return _instance;
	}

	private EmptyDoubleSet()
	{}

	@Override
	public IDoubleSet getAsSet()
	{
		return this;
	}

	@Override
	public IElementaryDoubleSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public ISimpleDoubleSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public EmptyDoubleSet getAsEmptySet()
	{
		return this;
	}

	@Override
	public IDoubleInterval getAsInterval()
	{
		return this;
	}

	@Override
	public @Nullable IDoubleBound getLowerBound()
	{
		return null;
	}

	@Override
	public @Nullable IDoubleBound getUpperBound()
	{
		return null;
	}

	@Override
	public Iterator<EmptyDoubleSet> iterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public Iterator<IDoubleBound> getBoundIterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public boolean contains(final double value)
	{
		return false;
	}

	@Override
	public List<? extends IDoubleSet> split(final double[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<EmptyDoubleSet> r = new ArrayList<EmptyDoubleSet>(values.length+1);
		Collections.fill(r, this);
		return r;
	}

	@Override
	public List<? extends IDoubleSet> split(final double... values)
	{
		final ArrayList<EmptyDoubleSet> r = new ArrayList<EmptyDoubleSet>(values.length+1);
		Collections.fill(r, this);
		return r;
	}

	@Override
	public EmptyDoubleSet getSimpleSet()
	{
		return this;
	}
}