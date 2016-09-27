package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.ArrayList;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleCompositeSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public abstract class AbstractSimpleCompositeSetBridge
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>,
	CompositeSet extends ICompositeSet<Set, SimpleSet, ElementarySet, CompositeSet, ?>,
	BridgedSet extends ISimpleCompositeSet<Set, SimpleSet, ElementarySet, CompositeSet, ElementarySet>,
	InternalChildrenCollectionType extends CheckedSetArrayList<Set, SimpleSet, ElementarySet>
>
extends	AbstractCompositeSetBridge<Set, SimpleSet, ElementarySet, CompositeSet, ElementarySet, BridgedSet, InternalChildrenCollectionType>
{
	public AbstractSimpleCompositeSetBridge(final BridgedSet bridged, final InternalChildrenCollectionType childrenIterable)
	{
		super(bridged, childrenIterable);
	}

	@Override
	protected Set _doMergeWithSet(final Set set, final ArrayList<Set> keepElmts,
			final ArrayList<ElementarySet> toMergeElmts, final boolean intersects, final boolean setWithOverlap) {
		if((!intersects)||(setWithOverlap))
		{
			if(!set.isSimple())
				return set.unite(_bridged.getAsSet()).getAsSet();
			return getSetCreationToolbox().addChildToBridgedComposite(set).getAsSet();
		}

		ISet<Set, SimpleSet, ElementarySet> currentMerge = set;
		for(final ElementarySet toMerge:toMergeElmts)
		{
			currentMerge = toMerge.unite(currentMerge.getAsSet());
		}

		keepElmts.add(currentMerge.getAsSet());
		return getSetCreationToolbox().makeComposite(keepElmts);
	}
}