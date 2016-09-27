/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.BaseCompositeShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class MutableCompositeShortSet<ChildType extends IShortSet>
extends BaseCompositeShortSet<ChildType>
{

	public static <ChildType extends IShortSet> MutableCompositeShortSet<ChildType> makeSet()
	{
		return new MutableCompositeShortSet<ChildType>();
	}

	public static <ChildType extends IShortSet> MutableCompositeShortSet<ChildType> makeSet(final ChildType element)
	{
		return new MutableCompositeShortSet<ChildType>(element);
	}

	public static <ChildType extends IShortSet> MutableCompositeShortSet<ChildType> makeSet(final Collection<ChildType> collection)
	{
		return new MutableCompositeShortSet<ChildType>(collection);
	}

	public static <ChildType extends IShortSet> MutableCompositeShortSet<ChildType> makeSet(final ChildType... collection)
	{
		return new MutableCompositeShortSet<ChildType>(Arrays.asList(collection));
	}

	public static  MutableCompositeShortSet<? extends IElementaryShortSet> makeSetFromElements(final List<Short> asList)
	{
		final ArrayList<IElementaryShortSet> children = new ArrayList<IElementaryShortSet>(asList.size());
		for(final short data:asList)
		{
			children.add(DegenerateShortSet.makeSet(data));
		}
		return makeSet(children);
	}

	protected MutableCompositeShortSet()
	{
		super();
	}

	protected MutableCompositeShortSet(final ChildType element)
	{
		super(element);
	}

	protected MutableCompositeShortSet(final Collection<ChildType> collection)
	{
		super(collection);
	}
}
