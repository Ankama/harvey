/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.BaseCompositeContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class MutableCompositeContinuousShortSet<ChildType extends IContinuousShortSet>
extends BaseCompositeContinuousShortSet<ChildType>
{
	public static <ChildType extends IContinuousShortSet> MutableCompositeContinuousShortSet<ChildType> makeSet()
	{
		return new MutableCompositeContinuousShortSet<ChildType>();
	}

	public static <ChildType extends IContinuousShortSet> MutableCompositeContinuousShortSet<ChildType> makeSet(final ChildType element)
	{
		return new MutableCompositeContinuousShortSet<ChildType>(element);
	}

	public static <ChildType extends IContinuousShortSet> MutableCompositeContinuousShortSet<ChildType> makeSet(final Iterable<ChildType> collection)
	{
		return new MutableCompositeContinuousShortSet<ChildType>(collection);
	}

	public static <ChildType extends IContinuousShortSet> MutableCompositeContinuousShortSet<ChildType> makeSet(final ChildType... collection)
	{
		return new MutableCompositeContinuousShortSet<ChildType>(Arrays.asList(collection));
	}

	public static <ChildType extends IContinuousShortSet> MutableCompositeContinuousShortSet<ChildType> makeSetFromElements(final List<Short> datas)
	{
		final List<ChildType> ret = new ArrayList<ChildType>(datas.size());
		for(final short elmt:datas)
		{
			ret.add((ChildType) DegenerateContinuousShortSet.makeSet(elmt));
		}
		return new MutableCompositeContinuousShortSet<ChildType>(ret);
	}

	protected MutableCompositeContinuousShortSet()
	{
		super();
	}

	protected MutableCompositeContinuousShortSet(final ChildType element)
	{
		super(element);
	}

	protected MutableCompositeContinuousShortSet(final Iterable<ChildType> collection)
	{
		super(collection);
	}
}
