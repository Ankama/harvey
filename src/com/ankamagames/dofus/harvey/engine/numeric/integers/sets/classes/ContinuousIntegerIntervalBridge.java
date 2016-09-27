/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.SplitOnRangeBridge;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes.ContinuousIntegerBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes.ContinuousIntegerEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes.ContinuousIntegerIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.ContinuousIntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerBound;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IEmptyContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleContinuousIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousIntegerIntervalBridge<Bridged extends ContinuousIntegerInterval>
extends AbstractIntervalBridge<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, IContinuousIntegerInterval, IEmptyContinuousIntegerSet, Bridged>
{
	protected ContinuousIntegerBoundComparisonToolbox<IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, Bridged>												_boundToolbox;
	protected ContinuousIntegerIntervalCreationToolbox<Bridged>																																					_creationToolbox;
	protected ContinuousIntegerEqualityToolbox<Bridged>																																							_equalityToolbox;
	protected SplitOnRangeBridge<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, IContinuousIntegerInterval, IEmptyContinuousIntegerSet, Bridged>	_splitBridge;

	public ContinuousIntegerIntervalBridge(final Bridged bridged)
	{
		super(bridged);
		_boundToolbox = new ContinuousIntegerBoundComparisonToolbox<IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, Bridged>(_bridged);
		_creationToolbox = new ContinuousIntegerIntervalCreationToolbox<Bridged>(_bridged);
		_equalityToolbox = new ContinuousIntegerEqualityToolbox<Bridged>(_bridged);
		_splitBridge = new SplitOnRangeBridge<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, IContinuousIntegerInterval, IEmptyContinuousIntegerSet, Bridged>(_bridged, _creationToolbox);
	}

	@Override
	protected ContinuousIntegerBoundComparisonToolbox<IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, Bridged> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}

	@Override
	protected ContinuousIntegerIntervalCreationToolbox<Bridged> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected ContinuousIntegerEqualityToolbox<Bridged> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected SplitOnRangeBridge<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, IContinuousIntegerInterval, IEmptyContinuousIntegerSet, Bridged> getSplitBridge()
	{
		return _splitBridge;
	}

	public List<? extends IContinuousIntegerSet> split(final int[] values, final boolean[] isIntervalStart)
	{
		return getSetCreationToolbox().split(values, isIntervalStart);
	}

	@Override
	public boolean isEmpty() {
		final IContinuousIntegerBound lowerBound = _bridged.getLowerBound();
		final IContinuousIntegerBound upperBound = _bridged.getUpperBound();
		if((!_bridged.isLowerBoundInfinity() && lowerBound == null) || (!_bridged.isUpperBoundInfinity() && upperBound == null))
			return true;
		if(lowerBound == null || upperBound == null)
			return false;

		final double compare = (lowerBound.getValue() - upperBound.getValue());
		return (compare>0) || ( compare == 0 && (!lowerBound.isClosed() || !upperBound.isClosed()));
	}

	@Override
	public IContinuousIntegerSet unite(final IContinuousIntegerSet set)
	{
		return super.unite(set).getAsSet();
	}

	@Override
	public IContinuousIntegerSet intersect(final IContinuousIntegerSet set)
	{
		return super.intersect(set).getAsSet();
	}
}