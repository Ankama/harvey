package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


@NonNullByDefault
public abstract class AbstractCompositeSet
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>,
	CompositeSet extends ICompositeSet<Set, SimpleSet, ElementarySet, CompositeSet, ?>,
	ChildType extends ISet<Set, SimpleSet, ElementarySet>
>
extends AbstractSet<Set, SimpleSet, ElementarySet>
implements ICompositeSet<Set, SimpleSet, ElementarySet, CompositeSet, ChildType>
{
	protected @Nullable ISimpleSet<Set, SimpleSet, ElementarySet> simpleSetCache = null;

	protected abstract AbstractCompositeSetBridge<Set, SimpleSet, ElementarySet, CompositeSet, ChildType, ? extends AbstractCompositeSet<Set, SimpleSet, ElementarySet, CompositeSet, ChildType>, ?> getBridge();

	@Override
	public boolean equals(@Nullable final Object obj)
	{
		return getBridge().isEqual(obj);
	}

	@Override
	public boolean equals(final Set set) {
		return getBridge().isEqual(set);
	}

	@Override
	public boolean isEmpty()
	{
		for(final ChildType child:getChildren())
		{
			if(!child.isEmpty())
				return false;
		}
		return true;
	}

	@Override
	public boolean isDegenerate()
	{
		ChildType degenerateSet = null;
		for(final ChildType child:getChildren())
		{
			final boolean isDegenerate = child.isDegenerate();
			if((!isDegenerate)&&(!child.isEmpty()))
				return false;
			if(isDegenerate)
			{
				if(degenerateSet == null)
					degenerateSet = child;
				else if(!degenerateSet.equals(child))
					return false;
			}
		}
		return (degenerateSet!=null);
	}

	@Override
	public boolean isElementary()
	{
		return false;
	}

	@Override
	public boolean isSimple()
	{
		for(final ChildType child:getChildren())
		{
			if((!child.isEmpty())&&(!child.isElementary()))
				return false;
		}
		return true;
	}

	@Override
	public double size()
	{
		if(isEmpty())
			return 0;
		if(isDegenerate())
			return 1;
		final ISimpleSet<Set, SimpleSet, ElementarySet> simpleSet = getSimpleSet();
		if(simpleSet.isElementary())
			return simpleSet.size();
		double r = 0;
		final Iterator<? extends IElementarySet<Set, SimpleSet, ElementarySet>> iterator = simpleSet.iterator();
		while(iterator.hasNext())
		{
			r += iterator.next().size();
		}
		return r;
	}

	@Override
	public boolean contains(final Set set)
	{
//		if(set.isEmpty())
//			return true;
//		if(set.isDegenerate())
//		{
//			for(final ChildType child:getChildren())
//				if(child.contains(set))
//					return true;
//			return false;
//		}
//		return set.subtract(getAsSet()).isEmpty();
		//TODO
		switch(_checkSimpleTypesContains(set))
		{
		case 0:
			return false;
		case 1:
			return true;
		default:
			return _checkOtherTypeContains(set);
		}
	}

	protected int _checkSimpleTypesContains(final Set set) {
		if(set.isEmpty())
			return 1;
		if(isEmpty())
			return 0;

		if(isDegenerate())
		{
			return (set.isDegenerate() && equals(set))?1:0;
		}

		if(set.isDegenerate())
		{
			for(final ChildType child:getChildren())
				if(child.contains(set))
					return 1;
			return 0;
		}
		return -1;
	}

	protected boolean _checkOtherTypeContains(final Set set)
	{
		final Stack<Set> parts = new Stack<Set>();

		parts.add(set);

		while(!parts.isEmpty()) // while things to test
		{
			final Set currentSet = parts.pop();
			if(currentSet.isEmpty())
				continue;

			boolean intersecting = false;
			boolean contained = false;
			for(final ChildType child:getChildren())
			{
				if(child.isIntersecting(currentSet))
				{
					intersecting = true;
					if(child.contains(currentSet))
					{
						contained = true;
						break;
					}
				}
			}
			if(!intersecting)
				return false;
			if(contained)
				continue;
			//!contained
			if(currentSet.isDegenerate())
			{
				return false;
			}

			switch(_checkPartOther(parts, currentSet))
			{
			case 0:
				return false;
			case 1:
				return true;
			}
		}
		return true;
	}

	protected int _checkPartOther(final Stack<Set> parts,
			final Set currentSet) {
		if(currentSet instanceof ICompositeSet)
		{
			final Iterator<? extends ISet<Set, SimpleSet, ElementarySet>> iterator = ((ICompositeSet<Set, SimpleSet, ElementarySet, CompositeSet, ?>)currentSet).getChildren().iterator();

			while(iterator.hasNext())
			{
				parts.push(iterator.next().getAsSet());
			}
			return -1;
		}

		final Iterator<? extends IElementarySet<Set, SimpleSet, ElementarySet>> iterator = currentSet.iterator();
		if(iterator.hasNext())
		{
			final IElementarySet<Set, SimpleSet, ElementarySet> firstPart = iterator.next();
			if(firstPart.equals(currentSet))
				return 0;
			parts.push(firstPart.getAsSet());
		}
		while(iterator.hasNext())
		{
			parts.push(iterator.next().getAsSet());
		}
		return -1;
	}

	@Override
	public boolean isContainedBy(final Set set)
	{
		return getBridge().isContainedBy(set);
	}

	@Override
	public boolean isIntersecting(final Set set)
	{
		final int result = _isIntersectingOnSimpleType(set);
		if(result!=0)
			return (result==1)?true:false;

		for(final ChildType child:getChildren())
		{
			if(child.isIntersecting(set))
				return true;
		}
		return false;
	}

	protected int  _isIntersectingOnSimpleType(final Set set) {
		if(isEmpty() || set.isEmpty())
			return -1;

		if(isDegenerate())
			return isContainedBy(set)?1:-1;
		return 0;
	}

	@Override
	public Set unite(final Set set)
	{
		return getBridge().unite(set);
	}

	@Override
	public Set intersect(final Set set)
	{
		return getBridge().intersect(set);
	}

	@Override
	public Set subtract(final Set set)
	{
		return getBridge().subtract(set);
	}

	@Override
	public Set symmetricSubtract(final Set set)
	{
		return getBridge().symmetricSubtract(set);
	}

	@Override
	public Iterator<? extends ElementarySet> iterator()
	{
		return new Iterator<ElementarySet>()
			{
				Iterator<? extends ChildType> childIt = getChildren().iterator();
				Iterator<? extends IElementarySet<Set, SimpleSet, ElementarySet>> currentIterator = EmptyIterator.getInstance();

				@Override
				public boolean hasNext()
				{
					while(!currentIterator.hasNext())
					{
						if(!childIt.hasNext())
							return false;
						currentIterator = childIt.next().iterator();
					}
					return true;
				}

				@Override
				public ElementarySet next()
				{
					while(!currentIterator.hasNext())
					{
						if(!childIt.hasNext())
							throw new NoSuchElementException();
						currentIterator = childIt.next().iterator();
					}
					return currentIterator.next().getAsElementarySet();
				}

				@Override
				public void remove()
				{
					throw new UnsupportedOperationException();
				}
			};
	}

	@Override
	public ISimpleSet<Set, SimpleSet, ElementarySet> getSimpleSet()
	{
		ISimpleSet<Set, SimpleSet, ElementarySet> simpleSet = simpleSetCache;
		if(simpleSet==null)
		{
			simpleSetCache = simpleSet = getBridge().getSimpleSet();
		}
		return simpleSet;
	}

	@Override
	public Iterable<? extends ChildType> getChildren()
	{
		return getBridge().getChildren();
	}

	@Override
	public int getChildrenCount()
	{
		return getBridge().getChildrenCount();
	}

	@Override
	public String toString()
	{
		switch(getChildrenCount())
		{
		case 0:
			return "∅";
		case 1:
			return getChildren().iterator().next().toString();
		default:
			String r = "{ ";
			for(final ChildType child:getChildren())
			{
				r +=  child.toString() + " ; ";
			}
			r = r.substring(0, r.length()-2);
			r+="}";
			return r;
		}
	}
}