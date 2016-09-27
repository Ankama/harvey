/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.ArrayList;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.SingleValueIterator;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractDegenerateSortedSet
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Interval extends IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>,
	DegenerateSet extends IDegenerateSortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet>
>
extends AbstractDegenerateIsAndHasSet<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet>
implements IDegenerateSortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet>
{
	@Override
	protected abstract AbstractDegenerateSortedSetBridge<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet, ? extends AbstractDegenerateSortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet>> getBridge();

	protected abstract AbstractDegenerateBoundBridge<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet, ? extends AbstractDegenerateSortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet>> getBoundBridge();

	@Override
	public boolean isInterval()
	{
		return true;
	}

	@Override
	public boolean isInRange(final Set set)
	{
		return getBridge().isInRange(set);
	}

	@Override
	public boolean hasValueInRange(final Set set)
	{
		return isInRange(set);
	}

	@Override
	public boolean isGreaterThan(final Set set)
	{
		return getBridge().isGreaterThan(set);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final Set set)
	{
		return getBridge().isGreaterThanOrEqualTo(set);
	}

	@Override
	public boolean hasValueLowerThan(final Set set)
	{
		return isLowerThan(set);
	}

	@Override
	public boolean isLowerThan(final Set set)
	{
		return getBridge().isLowerThan(set);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final Set set)
	{
		return getBridge().isLowerThanOrEqualTo(set);
	}

	@Override
	public boolean hasValueGreaterThan(final Set set)
	{
		return isGreaterThan(set);
	}

	@Override
	public Iterator<DegenerateSet> reverseIterator()
	{
		return new SingleValueIterator<DegenerateSet>(getAsDegenerateSet());
	}

	@Override
	public ArrayList<? extends Set> splitOnRange(final Set set)
	{
		return getBridge().splitOnRange(set);
	}

	@Override
	public boolean hasContiguityWith(final Set set)
	{
		if(set.isEmpty())
			return false;
		if(set.isDegenerate() || set.isInterval())
			return isPreceding(set) || isSucceeding(set);
		return set.hasContiguityWith(getAsSet());
	}

	@Override
	public Iterator<Bound> getBoundIterator()
	{
		return getBoundBridge().getBoundIterator();
	}

	@Override
	public int getBoundCount()
	{
		return getBoundBridge().getBoundCount();
	}
}