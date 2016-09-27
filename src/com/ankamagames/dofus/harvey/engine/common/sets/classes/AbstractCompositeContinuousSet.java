/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.Iterator;
import java.util.SortedSet;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementaryContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractCompositeContinuousSet
<
	Bound extends IContinuousBound<Bound>,
	Set extends IContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	CompositeSet extends ICompositeContinuousSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ?>,
	ChildType extends IContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	InternalChildrenCollectionType extends SortedSet<ChildType>
>
extends AbstractCompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType, InternalChildrenCollectionType>
implements ICompositeContinuousSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType>
{

	@Override
	protected abstract AbstractCompositeContinuousBoundBridge<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType, ? extends AbstractCompositeContinuousSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType, InternalChildrenCollectionType>> getBoundBridge();

	@Override
	public ISimpleContinuousSet<Bound, Set, SimpleSet, ElementarySet> getSimpleSet()
	{
		return super.getSimpleSet().getAsSimpleSet();
	}

	@Override
	public boolean isLowerBoundClosed()
	{
		if(getChildren().isEmpty())
			return false;
		return getChildren().first().isLowerBoundClosed();
	}

	@Override
	public boolean isUpperBoundClosed()
	{
		if(getChildrenDescending().isEmpty())
			return false;
		return getChildrenDescending().first().isUpperBoundClosed();
	}

	@Override
	public boolean isLowerBoundInfinity()
	{
		if(getChildren().isEmpty())
			return false;
		return getChildren().first().isLowerBoundInfinity();
	}

	@Override
	public boolean isUpperBoundInfinity()
	{
		if(getChildrenDescending().isEmpty())
			return false;
		return getChildrenDescending().first().isUpperBoundInfinity();
	}

	@Override
	public double size()
	{
		if(isEmpty())
			return 0;
		double r = 0;
		final Iterator<Bound> boundIterator = getBoundIterator();
		while(boundIterator.hasNext())
		{
			final Bound lowerBound = boundIterator.next();
			final Bound upperBound = boundIterator.next();
			r += upperBound.compareToContinuous(lowerBound);
		}
		return r;
	}
}