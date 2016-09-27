/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeContinuousSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes.CompositeContinuousShortSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes.ContinuousShortBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes.ContinuousShortEqualityToolbox;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ICompositeContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleContinuousShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class CompositeContinuousShortSetBridge<ChildType extends IContinuousShortSet, BridgedSet extends BaseCompositeContinuousShortSet<ChildType>>
extends AbstractCompositeContinuousSetBridge
<
	IContinuousShortBound,
	IContinuousShortSet,
	ISimpleContinuousShortSet,
	IElementaryContinuousShortSet,
	ICompositeContinuousShortSet<?>,
	ChildType,
	BridgedSet,
	SortedSet<ChildType>
>
{
	public static class ContinuousShortSetFactory<ChildType extends IContinuousShortSet>
	extends SortedSetFactory<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, ChildType, SortedSet<ChildType>>
	{
		@Override
		public TreeSet<ChildType> makeSet(final Comparator<? super ChildType> comparator)
		{
			return new TreeSet<ChildType>(comparator);
		}
	}

	protected ContinuousShortBoundComparisonToolbox<IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, BridgedSet> _boundComparisonToolbox;
	protected ContinuousShortEqualityToolbox<BridgedSet> _equalityToolbox;
	protected CompositeContinuousShortSetCreationToolbox<ChildType, BridgedSet> _setCreationToolbox;

	public CompositeContinuousShortSetBridge(final BridgedSet bridged) {
		super(bridged, new ContinuousShortSetFactory<ChildType>());
		_boundComparisonToolbox = new ContinuousShortBoundComparisonToolbox<IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, BridgedSet>(bridged);
		_equalityToolbox = new ContinuousShortEqualityToolbox<BridgedSet>(bridged);
		_setCreationToolbox = new CompositeContinuousShortSetCreationToolbox<ChildType, BridgedSet>(bridged);
	}

	public CompositeContinuousShortSetBridge(final BridgedSet bridged, final ChildType child) {
		super(bridged, new ContinuousShortSetFactory<ChildType>(), child);
		_boundComparisonToolbox = new ContinuousShortBoundComparisonToolbox<IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, BridgedSet>(bridged);
		_equalityToolbox = new ContinuousShortEqualityToolbox<BridgedSet>(bridged);
		_setCreationToolbox = new CompositeContinuousShortSetCreationToolbox<ChildType, BridgedSet>(bridged);
	}

	public CompositeContinuousShortSetBridge(final BridgedSet bridged, final Iterable<ChildType> childrenIterable) {
		super(bridged, new ContinuousShortSetFactory<ChildType>(), childrenIterable);
		_boundComparisonToolbox = new ContinuousShortBoundComparisonToolbox<IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, BridgedSet>(bridged);
		_equalityToolbox = new ContinuousShortEqualityToolbox<BridgedSet>(bridged);
		_setCreationToolbox = new CompositeContinuousShortSetCreationToolbox<ChildType, BridgedSet>(bridged);
	}


	@Override
	protected ContinuousShortBoundComparisonToolbox<IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, BridgedSet> getBoundComparisonToolbox()
	{
		return _boundComparisonToolbox;
	}

	@Override
	protected ContinuousShortEqualityToolbox<BridgedSet> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected CompositeContinuousShortSetCreationToolbox<ChildType, BridgedSet> getSetCreationToolbox()
	{
		return _setCreationToolbox;
	}
}
