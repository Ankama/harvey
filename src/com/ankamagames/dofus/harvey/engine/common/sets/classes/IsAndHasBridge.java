package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet.SetCoveringMask;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class IsAndHasBridge
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Bridged extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>
>
extends AbstractSetBridge<Set, SimpleSet, ElementarySet, Bridged> {

	public IsAndHasBridge(final Bridged bridged)
	{
		super(bridged);
	}

	public boolean is(final SetCoveringMask mask, final Set set)
	{
		switch(mask)
		{
		case ALL:
			return !_bridged.isEmpty()&&!set.isEmpty();
		case EMPTY:
			return _bridged.isEmpty()||set.isEmpty();
		case EQUAL:
			return _bridged.isContainedBy(set);
		case GREATER:
			return _bridged.isGreaterThan(set);
		case GREATER_OR_EQUAL:
		{
			final List<? extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>> splited = _bridged.splitOnRange(set);
			if((!splited.get(0).isEmpty())&&((!splited.get(0).isDegenerate())||(!splited.get(0).isContainedBy(set))))
				return false;
			if(!splited.get(1).isContainedBy(set))
				return false;
			return true;
		}
		case GREATER_OR_IN_RANGE:
			return set.isEmpty()?_bridged.isEmpty():!_bridged.hasValueLowerThan(set);
		case GREATER_OR_IN_RANGE_BUT_NOT_EQUAL:
			return set.isEmpty()?false:!_bridged.hasValueLowerThan(set) && !_bridged.isIntersecting(set);
		case IN_RANGE:
			return _bridged.isInRange(set);
		case IN_RANGE_BUT_NOT_EQUAL:
			return _bridged.isInRange(set) && !_bridged.isIntersecting(set);
		case LOWER:
			return _bridged.isLowerThan(set);
		case LOWER_OR_EQUAL:
		{
			final List<? extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>> splited = _bridged.splitOnRange(set);
			if(!splited.get(2).isEmpty())
				return false;
			if(!splited.get(1).isContainedBy(set))
				return false;
			return true;
		}
		case LOWER_OR_IN_RANGE:
			return !_bridged.hasValueGreaterThan(set);
		case LOWER_OR_IN_RANGE_BUT_NOT_EQUAL:
			return !_bridged.hasValueGreaterThan(set) && !_bridged.isIntersecting(set);
		case NOT_EQUAL:
			return !_bridged.isIntersecting(set);
		case OUT_OF_RANGE:
			return !_bridged.hasValueInRange(set);
		case OUT_OF_RANGE_OR_EQUAL:
			final List<? extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>> splited = _bridged.splitOnRange(set);
			if(!splited.get(1).isContainedBy(set))
				return false;
			return true;
		default:
			throw new UnsupportedOperationException("SetCoveringMask value of " + mask + " is unknown.");
		}
	}

	public boolean has(final SetCoveringMask mask, final Set set)
	{
		switch(mask)
		{
		case ALL:
			return !_bridged.isEmpty()&&!set.isEmpty();
		case EMPTY:
			return _bridged.isEmpty()||set.isEmpty();
		case EQUAL:
			return _bridged.isIntersecting(set);
		case GREATER:
			return _bridged.hasValueGreaterThan(set);
		case GREATER_OR_EQUAL:
			return _bridged.hasValueGreaterThan(set)||_bridged.isIntersecting(set);
		case GREATER_OR_IN_RANGE:
			return _bridged.hasValueGreaterThan(set)||_bridged.hasValueInRange(set);
		case GREATER_OR_IN_RANGE_BUT_NOT_EQUAL:
		{
			final List<? extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>> splited = _bridged.splitOnRange(set);
			if(!splited.get(2).isEmpty())
				return true;
			if(!splited.get(1).isContainedBy(set))
				return true;
			return false;
		}
		case IN_RANGE:
			return _bridged.hasValueInRange(set);
		case IN_RANGE_BUT_NOT_EQUAL:
		{
			final List<? extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>> splited = _bridged.splitOnRange(set);
			if(!splited.get(1).isContainedBy(set))
				return true;
			return false;
		}
		case LOWER:
			return _bridged.hasValueLowerThan(set);
		case LOWER_OR_EQUAL:
			return _bridged.hasValueLowerThan(set)||_bridged.isIntersecting(set);
		case LOWER_OR_IN_RANGE:
			return _bridged.hasValueLowerThan(set)||_bridged.hasValueInRange(set);
		case LOWER_OR_IN_RANGE_BUT_NOT_EQUAL:
		{
			final List<? extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>> splited = _bridged.splitOnRange(set);
			if(!splited.get(0).isEmpty())
				return true;
			if(!splited.get(1).isContainedBy(set))
				return true;
			return false;
		}
		case NOT_EQUAL:
			return !_bridged.isContainedBy(set);
		case OUT_OF_RANGE:
			return set.isEmpty()?!_bridged.isEmpty():_bridged.hasValueLowerThan(set)||_bridged.hasValueGreaterThan(set);
		case OUT_OF_RANGE_OR_EQUAL:
			return set.isEmpty()?true:_bridged.hasValueLowerThan(set)||_bridged.hasValueGreaterThan(set)||_bridged.isIntersecting(set);
		default:
			throw new UnsupportedOperationException("SetCoveringMask value of " + mask + " is unknown.");
		}
	}


}
