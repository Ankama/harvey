/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.ArrayList;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.BoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ISortedSetCreationToolbox;
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
public abstract class AbstractDegenerateSortedSetBridge
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Interval extends IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>,
	DegenerateSet extends IDegenerateSortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet>,
	BridgedSet extends IDegenerateSortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet>
>
extends AbstractDegenerateSetBridge<Set, SimpleSet, ElementarySet, DegenerateSet, BridgedSet>
{
	@Override
	protected abstract IEqualityToolbox<Set, SimpleSet, ElementarySet, BridgedSet> getEqualityToolbox();

	@Override
	protected abstract ISortedSetCreationToolbox<Bound, Set, SimpleSet, ElementarySet, Interval, ?, BridgedSet> getSetCreationToolbox();

	protected abstract BoundComparisonToolbox<Bound, Set, SimpleSet, ElementarySet, BridgedSet> getBoundComparisonToolbox();

	public AbstractDegenerateSortedSetBridge(final BridgedSet bridged)
	{
		super(bridged);
	}

	public boolean isInRange(final Set set)
	{
		return getBoundComparisonToolbox().isLowerBoundInRangeOf(set);
	}

	public boolean isGreaterThan(final Set set)
	{
		return getBoundComparisonToolbox().isLowerBoundGreaterThanUpperBound(set);
	}

	public boolean isGreaterThanOrEqualTo(final Set set)
	{
		return isGreaterThan(set)||isContainedBy(set);
	}

	public boolean isLowerThan(final Set set)
	{
		return getBoundComparisonToolbox().isUpperBoundLowerThanLowerBound(set);
	}

	public boolean isLowerThanOrEqualTo(final Set set)
	{
		return isLowerThan(set)||isContainedBy(set);
	}

	@Override
	public Set unite(final Set set)
	{
		final Bound otherLowerBound = set.getLowerBound();
		final Bound otherUpperBound = set.getUpperBound();
		if(otherLowerBound == null || otherUpperBound == null)
			return _bridged.getAsSet();
		if(set.isDegenerate())
			if(getBoundComparisonToolbox().areLowerBoundsEqual(set))
				return _bridged.getAsSet();
			else if(_bridged.isPreceding(set))
				return getSetCreationToolbox().makeInterval(_bridged.getLowerBound(), otherUpperBound).getAsSet();
			else if(_bridged.isSucceeding(set))
				return getSetCreationToolbox().makeInterval(otherLowerBound, _bridged.getUpperBound()).getAsSet();
			else if(set.isElementary())
				return getSetCreationToolbox().makeComposite(set).getAsSet();
			else
				return set.unite(_bridged.getAsSet()).getAsSet();
		if(isContainedBy(set))
			return set;
		if(set.isInterval())
			if(_bridged.isPreceding(set))
				return getSetCreationToolbox().makeInterval(_bridged.getLowerBound(), otherUpperBound).getAsSet();
			else if(_bridged.isSucceeding(set))
				return getSetCreationToolbox().makeInterval(otherLowerBound, _bridged.getUpperBound()).getAsSet();
			else if(set.isElementary()) {
				ISortedSetCreationToolbox<Bound, Set, SimpleSet, ElementarySet, Interval, ?, BridgedSet> setCreationToolbox = getSetCreationToolbox();
				Set makeComposite = setCreationToolbox.makeComposite(set);
				Set asSet = makeComposite.getAsSet();
				return asSet;
			}

		return set.unite(_bridged.getAsSet()).getAsSet();
	}

	public ArrayList<? extends Set> splitOnRange(final Set set)
	{
		if(set.isEmpty())
		{
			final ArrayList<Set> r = new ArrayList<Set>(3);
			r.add(set);
			r.add(set);
			r.add(set);
			return r;
		}

		final Set empty = getSetCreationToolbox().makeEmptySet().getAsSet();

		final ArrayList<Set> r = new ArrayList<Set>(3);
		if(isLowerThan(set))
		{
			r.add(_bridged.getAsSet());
			r.add(empty);
			r.add(empty);
			return r;
		}
		if(isGreaterThan(set))
		{
			r.add(empty);
			r.add(empty);
			r.add(_bridged.getAsSet());
			return r;
		}
		r.add(empty);
		r.add(_bridged.getAsSet());
		r.add(empty);
		return r;
	}
}