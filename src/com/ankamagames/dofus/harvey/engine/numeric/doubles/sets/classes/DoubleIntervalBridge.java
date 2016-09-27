/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.SplitOnRangeBridge;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.interfaces.IDoubleBound;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.toolbox.DoubleBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.toolbox.DoubleEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.toolbox.DoubleIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.classes.EmptyDoubleSet;
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
public class DoubleIntervalBridge<BridgedSet extends IDoubleInterval>
extends AbstractIntervalBridge<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, EmptyDoubleSet, BridgedSet>
{
	protected DoubleBoundComparisonToolbox<BridgedSet>																						_boundToolbox;
	protected DoubleIntervalCreationToolbox<BridgedSet>																						_creationToolbox;
	protected DoubleEqualityToolbox<BridgedSet>																								_equalityToolbox;
	protected SplitOnRangeBridge<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, EmptyDoubleSet, BridgedSet>	_splitBridge;


	public DoubleIntervalBridge(final BridgedSet bridged)
	{
		super(bridged);
		_boundToolbox = new DoubleBoundComparisonToolbox<BridgedSet>(bridged);
		_creationToolbox = new DoubleIntervalCreationToolbox<BridgedSet>(bridged);
		_equalityToolbox = new DoubleEqualityToolbox<BridgedSet>(bridged);
		_splitBridge = new SplitOnRangeBridge<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, EmptyDoubleSet, BridgedSet>(bridged, _creationToolbox);
	}

	@Override
	protected DoubleBoundComparisonToolbox<BridgedSet> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}

	@Override
	protected DoubleIntervalCreationToolbox<BridgedSet> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected DoubleEqualityToolbox<BridgedSet> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected SplitOnRangeBridge<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, EmptyDoubleSet, BridgedSet> getSplitBridge()
	{
		return _splitBridge;
	}

	public List<? extends IDoubleSet> split(final double[] values, final boolean[] isIntervalStart)
	{
		return getSetCreationToolbox().split(values, isIntervalStart);
	}

	@Override
	public boolean isEmpty() {
		final IDoubleBound lowerBound = _bridged.getLowerBound();
		final IDoubleBound upperBound = _bridged.getUpperBound();
		if((!_bridged.isLowerBoundInfinity() && lowerBound == null) || (!_bridged.isUpperBoundInfinity() && upperBound == null))
			return true;
		if(lowerBound == null || upperBound == null)
			return false;

		final double compare = lowerBound.getValue() - upperBound.getValue();
		return (compare>0) || ( compare == 0 && (!lowerBound.isClosed() || !upperBound.isClosed()));
	}

	@Override
	public IDoubleSet unite(final IDoubleSet set)
	{
		return super.unite(set).getAsSet();
	}

	@Override
	public IDoubleSet intersect(final IDoubleSet set)
	{
		return super.intersect(set).getAsSet();
	}

}
