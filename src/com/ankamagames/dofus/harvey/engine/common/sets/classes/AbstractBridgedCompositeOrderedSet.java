/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.TreeSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IOrderedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IOrderedSet.SetCoveringMask;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractBridgedCompositeOrderedSet
<
	Set extends ISet<Set>,
	ChildType extends IOrderedSet<Set>,
	Bridged extends IOrderedSet<Set>
>
extends AbstractBridgedCompositeSet<Set, ChildType, TreeSet<ChildType>, Bridged>
{
	@Override
	public abstract TreeSet<ChildType> getChildren();
	
	public abstract TreeSet<ChildType> getChildrenDescending();

	public boolean is(final SetCoveringMask mask, final Set set)
	{
		for(final ChildType child:getChildren())
		{
			if(!child.is(mask, set))
				return false;
		}
		return true;
	}

	public boolean has(final SetCoveringMask mask, final Set set)
	{
		for(final ChildType child:getChildren())
		{
			if(child.has(mask, set))
				return true;
		}
		return false;
	}

	@Override
	public boolean isContainedBy(final Set set)
	{
		return is(SetCoveringMask.EQUAL, set);
	}

	public boolean isInRange(final Set set)
	{
		return is(SetCoveringMask.IN_RANGE, set);
	}

	public boolean hasValueInRange(final Set set)
	{
		return has(SetCoveringMask.IN_RANGE, set);
	}

	public boolean isGreaterThan(final Set set)
	{
		return is(SetCoveringMask.GREATER, set);
	}

	public boolean isGreaterThanOrEqualTo(final Set set)
	{
		return is(SetCoveringMask.GREATER_OR_EQUAL, set);
	}

	public boolean hasValueLowerThan(final Set set)
	{
		return has(SetCoveringMask.LOWER, set);
	}

	public boolean isLowerThan(final Set set)
	{
		return is(SetCoveringMask.LOWER, set);
	}

	public boolean isLowerThanOrEqualTo(final Set set)
	{
		return is(SetCoveringMask.LOWER_OR_EQUAL, set);
	}

	public boolean hasValueGreaterThan(final Set set)
	{
		return has(SetCoveringMask.GREATER, set);
	}
}