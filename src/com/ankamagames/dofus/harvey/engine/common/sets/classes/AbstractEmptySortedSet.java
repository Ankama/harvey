/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IEmptySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractEmptySortedSet
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Interval extends IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>,
	EmptySet extends IEmptySortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet>
>
extends AbstractEmptySet<Set, SimpleSet, ElementarySet, EmptySet>
implements IEmptySortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet>
{

	@Override
	public boolean isInterval()
	{
		return true;
	}

	@Override
	public Iterator<? extends IEmptySortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet>> iterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public Iterator<? extends IEmptySortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet>> reverseIterator()
	{
		return EmptyIterator.getInstance();
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
		return is(mask, set);
	}

	@Override
	public boolean isInRange(final Set set)
	{
		return true;
	}

	@Override
	public boolean hasValueInRange(final Set set)
	{
		return false;
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
	public List<? extends Set> splitOnRange(final Set set)
	{
		final ArrayList<Set> r = new ArrayList<Set>(3);
		r.add(getAsSet());
		r.add(getAsSet());
		r.add(getAsSet());
		return r;
	}

	@Override
	public boolean isSucceeding(final Set set)
	{
		return false;
	}

	@Override
	public boolean isPreceding(final Set set)
	{
		return false;
	}

	@Override
	public boolean hasContiguityWith(final Set set)
	{
		return false;
	}

	@Override
	public Iterator<Bound> getBoundIterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public int getBoundCount()
	{
		return 0;
	}
}