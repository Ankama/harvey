/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementaryContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public interface ICommonContinuousIntegerSet
<
	Bound extends IContinuousBound<Bound>,
	Set extends IContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet>
>
extends ICommonIntegerSet<Bound, Set, SimpleSet, ElementarySet>,
IContinuousSet<Bound, Set, SimpleSet, ElementarySet>
{}
