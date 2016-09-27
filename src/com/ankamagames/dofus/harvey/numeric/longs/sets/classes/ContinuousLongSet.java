/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.BaseCompositeContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleCompositeContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleContinuousLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public final class ContinuousLongSet<ChildType extends IElementaryContinuousLongSet>
extends BaseCompositeContinuousLongSet<ChildType>
implements ISimpleCompositeContinuousLongSet<ChildType>
{

	public static <ChildType extends IElementaryContinuousLongSet> ContinuousLongSet<ChildType> makeSet()
	{
		return new ContinuousLongSet<ChildType>();
	}

	public static <ChildType extends IElementaryContinuousLongSet> ContinuousLongSet<ChildType> makeSet(final ChildType element)
	{
		return new ContinuousLongSet<ChildType>(element);
	}

	public static <ChildType extends IElementaryContinuousLongSet> ContinuousLongSet<ChildType> makeSet(final Iterable<ChildType> collection)
	{
		return new ContinuousLongSet<ChildType>(collection);
	}

	public static <ChildType extends IElementaryContinuousLongSet> ContinuousLongSet<ChildType> makeSet(final ChildType... children)
	{
		return new ContinuousLongSet<ChildType>(Arrays.asList(children));
	}

	public static <ChildType extends IElementaryContinuousLongSet> ContinuousLongSet<ChildType> makeSetFromElements(final List<Long> asList)
	{
		final ArrayList<ChildType> ret = new ArrayList<ChildType>(asList.size());
		for(final long elmt:asList)
		{
			ret.add((ChildType)DegenerateContinuousLongSet.makeSet(elmt));
		}
		return new ContinuousLongSet<ChildType>(ret);
	}

	private ContinuousLongSet()
	{
		super();
	}

	private ContinuousLongSet(final ChildType element)
	{
		super(element);
	}

	private ContinuousLongSet(final Iterable<ChildType> collection)
	{
		super(collection);
	}

	@Override
	public ISimpleContinuousLongSet getAsSimpleSet()
	{
		return this;
	}
}
