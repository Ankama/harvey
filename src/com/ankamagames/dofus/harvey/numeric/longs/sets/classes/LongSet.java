package com.ankamagames.dofus.harvey.numeric.longs.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.BaseCompositeLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleCompositeLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public final class LongSet<ChildType extends IElementaryLongSet>
extends BaseCompositeLongSet<ChildType>
implements ISimpleCompositeLongSet<ChildType>
{

	public static <ChildType extends IElementaryLongSet> LongSet<ChildType> makeSet()
	{
		return new LongSet<ChildType>();
	}

	public static <ChildType extends IElementaryLongSet> LongSet<ChildType> makeSet(final ChildType element)
	{
		return new LongSet<ChildType>(element);
	}

	public static LongSet<DegenerateLongSet> makeSetFromElements(final List<Long> asList)
	{
		final ArrayList<DegenerateLongSet> children = new ArrayList<DegenerateLongSet>(asList.size());
		for(final long data:asList)
		{
			children.add(DegenerateLongSet.makeSet(data));
		}
		return makeSet(children);
	}

	public static <ChildType extends IElementaryLongSet> LongSet<ChildType> makeSet(final Collection<ChildType> collection)
		{
			return new LongSet<ChildType>(collection);
		}

	public static <ChildType extends IElementaryLongSet> LongSet<ChildType> makeSet(final ChildType... elements)
	{
		return makeSet(Arrays.asList(elements));
	}

	private LongSet()
	{
		super();
	}

	private LongSet(final ChildType element)
	{
		super(element);
	}

	private LongSet(final Collection<ChildType> collection)
	{
		super(collection);
	}

	@Override
	public LongSet<ChildType> getAsSimpleSet() {
		return this;
	}
}