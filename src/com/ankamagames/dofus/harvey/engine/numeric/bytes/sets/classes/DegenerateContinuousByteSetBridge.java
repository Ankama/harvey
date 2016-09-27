/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateContinuousSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes.ContinuousByteBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes.ContinuousByteEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes.ContinuousByteSetCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IDegenerateContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleContinuousByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class DegenerateContinuousByteSetBridge<BridgedSet extends IDegenerateContinuousByteSet>
	extends AbstractDegenerateContinuousSetBridge<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IContinuousByteInterval, IDegenerateContinuousByteSet, BridgedSet>
{
	protected ContinuousByteEqualityToolbox<BridgedSet> _equalityToolbox;
	protected ContinuousByteSetCreationToolbox<BridgedSet> _creationToolbox;
	protected ContinuousByteBoundComparisonToolbox<IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, BridgedSet> _boundToolbox;

	public DegenerateContinuousByteSetBridge(final BridgedSet bridged)
	{
		super(bridged);
		_equalityToolbox = new ContinuousByteEqualityToolbox<BridgedSet>(_bridged);
		_creationToolbox = new ContinuousByteSetCreationToolbox<BridgedSet>(_bridged);
		_boundToolbox = new ContinuousByteBoundComparisonToolbox<IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, BridgedSet>(_bridged);
	}

	@Override
	protected ContinuousByteEqualityToolbox<BridgedSet> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected ContinuousByteSetCreationToolbox<BridgedSet> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected ContinuousByteBoundComparisonToolbox<IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, BridgedSet> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}
}