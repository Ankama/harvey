/**
 * 
 */
package com.ankamagames.dofus.harvey.generic.sets.classes;

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.classes.sets.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractEmpty;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.BaseGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class EmptyGenericSet<Data>
	extends AbstractEmpty<IGenericSet<Data>>
	implements IGenericSet<Data>
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
	protected IGenericSet<Data> getThis()
	{
		return this;
	}

	@Override
	public boolean contains(@Nullable final Data value)
	{
		return false;
	}

	@Override
	public BaseGenericSet<Data, EmptyGenericSet<Data>> getMergedSet()
	{
		return BaseGenericSet.makeSet();
	}

	@Override
	public Iterator<Data> iterator()
	{
		return EmptyIterator.getInstance();
	}
}