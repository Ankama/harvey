/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementaryContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public interface IContinuousElementaryEvent
<
	Bound extends IContinuousBound<Bound>,
	Set extends IContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	DegenerateSet extends IDegenerateContinuousSet<Bound, Set, SimpleSet, ElementarySet, ?, DegenerateSet>,
	RandomVariable extends IContinuousRandomVariable<Bound, Set, SimpleSet, ElementarySet, DegenerateSet, RandomVariable, ?, ?>,
	IterableType extends IContinuousElementaryEvent<Bound, Set, SimpleSet, ElementarySet, DegenerateSet, RandomVariable, ?, ?>,
	ElementsType extends IDegenerateContinuousSet<Bound, Set, SimpleSet, ElementarySet, ?, DegenerateSet>
>
extends	ISortedElementaryEvent<Bound, Set, SimpleSet, ElementarySet, DegenerateSet, RandomVariable, IterableType, ElementsType> {

}
