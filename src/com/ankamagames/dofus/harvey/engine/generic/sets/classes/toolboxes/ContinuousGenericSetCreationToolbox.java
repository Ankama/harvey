/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractSetBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ISortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSortedSet;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparator;
import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.generic.sets.classes.ContinuousGenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.classes.ContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.classes.DegenerateContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.classes.EmptyContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.classes.MutableCompositeContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IEmptyContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleContinuousGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousGenericSetCreationToolbox<Data, BridgedType extends IContinuousGenericSet<Data>>
extends AbstractSetBridge<IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, BridgedType>
implements ISortedSetCreationToolbox<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>, IEmptyContinuousGenericSet<Data>, BridgedType>
{
	protected ContinuousComparator<? super Data> _comparator;

	protected Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> _splitter;

	public ContinuousGenericSetCreationToolbox(final BridgedType bridged, final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		super(bridged);
		_comparator = comparator;
		_splitter = splitter;
	}

	@Override
	public EmptyContinuousGenericSet<Data> makeEmptySet()
	{
		return EmptyContinuousGenericSet.getInstance();
	}

	@Override
	public ISimpleContinuousGenericSet<Data> makeSimplestSet(
		final Collection<IElementaryContinuousGenericSet<Data>> elements)
	{
		if(elements.isEmpty())
			return makeEmptySet();
		if(elements.size() == 1)
		{
			final IElementaryContinuousGenericSet<Data> element = elements.iterator().next().getAsElementarySet();
			if(element.isEmpty())
				return makeEmptySet();
			if(element.isDegenerate())
			{
				final IContinuousGenericBound<Data> lowerBound = element.getLowerBound();
				if(lowerBound != null)
					return DegenerateContinuousGenericSet.makeSet(lowerBound.getValue(), _comparator, _splitter);
				// Should never happen
				throw new NullPointerException();
			}
			else
				return element;
		}
		return ContinuousGenericSet.makeSet(elements, _comparator, _splitter);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IContinuousGenericSet<Data> makeComposite(final Collection<? extends IContinuousGenericSet<Data>> children)
	{
		if(children.size()<=0)
			return makeEmptySet();
		for(final IContinuousGenericSet<Data> child:children)
			if(!child.isElementary())
				return MutableCompositeContinuousGenericSet.makeSet(children, _comparator, _splitter);
		return ContinuousGenericSet.makeSet((List<? extends IElementaryContinuousGenericSet<Data>>)children, _comparator, _splitter);
	}

	@Override
	public IContinuousGenericSet<Data> makeComposite(final IContinuousGenericSet<Data>... sets)
	{
		return makeComposite(Arrays.asList(sets));
	}

	@Override
	public IContinuousGenericInterval<Data> makeInterval(final IContinuousGenericBound<Data> lowerBound,
			final IContinuousGenericBound<Data> upperBound) {
		if (lowerBound.isInfinity())
			if (upperBound.isInfinity())
				return ContinuousGenericInterval.makeUniverse(_comparator, _splitter);
			else {
				return ContinuousGenericInterval.makeRightBoundedInterval(upperBound.getValue(), upperBound.isClosed(),
						_comparator, _splitter);
			}
		if (upperBound.isInfinity()) {
			return ContinuousGenericInterval.makeLeftBoundedInterval(lowerBound.getValue(), lowerBound.isClosed(),
					_comparator, _splitter);
		}
		return ContinuousGenericInterval.makeInterval(lowerBound.getValue(), lowerBound.isClosed(),
				upperBound.getValue(), upperBound.isClosed(), _comparator, _splitter);
	}

	@Override
	public IDegenerateSortedSet<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>, ?> makeDegenerate(
			final IContinuousGenericBound<Data> bound)
	{
		return DegenerateContinuousGenericSet.makeSet(bound.getValue(), _comparator, _splitter);
	}
}