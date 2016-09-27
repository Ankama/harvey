/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes.IntegerBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes.IntegerEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes.IntegerSetCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IDegenerateIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryIntegerSet;
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
public class DegenerateIntegerSetBridge<BridgedSet extends IDegenerateIntegerSet>
	extends AbstractDegenerateSortedSetBridge<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval, IDegenerateIntegerSet, BridgedSet>
{
	protected IntegerEqualityToolbox<IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, BridgedSet> _equalityToolbox;
	protected IntegerSetCreationToolbox<BridgedSet> _creationToolbox;
	protected IntegerBoundComparisonToolbox<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, BridgedSet> _boundToolbox;

	public DegenerateIntegerSetBridge(final BridgedSet bridged)
	{
		super(bridged);
		_equalityToolbox = new IntegerEqualityToolbox<IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, BridgedSet>(_bridged);
		_creationToolbox = new IntegerSetCreationToolbox<BridgedSet>(_bridged);
		_boundToolbox = new IntegerBoundComparisonToolbox<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, BridgedSet>(_bridged);
	}

	@Override
	protected IntegerEqualityToolbox<IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, BridgedSet> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected IntegerSetCreationToolbox<BridgedSet> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected IntegerBoundComparisonToolbox<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, BridgedSet> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}
}