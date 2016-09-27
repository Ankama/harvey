/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.SortedSet;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.Comparator.ContinuousAscendingComparator;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.Comparator.ContinuousDescendingComparator;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementaryContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractCompositeContinuousSetBridge
<
	Bound extends IContinuousBound<Bound>,
	Set extends IContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	CompositeSet extends ICompositeContinuousSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ?>,
	ChildType extends IContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	Bridged extends ICompositeContinuousSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType>,
	InternalChildrenCollectionType extends SortedSet<ChildType>
>
extends AbstractCompositeSortedSetBridge<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType, Bridged, InternalChildrenCollectionType>
{
	public AbstractCompositeContinuousSetBridge(final Bridged bridged, final SortedSetFactory<Bound, Set, SimpleSet, ElementarySet, ChildType, InternalChildrenCollectionType> factory)
	{
		super(bridged, factory, new ContinuousAscendingComparator<Set, ChildType>(), new ContinuousDescendingComparator<Set, ChildType>());
	}

	public  AbstractCompositeContinuousSetBridge(final Bridged bridged, final SortedSetFactory<Bound, Set, SimpleSet, ElementarySet, ChildType, InternalChildrenCollectionType> factory, final ChildType child)
	{
		super(bridged, factory, child, new ContinuousAscendingComparator<Set, ChildType>(), new ContinuousDescendingComparator<Set, ChildType>());
	}

	public AbstractCompositeContinuousSetBridge(final Bridged bridged, final SortedSetFactory<Bound, Set, SimpleSet, ElementarySet, ChildType, InternalChildrenCollectionType> factory, final Iterable<ChildType> childrenIterable)
	{
		super(bridged, factory, childrenIterable, new ContinuousAscendingComparator<Set, ChildType>(), new ContinuousDescendingComparator<Set, ChildType>());
	}
}