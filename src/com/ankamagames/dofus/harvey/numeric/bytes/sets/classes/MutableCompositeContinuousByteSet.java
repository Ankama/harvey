/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.BaseCompositeContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class MutableCompositeContinuousByteSet<ChildType extends IContinuousByteSet>
extends BaseCompositeContinuousByteSet<ChildType>
{
	public static <ChildType extends IContinuousByteSet> MutableCompositeContinuousByteSet<ChildType> makeSet()
	{
		return new MutableCompositeContinuousByteSet<ChildType>();
	}

	public static <ChildType extends IContinuousByteSet> MutableCompositeContinuousByteSet<ChildType> makeSet(final ChildType element)
	{
		return new MutableCompositeContinuousByteSet<ChildType>(element);
	}

	public static <ChildType extends IContinuousByteSet> MutableCompositeContinuousByteSet<ChildType> makeSet(final Iterable<ChildType> collection)
	{
		return new MutableCompositeContinuousByteSet<ChildType>(collection);
	}

	public static <ChildType extends IContinuousByteSet> MutableCompositeContinuousByteSet<ChildType> makeSet(final ChildType... collection)
	{
		return new MutableCompositeContinuousByteSet<ChildType>(Arrays.asList(collection));
	}

	public static <ChildType extends IContinuousByteSet> MutableCompositeContinuousByteSet<ChildType> makeSetFromElements(final List<Byte> datas)
	{
		final List<ChildType> ret = new ArrayList<ChildType>(datas.size());
		for(final byte elmt:datas)
		{
			ret.add((ChildType) DegenerateContinuousByteSet.makeSet(elmt));
		}
		return new MutableCompositeContinuousByteSet<ChildType>(ret);
	}

	protected MutableCompositeContinuousByteSet()
	{
		super();
	}

	protected MutableCompositeContinuousByteSet(final ChildType element)
	{
		super(element);
	}

	protected MutableCompositeContinuousByteSet(final Iterable<ChildType> collection)
	{
		super(collection);
	}
}
