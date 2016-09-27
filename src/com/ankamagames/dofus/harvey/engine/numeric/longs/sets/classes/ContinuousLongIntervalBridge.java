/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.SplitOnRangeBridge;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes.ContinuousLongBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes.ContinuousLongEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes.ContinuousLongIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.ContinuousLongInterval;
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
public class ContinuousLongIntervalBridge<Bridged extends ContinuousLongInterval>
extends AbstractIntervalBridge<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval, IEmptyContinuousLongSet, Bridged>
{
	protected ContinuousLongBoundComparisonToolbox<IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, Bridged>												_boundToolbox;
	protected ContinuousLongIntervalCreationToolbox<Bridged>																																					_creationToolbox;
	protected ContinuousLongEqualityToolbox<Bridged>																																							_equalityToolbox;
	protected SplitOnRangeBridge<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval, IEmptyContinuousLongSet, Bridged>	_splitBridge;

	public ContinuousLongIntervalBridge(final Bridged bridged)
	{
		super(bridged);
		_boundToolbox = new ContinuousLongBoundComparisonToolbox<IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, Bridged>(_bridged);
		_creationToolbox = new ContinuousLongIntervalCreationToolbox<Bridged>(_bridged);
		_equalityToolbox = new ContinuousLongEqualityToolbox<Bridged>(_bridged);
		_splitBridge = new SplitOnRangeBridge<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval, IEmptyContinuousLongSet, Bridged>(_bridged, _creationToolbox);
	}

	@Override
	protected ContinuousLongBoundComparisonToolbox<IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, Bridged> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}

	@Override
	protected ContinuousLongIntervalCreationToolbox<Bridged> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected ContinuousLongEqualityToolbox<Bridged> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected SplitOnRangeBridge<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval, IEmptyContinuousLongSet, Bridged> getSplitBridge()
	{
		return _splitBridge;
	}

	public List<? extends IContinuousLongSet> split(final long[] values, final boolean[] isIntervalStart)
	{
		return getSetCreationToolbox().split(values, isIntervalStart);
	}

	@Override
	public boolean isEmpty() {
		final IContinuousLongBound lowerBound = _bridged.getLowerBound();
		final IContinuousLongBound upperBound = _bridged.getUpperBound();
		if((!_bridged.isLowerBoundInfinity() && lowerBound == null) || (!_bridged.isUpperBoundInfinity() && upperBound == null))
			return true;
		if(lowerBound == null || upperBound == null)
			return false;

		final double compare = (lowerBound.getValue() - upperBound.getValue());
		return (compare>0) || ( compare == 0 && (!lowerBound.isClosed() || !upperBound.isClosed()));
	}

	@Override
	public IContinuousLongSet unite(final IContinuousLongSet set)
	{
		return super.unite(set).getAsSet();
	}

	@Override
	public IContinuousLongSet intersect(final IContinuousLongSet set)
	{
		return super.intersect(set).getAsSet();
	}
}