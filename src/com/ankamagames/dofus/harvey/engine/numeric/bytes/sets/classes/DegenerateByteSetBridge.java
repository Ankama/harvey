/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes.ByteBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes.ByteEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes.ByteSetCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IDegenerateByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class DegenerateByteSetBridge<BridgedSet extends IDegenerateByteSet>
	extends AbstractDegenerateSortedSetBridge<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval, IDegenerateByteSet, BridgedSet>
{
	protected ByteEqualityToolbox<IByteSet, ISimpleByteSet, IElementaryByteSet, BridgedSet> _equalityToolbox;
	protected ByteSetCreationToolbox<BridgedSet> _creationToolbox;
	protected ByteBoundComparisonToolbox<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, BridgedSet> _boundToolbox;

	public DegenerateByteSetBridge(final BridgedSet bridged)
	{
		super(bridged);
		_equalityToolbox = new ByteEqualityToolbox<IByteSet, ISimpleByteSet, IElementaryByteSet, BridgedSet>(_bridged);
		_creationToolbox = new ByteSetCreationToolbox<BridgedSet>(_bridged);
		_boundToolbox = new ByteBoundComparisonToolbox<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, BridgedSet>(_bridged);
	}

	@Override
	protected ByteEqualityToolbox<IByteSet, ISimpleByteSet, IElementaryByteSet, BridgedSet> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected ByteSetCreationToolbox<BridgedSet> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected ByteBoundComparisonToolbox<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, BridgedSet> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}
}