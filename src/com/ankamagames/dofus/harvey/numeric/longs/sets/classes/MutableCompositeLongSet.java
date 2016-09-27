/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.BaseCompositeLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class MutableCompositeLongSet<ChildType extends ILongSet>
extends BaseCompositeLongSet<ChildType>
{

	public static <ChildType extends ILongSet> MutableCompositeLongSet<ChildType> makeSet()
	{
		return new MutableCompositeLongSet<ChildType>();
	}

	public static <ChildType extends ILongSet> MutableCompositeLongSet<ChildType> makeSet(final ChildType element)
	{
		return new MutableCompositeLongSet<ChildType>(element);
	}

	public static <ChildType extends ILongSet> MutableCompositeLongSet<ChildType> makeSet(final Collection<ChildType> collection)
	{
		return new MutableCompositeLongSet<ChildType>(collection);
	}

	public static <ChildType extends ILongSet> MutableCompositeLongSet<ChildType> makeSet(final ChildType... collection)
	{
		return new MutableCompositeLongSet<ChildType>(Arrays.asList(collection));
	}

	public static  MutableCompositeLongSet<? extends IElementaryLongSet> makeSetFromElements(final List<Long> asList)
	{
		final ArrayList<IElementaryLongSet> children = new ArrayList<IElementaryLongSet>(asList.size());
		for(final long data:asList)
		{
			children.add(DegenerateLongSet.makeSet(data));
		}
		return makeSet(children);
	}

	protected MutableCompositeLongSet()
	{
		super();
	}

	protected MutableCompositeLongSet(final ChildType element)
	{
		super(element);
	}

	protected MutableCompositeLongSet(final Collection<ChildType> collection)
	{
		super(collection);
	}
}
