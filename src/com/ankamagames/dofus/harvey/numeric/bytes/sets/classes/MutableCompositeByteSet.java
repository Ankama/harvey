/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.BaseCompositeByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class MutableCompositeByteSet<ChildType extends IByteSet>
extends BaseCompositeByteSet<ChildType>
{

	public static <ChildType extends IByteSet> MutableCompositeByteSet<ChildType> makeSet()
	{
		return new MutableCompositeByteSet<ChildType>();
	}

	public static <ChildType extends IByteSet> MutableCompositeByteSet<ChildType> makeSet(final ChildType element)
	{
		return new MutableCompositeByteSet<ChildType>(element);
	}

	public static <ChildType extends IByteSet> MutableCompositeByteSet<ChildType> makeSet(final Collection<ChildType> collection)
	{
		return new MutableCompositeByteSet<ChildType>(collection);
	}

	public static <ChildType extends IByteSet> MutableCompositeByteSet<ChildType> makeSet(final ChildType... collection)
	{
		return new MutableCompositeByteSet<ChildType>(Arrays.asList(collection));
	}

	public static  MutableCompositeByteSet<? extends IElementaryByteSet> makeSetFromElements(final List<Byte> asList)
	{
		final ArrayList<IElementaryByteSet> children = new ArrayList<IElementaryByteSet>(asList.size());
		for(final byte data:asList)
		{
			children.add(DegenerateByteSet.makeSet(data));
		}
		return makeSet(children);
	}

	protected MutableCompositeByteSet()
	{
		super();
	}

	protected MutableCompositeByteSet(final ChildType element)
	{
		super(element);
	}

	protected MutableCompositeByteSet(final Collection<ChildType> collection)
	{
		super(collection);
	}
}
