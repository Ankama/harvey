/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.BaseCompositeContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleCompositeContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleContinuousByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public final class ContinuousByteSet<ChildType extends IElementaryContinuousByteSet>
extends BaseCompositeContinuousByteSet<ChildType>
implements ISimpleCompositeContinuousByteSet<ChildType>
{

	public static <ChildType extends IElementaryContinuousByteSet> ContinuousByteSet<ChildType> makeSet()
	{
		return new ContinuousByteSet<ChildType>();
	}

	public static <ChildType extends IElementaryContinuousByteSet> ContinuousByteSet<ChildType> makeSet(final ChildType element)
	{
		return new ContinuousByteSet<ChildType>(element);
	}

	public static <ChildType extends IElementaryContinuousByteSet> ContinuousByteSet<ChildType> makeSet(final Iterable<ChildType> collection)
	{
		return new ContinuousByteSet<ChildType>(collection);
	}

	public static <ChildType extends IElementaryContinuousByteSet> ContinuousByteSet<ChildType> makeSet(final ChildType... children)
	{
		return new ContinuousByteSet<ChildType>(Arrays.asList(children));
	}

	public static <ChildType extends IElementaryContinuousByteSet> ContinuousByteSet<ChildType> makeSetFromElements(final List<Byte> asList)
	{
		final ArrayList<ChildType> ret = new ArrayList<ChildType>(asList.size());
		for(final byte elmt:asList)
		{
			ret.add((ChildType)DegenerateContinuousByteSet.makeSet(elmt));
		}
		return new ContinuousByteSet<ChildType>(ret);
	}

	private ContinuousByteSet()
	{
		super();
	}

	private ContinuousByteSet(final ChildType element)
	{
		super(element);
	}

	private ContinuousByteSet(final Iterable<ChildType> collection)
	{
		super(collection);
	}

	@Override
	public ISimpleContinuousByteSet getAsSimpleSet()
	{
		return this;
	}
}
