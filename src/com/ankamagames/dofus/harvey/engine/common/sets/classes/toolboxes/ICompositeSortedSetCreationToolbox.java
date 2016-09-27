/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IEmptySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public interface ICompositeSortedSetCreationToolbox
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Interval extends IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>,
	EmptySet extends IEmptySortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet>,
	CompositeSet extends ICompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ?>,
	ChildType extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Bridged extends ICompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType>
>
extends ICompositeSetCreationToolbox<Set, SimpleSet, ElementarySet, EmptySet, CompositeSet, ChildType, Bridged>,
ISortedSetCreationToolbox<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet, Bridged>
{

}
