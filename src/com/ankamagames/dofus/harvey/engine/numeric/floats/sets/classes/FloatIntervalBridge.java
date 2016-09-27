/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.SplitOnRangeBridge;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IFloatBound;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.toolbox.FloatBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.toolbox.FloatEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.toolbox.FloatIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.floats.sets.classes.EmptyFloatSet;
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
public class FloatIntervalBridge<BridgedSet extends IFloatInterval>
extends AbstractIntervalBridge<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, EmptyFloatSet, BridgedSet>
{
	protected FloatBoundComparisonToolbox<BridgedSet>																						_boundToolbox;
	protected FloatIntervalCreationToolbox<BridgedSet>																						_creationToolbox;
	protected FloatEqualityToolbox<BridgedSet>																								_equalityToolbox;
	protected SplitOnRangeBridge<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, EmptyFloatSet, BridgedSet>	_splitBridge;


	public FloatIntervalBridge(final BridgedSet bridged)
	{
		super(bridged);
		_boundToolbox = new FloatBoundComparisonToolbox<BridgedSet>(bridged);
		_creationToolbox = new FloatIntervalCreationToolbox<BridgedSet>(bridged);
		_equalityToolbox = new FloatEqualityToolbox<BridgedSet>(bridged);
		_splitBridge = new SplitOnRangeBridge<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, EmptyFloatSet, BridgedSet>(bridged, _creationToolbox);
	}

	@Override
	protected FloatBoundComparisonToolbox<BridgedSet> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}

	@Override
	protected FloatIntervalCreationToolbox<BridgedSet> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected FloatEqualityToolbox<BridgedSet> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected SplitOnRangeBridge<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, EmptyFloatSet, BridgedSet> getSplitBridge()
	{
		return _splitBridge;
	}

	public List<? extends IFloatSet> split(final float[] values, final boolean[] isIntervalStart)
	{
		return getSetCreationToolbox().split(values, isIntervalStart);
	}

	@Override
	public boolean isEmpty() {
		final IFloatBound lowerBound = _bridged.getLowerBound();
		final IFloatBound upperBound = _bridged.getUpperBound();
		if((!_bridged.isLowerBoundInfinity() && lowerBound == null) || (!_bridged.isUpperBoundInfinity() && upperBound == null))
			return true;
		if(lowerBound == null || upperBound == null)
			return false;

		final double compare = lowerBound.getValue() - upperBound.getValue();
		return (compare>0) || ( compare == 0 && (!lowerBound.isClosed() || !upperBound.isClosed()));
	}

	@Override
	public IFloatSet unite(final IFloatSet set)
	{
		return super.unite(set).getAsSet();
	}

	@Override
	public IFloatSet intersect(final IFloatSet set)
	{
		return super.intersect(set).getAsSet();
	}

}
