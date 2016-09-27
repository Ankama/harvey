/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractSetBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ISortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSortedSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.ContinuousByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.ContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.DegenerateContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.EmptyContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.MutableCompositeContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IEmptyContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleContinuousByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousByteSetCreationToolbox<BridgedType extends IContinuousByteSet>
extends AbstractSetBridge<IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, BridgedType>
implements ISortedSetCreationToolbox<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IContinuousByteInterval, IEmptyContinuousByteSet, BridgedType>
{


	public ContinuousByteSetCreationToolbox(final BridgedType bridged)
	{
		super(bridged);
	}

	@Override
	public EmptyContinuousByteSet makeEmptySet()
	{
		return EmptyContinuousByteSet.getInstance();
	}

	@Override
	public ISimpleContinuousByteSet makeSimplestSet(
		final Collection<IElementaryContinuousByteSet> elements)
	{
		if(elements.isEmpty())
			return makeEmptySet();
		if(elements.size() == 1)
		{
			final IElementaryContinuousByteSet element = elements.iterator().next().getAsElementarySet();
			if(element.isEmpty())
				return makeEmptySet();
			if(element.isDegenerate())
			{
				final IContinuousByteBound lowerBound = element.getLowerBound();
				if(lowerBound != null)
					return DegenerateContinuousByteSet.makeSet(lowerBound.getValue());
				// Should never happen
				throw new NullPointerException();
			}
			else
				return element;
		}
		return ContinuousByteSet.makeSet(elements);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IContinuousByteSet makeComposite(final Collection<? extends IContinuousByteSet> children)
	{
		if(children.size()<=0)
			return makeEmptySet();
		for(final IContinuousByteSet child:children)
			if(!child.isElementary())
				return MutableCompositeContinuousByteSet.makeSet(children);
		return ContinuousByteSet.makeSet((List<? extends IElementaryContinuousByteSet>)children);
	}

	@Override
	public IContinuousByteSet makeComposite(final IContinuousByteSet... sets)
	{
		return makeComposite(Arrays.asList(sets));
	}

	@Override
	public IContinuousByteInterval makeInterval(final IContinuousByteBound lowerBound,
			final IContinuousByteBound upperBound)
	{
		return ContinuousByteInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(),
				upperBound.getValue(), upperBound.isClosed());
	}

	@Override
	public IDegenerateSortedSet<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IContinuousByteInterval, ?> makeDegenerate(
			final IContinuousByteBound bound)
	{
		return DegenerateContinuousByteSet.makeSet(bound.getValue());
	}
}