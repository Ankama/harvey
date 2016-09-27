/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractSetBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ISortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSortedSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.ContinuousLongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.ContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.DegenerateContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.EmptyContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.MutableCompositeContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongBound;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IEmptyContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleContinuousLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousLongSetCreationToolbox<BridgedType extends IContinuousLongSet>
extends AbstractSetBridge<IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, BridgedType>
implements ISortedSetCreationToolbox<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval, IEmptyContinuousLongSet, BridgedType>
{


	public ContinuousLongSetCreationToolbox(final BridgedType bridged)
	{
		super(bridged);
	}

	@Override
	public EmptyContinuousLongSet makeEmptySet()
	{
		return EmptyContinuousLongSet.getInstance();
	}

	@Override
	public ISimpleContinuousLongSet makeSimplestSet(
		final Collection<IElementaryContinuousLongSet> elements)
	{
		if(elements.isEmpty())
			return makeEmptySet();
		if(elements.size() == 1)
		{
			final IElementaryContinuousLongSet element = elements.iterator().next().getAsElementarySet();
			if(element.isEmpty())
				return makeEmptySet();
			if(element.isDegenerate())
			{
				final IContinuousLongBound lowerBound = element.getLowerBound();
				if(lowerBound != null)
					return DegenerateContinuousLongSet.makeSet(lowerBound.getValue());
				// Should never happen
				throw new NullPointerException();
			}
			else
				return element;
		}
		return ContinuousLongSet.makeSet(elements);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IContinuousLongSet makeComposite(final Collection<? extends IContinuousLongSet> children)
	{
		if(children.size()<=0)
			return makeEmptySet();
		for(final IContinuousLongSet child:children)
			if(!child.isElementary())
				return MutableCompositeContinuousLongSet.makeSet(children);
		return ContinuousLongSet.makeSet((List<? extends IElementaryContinuousLongSet>)children);
	}

	@Override
	public IContinuousLongSet makeComposite(final IContinuousLongSet... sets)
	{
		return makeComposite(Arrays.asList(sets));
	}

	@Override
	public IContinuousLongInterval makeInterval(final IContinuousLongBound lowerBound,
			final IContinuousLongBound upperBound)
	{
		return ContinuousLongInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(),
				upperBound.getValue(), upperBound.isClosed());
	}

	@Override
	public IDegenerateSortedSet<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval, ?> makeDegenerate(
			final IContinuousLongBound bound)
	{
		return DegenerateContinuousLongSet.makeSet(bound.getValue());
	}
}