/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateContinuousSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes.ContinuousIntegerBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes.ContinuousIntegerEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes.ContinuousIntegerSetCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerBound;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IDegenerateContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleContinuousIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class DegenerateContinuousIntegerSetBridge<BridgedSet extends IDegenerateContinuousIntegerSet>
	extends AbstractDegenerateContinuousSetBridge<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, IContinuousIntegerInterval, IDegenerateContinuousIntegerSet, BridgedSet>
{
	protected ContinuousIntegerEqualityToolbox<BridgedSet> _equalityToolbox;
	protected ContinuousIntegerSetCreationToolbox<BridgedSet> _creationToolbox;
	protected ContinuousIntegerBoundComparisonToolbox<IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, BridgedSet> _boundToolbox;

	public DegenerateContinuousIntegerSetBridge(final BridgedSet bridged)
	{
		super(bridged);
		_equalityToolbox = new ContinuousIntegerEqualityToolbox<BridgedSet>(_bridged);
		_creationToolbox = new ContinuousIntegerSetCreationToolbox<BridgedSet>(_bridged);
		_boundToolbox = new ContinuousIntegerBoundComparisonToolbox<IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, BridgedSet>(_bridged);
	}

	@Override
	protected ContinuousIntegerEqualityToolbox<BridgedSet> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected ContinuousIntegerSetCreationToolbox<BridgedSet> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected ContinuousIntegerBoundComparisonToolbox<IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, BridgedSet> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}
}