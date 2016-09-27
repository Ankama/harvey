/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.BaseCompositeContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class MutableCompositeContinuousLongSet<ChildType extends IContinuousLongSet>
extends BaseCompositeContinuousLongSet<ChildType>
{
	public static <ChildType extends IContinuousLongSet> MutableCompositeContinuousLongSet<ChildType> makeSet()
	{
		return new MutableCompositeContinuousLongSet<ChildType>();
	}

	public static <ChildType extends IContinuousLongSet> MutableCompositeContinuousLongSet<ChildType> makeSet(final ChildType element)
	{
		return new MutableCompositeContinuousLongSet<ChildType>(element);
	}

	public static <ChildType extends IContinuousLongSet> MutableCompositeContinuousLongSet<ChildType> makeSet(final Iterable<ChildType> collection)
	{
		return new MutableCompositeContinuousLongSet<ChildType>(collection);
	}

	public static <ChildType extends IContinuousLongSet> MutableCompositeContinuousLongSet<ChildType> makeSet(final ChildType... collection)
	{
		return new MutableCompositeContinuousLongSet<ChildType>(Arrays.asList(collection));
	}

	public static <ChildType extends IContinuousLongSet> MutableCompositeContinuousLongSet<ChildType> makeSetFromElements(final List<Long> datas)
	{
		final List<ChildType> ret = new ArrayList<ChildType>(datas.size());
		for(final long elmt:datas)
		{
			ret.add((ChildType) DegenerateContinuousLongSet.makeSet(elmt));
		}
		return new MutableCompositeContinuousLongSet<ChildType>(ret);
	}

	protected MutableCompositeContinuousLongSet()
	{
		super();
	}

	protected MutableCompositeContinuousLongSet(final ChildType element)
	{
		super(element);
	}

	protected MutableCompositeContinuousLongSet(final Iterable<ChildType> collection)
	{
		super(collection);
	}
}
