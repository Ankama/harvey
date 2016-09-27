/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractInterval
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Interval extends IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>
>
extends AbstractIsAndHasSet<Bound, Set, SimpleSet, ElementarySet>
implements IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>
{
	protected abstract AbstractIntervalBridge<Bound, Set, SimpleSet, ElementarySet, Interval, ?, ? extends IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>> getBridge();

	protected abstract AbstractIntervalBoundBridge<Bound, Set, SimpleSet, ElementarySet, Interval, ? extends IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>> getBoundBridge();

	@Override
	public boolean isElementary()
	{
		return true;
	}

	@Override
	public boolean isSimple()
	{
		return true;
	}

	@Override
	public boolean isInterval()
	{
		return true;
	}

	@Override
	public boolean isEmpty()
	{
		return getBridge().isEmpty();
	}

	@Override
	public boolean isDegenerate()
	{
		return getBridge().isDegenerate();
	}

	@Override
	public boolean equals(final @Nullable Object set)
	{
		return getBridge().isEqual(set);
	}

	@Override
	public boolean equals(final Set set)
	{
		return getBridge().isEqual(set);
	}

	@Override
	public boolean contains(final Set set)
	{
		return getBridge().contains(set);
	}

	@Override
	public boolean isContainedBy(final Set set)
	{
		return getBridge().isContainedBy(set);
	}

	@Override
	public boolean isIntersecting(final Set set)
	{
		return getBridge().isIntersecting(set);
	}

	@Override
	public Set unite(final Set set)
	{
		return getBridge().unite(set).getAsSet();
	}

	@Override
	public Set intersect(final Set set)
	{
		return getBridge().intersect(set).getAsSet();
	}

	@Override
	public Set subtract(final Set set)
	{
		return getBridge().subtract(set).getAsSet();
	}

	@Override
	public Set symmetricSubtract(final Set set)
	{
		return getBridge().symmetricSubtract(set).getAsSet();
	}

	@Override
	public Iterator<? extends ElementarySet> iterator()
	{
		return getBridge().iterator();
	}

	@Override
	public Iterator<? extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>> reverseIterator()
	{
		return getBridge().reverseIterator();
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

	@Override
	public boolean isInRange(final Set set)
{
		return getBridge().isInRange(set);
	}

	@Override
	public boolean hasValueInRange(final Set set)
{
		return getBridge().hasValueInRange(set);
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
		return getBridge().hasValueLowerThan(set);
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
		return getBridge().hasValueGreaterThan(set);
	}

	@Override
	public List<? extends Set> splitOnRange(final Set set)
	{
		return getBridge().splitOnRange(set);
	}

	@Override
	public boolean hasContiguityWith(final Set set)
	{
		if(isEmpty()||set.isEmpty())
			return false;
		if(set.isDegenerate() || set.isInterval())
			return isPreceding(set) || isSucceeding(set);
		return set.hasContiguityWith(getAsSet());
	}
}