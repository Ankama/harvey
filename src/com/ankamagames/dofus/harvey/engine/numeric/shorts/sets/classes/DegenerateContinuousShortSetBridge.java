/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateContinuousSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes.ContinuousShortBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes.ContinuousShortEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes.ContinuousShortSetCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IDegenerateContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleContinuousShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class DegenerateContinuousShortSetBridge<BridgedSet extends IDegenerateContinuousShortSet>
	extends AbstractDegenerateContinuousSetBridge<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, IContinuousShortInterval, IDegenerateContinuousShortSet, BridgedSet>
{
	protected ContinuousShortEqualityToolbox<BridgedSet> _equalityToolbox;
	protected ContinuousShortSetCreationToolbox<BridgedSet> _creationToolbox;
	protected ContinuousShortBoundComparisonToolbox<IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, BridgedSet> _boundToolbox;

	public DegenerateContinuousShortSetBridge(final BridgedSet bridged)
	{
		super(bridged);
		_equalityToolbox = new ContinuousShortEqualityToolbox<BridgedSet>(_bridged);
		_creationToolbox = new ContinuousShortSetCreationToolbox<BridgedSet>(_bridged);
		_boundToolbox = new ContinuousShortBoundComparisonToolbox<IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, BridgedSet>(_bridged);
	}

	@Override
	protected ContinuousShortEqualityToolbox<BridgedSet> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected ContinuousShortSetCreationToolbox<BridgedSet> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected ContinuousShortBoundComparisonToolbox<IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, BridgedSet> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}
}