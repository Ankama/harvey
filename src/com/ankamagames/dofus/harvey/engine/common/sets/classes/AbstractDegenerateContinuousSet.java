package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.SingleValueIterator;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementaryContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public abstract class AbstractDegenerateContinuousSet
<
	Bound extends IContinuousBound<Bound>,
	Set extends IContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	Interval extends IContinuousInterval<Bound, Set, SimpleSet, ElementarySet, Interval>,
	DegenerateSet extends IDegenerateContinuousSet<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet>
>
extends AbstractDegenerateSortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet>
implements IDegenerateContinuousSet<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet>
{
	@Override
	public Iterator<DegenerateSet> iterator() {
		return new SingleValueIterator<DegenerateSet>(getAsDegenerateSet());
	}

	@Override
	public AbstractDegenerateContinuousSet<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet> getSimpleSet() {
		return this;
	}

	@Override
	public double size() {
		return 0;
	}

	@Override
	public boolean isLowerBoundInfinity()
	{
		return false;
	}

	@Override
	public boolean isUpperBoundInfinity()
	{
		return false;
	}

	@Override
	public boolean isLowerBoundClosed()
	{
		return true;
	}

	@Override
	public boolean isUpperBoundClosed()
	{
		return true;
	}
}