/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IOrderedSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractOrderedEmpty<Set extends IOrderedSet<Set>>
	extends AbstractEmpty<Set>
	implements IInterval<Set>
{
	protected AbstractOrderedEmpty()
	{
	}
	
	@Override
	public boolean isInterval()
	{
		return true;
	}

	@Override
	public boolean isInRange(final Set set)
	{
		return false;
	}

	@Override
	public boolean hasValueInRange(final Set set)
	{
		return false;
	}
	
	@Override
	public boolean containsAllValuesInRange(final Set set)
	{
		return set.isEmpty();
	}

	@Override
	public boolean isGreaterThan(final Set set)
	{
		return false;
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final Set set)
	{
		return set.isEmpty();
	}

	@Override
	public boolean hasValueLowerThan(final Set set)
	{
		return false;
	}

	@Override
	public boolean isLowerThan(final Set set)
	{
		return false;
	}

	@Override
	public boolean isLowerThanOrEqualTo(final Set set)
	{
		return set.isEmpty();
	}

	@Override
	public boolean hasValueGreaterThan(final Set set)
	{
		return false;
	}

	@Override
	public boolean is(final SetCoveringMask mask, final Set set)
	{
		if(mask == SetCoveringMask.EMPTY)
			return true;
		if((mask.getMask() & SetCoveringMask.EQUAL.getMask()) != 0)
			return set.isEmpty();
		return false;
	}

	@Override
	public boolean has(final SetCoveringMask mask, final Set set)
	{
		if(mask == SetCoveringMask.EMPTY)
			return true;
		if((mask.getMask() & SetCoveringMask.EQUAL.getMask()) != 0)
			return set.isEmpty();
		return false;
	}
	
	@Override
	public List<? extends Set> splitOnRange(final Set set)
	{
		final ArrayList<Set> r = new ArrayList<Set>(3);
		r.add(getThis());
		r.add(getThis());
		r.add(getThis());
		return r;
	}
}