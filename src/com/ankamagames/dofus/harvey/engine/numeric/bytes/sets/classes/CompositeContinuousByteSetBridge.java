/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeContinuousSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes.CompositeContinuousByteSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes.ContinuousByteBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes.ContinuousByteEqualityToolbox;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ICompositeContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleContinuousByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class CompositeContinuousByteSetBridge<ChildType extends IContinuousByteSet, BridgedSet extends BaseCompositeContinuousByteSet<ChildType>>
extends AbstractCompositeContinuousSetBridge
<
	IContinuousByteBound,
	IContinuousByteSet,
	ISimpleContinuousByteSet,
	IElementaryContinuousByteSet,
	ICompositeContinuousByteSet<?>,
	ChildType,
	BridgedSet,
	SortedSet<ChildType>
>
{
	public static class ContinuousByteSetFactory<ChildType extends IContinuousByteSet>
	extends SortedSetFactory<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, ChildType, SortedSet<ChildType>>
	{
		@Override
		public TreeSet<ChildType> makeSet(final Comparator<? super ChildType> comparator)
		{
			return new TreeSet<ChildType>(comparator);
		}
	}

	protected ContinuousByteBoundComparisonToolbox<IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, BridgedSet> _boundComparisonToolbox;
	protected ContinuousByteEqualityToolbox<BridgedSet> _equalityToolbox;
	protected CompositeContinuousByteSetCreationToolbox<ChildType, BridgedSet> _setCreationToolbox;

	public CompositeContinuousByteSetBridge(final BridgedSet bridged) {
		super(bridged, new ContinuousByteSetFactory<ChildType>());
		_boundComparisonToolbox = new ContinuousByteBoundComparisonToolbox<IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, BridgedSet>(bridged);
		_equalityToolbox = new ContinuousByteEqualityToolbox<BridgedSet>(bridged);
		_setCreationToolbox = new CompositeContinuousByteSetCreationToolbox<ChildType, BridgedSet>(bridged);
	}

	public CompositeContinuousByteSetBridge(final BridgedSet bridged, final ChildType child) {
		super(bridged, new ContinuousByteSetFactory<ChildType>(), child);
		_boundComparisonToolbox = new ContinuousByteBoundComparisonToolbox<IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, BridgedSet>(bridged);
		_equalityToolbox = new ContinuousByteEqualityToolbox<BridgedSet>(bridged);
		_setCreationToolbox = new CompositeContinuousByteSetCreationToolbox<ChildType, BridgedSet>(bridged);
	}

	public CompositeContinuousByteSetBridge(final BridgedSet bridged, final Iterable<ChildType> childrenIterable) {
		super(bridged, new ContinuousByteSetFactory<ChildType>(), childrenIterable);
		_boundComparisonToolbox = new ContinuousByteBoundComparisonToolbox<IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, BridgedSet>(bridged);
		_equalityToolbox = new ContinuousByteEqualityToolbox<BridgedSet>(bridged);
		_setCreationToolbox = new CompositeContinuousByteSetCreationToolbox<ChildType, BridgedSet>(bridged);
	}


	@Override
	protected ContinuousByteBoundComparisonToolbox<IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, BridgedSet> getBoundComparisonToolbox()
	{
		return _boundComparisonToolbox;
	}

	@Override
	protected ContinuousByteEqualityToolbox<BridgedSet> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected CompositeContinuousByteSetCreationToolbox<ChildType, BridgedSet> getSetCreationToolbox()
	{
		return _setCreationToolbox;
	}
}
