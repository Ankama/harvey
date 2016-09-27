package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


@NonNullByDefault
public abstract class AbstractDegenerateIsAndHasSet
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Interval extends IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>,
	DegenerateSet extends IDegenerateSortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet>
>
extends AbstractDegenerateSet<Set, SimpleSet, ElementarySet, DegenerateSet>
implements IDegenerateSortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet>
{
	@Override
	public boolean is(final SetCoveringMask mask, final Set set)
	{
		switch(mask)
		{
		case ALL:
			return !set.isEmpty();
		case EMPTY:
			return false;
		case EQUAL:
			return isContainedBy(set);
		case GREATER:
			return isGreaterThan(set);
		case GREATER_OR_EQUAL:
			return isGreaterThan(set)||isContainedBy(set);
		case GREATER_OR_IN_RANGE:
			return !isLowerThan(set);
		case GREATER_OR_IN_RANGE_BUT_NOT_EQUAL:
			return !isLowerThan(set) && !isContainedBy(set);
		case IN_RANGE:
			return isInRange(set);
		case IN_RANGE_BUT_NOT_EQUAL:
			return isInRange(set) && !isContainedBy(set);
		case LOWER:
			return isLowerThan(set);
		case LOWER_OR_EQUAL:
			return isLowerThan(set)||isContainedBy(set);
		case LOWER_OR_IN_RANGE:
			return !isGreaterThan(set);
		case LOWER_OR_IN_RANGE_BUT_NOT_EQUAL:
			return !isGreaterThan(set) && !isContainedBy(set);
		case NOT_EQUAL:
			return !isContainedBy(set);
		case OUT_OF_RANGE:
			return !isInRange(set);
		case OUT_OF_RANGE_OR_EQUAL:
			return !isInRange(set) || isContainedBy(set);
		default:
			throw new UnsupportedOperationException("SetCoveringMask value of " + mask + " is unknown.");
		}
	}

	@Override
	public boolean has(final SetCoveringMask mask, final Set set)
	{
		return is(mask, set);
	}
}