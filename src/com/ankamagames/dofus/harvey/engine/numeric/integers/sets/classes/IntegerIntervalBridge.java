/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.SplitOnRangeBridge;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes.IntegerBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes.IntegerEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes.IntegerIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.IntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IEmptyIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerBound;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class IntegerIntervalBridge<Bridged extends IntegerInterval>
extends AbstractIntervalBridge<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval, IEmptyIntegerSet, Bridged>
{
	protected IntegerBoundComparisonToolbox<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, Bridged> _boundToolbox;
	protected IntegerIntervalCreationToolbox<Bridged> _creationToolbox;
	protected IntegerEqualityToolbox<IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, Bridged> _equalityToolbox;
	protected SplitOnRangeBridge<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval, IEmptyIntegerSet, Bridged> _splitBridge;

	public IntegerIntervalBridge(final Bridged bridged)
	{
		super(bridged);
		_boundToolbox = new IntegerBoundComparisonToolbox<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, Bridged>(_bridged);
		_creationToolbox = new IntegerIntervalCreationToolbox<Bridged>(_bridged);
		_equalityToolbox = new IntegerEqualityToolbox<IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, Bridged>(_bridged);
		_splitBridge = new SplitOnRangeBridge<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval, IEmptyIntegerSet, Bridged>(_bridged, _creationToolbox);
	}

	@Override
	protected SplitOnRangeBridge<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval, IEmptyIntegerSet, Bridged> getSplitBridge() {
		return _splitBridge;
	}

	@Override
	protected IntegerBoundComparisonToolbox<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, Bridged> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}

	@Override
	protected IntegerIntervalCreationToolbox<Bridged> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected IntegerEqualityToolbox<IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, Bridged> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	public List<? extends IIntegerSet> split(final int[] values, final boolean[] isIntervalStart)
	{
		return getSetCreationToolbox().split(values, isIntervalStart);
	}

	@Override
	public boolean isEmpty() {
		final IIntegerBound lowerBound = _bridged.getLowerBound();
		final IIntegerBound upperBound = _bridged.getUpperBound();
		if(lowerBound == null || upperBound == null)
			return true;
		return (lowerBound.getValue() - upperBound.getValue())>0;
	}

	@Override
	public IIntegerSet unite(final IIntegerSet set)
	{
		return super.unite(set).getAsSet();
	}

	@Override
	public IIntegerSet intersect(final IIntegerSet set)
	{
		return super.intersect(set).getAsSet();
	}
}