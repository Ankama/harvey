/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractSetBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ISortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.ShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.ShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.DegenerateShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.EmptyShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.MutableCompositeShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IDegenerateShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IEmptyShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ShortSetCreationToolbox<BridgedType extends IShortSet>
extends AbstractSetBridge<IShortSet, ISimpleShortSet, IElementaryShortSet, BridgedType>
implements ISortedSetCreationToolbox<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval, IEmptyShortSet, BridgedType>
{

	public ShortSetCreationToolbox(final BridgedType bridged)
	{
		super(bridged);
	}

	@Override
	public EmptyShortSet makeEmptySet()
	{
		return EmptyShortSet.getInstance();
	}

	@Override
	public ISimpleSet<IShortSet, ISimpleShortSet, IElementaryShortSet> makeSimplestSet(
		final Collection<IElementaryShortSet> elements)
	{
		if(elements.isEmpty())
			return makeEmptySet();
		if(elements.size() == 1)
		{
			final IElementaryShortSet element = elements.iterator().next().getAsElementarySet();
			if(element.isEmpty())
				return makeEmptySet();
			if(element.isDegenerate())
				return DegenerateShortSet.makeSet(element.getDataIterator().next());
			else
				return element;
		}
		return ShortSet.makeSet(elements);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IShortSet makeComposite(final Collection<? extends IShortSet> children)
	{
		if(children.size()<=0)
			return makeEmptySet();
		for(final IShortSet child:children)
			if(!child.isElementary())
				return MutableCompositeShortSet.makeSet(children);
		return ShortSet.makeSet((List<? extends IElementaryShortSet>)children);
	}

	@Override
	public IShortSet makeComposite(final IShortSet... sets)
	{
		return makeComposite(Arrays.asList(sets));
	}

	@Override
	public IShortInterval makeInterval(
		final IShortBound lowerBound, final IShortBound upperBound)
	{
		return ShortInterval.makeInterval(lowerBound.getValue(), upperBound.getValue());
	}

	@Override
	public IDegenerateShortSet makeDegenerate(
			final IShortBound bound)
	{
		return DegenerateShortSet.makeSet(bound.getValue());
	}
}