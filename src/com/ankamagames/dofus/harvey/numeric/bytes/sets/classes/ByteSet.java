package com.ankamagames.dofus.harvey.numeric.bytes.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.BaseCompositeByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleCompositeByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public final class ByteSet<ChildType extends IElementaryByteSet>
extends BaseCompositeByteSet<ChildType>
implements ISimpleCompositeByteSet<ChildType>
{

	public static <ChildType extends IElementaryByteSet> ByteSet<ChildType> makeSet()
	{
		return new ByteSet<ChildType>();
	}

	public static <ChildType extends IElementaryByteSet> ByteSet<ChildType> makeSet(final ChildType element)
	{
		return new ByteSet<ChildType>(element);
	}

	public static ByteSet<DegenerateByteSet> makeSetFromElements(final List<Byte> asList)
	{
		final ArrayList<DegenerateByteSet> children = new ArrayList<DegenerateByteSet>(asList.size());
		for(final byte data:asList)
		{
			children.add(DegenerateByteSet.makeSet(data));
		}
		return makeSet(children);
	}

	public static <ChildType extends IElementaryByteSet> ByteSet<ChildType> makeSet(final Collection<ChildType> collection)
		{
			return new ByteSet<ChildType>(collection);
		}

	public static <ChildType extends IElementaryByteSet> ByteSet<ChildType> makeSet(final ChildType... elements)
	{
		return makeSet(Arrays.asList(elements));
	}

	private ByteSet()
	{
		super();
	}

	private ByteSet(final ChildType element)
	{
		super(element);
	}

	private ByteSet(final Collection<ChildType> collection)
	{
		super(collection);
	}

	@Override
	public ByteSet<ChildType> getAsSimpleSet() {
		return this;
	}
}