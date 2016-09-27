package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class CheckedSetArrayList
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>
>
extends ArrayList<ElementarySet>
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8675914245677956277L;

	public static <Set extends ISet<Set, SimpleSet, ElementarySet>, SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>, ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>>
	CheckedSetArrayList<Set, SimpleSet, ElementarySet> makeCheckedSetArrayList()
	{
		List<ElementarySet> emptyList = Arrays.asList();
		return new CheckedSetArrayList<Set, SimpleSet, ElementarySet>(emptyList);
	}
	
	public static <Set extends ISet<Set, SimpleSet, ElementarySet>, SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>, ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>>
	CheckedSetArrayList<Set, SimpleSet, ElementarySet> makeCheckedSetArrayList(Collection<? extends ISet<Set, SimpleSet, ElementarySet>> elements)
	{
		return new CheckedSetArrayList<Set, SimpleSet, ElementarySet>(elements);
	}

	private CheckedSetArrayList(Collection<? extends ISet<Set, SimpleSet, ElementarySet>> elements)
	{
		super(elements.size());
		_addAllToElementarySetArrayList(elements);
	}

	protected void _addAllToElementarySetArrayList(Collection<? extends ISet<Set, SimpleSet, ElementarySet>> sourceCollection)
	{
		for(ISet<Set, SimpleSet, ElementarySet> child:sourceCollection)
		{
			if(child!=null)
				_addSet(child);
		}
	}

	@SuppressWarnings("unchecked")
	protected void _addSet(ISet<Set, SimpleSet, ElementarySet> child)
	{
		if(!child.isEmpty())
		{
			if(child.isElementary())
				_addToElementarySetArrayList((ElementarySet)child);
			else
			{
				Iterator<? extends IElementarySet<Set, SimpleSet, ElementarySet>> childIt = child.iterator();
				while(childIt.hasNext())
					_addToElementarySetArrayList(childIt.next().getAsElementarySet());
			}
		}
	}

	protected void _addToElementarySetArrayList(ElementarySet newChild) {
		final Iterator<ElementarySet> elmtsIt = iterator();
		final LinkedList<ElementarySet> intersecting = new LinkedList<ElementarySet>();
		Set childAsSet = newChild.getAsSet();
		while(elmtsIt.hasNext())
		{
			final ElementarySet alreadyAdded = elmtsIt.next();
			if(alreadyAdded.isIntersecting(childAsSet))
			{
				if(alreadyAdded.contains(childAsSet))
					return;
				if(alreadyAdded.isContainedBy(childAsSet))
					elmtsIt.remove();
				else
				{
					intersecting.add(alreadyAdded);
					elmtsIt.remove();
				}
			}
		}
		if(intersecting.isEmpty())
			super.add(newChild);
		else
		{
			_addMergedElement(newChild, intersecting);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void _addMergedElement(ElementarySet newChild, final LinkedList<ElementarySet> intersecting)
	{
		Iterator<ElementarySet> intersectingIt = intersecting.iterator();
		while(intersectingIt.hasNext())
		{
			final ElementarySet toMerge = intersectingIt.next();
			final ISet<Set, SimpleSet, ElementarySet> merged = toMerge.unite(newChild.getAsSet());
			if(merged.isElementary())
			{
				newChild = (ElementarySet) merged;
				intersectingIt.remove();
			}
		}

		if(intersecting.isEmpty())
		{
			super.add(newChild);
			return;
		}

		intersectingIt = intersecting.iterator();
		while(intersectingIt.hasNext())
		{
			final ISet<Set, SimpleSet, ElementarySet> merged = newChild.unite(intersectingIt.next().getAsSet());
			if(merged.isElementary())
			{
				newChild = (ElementarySet)merged;
				intersectingIt.remove();
			}
		}

		if(intersecting.isEmpty())
		{
			super.add(newChild);
			return;
		}

		double intersectingCumulSize = 0;
		intersectingIt = intersecting.iterator();
		while(intersectingIt.hasNext())
		{
			intersectingCumulSize += intersectingIt.next().size();
		}
		if(newChild.size() < intersectingCumulSize)
		{
			// on casse le currentSet en sous-parties
			
			super.addAll(intersecting);

			final Iterator<? extends IElementarySet<Set, SimpleSet, ElementarySet>> rIt = newChild.iterator();
			while(rIt.hasNext())
				_addToElementarySetArrayList(rIt.next().getAsElementarySet());
		}
		else
		{
			// on casse les set dans intersecting en sous-parties

			super.add(newChild);
			for(final ElementarySet intersectingElement:intersecting)
			{
				Iterator<? extends IElementarySet<Set, SimpleSet, ElementarySet>> intersectingElmtIt = intersectingElement.iterator();
				while(intersectingElmtIt.hasNext())
					_addToElementarySetArrayList(intersectingElmtIt.next().getAsElementarySet());
			}
		}
	}
	
	public void addSet(Set set)
	{
		_addSet(set);
	}

	@Override
	public boolean add(@Nullable ElementarySet e)
	{
		if(e!=null)
		{
			_addToElementarySetArrayList(e);
			return true;
		}
		return false;
	}
	
	public void addAllSets(Collection<? extends ISet<Set, SimpleSet, ElementarySet>> c)
	{
		_addAllToElementarySetArrayList(c);
	}

	@Override
	public boolean addAll(@Nullable Collection<? extends ElementarySet> c)
	{
		if(c!=null)
		{
			_addAllToElementarySetArrayList(c);
			return true;
		}
		return false;
	}
}
