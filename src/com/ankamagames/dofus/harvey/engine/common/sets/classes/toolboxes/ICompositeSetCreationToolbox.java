package com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IEmptySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface ICompositeSetCreationToolbox
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>,
	EmptySet extends IEmptySet<Set, SimpleSet, ElementarySet, EmptySet>,
	CompositeSet extends ICompositeSet<Set, SimpleSet, ElementarySet, CompositeSet, ?>,
	ChildType extends ISet<Set, SimpleSet, ElementarySet>,
	Bridged extends ICompositeSet<Set, SimpleSet, ElementarySet, CompositeSet, ChildType>
>
extends ISetCreationToolbox<Set, SimpleSet, ElementarySet, EmptySet, Bridged>
{
	/**
	 * Returns a NEW Composite which has all the childs of the
	 * bridged set and the given set
	 *
	 * @param set
	 * @return
	 */
	Set addChildToBridgedComposite(Set set);
}