/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractSetBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class BoundComparisonToolbox
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Bridged extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>
>
extends AbstractSetBridge<Set, SimpleSet, ElementarySet, Bridged>
{
	public BoundComparisonToolbox(final Bridged bridged) {
		super(bridged);
	}

	public abstract int compareBound(final @Nullable Bound b1, final @Nullable Bound b2);

	public boolean areLowerBoundsEqual(final Set set)
	{
		final Bound lowerBound = _bridged.getLowerBound();
		final Bound setLowerBound = set.getLowerBound();
		if(lowerBound!=null &&  setLowerBound != null)
			return compareBound(lowerBound, setLowerBound) == 0;
		return setLowerBound==null?lowerBound==null:false;
	}

	public boolean areUpperBoundsEqual(final Set set)
	{
		final Bound upperBound = _bridged.getUpperBound();
		final Bound setUpperBound = set.getUpperBound();
		if(upperBound!=null && setUpperBound!=null)
			return compareBound(upperBound, setUpperBound)== 0;
		return setUpperBound==null?upperBound==null:false;
	}

	public boolean lowerBoundEqualsUpperBound(final Set set)
	{
		final Bound lowerBound = _bridged.getLowerBound();
		final Bound setUpperBound = set.getUpperBound();
		if(lowerBound!=null && setUpperBound!=null)
			return compareBound(lowerBound, setUpperBound)== 0;
		return setUpperBound==null?lowerBound==null:false;
	}

	public boolean upperBoundEqualsLowerBound(final Set set)
	{
		final Bound upperBound = _bridged.getUpperBound();
		final Bound setLowerBound = set.getLowerBound();
		if(upperBound!=null && setLowerBound!=null)
			return compareBound(upperBound, setLowerBound)== 0;
		return setLowerBound==null?upperBound==null:false;

	}

	public boolean isLowerBoundLowerThanLowerBound(final Set set)
	{
		final Bound lowerBound = _bridged.getLowerBound();
		final Bound setLowerBound = set.getLowerBound();
		if(lowerBound!=null && setLowerBound!=null)
			return compareBound(lowerBound, setLowerBound) < 0;
		return false;

	}

	public boolean isLowerBoundLowerThanUpperBound(final Set set)
	{
		final Bound lowerBound = _bridged.getLowerBound();
		final Bound setUpperBound = set.getUpperBound();
		if(lowerBound!=null && setUpperBound!=null)
			return compareBound(lowerBound, setUpperBound) < 0;
		return false;
	}

	public boolean isLowerBoundGreaterThanLowerBound(final Set set)
	{
		final Bound lowerBound = _bridged.getLowerBound();
		final Bound setLowerBound = set.getLowerBound();
		if(lowerBound!=null && setLowerBound!=null)
			return compareBound(lowerBound, setLowerBound) > 0;
		return false;
	}

	public boolean isLowerBoundGreaterThanUpperBound(final Set set)
	{
		final Bound lowerBound = _bridged.getLowerBound();
		final Bound setUpperBound = set.getUpperBound();
		if(lowerBound!=null && setUpperBound!=null)
			return compareBound(lowerBound, setUpperBound) > 0;
		return false;
	}

	public boolean isUpperBoundLowerThanLowerBound(final Set set)
	{
		final Bound upperBound = _bridged.getUpperBound();
		final Bound setLowerBound = set.getLowerBound();
		if(upperBound!=null && setLowerBound!=null)
			return compareBound(upperBound, setLowerBound) < 0;
		return false;
	}

	public boolean isUpperBoundLowerThanUpperBound(final Set set)
	{
		final Bound upperBound = _bridged.getUpperBound();
		final Bound setUpperBound = set.getUpperBound();
		if(upperBound!=null && setUpperBound!=null)
			return compareBound(upperBound, setUpperBound) < 0;
		return false;
	}

	public boolean isUpperBoundGreaterThanLowerBound(final Set set)
	{
		final Bound upperBound = _bridged.getUpperBound();
		final Bound setLowerBound = set.getLowerBound();
		if(upperBound!=null && setLowerBound!=null)
			return compareBound(upperBound, setLowerBound) > 0;
		return false;
	}

	public boolean isUpperBoundGreaterThanUpperBound(final Set set)
	{
		final Bound upperBound = _bridged.getUpperBound();
		final Bound setUpperBound = set.getUpperBound();
		if(upperBound!=null && setUpperBound!=null)
			return compareBound(upperBound, setUpperBound) > 0;
		return false;
	}

	public boolean isLowerBoundInRangeOf(final Set set)
	{
		return (isLowerBoundGreaterThanLowerBound(set)||areLowerBoundsEqual(set)) && ((isLowerBoundLowerThanUpperBound(set)) || (lowerBoundEqualsUpperBound(set)));
	}

	public boolean isUpperBoundInRangeOf(final Set set)
	{
		return (isUpperBoundGreaterThanLowerBound(set)||upperBoundEqualsLowerBound(set)) && ((isUpperBoundLowerThanUpperBound(set)) || (areUpperBoundsEqual(set)));
	}

	public boolean isAnyBoundInRangeOf(final Set set)
	{
		return isLowerBoundInRangeOf(set) || isUpperBoundInRangeOf(set);
	}

	public boolean areBothBoundsInRangeOf(final Set set)
	{
		return (isLowerBoundGreaterThanLowerBound(set)||areLowerBoundsEqual(set)) && ((isUpperBoundLowerThanUpperBound(set)) || (areUpperBoundsEqual(set)));
	}

	public boolean containsLowerBound(final Set set)
	{
		return (isLowerBoundLowerThanLowerBound(set) || areLowerBoundsEqual(set)) && (isUpperBoundGreaterThanLowerBound(set) || upperBoundEqualsLowerBound(set));
	}

	public boolean containsUpperBound(final Set set)
	{
		return (isLowerBoundLowerThanUpperBound(set) || lowerBoundEqualsUpperBound(set)) && (isUpperBoundGreaterThanUpperBound(set) || areUpperBoundsEqual(set));
	}

	public boolean containsAnyBound(final Set set)
	{
		return containsLowerBound(set) || containsUpperBound(set);
	}

	public boolean containsBothBounds(final Set set)
	{
		return (isLowerBoundLowerThanLowerBound(set) || areLowerBoundsEqual(set)) &&
				(isUpperBoundGreaterThanUpperBound(set) || areUpperBoundsEqual(set));
	}
}