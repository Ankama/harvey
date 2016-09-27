/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.floats.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.BaseCompositeFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IElementaryFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.ISimpleCompositeFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.ISimpleFloatSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FloatSet<ChildType extends IElementaryFloatSet>
extends BaseCompositeFloatSet<ChildType>
implements ISimpleCompositeFloatSet<ChildType>
{

	public static <ChildType extends IElementaryFloatSet> FloatSet<ChildType> makeSet()
	{
		return new FloatSet<ChildType>();
	}

	public static <ChildType extends IElementaryFloatSet> FloatSet<ChildType> makeSet(final ChildType element)
	{
		return new FloatSet<ChildType>(element);
	}

	public static <ChildType extends IElementaryFloatSet> FloatSet<ChildType> makeSet(final Iterable<ChildType> collection)
	{
		return new FloatSet<ChildType>(collection);
	}

	public static <ChildType extends IElementaryFloatSet> FloatSet<ChildType> makeSet(final ChildType... children)
	{
		return new FloatSet<ChildType>(Arrays.asList(children));
	}

	public static <ChildType extends IElementaryFloatSet> FloatSet<ChildType> makeSetFromElements(final List<Float> asList)
	{
		final ArrayList<ChildType> ret = new ArrayList<ChildType>(asList.size());
		for(final float elmt:asList)
		{
			ret.add((ChildType)DegenerateFloatSet.makeSet(elmt));
		}
		return new FloatSet<ChildType>(ret);
	}

	private FloatSet()
	{
		super();
	}

	private FloatSet(final ChildType element)
	{
		super(element);
	}

	private FloatSet(final Iterable<ChildType> collection)
	{
		super(collection);
	}

	@Override
	public ISimpleFloatSet getAsSimpleSet()
	{
		return this;
	}
}