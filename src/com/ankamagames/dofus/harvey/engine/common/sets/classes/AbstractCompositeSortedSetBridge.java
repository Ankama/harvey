/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.concurrent.ArrayBlockingQueue;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.Comparator.SortedAscendingComparator;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.Comparator.SortedDescendingComparator;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.BoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ICompositeSortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public abstract class AbstractCompositeSortedSetBridge
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	CompositeSet extends ICompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ?>,
	ChildType extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Bridged extends ICompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType>,
	InternalChildrenCollectionType extends SortedSet<ChildType>
>
extends AbstractCompositeSetBridge<Set, SimpleSet, ElementarySet, CompositeSet, ChildType, Bridged, InternalChildrenCollectionType>
{
	public static abstract class SortedSetFactory
	<
		Bound extends IBound<Bound>,
		Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
		SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
		ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
		ChildType extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
		InternalChildrenCollectionType extends SortedSet<ChildType>
	>
	{
		public abstract InternalChildrenCollectionType makeSet(Comparator<? super ChildType> comparator);
	}

	protected abstract BoundComparisonToolbox<Bound, Set, SimpleSet, ElementarySet, Bridged> getBoundComparisonToolbox();
	@Override
	protected abstract ICompositeSortedSetCreationToolbox<Bound, Set, SimpleSet, ElementarySet, ?, ?, CompositeSet, ChildType, Bridged> getSetCreationToolbox();
	@Override
	protected abstract IEqualityToolbox<Set, SimpleSet, ElementarySet, Bridged> getEqualityToolbox();

	protected InternalChildrenCollectionType _descChildren;

	public AbstractCompositeSortedSetBridge(final Bridged bridged, final SortedSetFactory<Bound, Set, SimpleSet, ElementarySet, ChildType, InternalChildrenCollectionType> factory) {
		super(bridged, factory.makeSet(new SortedAscendingComparator<Set, ChildType>()));
		_descChildren = factory.makeSet(new SortedDescendingComparator<Set, ChildType>());
	}

	public AbstractCompositeSortedSetBridge(final Bridged bridged, final SortedSetFactory<Bound, Set, SimpleSet, ElementarySet, ChildType, InternalChildrenCollectionType> factory, final Comparator<ChildType> ascendingComparator, final Comparator<ChildType> descendingComparator) {
		super(bridged, factory.makeSet(ascendingComparator));
		_descChildren = factory.makeSet(descendingComparator);
	}

	public  AbstractCompositeSortedSetBridge(final Bridged bridged, final SortedSetFactory<Bound, Set, SimpleSet, ElementarySet, ChildType, InternalChildrenCollectionType> factory, final ChildType child) {
		this(bridged, factory);
		_children.add(child);
		_descChildren.add(child);
	}

	public  AbstractCompositeSortedSetBridge(final Bridged bridged, final SortedSetFactory<Bound, Set, SimpleSet, ElementarySet, ChildType, InternalChildrenCollectionType> factory, final ChildType child, final Comparator<ChildType> ascendingComparator, final Comparator<ChildType> descendingComparator) {
		this(bridged, factory, ascendingComparator, descendingComparator);
		_children.add(child);
		_descChildren.add(child);
	}

	public AbstractCompositeSortedSetBridge(final Bridged bridged, final SortedSetFactory<Bound, Set, SimpleSet, ElementarySet, ChildType, InternalChildrenCollectionType> factory, final Iterable<ChildType> childrenIterable) {
		this(bridged, factory);
		for(final ChildType child:childrenIterable)
		{
			_children.add(child);
			_descChildren.add(child);
		}
	}

	public AbstractCompositeSortedSetBridge(final Bridged bridged, final SortedSetFactory<Bound, Set, SimpleSet, ElementarySet, ChildType, InternalChildrenCollectionType> factory, final Iterable<ChildType> childrenIterable, final Comparator<ChildType> ascendingComparator, final Comparator<ChildType> descendingComparator) {
		this(bridged, factory, ascendingComparator, descendingComparator);
		for(final ChildType child:childrenIterable)
		{
			_children.add(child);
			_descChildren.add(child);
		}
	}

	private @Nullable SimpleSet _simpleSetCache = null;

	@Override
	public InternalChildrenCollectionType getChildren()
	{
		return _children;
	}

	public InternalChildrenCollectionType getChildrenDescending()
	{
		return _descChildren;
	}

	@Override
	public boolean isContainedBy(final Set set) {
		return set.contains(_bridged.getAsSet());
	}

	public boolean isInRange(final Set set)
	{
		if(_bridged.isEmpty())
			return true;
		if(set.isEmpty())
			return false;
		return getBoundComparisonToolbox().areBothBoundsInRangeOf(set);
	}

	public boolean isGreaterThan(final Set set)
	{
		return getBoundComparisonToolbox().isLowerBoundGreaterThanUpperBound(set);
	}

	public boolean isGreaterThanOrEqualTo(final Set set)
	{
		return getBoundComparisonToolbox().isLowerBoundInRangeOf(set) &&
				getBoundComparisonToolbox().isUpperBoundGreaterThanUpperBound(set);
	}

	public boolean isLowerThan(final Set set)
	{
		return getBoundComparisonToolbox().isUpperBoundLowerThanLowerBound(set);
	}

	public boolean isLowerThanOrEqualTo(final Set set)
	{
		return getBoundComparisonToolbox().isUpperBoundInRangeOf(set) &&
				getBoundComparisonToolbox().isLowerBoundLowerThanLowerBound(set);
	}

	public boolean isInterval()
	{
		return _bridged.getBoundCount()<=2;
	}

	@Override
	protected int _checkSimpleTypeContainBy(final Set set)
	{
		final int simpleCheck = super._checkSimpleTypeContainBy(set);
		if(simpleCheck != -1)
			return simpleCheck;
		if(set.isInterval())
		{
			return getBoundComparisonToolbox().areBothBoundsInRangeOf(set)?1:0;
		}
		return -1;

	}

	private void _addCurrentChunk(final Bound lowerBound, final Bound upperBound, final List<Set> childList)
	{
		if(upperBound.compareTo(lowerBound) == 0)
			childList.add(getSetCreationToolbox().makeDegenerate(lowerBound).getAsSet());
		else
			childList.add(getSetCreationToolbox().makeInterval(lowerBound, upperBound).getAsSet());
	}

	@Override
	public Set unite(final Set set)
	{
		if(set.isEmpty())
			return _bridged.getAsSet();

		if(_bridged.isEmpty())
			return set;

		Iterator<Bound> otherIterator = set.getBoundIterator();
		Iterator<Bound> currentIterator = _bridged.getBoundIterator();
		final List<Set> childList = new ArrayList<Set>(getChildrenCount()*2);

		Bound currentLowerBound;
		Bound currentUpperBound = null;
		Bound otherLowerBound;
		{
			final Bound currentBound = currentIterator.next();
			final Bound otherBound = otherIterator.next();
			final int compare = currentBound.compareTo(otherBound);
			if(compare<=0)
			{
				currentLowerBound = currentBound;
				otherLowerBound = otherBound;
			}
			else
			{
				final Iterator<Bound> tmp = currentIterator;
				currentIterator = otherIterator;
				otherIterator = tmp;
				currentLowerBound =  otherBound;
				otherLowerBound = currentBound;
			}
		}

		while(currentIterator.hasNext() || otherIterator.hasNext())
		{
			final Bound otherUpperBound;
			if(!currentIterator.hasNext())
			{
				final Iterator<Bound> tmpIt = currentIterator;
				currentIterator = otherIterator;
				otherIterator = tmpIt;
				final Bound tmp = currentIterator.next();
				if(tmp.compareTo(currentUpperBound)>0)
					currentUpperBound = tmp;
			}
			if(currentUpperBound == null)
				currentUpperBound = currentIterator.next(); // get the naive upperBound of the current Iterator
			if(currentIterator == otherIterator ||
				(otherLowerBound.compareTo(currentUpperBound)> 0 && !otherLowerBound.isSucceeding(currentUpperBound)) ||
				!otherIterator.hasNext())
			{
				_addCurrentChunk(currentLowerBound, currentUpperBound, childList);
				if(currentIterator.hasNext())
				{
					if(otherIterator.hasNext())
					{
						final Bound tmpLowerBound = currentIterator.next();
						final int compare = otherLowerBound.compareTo(tmpLowerBound);
						if(compare >= 0)
						{
							currentLowerBound = tmpLowerBound;
							currentUpperBound = currentIterator.next();
							continue;
						}
						else
						{
							final Iterator<Bound> tmp = otherIterator;
							otherIterator = currentIterator;
							currentIterator = tmp;
							currentLowerBound =  otherLowerBound;
							otherLowerBound = tmpLowerBound;
							currentUpperBound = currentIterator.next();
							continue;
						}
					}
					else // easy case just fill the childList with remaining interval
					{
						currentLowerBound = currentIterator.next();
						currentUpperBound = currentIterator.next();
						continue;
					}
				}
				else if(!otherIterator.hasNext())
				{
					currentUpperBound = null;
					break;
				}
			}
			otherUpperBound = otherIterator.next();
			if(otherUpperBound.compareTo(currentUpperBound)>0)
			{
				final Iterator<Bound> tmp = currentIterator;
				currentIterator = otherIterator;
				otherIterator = tmp;
				currentUpperBound = otherUpperBound;
				if(otherIterator.hasNext())
					otherLowerBound = otherIterator.next();
			}
			else if(otherIterator.hasNext())
			{
				otherLowerBound = otherIterator.next();
			}
		}
		if(currentUpperBound != null)
			_addCurrentChunk(currentLowerBound, currentUpperBound, childList);

		if(childList.size() == 0)
			return getSetCreationToolbox().makeEmptySet().getAsSet();
		if(childList.size() == 1)
			return childList.get(0);
		return getSetCreationToolbox().makeComposite(childList);

	}

	protected List<Set> _intersect(final Set set)
	{

		Iterator<Bound> otherIterator = set.getBoundIterator();
		Iterator<Bound> currentIterator = _bridged.getBoundIterator();
		final List<Set> childList = new ArrayList<Set>(getChildrenCount()*2);

		Bound currentLowerBound;
		Bound currentUpperBound = null;
		Bound otherLowerBound;
		Bound otherUpperBound;
		Bound currentBound = currentIterator.next();
		Bound otherBound = otherIterator.next();
		final int compare = currentBound.compareTo(otherBound);
		if(compare>=0)
		{
			currentLowerBound = currentBound;
			otherLowerBound = otherBound;
		}
		else
		{
			final Iterator<Bound> tmp = currentIterator;
			currentIterator = otherIterator;
			otherIterator = tmp;
			currentLowerBound =  otherBound;
			otherLowerBound = currentBound;
		}
		otherUpperBound = otherIterator.next();
		currentUpperBound = currentIterator.next(); // get the naive upperBound of the current Iterator

		while((currentIterator.hasNext() && otherIterator.hasNext())|| otherLowerBound.compareTo(currentLowerBound)<=0)
		{
			if(otherUpperBound.compareTo(currentLowerBound)<0) // if other part is lower than current part --> No intersection
			{
				otherLowerBound = otherIterator.next();
				if(otherLowerBound.compareTo(currentLowerBound)<= 0)
				{
					otherUpperBound = otherIterator.next();
				}
				else
				{
					final Bound tmp = otherLowerBound;
					otherLowerBound = currentLowerBound;
					currentLowerBound = tmp;
					final Iterator<Bound> tmpIterator = otherIterator;
					otherIterator = currentIterator;
					currentIterator = tmpIterator;
					otherUpperBound = currentUpperBound;
					currentUpperBound = currentIterator.next();
				}
			}
			else // intersection now we need to find the lowest upperBound
			{
				if(otherUpperBound.compareTo(currentUpperBound) >= 0 || otherUpperBound.isSucceeding(currentUpperBound))
				{
					_addCurrentChunk(currentLowerBound, currentUpperBound, childList);
					if(!currentIterator.hasNext())
					{
						return childList;
					}
					final Bound nextLowerBound = currentIterator.next();
					if(nextLowerBound.compareTo(otherUpperBound)<=0)
					{
						currentLowerBound = nextLowerBound;
						if(otherLowerBound.compareTo(currentBound)>0)
							currentLowerBound = otherLowerBound;
						currentUpperBound = currentIterator.next();
					}
					else
					{
						currentBound = nextLowerBound;
						if(!otherIterator.hasNext())
						{
							return childList;
						}
						otherBound = otherIterator.next();
						if(currentBound.compareTo(otherBound)>=0)
						{
							otherLowerBound = otherBound;
							currentLowerBound = currentBound;
						}
						else
						{
							final Iterator<Bound> tmp = currentIterator;
							currentIterator = otherIterator;
							otherIterator = tmp;
							otherLowerBound = currentBound;
							currentLowerBound = otherBound;
						}
						otherUpperBound = otherIterator.next();
						currentUpperBound = currentIterator.next();
					}
				}
				else
				{
					// add chunk with otherUpperBound as the lowest UpperBound
					_addCurrentChunk(currentLowerBound, otherUpperBound, childList);
					if(!otherIterator.hasNext())
					{
						return childList;
					}
					final Bound nextLowerBound = otherIterator.next();
					if(nextLowerBound.compareTo(currentUpperBound)<=0)
					{
						otherLowerBound = nextLowerBound;
						if(otherLowerBound.compareTo(currentLowerBound)>0)
							currentLowerBound = otherLowerBound;
						otherUpperBound = otherIterator.next();
					}
					else
					{
						otherBound = nextLowerBound;
						if(!otherIterator.hasNext())
						{
							return childList;
						}
						currentBound = currentIterator.next();
						if(currentBound.compareTo(otherBound)>=0)
						{
							otherLowerBound = otherBound;
							currentLowerBound = currentBound;
						}
						else
						{
							final Iterator<Bound> tmp = currentIterator;
							currentIterator = otherIterator;
							otherIterator = tmp;
							otherLowerBound = currentBound;
							currentLowerBound = otherBound;
						}
						otherUpperBound = otherIterator.next();
						currentUpperBound = currentIterator.next();
					}
				}
			}
		}
		if(currentUpperBound.compareTo(otherUpperBound)<=0)
			_addCurrentChunk(currentLowerBound, currentUpperBound, childList);
		else
			_addCurrentChunk(currentLowerBound, otherUpperBound, childList);
		return childList;
	}

	@Override
	public Set intersect(final Set set)
	{
		if(set.isEmpty() || _bridged.isEmpty())
			return getSetCreationToolbox().makeEmptySet().getAsSet();
		final List<Set> childList = _intersect(set);
		if(childList.size() == 0)
			return getSetCreationToolbox().makeEmptySet().getAsSet();
		if(childList.size() == 1)
			return childList.get(0);
		return getSetCreationToolbox().makeComposite(childList);

	}

	protected class IteratorValuePair
	{
		Iterator<? extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>> iterator;
		ElementarySet value;

		public IteratorValuePair(final Iterator<? extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>> _iterator, final ElementarySet _value)
		{
			iterator = _iterator;
			value = _value;
		}
	}

	final Comparator<IteratorValuePair> ascendingComparator = new Comparator<IteratorValuePair>()
	{
		@Override
		public int compare(final @Nullable IteratorValuePair o1, final @Nullable IteratorValuePair o2)
		{
			if((o1 == null)||(o1.value.isEmpty()))
				if((o2 == null)||(o2.value.isEmpty()))
					return 0;
				else
					return 1;
			if((o2 == null)||(o2.value.isEmpty()))
				return -1;
			if((o1.value.hasValueLowerThan(o2.value.getAsSet())) ||
					((!o2.value.hasValueLowerThan(o1.value.getAsSet())) && (o1.value.hasValueGreaterThan(o2.value.getAsSet()))))
				return -1;
			else if((o2.value.hasValueLowerThan(o1.value.getAsSet())) ||
					(o2.value.hasValueGreaterThan(o1.value.getAsSet())))
				return 1;
			return 0;
		}
	};

	final Comparator<IteratorValuePair> descendingComparator = new Comparator<IteratorValuePair>()
	{
		@Override
		public int compare(final @Nullable IteratorValuePair o1, final @Nullable IteratorValuePair o2)
		{
			if((o1==null)||(o1.value.isEmpty()))
				if((o2==null)||(o2.value.isEmpty()))
					return 0;
				else
					return 1;
			if((o2==null)||(o2.value.isEmpty()))
				return -1;
			if((o1.value.hasValueGreaterThan(o2.value.getAsSet())) ||
					((!o2.value.hasValueGreaterThan(o1.value.getAsSet())) && (o1.value.hasValueLowerThan(o2.value.getAsSet()))))
				return -1;
			else if((o2.value.hasValueGreaterThan(o1.value.getAsSet())) ||
					(o2.value.hasValueLowerThan(o1.value.getAsSet())))
				return 1;
			return 0;
		}
	};

	protected abstract class SortedSetIterator
	implements Iterator<ElementarySet>
	{
		PriorityQueue<IteratorValuePair> iteratorQueue = initializeQueue();

		protected abstract PriorityQueue<IteratorValuePair> initializeQueue();

		@Override
		public boolean hasNext()
		{
			return !iteratorQueue.isEmpty();
		}

		@Override
		public ElementarySet next()
		{
			final IteratorValuePair nextItValuePair = iteratorQueue.poll();
			if(nextItValuePair.iterator.hasNext())
				iteratorQueue.add(new IteratorValuePair(nextItValuePair.iterator, nextItValuePair.iterator.next().getAsElementarySet()));
			return nextItValuePair.value;
		}

	}

	public List<? extends Set> splitOnRange(final Set set)
	{
		final Set emptySet = getSetCreationToolbox().makeEmptySet().getAsSet();
		if(set.isEmpty())
		{
			return Arrays.asList(emptySet, emptySet, emptySet);
		}
		final List<Set> r = new ArrayList<Set>(3);
		final ArrayList<Set> before = new ArrayList<Set>(getChildrenCount());
		final ArrayList<Set> inRange = new ArrayList<Set>(getChildrenCount());
		final ArrayList<Set> after = new ArrayList<Set>(getChildrenCount());
		for(final ChildType child:getChildren())
		{
			if(child.isEmpty())
				continue;
			if(child.isDegenerate())
			{
				if(child.isLowerThan(set))
					before.add(child.getAsSet());
				else if(child.isGreaterThan(set))
					after.add(child.getAsSet());
				else
					inRange.add(child.getAsSet());
			}
			else
			{
				final List<? extends Set> currentSplit = child.splitOnRange(set);
				Set elmt = currentSplit.get(0);
				if(!elmt.isEmpty())
					before.add(elmt.getAsSet());
				elmt = currentSplit.get(1);
				if(!elmt.isEmpty())
					inRange.add(elmt.getAsSet());
				elmt = currentSplit.get(2);
				if(!elmt.isEmpty())
					after.add(elmt.getAsSet());
			}
		}

		if(before.isEmpty()) {
			r.add(emptySet);
		} else if(before.size()==1)
			r.add(before.get(0));
		else
			r.add(getSetCreationToolbox().makeComposite(before));

		if(inRange.isEmpty())
			r.add(emptySet);
		else if(inRange.size()==1)
			r.add(inRange.get(0));
		else
			r.add(getSetCreationToolbox().makeComposite(inRange));

		if(after.isEmpty())
			r.add(emptySet);
		else if(after.size()==1)
			r.add(after.get(0));
		else
			r.add(getSetCreationToolbox().makeComposite(after));

		return r;
	}

	public Iterator<? extends ElementarySet> iterator()
	{
		if(getChildrenCount()==0)
			return EmptyIterator.getInstance();
		final ArrayList<Iterator<? extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>>> iteratorList = new ArrayList<Iterator<? extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>>>(getChildrenCount());
		final ArrayList<ElementarySet> valueList = new ArrayList<ElementarySet>(getChildrenCount());
		final ArrayBlockingQueue<ElementarySet> elementaryList = new ArrayBlockingQueue<ElementarySet>(getChildrenCount());
		for(final ChildType child:getChildren())
		{
			if(!child.isEmpty())
			{
				if(child instanceof IElementarySortedSet)
					elementaryList.add(((IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>) child).getAsElementarySet());
				else
				{
					final Iterator<? extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>> it = child.iterator();
					if(it.hasNext())
					{
						iteratorList.add(it);
						valueList.add(it.next().getAsElementarySet());
					}
				}
			}
		}
		if(iteratorList.isEmpty() && elementaryList.isEmpty())
			return EmptyIterator.getInstance();
		return new Iterator<ElementarySet>()
		{
			@Override
			public boolean hasNext()
			{
				if(!elementaryList.isEmpty())
					return true;
				for(final Iterator<? extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>> iterator:iteratorList)
					if (iterator != null)
						return true;
				return false;
			}

			@Override
			public ElementarySet next()
			{
				int minI = -1;
				ElementarySet min = null;
				for(int i = 0 ; i<valueList.size() ; i++)
				{
					if(iteratorList.get(i)!=null)
					{
						if(min == null)
						{
							minI = i;
							min = valueList.get(i);
						}
						else
						{
							final ElementarySet tmpValue = valueList.get(i);
							if(tmpValue.hasValueLowerThan(min.getAsSet()))
							{
								minI = i;
								min = tmpValue;
							}
							else if(!min.hasValueLowerThan(tmpValue.getAsSet()))
							{
								if(tmpValue.isContainedBy(min.getAsSet()))
								{
									if(iteratorList.get(i).hasNext())
										valueList.set(i, iteratorList.get(i).next().getAsElementarySet());
									else
										iteratorList.set(i, null);
								}
								else if(tmpValue.contains(min.getAsSet()))
								{
									if(iteratorList.get(minI).hasNext())
										valueList.set(minI, iteratorList.get(minI).next().getAsElementarySet());
									else
										iteratorList.set(minI, null);
									minI = i;
									min = tmpValue;
								}
							}
						}
					}
				}

				if(min==null)
					if(!elementaryList.isEmpty())
						return elementaryList.poll();
					else
						throw new NoSuchElementException();

				if((!elementaryList.isEmpty())&&(elementaryList.peek().hasValueLowerThan(min.getAsSet())))
				{
					return elementaryList.poll();
				}
				if(iteratorList.get(minI).hasNext())
					return valueList.set(minI, iteratorList.get(minI).next().getAsElementarySet());
				iteratorList.set(minI, null);
				return valueList.get(minI);
			}
		};
	}

	public Iterator<? extends ElementarySet> reverseIterator()
	{
		return new SortedSetIterator()
		{
			@Override
			protected PriorityQueue<IteratorValuePair> initializeQueue()
			{
				final PriorityQueue<IteratorValuePair> r = new PriorityQueue<IteratorValuePair>(Math.max(1, getChildrenCount()), descendingComparator);
				for(final ChildType child:getChildrenDescending())
				{
					if(child.isElementary())
					{
						if(!child.isEmpty())
						{
							@SuppressWarnings("unchecked")
							final
							IElementarySet<Set, SimpleSet, ElementarySet> tmp = (IElementarySet<Set, SimpleSet, ElementarySet>) child;
							final EmptyIterator<IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>> iterator = EmptyIterator.getInstance();
							r.add(new IteratorValuePair(iterator, tmp.getAsElementarySet()));
						}
					}
					else
					{
						final Iterator<? extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>> iterator = child.reverseIterator();
						if(iterator.hasNext())
							r.add(new IteratorValuePair(iterator, iterator.next().getAsElementarySet()));
					}
				}
				return r;
			}
		};
	}

	@Override
	public SimpleSet getSimpleSet()
	{
		if(_simpleSetCache != null)
			return _simpleSetCache;
		final Iterator<Bound> boundIterator = _bridged.getBoundIterator();
		final Iterator<? extends ChildType> childIterator = _bridged.getChildren().iterator();
		final ArrayList<ElementarySet> children = new ArrayList<ElementarySet>(getChildrenCount()*2);
		final ICompositeSortedSetCreationToolbox<Bound, Set, SimpleSet, ElementarySet, ?, ?, CompositeSet, ChildType, Bridged> setCreationToolbox = getSetCreationToolbox();
		ChildType child = null;
		while(boundIterator.hasNext())
		{
			final Bound intervalStart = boundIterator.next();
			final Bound intervalEnd = boundIterator.next();
			while(childIterator.hasNext() && intervalStart.compareTo((child = childIterator.next()).getLowerBound())>0);
			if(intervalStart.compareTo(intervalEnd) == 0)
			{
				if((child instanceof IDegenerateSortedSet) && (intervalEnd.compareTo(child.getLowerBound()) == 0))
					children.add((ElementarySet)child);
				else
					children.add(setCreationToolbox.makeDegenerate(intervalStart).getAsElementarySet());
			} else
				if((child instanceof IInterval) && (intervalStart.compareTo(child.getLowerBound()) == 0) && (intervalEnd.compareTo(child.getUpperBound()) == 0))
					children.add((ElementarySet)child);
				else
					children.add(setCreationToolbox.makeInterval(intervalStart, intervalEnd).getAsElementarySet());
		}
		return _simpleSetCache = setCreationToolbox.makeSimplestSet(children).getAsSimpleSet();

	}

	protected void _addWithChecks(final Collection<ElementarySet> collection,
			final IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet> toAdd) {
		ArrayList<IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>> toMerge;
		toMerge = new ArrayList<IElementarySortedSet<Bound, Set,SimpleSet,ElementarySet>>(collection.size());

		for(final IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet> alreadyInSet:collection)
		{
			if((alreadyInSet.isIntersecting(toAdd.getAsSet())) || alreadyInSet.hasContiguityWith(toAdd.getAsSet()))
			{
				toMerge.add(alreadyInSet);
			}
		}
		if(toMerge.isEmpty()) // si il n'y a pas de lien(intersection ou juste à coté) entre les sets
			collection.add(toAdd.getAsElementarySet());
		else
		{
			Set currentAdd = toAdd.getAsSet();
			for(final IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet> mergeElmt:toMerge)
			{
				if(currentAdd.contains(mergeElmt.getAsSet()))
				{
					collection.remove(mergeElmt);
				}
				else if(currentAdd.isContainedBy(mergeElmt.getAsSet()))
					return;
				else
				{
					collection.remove(mergeElmt);
					currentAdd = currentAdd.unite(mergeElmt.getAsSet()).getAsSet();
				}
			}
			if(currentAdd instanceof IElementarySortedSet)
				collection.add(((IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>)currentAdd).getAsElementarySet());
			else
			{
				final Iterator<? extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>> it = currentAdd.iterator();
				while(it.hasNext())
					_addWithChecks(collection, it.next());
			}
		}
	}
}