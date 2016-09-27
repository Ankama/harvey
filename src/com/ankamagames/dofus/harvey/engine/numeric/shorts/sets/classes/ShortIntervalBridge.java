/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.SplitOnRangeBridge;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes.ShortBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes.ShortEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes.ShortIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.ShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IEmptyShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ShortIntervalBridge<Bridged extends ShortInterval>
extends AbstractIntervalBridge<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval, IEmptyShortSet, Bridged>
{
	protected ShortBoundComparisonToolbox<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, Bridged> _boundToolbox;
	protected ShortIntervalCreationToolbox<Bridged> _creationToolbox;
	protected ShortEqualityToolbox<IShortSet, ISimpleShortSet, IElementaryShortSet, Bridged> _equalityToolbox;
	protected SplitOnRangeBridge<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval, IEmptyShortSet, Bridged> _splitBridge;

	public ShortIntervalBridge(final Bridged bridged)
	{
		super(bridged);
		_boundToolbox = new ShortBoundComparisonToolbox<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, Bridged>(_bridged);
		_creationToolbox = new ShortIntervalCreationToolbox<Bridged>(_bridged);
		_equalityToolbox = new ShortEqualityToolbox<IShortSet, ISimpleShortSet, IElementaryShortSet, Bridged>(_bridged);
		_splitBridge = new SplitOnRangeBridge<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval, IEmptyShortSet, Bridged>(_bridged, _creationToolbox);
	}

	@Override
	protected SplitOnRangeBridge<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval, IEmptyShortSet, Bridged> getSplitBridge() {
		return _splitBridge;
	}

	@Override
	protected ShortBoundComparisonToolbox<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, Bridged> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}

	@Override
	protected ShortIntervalCreationToolbox<Bridged> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected ShortEqualityToolbox<IShortSet, ISimpleShortSet, IElementaryShortSet, Bridged> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	public List<? extends IShortSet> split(final short[] values, final boolean[] isIntervalStart)
	{
		return getSetCreationToolbox().split(values, isIntervalStart);
	}

	@Override
	public boolean isEmpty() {
		final IShortBound lowerBound = _bridged.getLowerBound();
		final IShortBound upperBound = _bridged.getUpperBound();
		if(lowerBound == null || upperBound == null)
			return true;
		return (lowerBound.getValue() - upperBound.getValue())>0;
	}

	@Override
	public IShortSet unite(final IShortSet set)
	{
		return super.unite(set).getAsSet();
	}

	@Override
	public IShortSet intersect(final IShortSet set)
	{
		return super.intersect(set).getAsSet();
	}
}