/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementaryContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleContinuousSet;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparator;
import com.ankamagames.dofus.harvey.engine.generic.sets.interfaces.IIContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class ContinuousGenericBoundComparisonToolbox
<
	Data,
	Set extends IIContinuousGenericSet<Data, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<IContinuousGenericBound<Data>, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<IContinuousGenericBound<Data>, Set, SimpleSet, ElementarySet>,
	Bridged extends IIContinuousGenericSet<Data, Set, SimpleSet, ElementarySet>
>
extends GenericBoundComparisonToolbox<Data, IContinuousGenericBound<Data>, Set, SimpleSet, ElementarySet, Bridged>
{
	public ContinuousGenericBoundComparisonToolbox(final Bridged bridged, final ContinuousComparator<? super Data> comparator) {
		super(bridged, new Comparator<Data>()
			{
				@Override
				public int compare(@Nullable final Data o1, @Nullable final Data o2)
				{
					return (int)Math.signum(comparator.compareContinuous(o1, o2));
				}
			});
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