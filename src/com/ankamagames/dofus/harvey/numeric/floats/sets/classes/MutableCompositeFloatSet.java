/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.floats.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.BaseCompositeFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class MutableCompositeFloatSet<ChildType extends IFloatSet>
extends BaseCompositeFloatSet<ChildType>
{
	public static <Data, ChildType extends IFloatSet> MutableCompositeFloatSet<ChildType> makeSet()
	{
		return new MutableCompositeFloatSet<ChildType>();
	}

	public static <Data, ChildType extends IFloatSet> MutableCompositeFloatSet<ChildType> makeSet(final ChildType element)
	{
		return new MutableCompositeFloatSet<ChildType>(element);
	}

	public static <Data, ChildType extends IFloatSet> MutableCompositeFloatSet<ChildType> makeSet(final Iterable<ChildType> collection)
	{
		return new MutableCompositeFloatSet<ChildType>(collection);
	}

	public static <Data, ChildType extends IFloatSet> MutableCompositeFloatSet<ChildType> makeSet(final ChildType... collection)
	{
		return new MutableCompositeFloatSet<ChildType>(Arrays.asList(collection));
	}

	public static <Data, ChildType extends IFloatSet> MutableCompositeFloatSet<ChildType> makeSetFromElements(final List<Float> datas)
	{
		final List<ChildType> ret = new ArrayList<ChildType>(datas.size());
		for(final float elmt:datas)
		{
			ret.add((ChildType) DegenerateFloatSet.makeSet(elmt));
		}
		return new MutableCompositeFloatSet<ChildType>(ret);
	}

	protected MutableCompositeFloatSet()
	{
		super();
	}

	protected MutableCompositeFloatSet(final ChildType element)
	{
		super(element);
	}

	protected MutableCompositeFloatSet(final Iterable<ChildType> collection)
	{
		super(collection);
	}
}
