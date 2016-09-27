/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractSetBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ISortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.LongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.LongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.DegenerateLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.EmptyLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.MutableCompositeLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongBound;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IDegenerateLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IEmptyLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class LongSetCreationToolbox<BridgedType extends ILongSet>
extends AbstractSetBridge<ILongSet, ISimpleLongSet, IElementaryLongSet, BridgedType>
implements ISortedSetCreationToolbox<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval, IEmptyLongSet, BridgedType>
{

	public LongSetCreationToolbox(final BridgedType bridged)
	{
		super(bridged);
	}

	@Override
	public EmptyLongSet makeEmptySet()
	{
		return EmptyLongSet.getInstance();
	}

	@Override
	public ISimpleSet<ILongSet, ISimpleLongSet, IElementaryLongSet> makeSimplestSet(
		final Collection<IElementaryLongSet> elements)
	{
		if(elements.isEmpty())
			return makeEmptySet();
		if(elements.size() == 1)
		{
			final IElementaryLongSet element = elements.iterator().next().getAsElementarySet();
			if(element.isEmpty())
				return makeEmptySet();
			if(element.isDegenerate())
				return DegenerateLongSet.makeSet(element.getDataIterator().next());
			else
				return element;
		}
		return LongSet.makeSet(elements);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ILongSet makeComposite(final Collection<? extends ILongSet> children)
	{
		if(children.size()<=0)
			return makeEmptySet();
		for(final ILongSet child:children)
			if(!child.isElementary())
				return MutableCompositeLongSet.makeSet(children);
		return LongSet.makeSet((List<? extends IElementaryLongSet>)children);
	}

	@Override
	public ILongSet makeComposite(final ILongSet... sets)
	{
		return makeComposite(Arrays.asList(sets));
	}

	@Override
	public ILongInterval makeInterval(
		final ILongBound lowerBound, final ILongBound upperBound)
	{
		return LongInterval.makeInterval(lowerBound.getValue(), upperBound.getValue());
	}

	@Override
	public IDegenerateLongSet makeDegenerate(
			final ILongBound bound)
	{
		return DegenerateLongSet.makeSet(bound.getValue());
	}
}