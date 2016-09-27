/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementaryContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleContinuousSet;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparator;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.interfaces.IIContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongBound;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class ContinuousLongBoundComparisonToolbox
<
	Set extends IIContinuousLongSet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<IContinuousLongBound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<IContinuousLongBound, Set, SimpleSet, ElementarySet>,
	Bridged extends IIContinuousLongSet<Set, SimpleSet, ElementarySet>
>
extends LongBoundComparisonToolbox<IContinuousLongBound, Set, SimpleSet, ElementarySet, Bridged>
{
	public ContinuousLongBoundComparisonToolbox(final Bridged bridged) {
		super(bridged);
	}

	@Override
	public boolean areLowerBoundsEqual(final Set set)
	{
		return (_bridged.isLowerBoundInfinity() && set.isLowerBoundInfinity()) ||
				(super.areLowerBoundsEqual(set) && (_bridged.isLowerBoundClosed() == set.isLowerBoundClosed()));
	}

	@Override
	public boolean areUpperBoundsEqual(final Set set)
	{
		return (_bridged.isUpperBoundInfinity() && set.isUpperBoundInfinity()) ||
				super.areUpperBoundsEqual(set) && (_bridged.isUpperBoundClosed() == set.isUpperBoundClosed());
	}

	@Override
	public boolean lowerBoundEqualsUpperBound(final Set set)
	{
		return _bridged.isLowerBoundClosed() &&  set.isUpperBoundClosed() &&
				super.lowerBoundEqualsUpperBound(set);
	}

	@Override
	public boolean upperBoundEqualsLowerBound(final Set set)
	{
		return _bridged.isUpperBoundClosed() && set.isLowerBoundClosed() &&
				super.upperBoundEqualsLowerBound(set);
	}

	@Override
	public boolean isLowerBoundLowerThanLowerBound(final Set set)
	{
		return !set.isLowerBoundInfinity() &&
				(_bridged.isLowerBoundInfinity() ||
				super.isLowerBoundLowerThanLowerBound(set) ||
				(_bridged.isLowerBoundClosed() && !set.isLowerBoundClosed() && super.areLowerBoundsEqual(set)));
	}

//	public boolean isLowerBoundLowerThanUpperBound(final Set set)

	@Override
	public boolean isLowerBoundGreaterThanLowerBound(final Set set)
	{
		return !_bridged.isLowerBoundInfinity() &&
				(set.isLowerBoundInfinity() ||
				super.isLowerBoundGreaterThanLowerBound(set) ||
				(!_bridged.isLowerBoundClosed() && set.isLowerBoundClosed() && super.areLowerBoundsEqual(set)));
	}

	@Override
	public boolean isLowerBoundGreaterThanUpperBound(final Set set)
	{
		return !_bridged.isLowerBoundInfinity() &&
				!set.isUpperBoundInfinity() &&
				super.isLowerBoundGreaterThanUpperBound(set) ||
				((_bridged.isLowerBoundClosed() != set.isUpperBoundClosed()) && super.lowerBoundEqualsUpperBound(set));
	}

	@Override
	public boolean isUpperBoundLowerThanLowerBound(final Set set)
	{
		return !_bridged.isUpperBoundInfinity() &&
				!set.isLowerBoundInfinity() &&
				super.isUpperBoundLowerThanLowerBound(set) ||
				((_bridged.isUpperBoundClosed() != set.isUpperBoundClosed()) && super.upperBoundEqualsLowerBound(set));
	}

	@Override
	public boolean isUpperBoundLowerThanUpperBound(final Set set)
	{
		return !_bridged.isUpperBoundInfinity() &&
				(set.isUpperBoundInfinity() ||
				super.isUpperBoundLowerThanUpperBound(set) ||
				(!_bridged.isUpperBoundClosed() && set.isUpperBoundClosed() && super.areUpperBoundsEqual(set)));
	}

//	public boolean isUpperBoundGreaterThanLowerBound(final Set set)

	@Override
	public boolean isUpperBoundGreaterThanUpperBound(final Set set)
	{
		return !set.isUpperBoundInfinity() &&
				(_bridged.isUpperBoundInfinity() ||
				super.isUpperBoundGreaterThanUpperBound(set) ||
				(_bridged.isUpperBoundClosed() && !set.isUpperBoundClosed() && super.areUpperBoundsEqual(set)));
	}
}