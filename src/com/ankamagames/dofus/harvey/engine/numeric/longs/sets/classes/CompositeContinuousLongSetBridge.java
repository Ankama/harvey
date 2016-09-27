/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeContinuousSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes.CompositeContinuousLongSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes.ContinuousLongBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes.ContinuousLongEqualityToolbox;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ICompositeContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongBound;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleContinuousLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class CompositeContinuousLongSetBridge<ChildType extends IContinuousLongSet, BridgedSet extends BaseCompositeContinuousLongSet<ChildType>>
extends AbstractCompositeContinuousSetBridge
<
	IContinuousLongBound,
	IContinuousLongSet,
	ISimpleContinuousLongSet,
	IElementaryContinuousLongSet,
	ICompositeContinuousLongSet<?>,
	ChildType,
	BridgedSet,
	SortedSet<ChildType>
>
{
	public static class ContinuousLongSetFactory<ChildType extends IContinuousLongSet>
	extends SortedSetFactory<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, ChildType, SortedSet<ChildType>>
	{
		@Override
		public TreeSet<ChildType> makeSet(final Comparator<? super ChildType> comparator)
		{
			return new TreeSet<ChildType>(comparator);
		}
	}

	protected ContinuousLongBoundComparisonToolbox<IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, BridgedSet> _boundComparisonToolbox;
	protected ContinuousLongEqualityToolbox<BridgedSet> _equalityToolbox;
	protected CompositeContinuousLongSetCreationToolbox<ChildType, BridgedSet> _setCreationToolbox;

	public CompositeContinuousLongSetBridge(final BridgedSet bridged) {
		super(bridged, new ContinuousLongSetFactory<ChildType>());
		_boundComparisonToolbox = new ContinuousLongBoundComparisonToolbox<IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, BridgedSet>(bridged);
		_equalityToolbox = new ContinuousLongEqualityToolbox<BridgedSet>(bridged);
		_setCreationToolbox = new CompositeContinuousLongSetCreationToolbox<ChildType, BridgedSet>(bridged);
	}

	public CompositeContinuousLongSetBridge(final BridgedSet bridged, final ChildType child) {
		super(bridged, new ContinuousLongSetFactory<ChildType>(), child);
		_boundComparisonToolbox = new ContinuousLongBoundComparisonToolbox<IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, BridgedSet>(bridged);
		_equalityToolbox = new ContinuousLongEqualityToolbox<BridgedSet>(bridged);
		_setCreationToolbox = new CompositeContinuousLongSetCreationToolbox<ChildType, BridgedSet>(bridged);
	}

	public CompositeContinuousLongSetBridge(final BridgedSet bridged, final Iterable<ChildType> childrenIterable) {
		super(bridged, new ContinuousLongSetFactory<ChildType>(), childrenIterable);
		_boundComparisonToolbox = new ContinuousLongBoundComparisonToolbox<IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, BridgedSet>(bridged);
		_equalityToolbox = new ContinuousLongEqualityToolbox<BridgedSet>(bridged);
		_setCreationToolbox = new CompositeContinuousLongSetCreationToolbox<ChildType, BridgedSet>(bridged);
	}


	@Override
	protected ContinuousLongBoundComparisonToolbox<IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, BridgedSet> getBoundComparisonToolbox()
	{
		return _boundComparisonToolbox;
	}

	@Override
	protected ContinuousLongEqualityToolbox<BridgedSet> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected CompositeContinuousLongSetCreationToolbox<ChildType, BridgedSet> getSetCreationToolbox()
	{
		return _setCreationToolbox;
	}
}
