/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractCompositeSortedSet
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	CompositeSet extends ICompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ?>,
	ChildType extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	InternalChildrenCollectionType extends SortedSet<ChildType>
>
extends AbstractCompositeSet<Set, SimpleSet, ElementarySet, CompositeSet, ChildType>
implements ICompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType>
{

	protected abstract AbstractCompositeBoundBridge<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType, ? extends AbstractCompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType, InternalChildrenCollectionType>> getBoundBridge();

	@Override
	protected abstract AbstractCompositeSortedSetBridge<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType, ? extends AbstractCompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType, InternalChildrenCollectionType>, InternalChildrenCollectionType> getBridge();

	protected IsAndHasBridge<Bound, Set, SimpleSet, ElementarySet, ICompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType>> _isAndHasBridge =
			new IsAndHasBridge<Bound, Set, SimpleSet, ElementarySet, ICompositeSortedSet<Bound, Set,SimpleSet,ElementarySet,CompositeSet,ChildType>>(this);

	@Override
	public double size()
	{
		if(isEmpty())
			return 0;
		int r = 0;
		final Iterator<Bound> boundIterator = getBoundIterator();
		while(boundIterator.hasNext())
		{
			final Bound lowerBound = boundIterator.next();
			final Bound upperBound = boundIterator.next();
			r += upperBound.compareTo(lowerBound)+1;
		}
		return r;
	}

	@Override
	protected int _checkSimpleTypesContains(final Set set)
	{
		final int r = super._checkSimpleTypesContains(set);
		if(r == -1)
			if(!set.isInRange(getAsSet()))
				return 0;
		return r;
	}

	@Override
	protected boolean _checkOtherTypeContains(final Set set)
	{
		final Iterator<Bound> boundIterator = getBoundIterator();
		boundIterator.next();
		boolean isHole = false;
		if(set.isInterval())
		{
			while(boundIterator.hasNext())
			{
				final Bound currentBound = boundIterator.next();
				if(isHole)
				{
					if(currentBound.compareTo(set.getLowerBound())>0)
						return false;
				}
				else
				{
					if(currentBound.compareTo(set.getUpperBound())>=0)
						return true;
				}
				isHole = !isHole;
			}
			return false;
		}

		final Iterator<Bound> otherBoundIterator = set.getBoundIterator();
		if(!otherBoundIterator.hasNext())
			return true;
		Bound lastOtherBound = otherBoundIterator.next();
		boolean otherIsHole = true;

		while(boundIterator.hasNext())
		{
			final Bound currentBound = boundIterator.next();
			if(isHole)
			{
				if(currentBound.compareTo(lastOtherBound)>0)
					return false;
			}
			else
			{
				while(lastOtherBound.compareTo(currentBound)<=0)
				{
					if(!otherBoundIterator.hasNext())
						return true;
					lastOtherBound = otherBoundIterator.next();
					otherIsHole = !otherIsHole;
				}
				if(!otherIsHole)
					return false;

			}
			isHole = !isHole;
		}
		return false;
	}

	@Override
	public boolean isIntersecting(final Set set)
	{
		if(isEmpty() || set.isEmpty())
			return false;

		final Iterator<Bound> currentBoundIt = getBoundIterator();
		Iterator<Bound> otherBoundIt = set.getBoundIterator();

		Bound currentLowerBound = currentBoundIt.next();
		Bound currentUpperBound = currentBoundIt.next();
		Bound otherLowerBound = otherBoundIt.next();
		Bound otherUpperBound = otherBoundIt.next();
		while(true)
		{
			if(otherUpperBound.compareTo(currentLowerBound)<0)
			{
				if(otherBoundIt.hasNext())
				{
					otherLowerBound = otherBoundIt.next();
					otherUpperBound = otherBoundIt.next();
				}
				else if(currentBoundIt.hasNext())
				{
					otherBoundIt = set.getBoundIterator();
					otherLowerBound = otherBoundIt.next();
					otherUpperBound = otherBoundIt.next();
					currentLowerBound = currentBoundIt.next();
					currentUpperBound = currentBoundIt.next();
					continue;
				}
				return false;
			}
			if(otherLowerBound.compareTo(currentUpperBound)>0)
			{
				if(currentBoundIt.hasNext())
				{
					otherBoundIt = set.getBoundIterator();
					otherLowerBound = otherBoundIt.next();
					otherUpperBound = otherBoundIt.next();
					currentLowerBound = currentBoundIt.next();
					currentUpperBound = currentBoundIt.next();
					continue;
				}
				return false;
			}
			return true;
		}
	}

	@Override
	public boolean isInRange(final Set set)
	{
		return getBridge().isInRange(set);
	}

	@Override
	public boolean hasValueInRange(final Set set)
	{
		if(set.isEmpty() || isEmpty())
			return false;

		for(final ChildType child:getChildren())
		{
			if(child.hasValueInRange(set))
				return true;
		}
		return false;
	}

	@Override
	public boolean is(final SetCoveringMask mask, final Set set)
	{
		return _isAndHasBridge.is(mask, set);
	}

	@Override
	public boolean has(final SetCoveringMask mask, final Set set)
	{
		return _isAndHasBridge.has(mask, set);
	}

	@Override
	public boolean isEmpty()
	{
		return (getChildrenCount() == 0) || getChildren().first().isEmpty();
	}

	@Override
	public boolean isInterval() {
		return getBridge().isInterval();
	}

	@Override
	public boolean isGreaterThan(final Set set)
	{
		boolean r = false;
		for(final ChildType child:getChildren())
		{
			if(!child.isEmpty())
			{
				if(!child.isGreaterThan(set))
					return false;
				r = true;
			}
		}
		return r;
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final Set set)
	{
		boolean r = set.isEmpty();
		for(final ChildType child:getChildren())
		{
			if(!child.isEmpty())
			{
				if(!child.isGreaterThanOrEqualTo(set))
					return false;
				r = true;
			}
		}
		return r;
	}

	@Override
	public boolean hasValueLowerThan(final Set set)
	{
		if(set.isEmpty() || isEmpty())
			return false;

		for(final ChildType child:getChildren())
		{
			if(child.hasValueLowerThan(set))
				return true;
		}
		return false;
	}

	@Override
	public boolean isLowerThan(final Set set)
	{
		boolean r = false;
		for(final ChildType child:getChildren())
		{
			if(!child.isEmpty())
			{
				if(!child.isLowerThan(set))
					return false;
				r = true;
			}
		}
		return r;
	}

	@Override
	public boolean isLowerThanOrEqualTo(final Set set)
	{
		boolean r = set.isEmpty();
		for(final ChildType child:getChildren())
		{
			if(!child.isEmpty())
			{
				if(!child.isLowerThanOrEqualTo(set))
					return false;
				r = true;
			}
		}
		return r;
	}

	@Override
	public boolean hasValueGreaterThan(final Set set)
	{
		if(set.isEmpty() || isEmpty())
			return false;

		for(final ChildType child:getChildren())
		{
			if(child.hasValueGreaterThan(set))
				return true;
		}
		return false;
	}

	@Override
	public InternalChildrenCollectionType getChildren()
	{
		return getBridge().getChildren();
	}

	@Override
	public InternalChildrenCollectionType getChildrenDescending()
	{
		return getBridge().getChildrenDescending();
	}

	@Override
	public List<? extends Set> splitOnRange(final Set set)
	{
		return getBridge().splitOnRange(set);
	}

	@Override
	public Iterator<? extends ElementarySet> iterator()
	{
		return getBridge().iterator();
	}

	@Override
	public Iterator<? extends ElementarySet> reverseIterator()
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
	public ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet> getSimpleSet()
	{
		return getBridge().getSimpleSet();
	}

	@Override
	public boolean isPreceding(final Set set)
	{
		if(isEmpty())
			return false;
		return getChildrenDescending().first().isPreceding(set);
	}

	@Override
	public boolean isSucceeding(final Set set)
	{
		if(isEmpty())
			return false;
		return getChildren().first().isSucceeding(set);
	}

//	protected boolean _areBoundContiguous(final Bound b1, final boolean isLowerBound, final Bound b2, final boolean isOtherLowerBound, final int compare)
//	{
//		if(compare != 0 && isLowerBound == !isOtherLowerBound)
//			return true;
//		return false;
//	}

	@Override
	public boolean hasContiguityWith(final Set set) {
		if(isEmpty()||set.isEmpty())
			return false;
		if (set.isLowerThan(getAsSet()))
			return getChildren().first().isSucceeding(set);
		if (set.isGreaterThan(getAsSet()))
			return getChildrenDescending().first().isPreceding(set);

		if(isInterval() && !set.isInterval())
		{
			return set.hasContiguityWith(getAsSet());
		}
		boolean hasContiguity = false;
		for(final ChildType child:getChildren())
		{
			if(child.isIntersecting(set))
				return false;
			if(child.hasContiguityWith(set))
				hasContiguity = true;
		}
		return hasContiguity;
	}
}