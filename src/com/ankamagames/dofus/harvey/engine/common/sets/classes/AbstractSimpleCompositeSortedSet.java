package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.SortedSet;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleCompositeSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public abstract class AbstractSimpleCompositeSortedSet
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	CompositeSet extends ICompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ?>,
	SimpleCompositeSet extends ISimpleCompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, SimpleCompositeSet, ?>,
	ChildType extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	InternalChildrenCollectionType extends SortedSet<ChildType>
>
extends AbstractCompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType, InternalChildrenCollectionType>
implements ISimpleCompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, SimpleCompositeSet, ChildType>
{}