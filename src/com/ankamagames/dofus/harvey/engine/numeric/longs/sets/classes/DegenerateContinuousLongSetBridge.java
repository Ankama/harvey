/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateContinuousSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes.ContinuousLongBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes.ContinuousLongEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes.ContinuousLongSetCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongBound;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IDegenerateContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleContinuousLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class DegenerateContinuousLongSetBridge<BridgedSet extends IDegenerateContinuousLongSet>
	extends AbstractDegenerateContinuousSetBridge<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval, IDegenerateContinuousLongSet, BridgedSet>
{
	protected ContinuousLongEqualityToolbox<BridgedSet> _equalityToolbox;
	protected ContinuousLongSetCreationToolbox<BridgedSet> _creationToolbox;
	protected ContinuousLongBoundComparisonToolbox<IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, BridgedSet> _boundToolbox;

	public DegenerateContinuousLongSetBridge(final BridgedSet bridged)
	{
		super(bridged);
		_equalityToolbox = new ContinuousLongEqualityToolbox<BridgedSet>(_bridged);
		_creationToolbox = new ContinuousLongSetCreationToolbox<BridgedSet>(_bridged);
		_boundToolbox = new ContinuousLongBoundComparisonToolbox<IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, BridgedSet>(_bridged);
	}

	@Override
	protected ContinuousLongEqualityToolbox<BridgedSet> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected ContinuousLongSetCreationToolbox<BridgedSet> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected ContinuousLongBoundComparisonToolbox<IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, BridgedSet> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}
}