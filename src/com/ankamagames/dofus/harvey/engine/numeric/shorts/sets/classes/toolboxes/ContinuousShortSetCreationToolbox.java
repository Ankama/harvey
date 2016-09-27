/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractSetBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ISortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSortedSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.ContinuousShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.ContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.DegenerateContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.EmptyContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.MutableCompositeContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IEmptyContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleContinuousShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousShortSetCreationToolbox<BridgedType extends IContinuousShortSet>
extends AbstractSetBridge<IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, BridgedType>
implements ISortedSetCreationToolbox<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval, IEmptyContinuousShortSet, BridgedType>
{


	public ContinuousShortSetCreationToolbox(final BridgedType bridged)
	{
		super(bridged);
	}

	@Override
	public EmptyContinuousShortSet makeEmptySet()
	{
		return EmptyContinuousShortSet.getInstance();
	}

	@Override
	public ISimpleContinuousShortSet makeSimplestSet(
		final Collection<IElementaryContinuousShortSet> elements)
	{
		if(elements.isEmpty())
			return makeEmptySet();
		if(elements.size() == 1)
		{
			final IElementaryContinuousShortSet element = elements.iterator().next().getAsElementarySet();
			if(element.isEmpty())
				return makeEmptySet();
			if(element.isDegenerate())
			{
				final IContinuousShortBound lowerBound = element.getLowerBound();
				if(lowerBound != null)
					return DegenerateContinuousShortSet.makeSet(lowerBound.getValue());
				// Should never happen
				throw new NullPointerException();
			}
			else
				return element;
		}
		return ContinuousShortSet.makeSet(elements);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IContinuousShortSet makeComposite(final Collection<? extends IContinuousShortSet> children)
	{
		if(children.size()<=0)
			return makeEmptySet();
		for(final IContinuousShortSet child:children)
			if(!child.isElementary())
				return MutableCompositeContinuousShortSet.makeSet(children);
		return ContinuousShortSet.makeSet((List<? extends IElementaryContinuousShortSet>)children);
	}

	@Override
	public IContinuousShortSet makeComposite(final IContinuousShortSet... sets)
	{
		return makeComposite(Arrays.asList(sets));
	}

	@Override
	public IContinuousShortInterval makeInterval(final IContinuousShortBound lowerBound,
			final IContinuousShortBound upperBound)
	{
		return ContinuousShortInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(),
				upperBound.getValue(), upperBound.isClosed());
	}

	@Override
	public IDegenerateSortedSet<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval, ?> makeDegenerate(
			final IContinuousShortBound bound)
	{
		return DegenerateContinuousShortSet.makeSet(bound.getValue());
	}
}