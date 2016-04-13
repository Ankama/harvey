/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.ArrayList;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateOrderedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IOrderedSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractDegenerateOrderedSet<Set extends IOrderedSet<Set>>
	extends AbstractDegenerateSet<Set>
	implements IDegenerateOrderedSet<Set>
{
	@Override
	public boolean isInterval()
	{
		return true;
	}
	
	@Override
	public abstract boolean is(SetCoveringMask mask, Set set);

	@Override
	public boolean has(final SetCoveringMask mask, final Set set)
	{
		return is(mask, set);
	}

	@Override
	public abstract boolean isInRange(Set set);

	@Override
	public boolean hasValueInRange(final Set set)
	{
		return isInRange(set);
	}
	
	@Override
	public boolean containsAllValuesInRange(final Set set)
	{
		return set.isDegenerate()&&equals(set);
	}

	@Override
	public abstract boolean isGreaterThan(Set set);

	@Override
	public abstract boolean isGreaterThanOrEqualTo(Set set);

	@Override
	public boolean hasValueLowerThan(final Set set)
	{
		return isLowerThan(set);
	}

	@Override
	public abstract boolean isLowerThan(Set set);

	@Override
	public abstract boolean isLowerThanOrEqualTo(Set set);

	@Override
	public boolean hasValueGreaterThan(final Set set)
	{
		return isGreaterThan(set);
	}

	protected abstract Set getEmpty();
	
	@Override
	public ArrayList<? extends Set> splitOnRange(final Set set)
	{
		final Set empty = getEmpty();
		
		final ArrayList<Set> r = new ArrayList<Set>(3);
		if(isLowerThan(set))
		{
			r.add(getThis());
			r.add(empty);
			r.add(empty);
			return r;
		}
		if(isGreaterThan(set))
		{
			r.add(empty);
			r.add(empty);
			r.add(getThis());
			return r;
		}
		r.add(empty);
		r.add(getThis());
		r.add(empty);
		return r;
	}
}