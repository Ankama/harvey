/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeContinuousSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes.CompositeContinuousIntegerSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes.ContinuousIntegerBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes.ContinuousIntegerEqualityToolbox;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ICompositeContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerBound;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleContinuousIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class CompositeContinuousIntegerSetBridge<ChildType extends IContinuousIntegerSet, BridgedSet extends BaseCompositeContinuousIntegerSet<ChildType>>
extends AbstractCompositeContinuousSetBridge
<
	IContinuousIntegerBound,
	IContinuousIntegerSet,
	ISimpleContinuousIntegerSet,
	IElementaryContinuousIntegerSet,
	ICompositeContinuousIntegerSet<?>,
	ChildType,
	BridgedSet,
	SortedSet<ChildType>
>
{
	public static class ContinuousIntegerSetFactory<ChildType extends IContinuousIntegerSet>
	extends SortedSetFactory<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, ChildType, SortedSet<ChildType>>
	{
		@Override
		public TreeSet<ChildType> makeSet(final Comparator<? super ChildType> comparator)
		{
			return new TreeSet<ChildType>(comparator);
		}
	}

	protected ContinuousIntegerBoundComparisonToolbox<IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, BridgedSet> _boundComparisonToolbox;
	protected ContinuousIntegerEqualityToolbox<BridgedSet> _equalityToolbox;
	protected CompositeContinuousIntegerSetCreationToolbox<ChildType, BridgedSet> _setCreationToolbox;

	public CompositeContinuousIntegerSetBridge(final BridgedSet bridged) {
		super(bridged, new ContinuousIntegerSetFactory<ChildType>());
		_boundComparisonToolbox = new ContinuousIntegerBoundComparisonToolbox<IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, BridgedSet>(bridged);
		_equalityToolbox = new ContinuousIntegerEqualityToolbox<BridgedSet>(bridged);
		_setCreationToolbox = new CompositeContinuousIntegerSetCreationToolbox<ChildType, BridgedSet>(bridged);
	}

	public CompositeContinuousIntegerSetBridge(final BridgedSet bridged, final ChildType child) {
		super(bridged, new ContinuousIntegerSetFactory<ChildType>(), child);
		_boundComparisonToolbox = new ContinuousIntegerBoundComparisonToolbox<IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, BridgedSet>(bridged);
		_equalityToolbox = new ContinuousIntegerEqualityToolbox<BridgedSet>(bridged);
		_setCreationToolbox = new CompositeContinuousIntegerSetCreationToolbox<ChildType, BridgedSet>(bridged);
	}

	public CompositeContinuousIntegerSetBridge(final BridgedSet bridged, final Iterable<ChildType> childrenIterable) {
		super(bridged, new ContinuousIntegerSetFactory<ChildType>(), childrenIterable);
		_boundComparisonToolbox = new ContinuousIntegerBoundComparisonToolbox<IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, BridgedSet>(bridged);
		_equalityToolbox = new ContinuousIntegerEqualityToolbox<BridgedSet>(bridged);
		_setCreationToolbox = new CompositeContinuousIntegerSetCreationToolbox<ChildType, BridgedSet>(bridged);
	}


	@Override
	protected ContinuousIntegerBoundComparisonToolbox<IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, BridgedSet> getBoundComparisonToolbox()
	{
		return _boundComparisonToolbox;
	}

	@Override
	protected ContinuousIntegerEqualityToolbox<BridgedSet> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected CompositeContinuousIntegerSetCreationToolbox<ChildType, BridgedSet> getSetCreationToolbox()
	{
		return _setCreationToolbox;
	}
}
