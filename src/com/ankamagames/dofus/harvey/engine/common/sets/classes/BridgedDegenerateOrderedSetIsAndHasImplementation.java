package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateOrderedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IOrderedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IOrderedSet.SetCoveringMask;


@NonNullByDefault
public class BridgedDegenerateOrderedSetIsAndHasImplementation<Set extends IOrderedSet<Set>, BridgedType extends IDegenerateOrderedSet<Set>>
{
	protected BridgedType _bridged;

	public BridgedDegenerateOrderedSetIsAndHasImplementation(final BridgedType bridged)
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
			return !set.isEmpty();
		case EMPTY:
			return false;
		case EQUAL:
			return getBridged().isContainedBy(set);
		case GREATER:
			return getBridged().isGreaterThan(set);
		case GREATER_OR_EQUAL:
			return getBridged().isGreaterThan(set)||getBridged().isContainedBy(set);
		case GREATER_OR_IN_RANGE:
			return !getBridged().isLowerThan(set);
		case GREATER_OR_IN_RANGE_BUT_NOT_EQUAL:
			return !getBridged().isLowerThan(set) && !getBridged().isContainedBy(set);
		case IN_RANGE:
			return getBridged().isInRange(set);
		case IN_RANGE_BUT_NOT_EQUAL:
			return getBridged().isInRange(set) && !getBridged().isContainedBy(set);
		case LOWER:
			return getBridged().isLowerThan(set);
		case LOWER_OR_EQUAL:
			return getBridged().isLowerThan(set)||getBridged().isContainedBy(set);
		case LOWER_OR_IN_RANGE:
			return !getBridged().isGreaterThan(set);
		case LOWER_OR_IN_RANGE_BUT_NOT_EQUAL:
			return !getBridged().isGreaterThan(set) && !getBridged().isContainedBy(set);
		case NOT_EQUAL:
			return !getBridged().isContainedBy(set);
		case OUT_OF_RANGE:
			return !getBridged().isInRange(set);
		case OUT_OF_RANGE_OR_EQUAL:
			return !getBridged().isInRange(set) || getBridged().isContainedBy(set);
		default:
			throw new UnsupportedOperationException("SetCoveringMask value of " + mask + " is unknown.");
		}
	}
	
	public boolean has(final SetCoveringMask mask, final Set set)
	{
		return is(mask, set);
	}
}