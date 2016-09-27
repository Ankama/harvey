/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.doubles.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.BaseCompositeDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IElementaryDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.ISimpleCompositeDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.ISimpleDoubleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class DoubleSet<ChildType extends IElementaryDoubleSet>
extends BaseCompositeDoubleSet<ChildType>
implements ISimpleCompositeDoubleSet<ChildType>
{

	public static <ChildType extends IElementaryDoubleSet> DoubleSet<ChildType> makeSet()
	{
		return new DoubleSet<ChildType>();
	}

	public static <ChildType extends IElementaryDoubleSet> DoubleSet<ChildType> makeSet(final ChildType element)
	{
		return new DoubleSet<ChildType>(element);
	}

	public static <ChildType extends IElementaryDoubleSet> DoubleSet<ChildType> makeSet(final Iterable<ChildType> collection)
	{
		return new DoubleSet<ChildType>(collection);
	}

	public static <ChildType extends IElementaryDoubleSet> DoubleSet<ChildType> makeSet(final ChildType... children)
	{
		return new DoubleSet<ChildType>(Arrays.asList(children));
	}

	public static <ChildType extends IElementaryDoubleSet> DoubleSet<ChildType> makeSetFromElements(final List<Double> asList)
	{
		final ArrayList<ChildType> ret = new ArrayList<ChildType>(asList.size());
		for(final double elmt:asList)
		{
			ret.add((ChildType)DegenerateDoubleSet.makeSet(elmt));
		}
		return new DoubleSet<ChildType>(ret);
	}

	private DoubleSet()
	{
		super();
	}

	private DoubleSet(final ChildType element)
	{
		super(element);
	}

	private DoubleSet(final Iterable<ChildType> collection)
	{
		super(collection);
	}

	@Override
	public ISimpleDoubleSet getAsSimpleSet()
	{
		return this;
	}
}