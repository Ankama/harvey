/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.SplitOnRangeBridge;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes.ByteBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes.ByteEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes.ByteIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.ByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IEmptyByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ByteIntervalBridge<Bridged extends ByteInterval>
extends AbstractIntervalBridge<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval, IEmptyByteSet, Bridged>
{
	protected ByteBoundComparisonToolbox<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, Bridged> _boundToolbox;
	protected ByteIntervalCreationToolbox<Bridged> _creationToolbox;
	protected ByteEqualityToolbox<IByteSet, ISimpleByteSet, IElementaryByteSet, Bridged> _equalityToolbox;
	protected SplitOnRangeBridge<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval, IEmptyByteSet, Bridged> _splitBridge;

	public ByteIntervalBridge(final Bridged bridged)
	{
		super(bridged);
		_boundToolbox = new ByteBoundComparisonToolbox<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, Bridged>(_bridged);
		_creationToolbox = new ByteIntervalCreationToolbox<Bridged>(_bridged);
		_equalityToolbox = new ByteEqualityToolbox<IByteSet, ISimpleByteSet, IElementaryByteSet, Bridged>(_bridged);
		_splitBridge = new SplitOnRangeBridge<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval, IEmptyByteSet, Bridged>(_bridged, _creationToolbox);
	}

	@Override
	protected SplitOnRangeBridge<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval, IEmptyByteSet, Bridged> getSplitBridge() {
		return _splitBridge;
	}

	@Override
	protected ByteBoundComparisonToolbox<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, Bridged> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}

	@Override
	protected ByteIntervalCreationToolbox<Bridged> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected ByteEqualityToolbox<IByteSet, ISimpleByteSet, IElementaryByteSet, Bridged> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	public List<? extends IByteSet> split(final byte[] values, final boolean[] isIntervalStart)
	{
		return getSetCreationToolbox().split(values, isIntervalStart);
	}

	@Override
	public boolean isEmpty() {
		final IByteBound lowerBound = _bridged.getLowerBound();
		final IByteBound upperBound = _bridged.getUpperBound();
		if(lowerBound == null || upperBound == null)
			return true;
		return (lowerBound.getValue() - upperBound.getValue())>0;
	}

	@Override
	public IByteSet unite(final IByteSet set)
	{
		return super.unite(set).getAsSet();
	}

	@Override
	public IByteSet intersect(final IByteSet set)
	{
		return super.intersect(set).getAsSet();
	}
}