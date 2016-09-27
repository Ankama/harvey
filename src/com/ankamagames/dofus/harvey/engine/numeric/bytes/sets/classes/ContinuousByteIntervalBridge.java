/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.SplitOnRangeBridge;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes.ContinuousByteBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes.ContinuousByteEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes.ContinuousByteIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.ContinuousByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IEmptyContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleContinuousByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousByteIntervalBridge<Bridged extends ContinuousByteInterval>
extends AbstractIntervalBridge<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IContinuousByteInterval, IEmptyContinuousByteSet, Bridged>
{
	protected ContinuousByteBoundComparisonToolbox<IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, Bridged>												_boundToolbox;
	protected ContinuousByteIntervalCreationToolbox<Bridged>																																					_creationToolbox;
	protected ContinuousByteEqualityToolbox<Bridged>																																							_equalityToolbox;
	protected SplitOnRangeBridge<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IContinuousByteInterval, IEmptyContinuousByteSet, Bridged>	_splitBridge;

	public ContinuousByteIntervalBridge(final Bridged bridged)
	{
		super(bridged);
		_boundToolbox = new ContinuousByteBoundComparisonToolbox<IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, Bridged>(_bridged);
		_creationToolbox = new ContinuousByteIntervalCreationToolbox<Bridged>(_bridged);
		_equalityToolbox = new ContinuousByteEqualityToolbox<Bridged>(_bridged);
		_splitBridge = new SplitOnRangeBridge<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IContinuousByteInterval, IEmptyContinuousByteSet, Bridged>(_bridged, _creationToolbox);
	}

	@Override
	protected ContinuousByteBoundComparisonToolbox<IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, Bridged> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}

	@Override
	protected ContinuousByteIntervalCreationToolbox<Bridged> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected ContinuousByteEqualityToolbox<Bridged> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected SplitOnRangeBridge<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IContinuousByteInterval, IEmptyContinuousByteSet, Bridged> getSplitBridge()
	{
		return _splitBridge;
	}

	public List<? extends IContinuousByteSet> split(final byte[] values, final boolean[] isIntervalStart)
	{
		return getSetCreationToolbox().split(values, isIntervalStart);
	}

	@Override
	public boolean isEmpty() {
		final IContinuousByteBound lowerBound = _bridged.getLowerBound();
		final IContinuousByteBound upperBound = _bridged.getUpperBound();
		if((!_bridged.isLowerBoundInfinity() && lowerBound == null) || (!_bridged.isUpperBoundInfinity() && upperBound == null))
			return true;
		if(lowerBound == null || upperBound == null)
			return false;

		final double compare = (lowerBound.getValue() - upperBound.getValue());
		return (compare>0) || ( compare == 0 && (!lowerBound.isClosed() || !upperBound.isClosed()));
	}

	@Override
	public IContinuousByteSet unite(final IContinuousByteSet set)
	{
		return super.unite(set).getAsSet();
	}

	@Override
	public IContinuousByteSet intersect(final IContinuousByteSet set)
	{
		return super.intersect(set).getAsSet();
	}
}