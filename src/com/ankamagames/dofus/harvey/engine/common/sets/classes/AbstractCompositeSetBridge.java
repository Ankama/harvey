/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ICompositeSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractCompositeSetBridge
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>,
	CompositeSet extends ICompositeSet<Set, SimpleSet, ElementarySet, CompositeSet, ?>,
	ChildType extends ISet<Set, SimpleSet, ElementarySet>,
	BridgedSet extends ICompositeSet<Set, SimpleSet, ElementarySet, CompositeSet, ChildType>,
	InternalChildrenCollectionType extends Collection<ChildType>
>
extends AbstractSetBridge<Set, SimpleSet, ElementarySet, BridgedSet>
{
	protected static final float BALANCING_THRESHOLD = 0.5f;

	protected InternalChildrenCollectionType _children;

	protected abstract IEqualityToolbox<Set, SimpleSet, ElementarySet, BridgedSet> getEqualityToolbox();

	protected abstract ICompositeSetCreationToolbox<Set, SimpleSet, ElementarySet, ?, CompositeSet, ChildType, BridgedSet> getSetCreationToolbox();

	private @Nullable
	SimpleSet _simpleSetCache = null;

	public AbstractCompositeSetBridge(final BridgedSet bridged, final InternalChildrenCollectionType childrenIterable)
	{
		super(bridged);
		_children = childrenIterable;
	}

	protected boolean _isEqual(final Set set)
	{
		if(set.isDegenerate())
			return _bridged.isDegenerate()&&getEqualityToolbox().equalsDegenerateSet(set);

		return _bridged.contains(set)&&isContainedBy(set);
	}

	public boolean isEqual(final @Nullable Object obj)
	{
		if(!(obj instanceof ISet))
			if(_bridged.isDegenerate())
				return getEqualityToolbox().equalsValue(obj);
			else
				return false;
		final ISet<?, ?, ?> set = (ISet<?, ?, ?>)obj;
		if(set.isEmpty())
			return _bridged.isEmpty();

		try
		{
			@SuppressWarnings("unchecked")
			final
			Set sameTypeSet = (Set)set;

			return _isEqual(sameTypeSet);
		}
		catch(final ClassCastException e)
		{
			return false;
		}
	}

	public boolean isEqual(final Set set)
	{
		if(set.isEmpty())
			return _bridged.isEmpty();

		return _isEqual(set);
	}

	public boolean isContainedBy(final Set set)
	{
		final int simpleCheck = _checkSimpleTypeContainBy(set);
		switch(simpleCheck)
		{
		case 0:
			return false;
		case 1:
			return true;
		default :
			return _checkOtherTypesContainBy(set);
		}
	}

	protected int _checkSimpleTypeContainBy(final Set set) {
		if(_bridged.isEmpty())
			return 1;
		if(set.isEmpty())
			return 0;
		if(set.isDegenerate())
		{
			if(_bridged.isDegenerate())
				return _bridged.iterator().next().isContainedBy(set)?1:0;
			return 0;
		}
		if(_bridged.isDegenerate())
			return set.contains(_bridged.getAsSet())?1:0;
		return -1;

	}

	protected boolean _checkOtherTypesContainBy(final Set set) {

		for(final ChildType child:getChildren())
		{
			if(!child.isContainedBy(set))
				return false;
		}
		return true;
	}

	public Set unite(final Set set)
	{
		final Set checkSimpleCase = _checkSimpleCase(set);
		if(checkSimpleCase != null)
			return checkSimpleCase;

		final ArrayList<Set> keepElmts = new ArrayList<Set>(getChildrenCount());
		final ArrayList<ChildType> toMergeElmts = new ArrayList<ChildType>(getChildrenCount());

		final boolean intersects = _checkIntersection(set, keepElmts, toMergeElmts);


		boolean setWithOverlap = false;
		if(intersects)
		{
			setWithOverlap = _checkOverlap(toMergeElmts);
		}

		return _doMergeWithSet(set, keepElmts, toMergeElmts, intersects, setWithOverlap);
	}

	protected Set _doMergeWithSet(final Set set, final ArrayList<Set> keepElmts,
			final ArrayList<ChildType> toMergeElmts, final boolean intersects, final boolean setWithOverlap) {
		if((!intersects)||(setWithOverlap))
		{
			if(set.size()>_bridged.size()*BALANCING_THRESHOLD)
				return getSetCreationToolbox().makeComposite(set).getAsSet();
			return getSetCreationToolbox().addChildToBridgedComposite(set).getAsSet();
		}

		ISet<Set, SimpleSet, ElementarySet> currentMerge = set;
		for(final ChildType toMerge:toMergeElmts)
		{
			currentMerge = toMerge.unite(currentMerge.getAsSet());
		}

		keepElmts.add(currentMerge.getAsSet());
		return getSetCreationToolbox().makeComposite(keepElmts);
	}

	protected boolean _checkOverlap(final ArrayList<ChildType> toMergeElmts) {
		boolean setWithOverlap = false;
		final ListIterator<ChildType> it = toMergeElmts.listIterator(0);
		while((it.hasNext()) && (!setWithOverlap))
		{
			final ChildType toMerge1 = it.next();
			final Iterator<ChildType> it2 = toMergeElmts.listIterator(it.nextIndex());
			while(it2.hasNext())
				if(toMerge1.isIntersecting(it2.next().getAsSet()))
				{
					setWithOverlap = true;
					break;
				}
		}
		return setWithOverlap;
	}

	protected boolean _checkIntersection(final Set set, final ArrayList<Set> keepElmts,
			final ArrayList<ChildType> toMergeElmts) {

		boolean intersects = false;
		for(final ChildType child:getChildren())
		{
			if(child.isIntersecting(set))
			{
				intersects = true;
				if(!child.isContainedBy(set))
					toMergeElmts.add(child);
			}
			else
				keepElmts.add(child.getAsSet());
		}
		return intersects;
	}

	protected @Nullable Set _checkSimpleCase(final Set set) {
		if(set.isEmpty())
			return _bridged.getAsSet();

		if(_bridged.contains(set))
			return _bridged.getAsSet();

		if(isContainedBy(set))
			return set;
		return null;
	}

	public Set intersect(final Set set)
	{
		if(set.isEmpty())
			return set;
		if(set.isDegenerate())
			if(_bridged.contains(set))
				return set;
			else
				return getSetCreationToolbox().makeEmptySet().getAsSet();
		if(_bridged.isContainedBy(set))
			return _bridged.getAsSet();

		boolean intersects = false;
		final ArrayList<Set> keepElmts = new ArrayList<Set>(getChildrenCount());
		for(final ChildType child:getChildren())
		{
			if(child.isIntersecting(set))
			{
				intersects = true;
				keepElmts.add(child.intersect(set).getAsSet());
			}
		}

		if(!intersects)
			return getSetCreationToolbox().makeEmptySet().getAsSet();
		return getSetCreationToolbox().makeComposite(keepElmts);
	}

	public Set subtract(final Set set)
	{
		if(set.isEmpty() || !_bridged.isIntersecting(set))
			return _bridged.getAsSet();

		if(isContainedBy(set))
			return getSetCreationToolbox().makeEmptySet().getAsSet();

		final ArrayList<Set> keepElmts = new ArrayList<Set>(getChildrenCount());

		for(final ChildType child:getChildren())
		{
			if(child.isIntersecting(set))
			{
				if(child.isContainedBy(set))
					continue;
				keepElmts.add(child.subtract(set).getAsSet());
			}
			else
				keepElmts.add(child.getAsSet());
		}

		return getSetCreationToolbox().makeComposite(keepElmts);
	}

	public Set symmetricSubtract(final Set set)
	{
		if(set.isEmpty())
			return _bridged.getAsSet();
		if(!_bridged.isIntersecting(set))
			return unite(set);
		if(isContainedBy(set))
			return set.subtract(_bridged.getAsSet()).getAsSet();
		if(_bridged.contains(set))
			return subtract(set);

		return unite(set).subtract(intersect(set)).getAsSet();
	}

	@SuppressWarnings("unchecked")
	private List<ElementarySet> _makeOneElementList(final ElementarySet elmt)
	{
		return Arrays.asList(elmt);
	}

	private List<ElementarySet> getMergedElementarySets(ElementarySet currentSet, final Collection<ElementarySet> elements, final LinkedList<ElementarySet> intersecting)
	{
		Iterator<ElementarySet> intersectingIt = intersecting.iterator();
		while(intersectingIt.hasNext())
		{
			final IElementarySet<Set, SimpleSet, ElementarySet> toMerge = intersectingIt.next();
			final ISet<Set, SimpleSet, ElementarySet> merged = toMerge.unite(currentSet.getAsSet());
			if(merged.isElementary())
			{
				currentSet = (ElementarySet)merged;
				intersectingIt.remove();
			}
		}

		if(intersecting.isEmpty())
			return _makeOneElementList(currentSet);

		intersectingIt = intersecting.iterator();
		while(intersectingIt.hasNext())
		{
			final ISet<Set, SimpleSet, ElementarySet> merged = currentSet.unite(intersectingIt.next().getAsSet());
			if(merged.isElementary())
			{
				currentSet = (ElementarySet)merged;
				intersectingIt.remove();
			}
		}

		if(intersecting.isEmpty())
			return _makeOneElementList(currentSet);

		double intersectingCumulSize = 0;
		intersectingIt = intersecting.iterator();
		while(intersectingIt.hasNext())
		{
			intersectingCumulSize += intersectingIt.next().size();
		}
		if(currentSet.size() < intersectingCumulSize)
		{
			// on casse le currentAsSet en sous-parties

			final ISimpleSet<Set, SimpleSet, ElementarySet> simpleSet = _getSimpleSet(currentSet.iterator(), elements, false);
			final LinkedList<ElementarySet> r = new LinkedList<ElementarySet>();
			final Iterator<? extends IElementarySet<Set, SimpleSet, ElementarySet>> rIt = simpleSet.iterator();
			while(rIt.hasNext())
				r.add(rIt.next().getAsElementarySet());
			return r;
		}
		else
		{
			// on casse les set dans intersecting en sous-parties

			elements.removeAll(intersecting);
			elements.add(currentSet);
			final LinkedList<ElementarySet> r = new LinkedList<ElementarySet>();
			for(final ElementarySet intersectingElement:intersecting)
			{
				final SimpleSet parts = _getSimpleSet(intersectingElement.iterator(), elements, false).getAsSimpleSet();
				if(parts.isElementary())
					r.add((ElementarySet)parts);
				else
				{
					final Iterator<? extends IElementarySet<Set, SimpleSet, ElementarySet>> partIt = parts.iterator();
					while(partIt.hasNext())
						r.add(partIt.next().getAsElementarySet());
				}
			}
			return r;
		}
	}

	protected ISimpleSet<Set, SimpleSet, ElementarySet> _getSimpleSet(
			final Iterator<? extends IElementarySet<Set, SimpleSet, ElementarySet>> it,
			final Collection<ElementarySet> elements,
			final boolean needSimplification)
	{
		while(it.hasNext())
		{
			final ElementarySet currentSubSet;
			if(needSimplification)
				currentSubSet = it.next().getSimpleSet().getAsElementarySet();
			else
				currentSubSet = it.next().getAsElementarySet();
			final Set currentSubSetAsSet = currentSubSet.getAsSet();
			if(!currentSubSet.isEmpty())
			{
				final Iterator<ElementarySet> elmtsIt = elements.iterator();
				final LinkedList<ElementarySet> intersecting = new LinkedList<ElementarySet>();
				boolean contained = false;
				while(elmtsIt.hasNext())
				{
					final ElementarySet alreadyAdded = elmtsIt.next();
					if(alreadyAdded.isIntersecting(currentSubSetAsSet))
					{
						if(alreadyAdded.contains(currentSubSetAsSet))
						{
							contained = true;
							break;
						}
						if(alreadyAdded.isContainedBy(currentSubSetAsSet))
							elmtsIt.remove();
						else
							intersecting.add(alreadyAdded);
					}
				}
				if(contained)
					continue;
				if(intersecting.isEmpty())
					elements.add(currentSubSet);
				else
				{
					elements.addAll(getMergedElementarySets(currentSubSet, elements, intersecting));
				}
			}
		}

		return getSetCreationToolbox().makeSimplestSet(elements);
	}

	public ISimpleSet<Set, SimpleSet, ElementarySet> getSimpleSet()
	{
		if(_simpleSetCache != null)
			return _simpleSetCache;
		final LinkedList<ElementarySet> elements = new LinkedList<ElementarySet>();
		return _simpleSetCache = _getSimpleSet(_bridged.iterator(), elements, true).getAsSimpleSet();
	}

	public InternalChildrenCollectionType getChildren()
	{
		return _children;
	}

	public int getChildrenCount()
	{
		return _children.size();
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
