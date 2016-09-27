package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


@NonNullByDefault
public abstract class AbstractIsAndHasSet
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>
>
extends AbstractSet<Set, SimpleSet, ElementarySet>
implements ISortedSet<Bound, Set, SimpleSet, ElementarySet>
{
	IsAndHasBridge<Bound, Set, SimpleSet, ElementarySet, ISortedSet<Bound, Set,SimpleSet,ElementarySet>> _isAndHasBridge = new IsAndHasBridge<Bound, Set, SimpleSet, ElementarySet, ISortedSet<Bound, Set,SimpleSet,ElementarySet>>(this);

	protected IsAndHasBridge<Bound, Set, SimpleSet, ElementarySet, ISortedSet<Bound, Set, SimpleSet, ElementarySet>> getIsAndHasBridge()
	{
		return _isAndHasBridge;
	}

	@Override
	public boolean is(final SetCoveringMask mask, final Set set)
	{
		return getIsAndHasBridge().is(mask, set);
	}

	@Override
	public boolean has(final SetCoveringMask mask, final Set set)
	{
		return getIsAndHasBridge().has(mask, set);
	}
}