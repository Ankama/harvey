/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.SplitOnRangeBridge;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes.LongBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes.LongEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes.LongIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.LongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongBound;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IEmptyLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class LongIntervalBridge<Bridged extends LongInterval>
extends AbstractIntervalBridge<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval, IEmptyLongSet, Bridged>
{
	protected LongBoundComparisonToolbox<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, Bridged> _boundToolbox;
	protected LongIntervalCreationToolbox<Bridged> _creationToolbox;
	protected LongEqualityToolbox<ILongSet, ISimpleLongSet, IElementaryLongSet, Bridged> _equalityToolbox;
	protected SplitOnRangeBridge<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval, IEmptyLongSet, Bridged> _splitBridge;

	public LongIntervalBridge(final Bridged bridged)
	{
		super(bridged);
		_boundToolbox = new LongBoundComparisonToolbox<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, Bridged>(_bridged);
		_creationToolbox = new LongIntervalCreationToolbox<Bridged>(_bridged);
		_equalityToolbox = new LongEqualityToolbox<ILongSet, ISimpleLongSet, IElementaryLongSet, Bridged>(_bridged);
		_splitBridge = new SplitOnRangeBridge<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval, IEmptyLongSet, Bridged>(_bridged, _creationToolbox);
	}

	@Override
	protected SplitOnRangeBridge<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval, IEmptyLongSet, Bridged> getSplitBridge() {
		return _splitBridge;
	}

	@Override
	protected LongBoundComparisonToolbox<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, Bridged> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}

	@Override
	protected LongIntervalCreationToolbox<Bridged> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected LongEqualityToolbox<ILongSet, ISimpleLongSet, IElementaryLongSet, Bridged> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	public List<? extends ILongSet> split(final long[] values, final boolean[] isIntervalStart)
	{
		return getSetCreationToolbox().split(values, isIntervalStart);
	}

	@Override
	public boolean isEmpty() {
		final ILongBound lowerBound = _bridged.getLowerBound();
		final ILongBound upperBound = _bridged.getUpperBound();
		if(lowerBound == null || upperBound == null)
			return true;
		return (lowerBound.getValue() - upperBound.getValue())>0;
	}

	@Override
	public ILongSet unite(final ILongSet set)
	{
		return super.unite(set).getAsSet();
	}

	@Override
	public ILongSet intersect(final ILongSet set)
	{
		return super.intersect(set).getAsSet();
	}
}