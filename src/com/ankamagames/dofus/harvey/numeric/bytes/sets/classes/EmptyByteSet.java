/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.classes;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractEmptySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IEmptyByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class EmptyByteSet
extends AbstractEmptySortedSet<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval, IEmptyByteSet>
implements IEmptyByteSet
{
	@SuppressWarnings("rawtypes")
	private static EmptyByteSet _instance = new EmptyByteSet();

	@SuppressWarnings("unchecked")
	public static EmptyByteSet getInstance()
	{
		return _instance;
	}

	private EmptyByteSet()
	{}

	@Override
	public EmptyByteSet getAsEmptySet()
	{
		return this;
	}

	@Override
	public EmptyByteSet getAsElementarySet()
	{
		return this;
	}

	@Override
	public EmptyByteSet getAsSimpleSet()
	{
		return this;
	}

	@Override
	public EmptyByteSet getAsSet()
	{
		return this;
	}

	@Override
	public EmptyByteSet getAsInterval()
	{
		return this;
	}

	@Override
	public EmptyByteSet getSimpleSet()
	{
		return this;
	}

	@Override
	public boolean contains(final byte value)
	{
		return false;
	}

	@Override
	public List<? extends IByteSet> split(final byte[] values, final boolean[] isIntervalStart)
	{
		return split(values);
	}

	@Override
	public List<? extends IByteSet> split(final byte... values)
	{
		return Collections.nCopies(values.length+1, this);
	}

	@Override
	public Iterator<? extends IEmptyByteSet> iterator() {
		return EmptyIterator.getInstance();
	}

	@Override
	public Iterator<Byte> getDataIterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public Iterator<Byte> getReverseDataIterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public @Nullable IByteBound getLowerBound()
	{
		return null;
	}

	@Override
	public @Nullable IByteBound getUpperBound()
	{
		return null;
	}

	@Override
	public List<Byte> sample(final int numberOfSample)
	{
		return sample();
	}

	@Override
	public List<Byte> sample()
	{
		return Arrays.asList();
	}
}