/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.BaseCompositeContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleCompositeContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleContinuousShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public final class ContinuousShortSet<ChildType extends IElementaryContinuousShortSet>
extends BaseCompositeContinuousShortSet<ChildType>
implements ISimpleCompositeContinuousShortSet<ChildType>
{

	public static <ChildType extends IElementaryContinuousShortSet> ContinuousShortSet<ChildType> makeSet()
	{
		return new ContinuousShortSet<ChildType>();
	}

	public static <ChildType extends IElementaryContinuousShortSet> ContinuousShortSet<ChildType> makeSet(final ChildType element)
	{
		return new ContinuousShortSet<ChildType>(element);
	}

	public static <ChildType extends IElementaryContinuousShortSet> ContinuousShortSet<ChildType> makeSet(final Iterable<ChildType> collection)
	{
		return new ContinuousShortSet<ChildType>(collection);
	}

	public static <ChildType extends IElementaryContinuousShortSet> ContinuousShortSet<ChildType> makeSet(final ChildType... children)
	{
		return new ContinuousShortSet<ChildType>(Arrays.asList(children));
	}

	public static <ChildType extends IElementaryContinuousShortSet> ContinuousShortSet<ChildType> makeSetFromElements(final List<Short> asList)
	{
		final ArrayList<ChildType> ret = new ArrayList<ChildType>(asList.size());
		for(final short elmt:asList)
		{
			ret.add((ChildType)DegenerateContinuousShortSet.makeSet(elmt));
		}
		return new ContinuousShortSet<ChildType>(ret);
	}

	private ContinuousShortSet()
	{
		super();
	}

	private ContinuousShortSet(final ChildType element)
	{
		super(element);
	}

	private ContinuousShortSet(final Iterable<ChildType> collection)
	{
		super(collection);
	}

	@Override
	public ISimpleContinuousShortSet getAsSimpleSet()
	{
		return this;
	}
}
