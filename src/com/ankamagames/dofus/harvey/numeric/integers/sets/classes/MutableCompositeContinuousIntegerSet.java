/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.BaseCompositeContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class MutableCompositeContinuousIntegerSet<ChildType extends IContinuousIntegerSet>
extends BaseCompositeContinuousIntegerSet<ChildType>
{
	public static <ChildType extends IContinuousIntegerSet> MutableCompositeContinuousIntegerSet<ChildType> makeSet()
	{
		return new MutableCompositeContinuousIntegerSet<ChildType>();
	}

	public static <ChildType extends IContinuousIntegerSet> MutableCompositeContinuousIntegerSet<ChildType> makeSet(final ChildType element)
	{
		return new MutableCompositeContinuousIntegerSet<ChildType>(element);
	}

	public static <ChildType extends IContinuousIntegerSet> MutableCompositeContinuousIntegerSet<ChildType> makeSet(final Iterable<ChildType> collection)
	{
		return new MutableCompositeContinuousIntegerSet<ChildType>(collection);
	}

	public static <ChildType extends IContinuousIntegerSet> MutableCompositeContinuousIntegerSet<ChildType> makeSet(final ChildType... collection)
	{
		return new MutableCompositeContinuousIntegerSet<ChildType>(Arrays.asList(collection));
	}

	public static <ChildType extends IContinuousIntegerSet> MutableCompositeContinuousIntegerSet<ChildType> makeSetFromElements(final List<Integer> datas)
	{
		final List<ChildType> ret = new ArrayList<ChildType>(datas.size());
		for(final int elmt:datas)
		{
			ret.add((ChildType) DegenerateContinuousIntegerSet.makeSet(elmt));
		}
		return new MutableCompositeContinuousIntegerSet<ChildType>(ret);
	}

	protected MutableCompositeContinuousIntegerSet()
	{
		super();
	}

	protected MutableCompositeContinuousIntegerSet(final ChildType element)
	{
		super(element);
	}

	protected MutableCompositeContinuousIntegerSet(final Iterable<ChildType> collection)
	{
		super(collection);
	}
}
