/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.classes;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractEmptySet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IEmptyGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class EmptyGenericSet<Data>
extends AbstractEmptySet<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, IEmptyGenericSet<Data>>
implements IEmptyGenericSet<Data>
{
	@SuppressWarnings("rawtypes")
	private static EmptyGenericSet _instance = new EmptyGenericSet();

	@SuppressWarnings("unchecked")
	public static <T> EmptyGenericSet<T> getInstance()
	{
		return _instance;
	}

	private EmptyGenericSet()
	{
	}

	@Override
	public IGenericSet<Data> getAsSet() {
		return this;
	}

	@Override
	public boolean contains(final @Nullable Data value)
	{
		return false;
	}

	@Override
	public EmptyGenericSet<Data> getSimpleSet()
	{
		return this;
	}

	@Override
	public Iterator<? extends EmptyGenericSet<Data>> iterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public Iterator<Data> getDataIterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public IEmptyGenericSet<Data> getAsEmptySet() {
		return this;
	}

	@Override
	public IElementaryGenericSet<Data> getAsElementarySet() {
		return this;
	}

	@Override
	public ISimpleGenericSet<Data> getAsSimpleSet() {
		return this;
	}

	@Override
	public List<Data> sample(final int numberOfSample)
	{
		return sample();
	}

	@Override
	public List<Data> sample()
	{
		return Arrays.asList();
	}
}