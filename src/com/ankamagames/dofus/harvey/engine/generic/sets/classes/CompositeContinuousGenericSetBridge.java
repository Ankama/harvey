/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeContinuousSetBridge;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparator;
import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.CompositeContinuousGenericSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.ContinuousGenericBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.ContinuousGenericEqualityToolbox;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ICompositeContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleContinuousGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class CompositeContinuousGenericSetBridge<Data, ChildType extends IContinuousGenericSet<Data>, BridgedSet extends BaseCompositeContinuousGenericSet<Data, ChildType>>
extends AbstractCompositeContinuousSetBridge
<
	IContinuousGenericBound<Data>,
	IContinuousGenericSet<Data>,
	ISimpleContinuousGenericSet<Data>,
	IElementaryContinuousGenericSet<Data>,
	ICompositeContinuousGenericSet<Data, ?>,
	ChildType,
	BridgedSet,
	SortedSet<ChildType>
>
{
	public static class ContinuousGenericSetFactory<Data, ChildType extends IContinuousGenericSet<Data>>
	extends SortedSetFactory<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, ChildType, SortedSet<ChildType>>
	{
		@Override
		public TreeSet<ChildType> makeSet(final Comparator<? super ChildType> comparator)
		{
			return new TreeSet<ChildType>(comparator);
		}
	}

	protected ContinuousGenericBoundComparisonToolbox<Data, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, BridgedSet> _boundComparisonToolbox;
	protected ContinuousGenericEqualityToolbox<Data, BridgedSet> _equalityToolbox;
	protected CompositeContinuousGenericSetCreationToolbox<Data, ChildType, BridgedSet> _setCreationToolbox;
	protected ContinuousComparator<? super Data> _comparator;
	protected Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> _splitter;

	public CompositeContinuousGenericSetBridge(final BridgedSet bridged, final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter) {
		super(bridged, new ContinuousGenericSetFactory<Data, ChildType>());
		_comparator = comparator;
		_splitter = splitter;
		_boundComparisonToolbox = new ContinuousGenericBoundComparisonToolbox<Data, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, BridgedSet>(bridged, comparator);
		_equalityToolbox = new ContinuousGenericEqualityToolbox<Data, BridgedSet>(bridged);
		_setCreationToolbox = new CompositeContinuousGenericSetCreationToolbox<Data, ChildType, BridgedSet>(bridged, comparator, splitter);
	}

	public CompositeContinuousGenericSetBridge(final BridgedSet bridged, final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter, final ChildType child) {
		super(bridged, new ContinuousGenericSetFactory<Data, ChildType>(), child);
		_comparator = comparator;
		_splitter = splitter;
		_boundComparisonToolbox = new ContinuousGenericBoundComparisonToolbox<Data, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, BridgedSet>(bridged, comparator);
		_equalityToolbox = new ContinuousGenericEqualityToolbox<Data, BridgedSet>(bridged);
		_setCreationToolbox = new CompositeContinuousGenericSetCreationToolbox<Data, ChildType, BridgedSet>(bridged, comparator, splitter);
	}

	public CompositeContinuousGenericSetBridge(final BridgedSet bridged, final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter, final Iterable<ChildType> childrenIterable) {
		super(bridged, new ContinuousGenericSetFactory<Data, ChildType>(), childrenIterable);
		_comparator = comparator;
		_splitter = splitter;
		_boundComparisonToolbox = new ContinuousGenericBoundComparisonToolbox<Data, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, BridgedSet>(bridged, comparator);
		_equalityToolbox = new ContinuousGenericEqualityToolbox<Data, BridgedSet>(bridged);
		_setCreationToolbox = new CompositeContinuousGenericSetCreationToolbox<Data, ChildType, BridgedSet>(bridged, comparator, splitter);
	}


	@Override
	protected ContinuousGenericBoundComparisonToolbox<Data, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, BridgedSet> getBoundComparisonToolbox()
	{
		return _boundComparisonToolbox;
	}

	@Override
	protected ContinuousGenericEqualityToolbox<Data, BridgedSet> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected CompositeContinuousGenericSetCreationToolbox<Data, ChildType, BridgedSet> getSetCreationToolbox()
	{
		return _setCreationToolbox;
	}
}
