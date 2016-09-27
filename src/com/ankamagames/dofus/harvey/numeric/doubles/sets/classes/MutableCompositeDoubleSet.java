/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.doubles.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.BaseCompositeDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class MutableCompositeDoubleSet<ChildType extends IDoubleSet>
extends BaseCompositeDoubleSet<ChildType>
{
	public static <Data, ChildType extends IDoubleSet> MutableCompositeDoubleSet<ChildType> makeSet()
	{
		return new MutableCompositeDoubleSet<ChildType>();
	}

	public static <Data, ChildType extends IDoubleSet> MutableCompositeDoubleSet<ChildType> makeSet(final ChildType element)
	{
		return new MutableCompositeDoubleSet<ChildType>(element);
	}

	public static <Data, ChildType extends IDoubleSet> MutableCompositeDoubleSet<ChildType> makeSet(final Iterable<ChildType> collection)
	{
		return new MutableCompositeDoubleSet<ChildType>(collection);
	}

	public static <Data, ChildType extends IDoubleSet> MutableCompositeDoubleSet<ChildType> makeSet(final ChildType... collection)
	{
		return new MutableCompositeDoubleSet<ChildType>(Arrays.asList(collection));
	}

	public static <Data, ChildType extends IDoubleSet> MutableCompositeDoubleSet<ChildType> makeSetFromElements(final List<Double> datas)
	{
		final List<ChildType> ret = new ArrayList<ChildType>(datas.size());
		for(final double elmt:datas)
		{
			ret.add((ChildType) DegenerateDoubleSet.makeSet(elmt));
		}
		return new MutableCompositeDoubleSet<ChildType>(ret);
	}

	protected MutableCompositeDoubleSet()
	{
		super();
	}

	protected MutableCompositeDoubleSet(final ChildType element)
	{
		super(element);
	}

	protected MutableCompositeDoubleSet(final Iterable<ChildType> collection)
	{
		super(collection);
	}
}
