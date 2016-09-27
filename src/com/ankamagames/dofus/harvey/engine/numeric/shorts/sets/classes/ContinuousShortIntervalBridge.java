/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.SplitOnRangeBridge;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes.ContinuousShortBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes.ContinuousShortEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes.ContinuousShortIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.ContinuousShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IEmptyContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleContinuousShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousShortIntervalBridge<Bridged extends ContinuousShortInterval>
extends AbstractIntervalBridge<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval, IEmptyContinuousShortSet, Bridged>
{
	protected ContinuousShortBoundComparisonToolbox<IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, Bridged>												_boundToolbox;
	protected ContinuousShortIntervalCreationToolbox<Bridged>																																					_creationToolbox;
	protected ContinuousShortEqualityToolbox<Bridged>																																							_equalityToolbox;
	protected SplitOnRangeBridge<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval, IEmptyContinuousShortSet, Bridged>	_splitBridge;

	public ContinuousShortIntervalBridge(final Bridged bridged)
	{
		super(bridged);
		_boundToolbox = new ContinuousShortBoundComparisonToolbox<IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, Bridged>(_bridged);
		_creationToolbox = new ContinuousShortIntervalCreationToolbox<Bridged>(_bridged);
		_equalityToolbox = new ContinuousShortEqualityToolbox<Bridged>(_bridged);
		_splitBridge = new SplitOnRangeBridge<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval, IEmptyContinuousShortSet, Bridged>(_bridged, _creationToolbox);
	}

	@Override
	protected ContinuousShortBoundComparisonToolbox<IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, Bridged> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}

	@Override
	protected ContinuousShortIntervalCreationToolbox<Bridged> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected ContinuousShortEqualityToolbox<Bridged> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected SplitOnRangeBridge<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval, IEmptyContinuousShortSet, Bridged> getSplitBridge()
	{
		return _splitBridge;
	}

	public List<? extends IContinuousShortSet> split(final short[] values, final boolean[] isIntervalStart)
	{
		return getSetCreationToolbox().split(values, isIntervalStart);
	}

	@Override
	public boolean isEmpty() {
		final IContinuousShortBound lowerBound = _bridged.getLowerBound();
		final IContinuousShortBound upperBound = _bridged.getUpperBound();
		if((!_bridged.isLowerBoundInfinity() && lowerBound == null) || (!_bridged.isUpperBoundInfinity() && upperBound == null))
			return true;
		if(lowerBound == null || upperBound == null)
			return false;

		final double compare = (lowerBound.getValue() - upperBound.getValue());
		return (compare>0) || ( compare == 0 && (!lowerBound.isClosed() || !upperBound.isClosed()));
	}

	@Override
	public IContinuousShortSet unite(final IContinuousShortSet set)
	{
		return super.unite(set).getAsSet();
	}

	@Override
	public IContinuousShortSet intersect(final IContinuousShortSet set)
	{
		return super.intersect(set).getAsSet();
	}
}