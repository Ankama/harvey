/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.classes;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractEmptyContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IEmptyContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleContinuousByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class EmptyContinuousByteSet
extends AbstractEmptyContinuousSet<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IContinuousByteInterval, IEmptyContinuousByteSet>
implements IEmptyContinuousByteSet
{
	private static EmptyContinuousByteSet _instance = new EmptyContinuousByteSet();

	public static  EmptyContinuousByteSet getInstance()
	{
		return _instance;
	}

	private EmptyContinuousByteSet()
	{}

	@Override
	public EmptyContinuousByteSet getAsSet()
	{
		return this;
	}

	@Override
	public ISimpleContinuousByteSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IElementaryContinuousByteSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public IContinuousByteInterval getAsInterval()
	{
		return this;
	}

	@Override
	public IEmptyContinuousByteSet getAsEmptySet()
	{
		return this;
	}

	@Override
	public @Nullable IContinuousByteBound getLowerBound()
	{
		return null;
	}

	@Override
	public @Nullable IContinuousByteBound getUpperBound()
	{
		return null;
	}

	@Override
	public boolean contains(final byte value)
	{
		return false;
	}

	@Override
	public Iterator<EmptyContinuousByteSet> iterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public List<? extends IContinuousByteSet> split(final byte[] values, final boolean[] isIntervalStart)
	{
		return split(values);
	}

	@SuppressWarnings("null")
	@Override
	public List<? extends IContinuousByteSet> split(final byte... values)
	{
		return Collections.nCopies(values.length+1, this);
	}

	@Override
	public IEmptyContinuousByteSet getSimpleSet()
	{
		return this;
	}

	@Override
	public Iterator<IContinuousByteBound> getBoundIterator()
	{
		return EmptyIterator.getInstance();
	}
}