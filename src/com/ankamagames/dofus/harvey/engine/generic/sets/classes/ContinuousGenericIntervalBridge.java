/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.SplitOnRangeBridge;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparator;
import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.ContinuousGenericBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.ContinuousGenericEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.ContinuousGenericIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.generic.sets.classes.ContinuousGenericInterval;
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
public class ContinuousGenericIntervalBridge<Data, Bridged extends ContinuousGenericInterval<Data>>
extends AbstractIntervalBridge<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>, IEmptyContinuousGenericSet<Data>, Bridged>
{
	protected ContinuousGenericBoundComparisonToolbox<Data, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, Bridged>												_boundToolbox;
	protected ContinuousGenericIntervalCreationToolbox<Data, Bridged>																																					_creationToolbox;
	protected ContinuousGenericEqualityToolbox<Data, Bridged>																																							_equalityToolbox;
	protected SplitOnRangeBridge<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>, IEmptyContinuousGenericSet<Data>, Bridged>	_splitBridge;
	protected ContinuousComparator<? super Data>																																										_comparator;

	public ContinuousGenericIntervalBridge(final Bridged bridged, final ContinuousComparator<? super Data> comparator,
		final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		super(bridged);
		_comparator = comparator;
		_boundToolbox = new ContinuousGenericBoundComparisonToolbox<Data, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, Bridged>(_bridged, comparator);
		_creationToolbox = new ContinuousGenericIntervalCreationToolbox<Data, Bridged>(_bridged, comparator, splitter);
		_equalityToolbox = new ContinuousGenericEqualityToolbox<Data, Bridged>(_bridged);
		_splitBridge = new SplitOnRangeBridge<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>, IEmptyContinuousGenericSet<Data>, Bridged>(_bridged, _creationToolbox);
	}

	@Override
	protected ContinuousGenericBoundComparisonToolbox<Data, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, Bridged> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}

	@Override
	protected ContinuousGenericIntervalCreationToolbox<Data, Bridged> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected ContinuousGenericEqualityToolbox<Data, Bridged> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected SplitOnRangeBridge<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>, IEmptyContinuousGenericSet<Data>, Bridged> getSplitBridge()
	{
		return _splitBridge;
	}

	public List<? extends IContinuousGenericSet<Data>> split(final Data[] values, final boolean[] isIntervalStart)
	{
		return getSetCreationToolbox().split(values, isIntervalStart);
	}

	@Override
	public boolean isEmpty() {
		final IContinuousGenericBound<Data> lowerBound = _bridged.getLowerBound();
		final IContinuousGenericBound<Data> upperBound = _bridged.getUpperBound();
		if((!_bridged.isLowerBoundInfinity() && lowerBound == null) || (!_bridged.isUpperBoundInfinity() && upperBound == null))
			return true;
		if(lowerBound == null || upperBound == null)
			return false;

		final double compare = _comparator.compareContinuous(lowerBound.getValue(), upperBound.getValue());
		return (compare>0) || ( compare == 0 && (!lowerBound.isClosed() || !upperBound.isClosed()));
	}

	@Override
	public IContinuousGenericSet<Data> unite(final IContinuousGenericSet<Data> set)
	{
		return super.unite(set).getAsSet();
	}

	@Override
	public IContinuousGenericSet<Data> intersect(final IContinuousGenericSet<Data> set)
	{
		return super.intersect(set).getAsSet();
	}
}