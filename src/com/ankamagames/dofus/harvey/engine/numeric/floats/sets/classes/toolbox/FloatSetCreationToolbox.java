/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.toolbox;

import java.util.Arrays;
import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractSetBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ISortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IFloatBound;
import com.ankamagames.dofus.harvey.numeric.floats.sets.classes.DegenerateFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.classes.EmptyFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.classes.FloatInterval;
import com.ankamagames.dofus.harvey.numeric.floats.sets.classes.FloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.classes.MutableCompositeFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IElementaryFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatInterval;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.ISimpleFloatSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class FloatSetCreationToolbox<Bridged extends IFloatSet>
extends AbstractSetBridge<IFloatSet, ISimpleFloatSet, IElementaryFloatSet, Bridged>
implements ISortedSetCreationToolbox<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, EmptyFloatSet, Bridged>
{

	public FloatSetCreationToolbox(final Bridged bridged)
	{
		super(bridged);
	}

	@Override
	public EmptyFloatSet makeEmptySet() {
		return EmptyFloatSet.getInstance();
	}

	@Override
	public IFloatSet makeComposite(final IFloatSet... sets)
	{
		return makeComposite(Arrays.asList(sets));
	}

	@Override
	public IFloatSet makeComposite(final Collection<? extends IFloatSet> children)
	{
		if(children.size() <= 0)
			return makeEmptySet();
		for(final IFloatSet child:children)
			if(!(child instanceof IElementaryFloatSet))
				return MutableCompositeFloatSet.makeSet(children);
		return FloatSet.makeSet((Collection<? extends IElementaryFloatSet>)children);
	}

	@Override
	public ISimpleSet<IFloatSet, ISimpleFloatSet, IElementaryFloatSet> makeSimplestSet(
			final Collection<IElementaryFloatSet> elements)
	{
		if(elements.isEmpty())
			return makeEmptySet();
		if(elements.size() == 1)
		{
			final IElementaryFloatSet element = elements.iterator().next();
			if(element.isEmpty())
				return makeEmptySet();
			if(element.isDegenerate())
			{
				final IFloatBound lowerBound = element.getLowerBound();
				if(lowerBound != null)
					return DegenerateFloatSet.makeSet(lowerBound.getValue());
				// this should never happen
				throw new NullPointerException();
			}
			else
				return element;
		}
		return FloatSet.makeSet(elements);
	}

	@Override
	public IDegenerateSortedSet<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, ?> makeDegenerate(
			final IFloatBound bound)
	{
		return DegenerateFloatSet.makeSet(bound.getValue());
	}

	@Override
	public IFloatInterval makeInterval(final IFloatBound lowerBound, final IFloatBound upperBound) {
		if (lowerBound.isInfinity())
			if (upperBound.isInfinity())
				return FloatInterval.makeUniverse();
			else {
				return FloatInterval.makeRightBoundedInterval(upperBound.getValue(), upperBound.isClosed());
			}
		if (upperBound.isInfinity()) {
			return FloatInterval.makeLeftBoundedInterval(lowerBound.getValue(), lowerBound.isClosed());
		}
		return FloatInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(),
				upperBound.getValue(), upperBound.isClosed());
	}

}
