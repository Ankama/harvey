/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.toolbox;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.BoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.interfaces.IDoubleBound;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IElementaryDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.ISimpleDoubleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class DoubleBoundComparisonToolbox<Bridged extends IDoubleSet>
extends BoundComparisonToolbox<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, Bridged>
{

	public DoubleBoundComparisonToolbox(final Bridged bridged) {
		super(bridged);
	}

	@Override
	public int compareBound(final @Nullable IDoubleBound b1, final @Nullable IDoubleBound b2)
	{
		if(b1 == null )
		{
			if(b2 == null)
				return 0;
			return 1;
		}
		else
		{
			if(b2 == null)
				return -1;

			if(b1.getValue() > b2.getValue())
				return 1;
			else if( b1.getValue() == b2.getValue())
				return 0;
			else
				return -1;
		}
	}

	@Override
	public boolean areLowerBoundsEqual(final IDoubleSet set)
	{
		return (_bridged.isLowerBoundInfinity() && set.isLowerBoundInfinity()) ||
				(super.areLowerBoundsEqual(set) && (_bridged.isLowerBoundClosed() == set.isLowerBoundClosed()));
	}

	@Override
	public boolean areUpperBoundsEqual(final IDoubleSet set)
	{
		return (_bridged.isUpperBoundInfinity() && set.isUpperBoundInfinity()) ||
				super.areUpperBoundsEqual(set) && (_bridged.isUpperBoundClosed() == set.isUpperBoundClosed());
	}

	@Override
	public boolean lowerBoundEqualsUpperBound(final IDoubleSet set)
	{
		return _bridged.isLowerBoundClosed() &&  set.isUpperBoundClosed() &&
				super.lowerBoundEqualsUpperBound(set);
	}

	@Override
	public boolean upperBoundEqualsLowerBound(final IDoubleSet set)
	{
		return _bridged.isUpperBoundClosed() && set.isLowerBoundClosed() &&
				super.upperBoundEqualsLowerBound(set);
	}

	@Override
	public boolean isLowerBoundLowerThanLowerBound(final IDoubleSet set)
	{
		return !set.isLowerBoundInfinity() &&
				(_bridged.isLowerBoundInfinity() ||
				super.isLowerBoundLowerThanLowerBound(set) ||
				(_bridged.isLowerBoundClosed() && !set.isLowerBoundClosed() && super.areLowerBoundsEqual(set)));
	}

//	public boolean isLowerBoundLowerThanUpperBound(final Set set)

	@Override
	public boolean isLowerBoundGreaterThanLowerBound(final IDoubleSet set)
	{
		return !_bridged.isLowerBoundInfinity() &&
				(set.isLowerBoundInfinity() ||
				super.isLowerBoundGreaterThanLowerBound(set) ||
				(!_bridged.isLowerBoundClosed() && set.isLowerBoundClosed() && super.areLowerBoundsEqual(set)));
	}

	@Override
	public boolean isLowerBoundGreaterThanUpperBound(final IDoubleSet set)
	{
		return !_bridged.isLowerBoundInfinity() &&
				!set.isUpperBoundInfinity() &&
				super.isLowerBoundGreaterThanUpperBound(set) ||
				((_bridged.isLowerBoundClosed() != set.isUpperBoundClosed()) && super.lowerBoundEqualsUpperBound(set));
	}

	@Override
	public boolean isUpperBoundLowerThanLowerBound(final IDoubleSet set)
	{
		return !_bridged.isUpperBoundInfinity() &&
				!set.isLowerBoundInfinity() &&
				super.isUpperBoundLowerThanLowerBound(set) ||
				((_bridged.isUpperBoundClosed() != set.isUpperBoundClosed()) && super.upperBoundEqualsLowerBound(set));
	}

	@Override
	public boolean isUpperBoundLowerThanUpperBound(final IDoubleSet set)
	{
		return !_bridged.isUpperBoundInfinity() &&
				(set.isUpperBoundInfinity() ||
				super.isUpperBoundLowerThanUpperBound(set) ||
				(!_bridged.isUpperBoundClosed() && set.isUpperBoundClosed() && super.areUpperBoundsEqual(set)));
	}

//	public boolean isUpperBoundGreaterThanLowerBound(final Set set)

	@Override
	public boolean isUpperBoundGreaterThanUpperBound(final IDoubleSet set)
	{
		return !set.isUpperBoundInfinity() &&
				(_bridged.isUpperBoundInfinity() ||
				super.isUpperBoundGreaterThanUpperBound(set) ||
				(_bridged.isUpperBoundClosed() && !set.isUpperBoundClosed() && super.areUpperBoundsEqual(set)));
	}

}
