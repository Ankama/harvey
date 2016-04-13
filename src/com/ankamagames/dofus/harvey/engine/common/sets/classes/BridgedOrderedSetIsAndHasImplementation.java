package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IOrderedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IOrderedSet.SetCoveringMask;


@NonNullByDefault
public class BridgedOrderedSetIsAndHasImplementation<Set extends IOrderedSet<Set>, BridgedType extends IOrderedSet<Set>>
{

	protected BridgedType _bridged;

	public BridgedOrderedSetIsAndHasImplementation(final BridgedType bridged)
	{
		_bridged = bridged;
	}

	/**
	 * @return the _bridged
	 */
	protected BridgedType getBridged()
	{
		return _bridged;
	}

	public boolean is(final SetCoveringMask mask, final Set set)
	{
		switch(mask)
		{
		case ALL:
			return !getBridged().isEmpty()&&!set.isEmpty();
		case EMPTY:
			return getBridged().isEmpty()||set.isEmpty();
		case EQUAL:
			return getBridged().isContainedBy(set);
		case GREATER:
			return getBridged().isGreaterThan(set);
		case GREATER_OR_EQUAL:
		{
			final List<? extends IOrderedSet<Set>> splited = getBridged().splitOnRange(set);
			if((!splited.get(0).isEmpty())&&((!splited.get(0).isDegenerate())||(!splited.get(0).isContainedBy(set))))
				return false;
			if(!splited.get(1).isContainedBy(set))
				return false;
			return true;
		}
		case GREATER_OR_IN_RANGE:
			return !getBridged().hasValueLowerThan(set);
		case GREATER_OR_IN_RANGE_BUT_NOT_EQUAL:
			return !getBridged().hasValueLowerThan(set) && !getBridged().intersects(set);
		case IN_RANGE:
			return getBridged().isInRange(set);
		case IN_RANGE_BUT_NOT_EQUAL:
			return getBridged().isInRange(set) && !getBridged().intersects(set);
		case LOWER:
			return getBridged().isLowerThan(set);
		case LOWER_OR_EQUAL:
		{
			final List<? extends IOrderedSet<Set>> splited = getBridged().splitOnRange(set);
			if(!splited.get(2).isEmpty())
				return false;
			if(!splited.get(1).isContainedBy(set))
				return false;
			return true;
		}
		case LOWER_OR_IN_RANGE:
			return !getBridged().hasValueGreaterThan(set);
		case LOWER_OR_IN_RANGE_BUT_NOT_EQUAL:
			return !getBridged().hasValueGreaterThan(set) && !getBridged().intersects(set);
		case NOT_EQUAL:
			return !getBridged().intersects(set);
		case OUT_OF_RANGE:
			return !getBridged().hasValueInRange(set);
		case OUT_OF_RANGE_OR_EQUAL:
			final List<? extends IOrderedSet<Set>> splited = getBridged().splitOnRange(set);
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
			return !getBridged().isEmpty()&&!set.isEmpty();
		case EMPTY:
			return getBridged().isEmpty()||set.isEmpty();
		case EQUAL:
			return getBridged().intersects(set);
		case GREATER:
			return getBridged().hasValueGreaterThan(set);
		case GREATER_OR_EQUAL:
			return getBridged().hasValueGreaterThan(set)||getBridged().intersects(set);
		case GREATER_OR_IN_RANGE:
			return getBridged().hasValueGreaterThan(set)||getBridged().hasValueInRange(set);
		case GREATER_OR_IN_RANGE_BUT_NOT_EQUAL:
		{
			final List<? extends IOrderedSet<Set>> splited = getBridged().splitOnRange(set);
			if(!splited.get(2).isEmpty())
				return true;
			if(!splited.get(1).isContainedBy(set))
				return true;
			return false;
		}
		case IN_RANGE:
			return getBridged().hasValueInRange(set);
		case IN_RANGE_BUT_NOT_EQUAL:
		{
			final List<? extends IOrderedSet<Set>> splited = getBridged().splitOnRange(set);
			if(!splited.get(1).isContainedBy(set))
				return true;
			return false;
		}
		case LOWER:
			return getBridged().hasValueLowerThan(set);
		case LOWER_OR_EQUAL:
			return getBridged().hasValueLowerThan(set)||getBridged().intersects(set);
		case LOWER_OR_IN_RANGE:
			return getBridged().hasValueLowerThan(set)||getBridged().hasValueInRange(set);
		case LOWER_OR_IN_RANGE_BUT_NOT_EQUAL:
		{
			final List<? extends IOrderedSet<Set>> splited = getBridged().splitOnRange(set);
			if(!splited.get(0).isEmpty())
				return true;
			if(!splited.get(1).isContainedBy(set))
				return true;
			return false;
		}
		case NOT_EQUAL:
			return !getBridged().isContainedBy(set);
		case OUT_OF_RANGE:
			return getBridged().hasValueLowerThan(set)||getBridged().hasValueGreaterThan(set);
		case OUT_OF_RANGE_OR_EQUAL:
			return getBridged().hasValueLowerThan(set)||getBridged().hasValueGreaterThan(set)||getBridged().intersects(set);
		default:
			throw new UnsupportedOperationException("SetCoveringMask value of " + mask + " is unknown.");
		}
	}
}