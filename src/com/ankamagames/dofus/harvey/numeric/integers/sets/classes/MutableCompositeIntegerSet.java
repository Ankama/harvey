/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.BaseCompositeIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class MutableCompositeIntegerSet<ChildType extends IIntegerSet>
extends BaseCompositeIntegerSet<ChildType>
{

	public static <ChildType extends IIntegerSet> MutableCompositeIntegerSet<ChildType> makeSet()
	{
		return new MutableCompositeIntegerSet<ChildType>();
	}

	public static <ChildType extends IIntegerSet> MutableCompositeIntegerSet<ChildType> makeSet(final ChildType element)
	{
		return new MutableCompositeIntegerSet<ChildType>(element);
	}

	public static <ChildType extends IIntegerSet> MutableCompositeIntegerSet<ChildType> makeSet(final Collection<ChildType> collection)
	{
		return new MutableCompositeIntegerSet<ChildType>(collection);
	}

	public static <ChildType extends IIntegerSet> MutableCompositeIntegerSet<ChildType> makeSet(final ChildType... collection)
	{
		return new MutableCompositeIntegerSet<ChildType>(Arrays.asList(collection));
	}

	public static  MutableCompositeIntegerSet<? extends IElementaryIntegerSet> makeSetFromElements(final List<Integer> asList)
	{
		final ArrayList<IElementaryIntegerSet> children = new ArrayList<IElementaryIntegerSet>(asList.size());
		for(final int data:asList)
		{
			children.add(DegenerateIntegerSet.makeSet(data));
		}
		return makeSet(children);
	}

	protected MutableCompositeIntegerSet()
	{
		super();
	}

	protected MutableCompositeIntegerSet(final ChildType element)
	{
		super(element);
	}

	protected MutableCompositeIntegerSet(final Collection<ChildType> collection)
	{
		super(collection);
	}
}
