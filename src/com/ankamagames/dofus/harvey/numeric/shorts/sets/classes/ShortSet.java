package com.ankamagames.dofus.harvey.numeric.shorts.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.BaseCompositeShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleCompositeShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public final class ShortSet<ChildType extends IElementaryShortSet>
extends BaseCompositeShortSet<ChildType>
implements ISimpleCompositeShortSet<ChildType>
{

	public static <ChildType extends IElementaryShortSet> ShortSet<ChildType> makeSet()
	{
		return new ShortSet<ChildType>();
	}

	public static <ChildType extends IElementaryShortSet> ShortSet<ChildType> makeSet(final ChildType element)
	{
		return new ShortSet<ChildType>(element);
	}

	public static ShortSet<DegenerateShortSet> makeSetFromElements(final List<Short> asList)
	{
		final ArrayList<DegenerateShortSet> children = new ArrayList<DegenerateShortSet>(asList.size());
		for(final short data:asList)
		{
			children.add(DegenerateShortSet.makeSet(data));
		}
		return makeSet(children);
	}

	public static <ChildType extends IElementaryShortSet> ShortSet<ChildType> makeSet(final Collection<ChildType> collection)
		{
			return new ShortSet<ChildType>(collection);
		}

	public static <ChildType extends IElementaryShortSet> ShortSet<ChildType> makeSet(final ChildType... elements)
	{
		return makeSet(Arrays.asList(elements));
	}

	private ShortSet()
	{
		super();
	}

	private ShortSet(final ChildType element)
	{
		super(element);
	}

	private ShortSet(final Collection<ChildType> collection)
	{
		super(collection);
	}

	@Override
	public ShortSet<ChildType> getAsSimpleSet() {
		return this;
	}
}