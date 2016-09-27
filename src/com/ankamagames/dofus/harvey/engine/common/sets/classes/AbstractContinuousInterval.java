/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementaryContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractContinuousInterval
<
	Bound extends IContinuousBound<Bound>,
	Set extends IContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	Interval extends IContinuousInterval<Bound, Set, SimpleSet, ElementarySet, Interval>
>
extends AbstractInterval<Bound, Set, SimpleSet, ElementarySet, Interval>
implements IContinuousInterval<Bound, Set, SimpleSet, ElementarySet, Interval>,
IContinuousSet<Bound, Set, SimpleSet, ElementarySet>
{
	@Override
	protected abstract AbstractIntervalBridge<Bound, Set, SimpleSet, ElementarySet, Interval, ?, ? extends IContinuousInterval<Bound, Set, SimpleSet, ElementarySet, Interval>> getBridge();
}