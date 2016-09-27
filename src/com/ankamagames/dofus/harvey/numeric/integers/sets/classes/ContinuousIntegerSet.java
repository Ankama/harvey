/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.BaseCompositeContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleCompositeContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleContinuousIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public final class ContinuousIntegerSet<ChildType extends IElementaryContinuousIntegerSet>
extends BaseCompositeContinuousIntegerSet<ChildType>
implements ISimpleCompositeContinuousIntegerSet<ChildType>
{

	public static <ChildType extends IElementaryContinuousIntegerSet> ContinuousIntegerSet<ChildType> makeSet()
	{
		return new ContinuousIntegerSet<ChildType>();
	}

	public static <ChildType extends IElementaryContinuousIntegerSet> ContinuousIntegerSet<ChildType> makeSet(final ChildType element)
	{
		return new ContinuousIntegerSet<ChildType>(element);
	}

	public static <ChildType extends IElementaryContinuousIntegerSet> ContinuousIntegerSet<ChildType> makeSet(final Iterable<ChildType> collection)
	{
		return new ContinuousIntegerSet<ChildType>(collection);
	}

	public static <ChildType extends IElementaryContinuousIntegerSet> ContinuousIntegerSet<ChildType> makeSet(final ChildType... children)
	{
		return new ContinuousIntegerSet<ChildType>(Arrays.asList(children));
	}

	public static <ChildType extends IElementaryContinuousIntegerSet> ContinuousIntegerSet<ChildType> makeSetFromElements(final List<Integer> asList)
	{
		final ArrayList<ChildType> ret = new ArrayList<ChildType>(asList.size());
		for(final int elmt:asList)
		{
			ret.add((ChildType)DegenerateContinuousIntegerSet.makeSet(elmt));
		}
		return new ContinuousIntegerSet<ChildType>(ret);
	}

	private ContinuousIntegerSet()
	{
		super();
	}

	private ContinuousIntegerSet(final ChildType element)
	{
		super(element);
	}

	private ContinuousIntegerSet(final Iterable<ChildType> collection)
	{
		super(collection);
	}

	@Override
	public ISimpleContinuousIntegerSet getAsSimpleSet()
	{
		return this;
	}
}
