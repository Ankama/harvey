/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.BoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IEmptySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractIntervalBridge
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Interval extends IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>,
	EmptySet extends IEmptySortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet>,
	Bridged extends IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>
>
extends AbstractSetBridge<Set, SimpleSet, ElementarySet, Bridged>
{
	private static final int DEFAULT_NUMBER_OF_PARTS = 2;

	public AbstractIntervalBridge(final Bridged bridged)
	{
		super(bridged);
	}
	abstract protected BoundComparisonToolbox<Bound, Set, SimpleSet, ElementarySet, Bridged> getBoundComparisonToolbox();

	abstract protected IIntervalCreationToolbox<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet, Bridged> getSetCreationToolbox();

	abstract protected IEqualityToolbox<Set, SimpleSet, ElementarySet, Bridged> getEqualityToolbox();

	abstract protected SplitOnRangeBridge<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet, Bridged> getSplitBridge();


	public boolean isEqual(@Nullable final Object obj)
	{
		if(!(obj instanceof ISortedSet))
		{
			if(!isDegenerate())
				return false;
			return getEqualityToolbox().equalsValue(obj);
		}

		final ISortedSet<Bound, ?, ?, ?> set = (ISortedSet<Bound, ?, ?, ?>)obj;
		if(isEmpty())
			return set.isEmpty();
		if(set.isEmpty())
			return false;
		if(isDegenerate() != set.isDegenerate())
			return false;
		if(!set.isInterval())
			return false;
		try
		{
			@SuppressWarnings("unchecked")
			final
			Set sameTypeSet = (Set)set;
			return getBoundComparisonToolbox().areLowerBoundsEqual(sameTypeSet)&&getBoundComparisonToolbox().areUpperBoundsEqual(sameTypeSet);
		}
		catch(final ClassCastException e)
		{
			return false;
		}
	}

	public boolean isEqual(final Set set) {
		if(set.isEmpty())
			return isEmpty();
		if(isEmpty())
			return false;
		if(set.isDegenerate())
		{
			return getBoundComparisonToolbox().areBothBoundsInRangeOf(set);
		}
		if(set.isInterval())
		{
			return (getBoundComparisonToolbox().areLowerBoundsEqual(set)&&
					getBoundComparisonToolbox().areUpperBoundsEqual(set));
		}
		return false;
	}


	public boolean isEmpty()
	{
		return getBoundComparisonToolbox().isLowerBoundGreaterThanUpperBound(_bridged.getAsSet());
	}


	public boolean isDegenerate()
	{
		return getBoundComparisonToolbox().lowerBoundEqualsUpperBound(_bridged.getAsSet());
	}


	public boolean contains(final Set set)
	{
		if(set.isEmpty())
			return true;
		if(isEmpty())
			return false;
		return getBoundComparisonToolbox().containsBothBounds(set);
	}


	public boolean isContainedBy(final Set set)
	{
		if(isEmpty())
			return true;
		if(set.isEmpty())
			return false;
		if(set.isDegenerate())
			return isDegenerate() && getBoundComparisonToolbox().areLowerBoundsEqual(set);
		if(set.isInterval())
			return getBoundComparisonToolbox().areBothBoundsInRangeOf(set);
		return set.contains(_bridged.getAsSet());
	}


	public boolean isIntersecting(final Set set)
	{
		if(isEmpty())
			return false;

		final int setBoundCount = set.getBoundCount();
		if(setBoundCount<=2)
		{

			final Bound lowerBound = set.getLowerBound();
			if(lowerBound == null)
				return false;
			if(lowerBound.compareTo(_bridged.getLowerBound())>=0 && lowerBound.compareTo(_bridged.getUpperBound())<=0)
				return true;
			else
			{
				if(set.isDegenerate())
					return false;
			}
			final Bound upperBound = set.getUpperBound();
			if(upperBound == null)
				throw new NullPointerException();// should never come here is set is empty
			if(upperBound.compareTo(_bridged.getLowerBound())>=0 && upperBound.compareTo(_bridged.getUpperBound())<=0)
				return true;
			return false;
		}



//		if(getBoundComparisonToolbox().containsAnyBound(set))
//			return true;
//
//		if(set.isDegenerate())
//			return false;

		return set.isIntersecting(_bridged.getAsSet());
	}

	public boolean isInRange(final Set set)
	{
		if(isEmpty())
			return true;
		if(set.isEmpty())
			return false;
		return getBoundComparisonToolbox().areBothBoundsInRangeOf(set);
	}

	public boolean hasValueInRange(final Set set)
	{
		if(set.isEmpty() || isEmpty())
			return false;
		if(getBoundComparisonToolbox().containsLowerBound(set))
			return true;
		if(set.isDegenerate())
			return false;
		if(getBoundComparisonToolbox().containsUpperBound(set))
			return true;
		return (getBoundComparisonToolbox().isLowerBoundGreaterThanLowerBound(set))&&
				(getBoundComparisonToolbox().isUpperBoundLowerThanUpperBound(set));
	}


	public boolean isGreaterThan(final Set set)
	{
		return getBoundComparisonToolbox().isLowerBoundGreaterThanUpperBound(set);
	}


	public boolean isGreaterThanOrEqualTo(final Set set)
	{
		final Bound otherUpperBound = set.getUpperBound();
		if(otherUpperBound == null)
			return isEmpty();
		final Bound lowerBound = _bridged.getLowerBound();
		if(lowerBound == null)
			return false;
		final BoundComparisonToolbox<Bound, Set, SimpleSet, ElementarySet, Bridged> boundComparisonToolbox = getBoundComparisonToolbox();
		if(set.isDegenerate()) {
			return (boundComparisonToolbox.isLowerBoundGreaterThanUpperBound(set) ||
					boundComparisonToolbox.lowerBoundEqualsUpperBound(set));
		}
		if(hasValueLowerThan(set))
			return false;
		if(boundComparisonToolbox.isUpperBoundLowerThanUpperBound(set) || boundComparisonToolbox.areUpperBoundsEqual(set))
			return isContainedBy(set);
		if(boundComparisonToolbox.isLowerBoundGreaterThanUpperBound(set))
			return true;
		final Interval inRange = getSetCreationToolbox().makeInterval(lowerBound, otherUpperBound);
		return inRange.isContainedBy(set);
	}


	public boolean hasValueLowerThan(final Set set)
	{
		return getBoundComparisonToolbox().isLowerBoundLowerThanLowerBound(set);
	}


	public boolean isLowerThan(final Set set)
	{
		return getBoundComparisonToolbox().isUpperBoundLowerThanLowerBound(set);
	}


	public boolean isLowerThanOrEqualTo(final Set set)
	{
		if(set.isEmpty())
			return isEmpty();
		if(isEmpty())
			return false;
		if(set.isDegenerate())
			return (getBoundComparisonToolbox().isUpperBoundLowerThanLowerBound(set) ||
					getBoundComparisonToolbox().upperBoundEqualsLowerBound(set));
		if(hasValueGreaterThan(set))
			return false;
		final List<? extends Set> splitOnRange = splitOnRange(set);
		return splitOnRange.get(1).isContainedBy(set);
	}

	public boolean hasValueGreaterThan(final Set set)
	{
		return getBoundComparisonToolbox().isUpperBoundGreaterThanUpperBound(set);
	}

	public Iterator<? extends ElementarySet> iterator()
	{
		final List<? extends ElementarySet> parts = getSetCreationToolbox().split(DEFAULT_NUMBER_OF_PARTS);
		return parts.iterator();
	}

	public Iterator<? extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>> reverseIterator()
	{
		final List<? extends ElementarySet> parts = getSetCreationToolbox().split(DEFAULT_NUMBER_OF_PARTS);
		Collections.reverse(parts);
		return parts.iterator();
	}

	public List<? extends Set> splitOnRange(final Set set)
	{
		return getSplitBridge().splitOnRange(set);
	}


	public ISortedSet<Bound, Set, SimpleSet, ElementarySet> unite(final Set set)
	{
		final Bound otherUpperBound = set.getUpperBound();
		final Bound otherLowerBound = set.getLowerBound();
		if(otherLowerBound == null || otherUpperBound == null || contains(set))	// if set.isEmpty() contains returns true
			return _bridged.getAsSet();
		final Bound lowerBound = _bridged.getLowerBound();
		final Bound upperBound = _bridged.getUpperBound();
		if(lowerBound == null || upperBound == null || isContainedBy(set))
			return set;
		if(set.isElementary())
		{
			if(set.isDegenerate())
			{
				if(isGreaterThan(set) && _bridged.isSucceeding(set))
						return getSetCreationToolbox().makeInterval(otherLowerBound, upperBound);
				if(isLowerThan(set)&& _bridged.isPreceding(set))
						return getSetCreationToolbox().makeInterval(lowerBound, otherUpperBound);

				final List<Set> tmp = new ArrayList<Set>(2);
				tmp.add(_bridged.getAsSet());
				tmp.add(set);
				return getSetCreationToolbox().makeComposite(tmp);
			}
			if(set.isInterval())
			{
				if((isGreaterThan(set) && _bridged.isSucceeding(set)) ||	(getBoundComparisonToolbox().isLowerBoundInRangeOf(set)))
					return getSetCreationToolbox().makeInterval(otherLowerBound, upperBound);
				if((isLowerThan(set)&& _bridged.isPreceding(set)) || (getBoundComparisonToolbox().isUpperBoundInRangeOf(set)))
					return getSetCreationToolbox().makeInterval(lowerBound, otherUpperBound);
				final List<Set> tmp = new ArrayList<Set>(2);
				tmp.add(_bridged.getAsSet());
				tmp.add(set);
				return getSetCreationToolbox().makeComposite(tmp);
			}
		}

		return set.unite(_bridged.getAsSet());
	}


	public ISortedSet<Bound, Set, SimpleSet, ElementarySet> intersect(final Set set)
	{
		if(contains(set))
			return set;
		if(isContainedBy(set))
			return _bridged;
		if(set.isDegenerate())
			return getSetCreationToolbox().makeEmptySet();
		if(set.isInterval())
		{

			final Bound otherUpperBound = set.getUpperBound();
			final Bound otherLowerBound = set.getLowerBound();
			if(otherLowerBound == null || otherUpperBound == null)	// if set.isEmpty() contains returns true
				return getSetCreationToolbox().makeEmptySet();
			final Bound lowerBound = _bridged.getLowerBound();
			final Bound upperBound = _bridged.getUpperBound();
			if(lowerBound == null || upperBound == null)
				return getSetCreationToolbox().makeEmptySet();
			if(hasValueGreaterThan(set) && hasValueInRange(set))
				return getSetCreationToolbox().makeInterval(lowerBound, otherUpperBound);
			if(hasValueLowerThan(set) && hasValueInRange(set))
				return getSetCreationToolbox().makeInterval(otherLowerBound, upperBound);
			return getSetCreationToolbox().makeEmptySet();
		}
		return set.intersect(_bridged.getAsSet());
	}

	public ISet<Set, SimpleSet, ElementarySet> subtract(final Set set)
	{
		if(set.isEmpty() || !isIntersecting(set))
			return _bridged.getAsSet();
		if(isContainedBy(set))
			return getSetCreationToolbox().makeEmptySet();
		if(set.isInterval())
		{
			if(!hasValueGreaterThan(set))
				return getSetCreationToolbox().makeIntervalFromLowerBounds(_bridged.getAsSet(), set);
			if(!hasValueLowerThan(set))
				return getSetCreationToolbox().makeIntervalFromUpperBounds(set, _bridged.getAsSet());
			//contains(set)
			final Interval lowerPart = getSetCreationToolbox().makeIntervalFromLowerBounds(_bridged.getAsSet(), set);
			final Interval upperPart = getSetCreationToolbox().makeIntervalFromUpperBounds(set, _bridged.getAsSet());
			return getSetCreationToolbox().makeSimpleCompositeSet(lowerPart.getAsElementarySet(), upperPart.getAsElementarySet());
		}

		Set currentSet = _bridged.getAsSet();
		final Iterator<? extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>> iterator = set.iterator();
		while(iterator.hasNext())
		{
			currentSet = currentSet.subtract(iterator.next().getAsSet()).getAsSet();
		}
		return currentSet;
	}

	public ISet<Set, SimpleSet, ElementarySet> symmetricSubtract(final Set set)
	{
		if(set.isEmpty())
			return _bridged.getAsSet();
		if(!isIntersecting(set))
			return unite(set);
		if(contains(set))
			return subtract(set);
		if(isContainedBy(set))
			return set.subtract(_bridged.getAsSet()).getAsSet();
		if(set.isInterval())
		{
			if(hasValueLowerThan(set))
			{
				final Interval lowerPart = getSetCreationToolbox().makeIntervalFromLowerBounds(_bridged.getAsSet(), set);
				final Interval upperPart = getSetCreationToolbox().makeIntervalFromUpperBounds(_bridged.getAsSet(), set);
				return getSetCreationToolbox().makeSimpleCompositeSet(lowerPart.getAsElementarySet(), upperPart.getAsElementarySet());
			}
			final Interval lowerPart = getSetCreationToolbox().makeIntervalFromLowerBounds(set, _bridged.getAsSet());
			final Interval upperPart = getSetCreationToolbox().makeIntervalFromUpperBounds(set, _bridged.getAsSet());
			return getSetCreationToolbox().makeSimpleCompositeSet(lowerPart.getAsElementarySet(), upperPart.getAsElementarySet());
		}
		return set.symmetricSubtract(_bridged.getAsSet());
	}
}