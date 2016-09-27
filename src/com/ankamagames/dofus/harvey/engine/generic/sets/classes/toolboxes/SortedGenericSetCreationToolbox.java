/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractSetBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ISortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.SurroundingValuesProvider;
import com.ankamagames.dofus.harvey.generic.sets.classes.DegenerateSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.classes.EmptySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.classes.GenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.classes.MutableCompositeSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.classes.SortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IDegenerateSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementarySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IEmptySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISortedGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class SortedGenericSetCreationToolbox<Data, BridgedType extends ISortedGenericSet<Data>>
extends AbstractSetBridge<ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, BridgedType>
implements ISortedSetCreationToolbox<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>, IEmptySortedGenericSet<Data>, BridgedType>
{
	protected Comparator<? super Data> _comparator;
	protected SurroundingValuesProvider<Data> _surroundingProvider;

	public SortedGenericSetCreationToolbox(final BridgedType bridged, final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		super(bridged);
		_comparator = comparator;
		_surroundingProvider = surroundingProvider;
	}

	@Override
	public EmptySortedGenericSet<Data> makeEmptySet()
	{
		return EmptySortedGenericSet.getInstance();
	}

	@Override
	public ISimpleSet<ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>> makeSimplestSet(
		final Collection<IElementarySortedGenericSet<Data>> elements)
	{
		if(elements.isEmpty())
			return makeEmptySet();
		if(elements.size() == 1)
		{
			final IElementarySortedGenericSet<Data> element = elements.iterator().next().getAsElementarySet();
			if(element.isEmpty())
				return makeEmptySet();
			if(element.isDegenerate())
				return DegenerateSortedGenericSet.makeSet(element.getDataIterator().next(), _comparator, _surroundingProvider);
			else
				return element;
		}
		return SortedGenericSet.makeSet(elements, _comparator, _surroundingProvider);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ISortedGenericSet<Data> makeComposite(final Collection<? extends ISortedGenericSet<Data>> children)
	{
		if(children.size()<=0)
			return makeEmptySet();
		for(final ISortedGenericSet<Data> child:children)
			if(!child.isElementary())
				return MutableCompositeSortedGenericSet.makeSet(children, _comparator, _surroundingProvider);
		return SortedGenericSet.makeSet((List<? extends IElementarySortedGenericSet<Data>>)children, _comparator, _surroundingProvider);
	}

	@Override
	public ISortedGenericSet<Data> makeComposite(final ISortedGenericSet<Data>... sets)
	{
		return makeComposite(Arrays.asList(sets));
	}

	@Override
	public IGenericInterval<Data> makeInterval(
		final IGenericBound<Data> lowerBound, final IGenericBound<Data> upperBound)
	{
		return GenericInterval.makeInterval(lowerBound.getValue(), upperBound.getValue(), _comparator, _surroundingProvider);
	}

	@Override
	public IDegenerateSortedGenericSet<Data> makeDegenerate(
			final IGenericBound<Data> bound)
	{
		return DegenerateSortedGenericSet.makeSet(bound.getValue(), _comparator, _surroundingProvider);
	}
}