/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.toolbox;

import java.util.Arrays;
import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractSetBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ISortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.interfaces.IDoubleBound;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.classes.DegenerateDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.classes.EmptyDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.classes.DoubleInterval;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.classes.DoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.classes.MutableCompositeDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IElementaryDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleInterval;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.ISimpleDoubleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class DoubleSetCreationToolbox<Bridged extends IDoubleSet>
extends AbstractSetBridge<IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, Bridged>
implements ISortedSetCreationToolbox<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, EmptyDoubleSet, Bridged>
{

	public DoubleSetCreationToolbox(final Bridged bridged)
	{
		super(bridged);
	}

	@Override
	public EmptyDoubleSet makeEmptySet() {
		return EmptyDoubleSet.getInstance();
	}

	@Override
	public IDoubleSet makeComposite(final IDoubleSet... sets)
	{
		return makeComposite(Arrays.asList(sets));
	}

	@Override
	public IDoubleSet makeComposite(final Collection<? extends IDoubleSet> children)
	{
		if(children.size() <= 0)
			return makeEmptySet();
		for(final IDoubleSet child:children)
			if(!(child instanceof IElementaryDoubleSet))
				return MutableCompositeDoubleSet.makeSet(children);
		return DoubleSet.makeSet((Collection<? extends IElementaryDoubleSet>)children);
	}

	@Override
	public ISimpleSet<IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet> makeSimplestSet(
			final Collection<IElementaryDoubleSet> elements)
	{
		if(elements.isEmpty())
			return makeEmptySet();
		if(elements.size() == 1)
		{
			final IElementaryDoubleSet element = elements.iterator().next();
			if(element.isEmpty())
				return makeEmptySet();
			if(element.isDegenerate())
			{
				final IDoubleBound lowerBound = element.getLowerBound();
				if(lowerBound != null)
					return DegenerateDoubleSet.makeSet(lowerBound.getValue());
				// this should never happen
				throw new NullPointerException();
			}
			else
				return element;
		}
		return DoubleSet.makeSet(elements);
	}

	@Override
	public IDegenerateSortedSet<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, ?> makeDegenerate(
			final IDoubleBound bound)
	{
		return DegenerateDoubleSet.makeSet(bound.getValue());
	}

	@Override
	public IDoubleInterval makeInterval(final IDoubleBound lowerBound, final IDoubleBound upperBound) {
		if (lowerBound.isInfinity())
			if (upperBound.isInfinity())
				return DoubleInterval.makeUniverse();
			else {
				return DoubleInterval.makeRightBoundedInterval(upperBound.getValue(), upperBound.isClosed());
			}
		if (upperBound.isInfinity()) {
			return DoubleInterval.makeLeftBoundedInterval(lowerBound.getValue(), lowerBound.isClosed());
		}
		return DoubleInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(),
				upperBound.getValue(), upperBound.isClosed());
	}

}
