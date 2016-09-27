/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes.LongBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes.LongEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes.LongSetCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongBound;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IDegenerateLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class DegenerateLongSetBridge<BridgedSet extends IDegenerateLongSet>
	extends AbstractDegenerateSortedSetBridge<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval, IDegenerateLongSet, BridgedSet>
{
	protected LongEqualityToolbox<ILongSet, ISimpleLongSet, IElementaryLongSet, BridgedSet> _equalityToolbox;
	protected LongSetCreationToolbox<BridgedSet> _creationToolbox;
	protected LongBoundComparisonToolbox<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, BridgedSet> _boundToolbox;

	public DegenerateLongSetBridge(final BridgedSet bridged)
	{
		super(bridged);
		_equalityToolbox = new LongEqualityToolbox<ILongSet, ISimpleLongSet, IElementaryLongSet, BridgedSet>(_bridged);
		_creationToolbox = new LongSetCreationToolbox<BridgedSet>(_bridged);
		_boundToolbox = new LongBoundComparisonToolbox<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, BridgedSet>(_bridged);
	}

	@Override
	protected LongEqualityToolbox<ILongSet, ISimpleLongSet, IElementaryLongSet, BridgedSet> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected LongSetCreationToolbox<BridgedSet> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected LongBoundComparisonToolbox<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, BridgedSet> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}
}