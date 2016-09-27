/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.generic.sets.classes.BaseCompositeGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class MutableCompositeGenericSet<Data, ChildType extends IGenericSet<Data>>
extends BaseCompositeGenericSet<Data, ChildType>
{

	public static <Data, ChildType extends IGenericSet<Data>>
	MutableCompositeGenericSet<Data, ChildType> makeSet()
	{
		return new MutableCompositeGenericSet<Data, ChildType>();
	}

	protected static <Data, ChildType extends IGenericSet<Data>>
	MutableCompositeGenericSet<Data, ChildType> makeSet(final int initialCapacity)
	{
		return new MutableCompositeGenericSet<Data, ChildType>(initialCapacity);
	}

	public static <Data, ChildType extends IGenericSet<Data>>
	MutableCompositeGenericSet<Data, ChildType> makeSet(final ChildType... children)
	{
		return makeSet(Arrays.asList(children));
	}

	public static <Data, ChildType extends IGenericSet<Data>>
	MutableCompositeGenericSet<Data, ChildType> makeSet(final Collection<ChildType> children)
	{
		return new MutableCompositeGenericSet<Data, ChildType>(children);
	}

	protected static <Data> MutableCompositeGenericSet<Data, DegenerateGenericSet<Data>> makeSetFromElement(final Data value)
	{
		return makeSet(DegenerateGenericSet.makeSet(value));
	}

	protected static <Data, ChildType extends IGenericSet<Data>>
	MutableCompositeGenericSet<Data, ChildType> makeSet(final ChildType child)
	{
		final MutableCompositeGenericSet<Data, ChildType> r = new MutableCompositeGenericSet<Data, ChildType>(1);
		r._bridge.getChildren().add(child);
		return r;
	}

	public static <Data> MutableCompositeGenericSet<Data, DegenerateGenericSet<Data>> makeSetFromElements(final Collection<? extends Data> elements)
	{
		final ArrayList<DegenerateGenericSet<Data>> children = new ArrayList<DegenerateGenericSet<Data>>(elements.size());
		for(final Data child:elements)
		{
			children.add(DegenerateGenericSet.makeSet(child));
		}
		return makeSet(children);
	}

	protected MutableCompositeGenericSet()
	{
		super();
	}

	protected MutableCompositeGenericSet(final int initialCapacity)
	{
		super(initialCapacity);
	}

	protected MutableCompositeGenericSet(final Collection<ChildType> children)
	{
		super(children);
	}

	public void clear()
	{
		// TODO Auto-generated method stub

	}

	public boolean add(final @Nullable Data value)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addAll(final Collection<? extends Data> c)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsAll(final Collection<? extends Data> c)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean remove(final @Nullable Data value)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeAll(final Collection<? extends Data> c)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean retainAll(final Collection<? extends Data> c)
	{
		// TODO Auto-generated method stub
		return false;
	}
}