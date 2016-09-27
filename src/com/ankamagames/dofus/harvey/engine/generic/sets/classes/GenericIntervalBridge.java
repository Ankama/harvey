/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.Comparator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.SplitOnRangeBridge;
import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.SurroundingValuesProvider;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.GenericBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.GenericEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.GenericIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.generic.sets.classes.GenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementarySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IEmptySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISortedGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class GenericIntervalBridge<Data, Bridged extends GenericInterval<Data>>
extends AbstractIntervalBridge<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>, IEmptySortedGenericSet<Data>, Bridged>
{
	protected GenericBoundComparisonToolbox<Data, IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, Bridged> _boundToolbox;
	protected GenericIntervalCreationToolbox<Data, Bridged> _creationToolbox;
	protected GenericEqualityToolbox<Data, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, Bridged> _equalityToolbox;
	protected SplitOnRangeBridge<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>, IEmptySortedGenericSet<Data>, Bridged> _splitBridge;
	protected Comparator<? super Data> _comparator;

	public GenericIntervalBridge(final Bridged bridged, final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider, @Nullable final Splitter<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>> splitter)
	{
		super(bridged);
		_comparator = comparator;
		_boundToolbox = new GenericBoundComparisonToolbox<Data, IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, Bridged>(_bridged, comparator);
		_creationToolbox = new GenericIntervalCreationToolbox<Data, Bridged>(_bridged, comparator, surroundingProvider, splitter);
		_equalityToolbox = new GenericEqualityToolbox<Data, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, Bridged>(_bridged);
		_splitBridge = new SplitOnRangeBridge<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>, IEmptySortedGenericSet<Data>, Bridged>(_bridged, _creationToolbox);
	}

	@Override
	protected SplitOnRangeBridge<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>, IEmptySortedGenericSet<Data>, Bridged> getSplitBridge() {
		return _splitBridge;
	}

	@Override
	protected GenericBoundComparisonToolbox<Data, IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, Bridged> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}

	@Override
	protected GenericIntervalCreationToolbox<Data, Bridged> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected GenericEqualityToolbox<Data, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, Bridged> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	public List<? extends ISortedGenericSet<Data>> split(final Data[] values, final boolean[] isIntervalStart)
	{
		return getSetCreationToolbox().split(values, isIntervalStart);
	}

	@Override
	public boolean isEmpty() {
		final IGenericBound<Data> lowerBound = _bridged.getLowerBound();
		final IGenericBound<Data> upperBound = _bridged.getUpperBound();
		if(lowerBound == null || upperBound == null)
			return true;
		return _comparator.compare(lowerBound.getValue(), upperBound.getValue())>0;
	}

	@Override
	public ISortedGenericSet<Data> unite(final ISortedGenericSet<Data> set)
	{
		return super.unite(set).getAsSet();
	}

	@Override
	public ISortedGenericSet<Data> intersect(final ISortedGenericSet<Data> set)
	{
		return super.intersect(set).getAsSet();
	}
}