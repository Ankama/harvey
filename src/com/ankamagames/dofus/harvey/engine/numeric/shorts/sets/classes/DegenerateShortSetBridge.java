/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes.ShortBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes.ShortEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes.ShortSetCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IDegenerateShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class DegenerateShortSetBridge<BridgedSet extends IDegenerateShortSet>
	extends AbstractDegenerateSortedSetBridge<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval, IDegenerateShortSet, BridgedSet>
{
	protected ShortEqualityToolbox<IShortSet, ISimpleShortSet, IElementaryShortSet, BridgedSet> _equalityToolbox;
	protected ShortSetCreationToolbox<BridgedSet> _creationToolbox;
	protected ShortBoundComparisonToolbox<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, BridgedSet> _boundToolbox;

	public DegenerateShortSetBridge(final BridgedSet bridged)
	{
		super(bridged);
		_equalityToolbox = new ShortEqualityToolbox<IShortSet, ISimpleShortSet, IElementaryShortSet, BridgedSet>(_bridged);
		_creationToolbox = new ShortSetCreationToolbox<BridgedSet>(_bridged);
		_boundToolbox = new ShortBoundComparisonToolbox<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, BridgedSet>(_bridged);
	}

	@Override
	protected ShortEqualityToolbox<IShortSet, ISimpleShortSet, IElementaryShortSet, BridgedSet> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected ShortSetCreationToolbox<BridgedSet> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected ShortBoundComparisonToolbox<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, BridgedSet> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}
}