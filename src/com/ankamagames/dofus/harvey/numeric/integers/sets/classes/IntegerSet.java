package com.ankamagames.dofus.harvey.numeric.integers.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.BaseCompositeIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleCompositeIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public final class IntegerSet<ChildType extends IElementaryIntegerSet>
extends BaseCompositeIntegerSet<ChildType>
implements ISimpleCompositeIntegerSet<ChildType>
{

	public static <ChildType extends IElementaryIntegerSet> IntegerSet<ChildType> makeSet()
	{
		return new IntegerSet<ChildType>();
	}

	public static <ChildType extends IElementaryIntegerSet> IntegerSet<ChildType> makeSet(final ChildType element)
	{
		return new IntegerSet<ChildType>(element);
	}

	public static IntegerSet<DegenerateIntegerSet> makeSetFromElements(final List<Integer> asList)
	{
		final ArrayList<DegenerateIntegerSet> children = new ArrayList<DegenerateIntegerSet>(asList.size());
		for(final int data:asList)
		{
			children.add(DegenerateIntegerSet.makeSet(data));
		}
		return makeSet(children);
	}

	public static <ChildType extends IElementaryIntegerSet> IntegerSet<ChildType> makeSet(final Collection<ChildType> collection)
		{
			return new IntegerSet<ChildType>(collection);
		}

	public static <ChildType extends IElementaryIntegerSet> IntegerSet<ChildType> makeSet(final ChildType... elements)
	{
		return makeSet(Arrays.asList(elements));
	}

	private IntegerSet()
	{
		super();
	}

	private IntegerSet(final ChildType element)
	{
		super(element);
	}

	private IntegerSet(final Collection<ChildType> collection)
	{
		super(collection);
	}

	@Override
	public IntegerSet<ChildType> getAsSimpleSet() {
		return this;
	}
}