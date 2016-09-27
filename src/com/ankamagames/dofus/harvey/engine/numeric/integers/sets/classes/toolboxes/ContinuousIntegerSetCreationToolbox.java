/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractSetBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ISortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSortedSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.ContinuousIntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.ContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.DegenerateContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.EmptyContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.MutableCompositeContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerBound;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IEmptyContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleContinuousIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousIntegerSetCreationToolbox<BridgedType extends IContinuousIntegerSet>
extends AbstractSetBridge<IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, BridgedType>
implements ISortedSetCreationToolbox<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, IContinuousIntegerInterval, IEmptyContinuousIntegerSet, BridgedType>
{


	public ContinuousIntegerSetCreationToolbox(final BridgedType bridged)
	{
		super(bridged);
	}

	@Override
	public EmptyContinuousIntegerSet makeEmptySet()
	{
		return EmptyContinuousIntegerSet.getInstance();
	}

	@Override
	public ISimpleContinuousIntegerSet makeSimplestSet(
		final Collection<IElementaryContinuousIntegerSet> elements)
	{
		if(elements.isEmpty())
			return makeEmptySet();
		if(elements.size() == 1)
		{
			final IElementaryContinuousIntegerSet element = elements.iterator().next().getAsElementarySet();
			if(element.isEmpty())
				return makeEmptySet();
			if(element.isDegenerate())
			{
				final IContinuousIntegerBound lowerBound = element.getLowerBound();
				if(lowerBound != null)
					return DegenerateContinuousIntegerSet.makeSet(lowerBound.getValue());
				// Should never happen
				throw new NullPointerException();
			}
			else
				return element;
		}
		return ContinuousIntegerSet.makeSet(elements);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IContinuousIntegerSet makeComposite(final Collection<? extends IContinuousIntegerSet> children)
	{
		if(children.size()<=0)
			return makeEmptySet();
		for(final IContinuousIntegerSet child:children)
			if(!child.isElementary())
				return MutableCompositeContinuousIntegerSet.makeSet(children);
		return ContinuousIntegerSet.makeSet((List<? extends IElementaryContinuousIntegerSet>)children);
	}

	@Override
	public IContinuousIntegerSet makeComposite(final IContinuousIntegerSet... sets)
	{
		return makeComposite(Arrays.asList(sets));
	}

	@Override
	public IContinuousIntegerInterval makeInterval(final IContinuousIntegerBound lowerBound,
			final IContinuousIntegerBound upperBound)
	{
		return ContinuousIntegerInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(),
				upperBound.getValue(), upperBound.isClosed());
	}

	@Override
	public IDegenerateSortedSet<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, IContinuousIntegerInterval, ?> makeDegenerate(
			final IContinuousIntegerBound bound)
	{
		return DegenerateContinuousIntegerSet.makeSet(bound.getValue());
	}
}