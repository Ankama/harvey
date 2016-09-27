/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeSortedSet;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.SurroundingValuesProvider;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ICompositeSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementarySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISortedGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseCompositeSortedGenericSet<Data, ChildType extends ISortedGenericSet<Data>>
extends	AbstractCompositeSortedSet
<
	IGenericBound<Data>,
	ISortedGenericSet<Data>,                                          				// Set
	ISimpleSortedGenericSet<Data>,                                    				// SimpleSet
	IElementarySortedGenericSet<Data>,                                				// ElementarySet
	ICompositeSortedGenericSet<Data, ?>,                                 			// CompositeSet
	ChildType,                                                         				// ChildType
	TreeSet<ChildType>                                                 				// InternalChildrenCollectionType
>
implements ICompositeSortedGenericSet<Data, ChildType>
{
	private static final float MAX_SAMPLE = 200f;
	protected Comparator<? super Data>																		_comparator;
	protected SurroundingValuesProvider<Data>																_surroundingProvider;

	CompositeSortedGenericSetBridge<Data, ChildType> _bridge;
	AbstractCompositeBoundBridge<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, ICompositeSortedGenericSet<Data, ?>, ChildType, BaseCompositeSortedGenericSet<Data, ChildType>> _boundBridge;

	CommonCompositeSortedGenericSetBridge<Data, IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, ICompositeSortedGenericSet<Data, ?>, ChildType,  BaseCompositeSortedGenericSet<Data, ChildType>> _compositeBridge;

	protected BaseCompositeSortedGenericSet(final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		_bridge = new CompositeSortedGenericSetBridge<Data, ChildType>(this, comparator, surroundingProvider);
		_compositeBridge = new CommonCompositeSortedGenericSetBridge<Data, IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, ICompositeSortedGenericSet<Data,?>, ChildType, BaseCompositeSortedGenericSet<Data,ChildType>>(
				this, comparator, _bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeBoundBridge<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, ICompositeSortedGenericSet<Data,?>, ChildType, BaseCompositeSortedGenericSet<Data,ChildType>>(this);
		_comparator = comparator;
		_surroundingProvider = surroundingProvider;
	}

	@SuppressWarnings("unchecked")
	protected BaseCompositeSortedGenericSet(final ChildType element, final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		_bridge = new CompositeSortedGenericSetBridge<Data, ChildType>(this, comparator, surroundingProvider, element);
		_compositeBridge = new CommonCompositeSortedGenericSetBridge<Data, IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, ICompositeSortedGenericSet<Data,?>, ChildType, BaseCompositeSortedGenericSet<Data,ChildType>>(
				this, comparator, _bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeBoundBridge<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, ICompositeSortedGenericSet<Data,?>, ChildType, BaseCompositeSortedGenericSet<Data,ChildType>>(this);
		_comparator = comparator;
		_surroundingProvider = surroundingProvider;
	}

	protected BaseCompositeSortedGenericSet(final Collection<ChildType> collection, final Comparator<? super Data> comparator,
		final SurroundingValuesProvider<Data> surroundingProvider)
	{
		_bridge = new CompositeSortedGenericSetBridge<Data, ChildType>(this, comparator, surroundingProvider, collection);
		_compositeBridge = new CommonCompositeSortedGenericSetBridge<Data, IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, ICompositeSortedGenericSet<Data,?>, ChildType, BaseCompositeSortedGenericSet<Data,ChildType>>(
				this, comparator, _bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeBoundBridge<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, ICompositeSortedGenericSet<Data,?>, ChildType, BaseCompositeSortedGenericSet<Data,ChildType>>(this);
		_comparator = comparator;
		_surroundingProvider = surroundingProvider;
	}

	@Override
	protected CompositeSortedGenericSetBridge<Data, ChildType> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractCompositeBoundBridge<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, ICompositeSortedGenericSet<Data,?>, ChildType, BaseCompositeSortedGenericSet<Data,ChildType>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public ISortedGenericSet<Data> getAsSet()
	{
		return this;
	}

	@Override
	public ICompositeSortedGenericSet<Data, ChildType> getAsComposite()
	{
		return this;
	}

	@Override
	public @Nullable IGenericBound<Data> getLowerBound()
	{
		return _compositeBridge.getLowerBound();
	}

	@Override
	public @Nullable IGenericBound<Data> getUpperBound()
	{
		return _compositeBridge.getUpperBound();
	}

	@Override
	public boolean contains(@Nullable final Data value)
	{
		return _compositeBridge.contains(value);
	}

	@Override
	public List<? extends ISortedGenericSet<Data>> split(final Data[] values, final boolean[] isIntervalStart)
	{
		return _compositeBridge.split(values, isIntervalStart);
	}

	@Override
	public List<? extends ISortedGenericSet<Data>> split(final Data... values)
	{
		final boolean[] inNextInterval = new boolean[values.length];
		Arrays.fill(inNextInterval, true);
		return split(values, inNextInterval);
	}

	@Override
	public ISimpleSortedGenericSet<Data> getSimpleSet()
	{
		return super.getSimpleSet().getAsSimpleSet();
	}

	class IteratorValuePair
	{
		Iterator<Data> iterator;
		@Nullable Data value;

		public IteratorValuePair(final Iterator<Data> _iterator, final @Nullable Data _value)
		{
			iterator = _iterator;
			value = _value;
		}
	}

	Comparator<IteratorValuePair> ascendingComparator = new Comparator<IteratorValuePair>()
	{
		@Override
		public int compare(final @Nullable IteratorValuePair o1, final @Nullable IteratorValuePair o2)
		{
			if(o1 == null)
				if(o2 == null)
					return 0;
				else
					return 1;
			if(o2 == null)
				return -1;
			return _comparator.compare(o1.value, o2.value);
		}
	};

	Comparator<IteratorValuePair> descendingComparator = new Comparator<IteratorValuePair>()
	{
		@Override
		public int compare(final @Nullable IteratorValuePair o1, final @Nullable IteratorValuePair o2)
		{
			if(o1==null)
				if(o2==null)
					return 0;
				else
					return -1;
			if(o2==null)
				return 1;
			return _comparator.compare(o2.value, o1.value);
		}
	};

	protected abstract class GenericSortedSetIterator
	implements Iterator<Data>
	{
		PriorityQueue<IteratorValuePair> iteratorQueue = initializeQueue();

		protected abstract PriorityQueue<IteratorValuePair> initializeQueue();

		@Override
		public boolean hasNext()
		{
			return !iteratorQueue.isEmpty();
		}

		@Override
		public @Nullable Data next()
		{
			final IteratorValuePair nextItValuePair = iteratorQueue.poll();
			if(nextItValuePair.iterator.hasNext())
			{
				final Data next = nextItValuePair.iterator.next();
				final Iterator<IteratorValuePair> it = iteratorQueue.iterator();
				final LinkedList<IteratorValuePair> toAdd = new LinkedList<IteratorValuePair>();
				while(it.hasNext())
				{
					final IteratorValuePair currentIt = it.next();
					if(currentIt.value != null && currentIt.value.equals(next))
						it.remove();
						if(currentIt.iterator.hasNext())
						{
							currentIt.value = currentIt.iterator.next();
							toAdd.add(currentIt);
						}
				}
				iteratorQueue.addAll(toAdd);
				iteratorQueue.add(new IteratorValuePair(nextItValuePair.iterator, next));
			}
			return nextItValuePair.value;
		}
	}

	@Override
	public Iterator<Data> getDataIterator()
	{
		return new GenericSortedSetIterator()
		{
			@Override
			protected PriorityQueue<IteratorValuePair> initializeQueue()
			{
				final PriorityQueue<IteratorValuePair> r = new PriorityQueue<IteratorValuePair>(Math.max(1, getChildrenCount()), ascendingComparator);
				for(final ChildType child:getChildren())
				{
					final Iterator<Data> dataIterator = child.getDataIterator();
					if(dataIterator.hasNext())
						r.add(new IteratorValuePair(dataIterator, dataIterator.next()));
				}
				return r;
			}
		};
	}

	@Override
	public Iterator<Data> getReverseDataIterator()
	{
		return new GenericSortedSetIterator()
		{
			@Override
			protected PriorityQueue<IteratorValuePair> initializeQueue()
			{
				final PriorityQueue<IteratorValuePair> r = new PriorityQueue<IteratorValuePair>(Math.max(1, getChildrenCount()), descendingComparator);
				for(final ChildType child:getChildrenDescending())
				{
					final Iterator<Data> dataIterator = child.getReverseDataIterator();
					if(dataIterator.hasNext())
						r.add(new IteratorValuePair(dataIterator, dataIterator.next()));
				}
				return r;
			}
		};
	}

	@Override
	public List<Data> sample(final int numberOfSample)
	{
		if(numberOfSample<=0 || numberOfSample > size())
			return sample();
		final int chunk = (int) (size()/(numberOfSample-1));
		final List<Data> ret = new ArrayList<Data>(numberOfSample);
		final Iterator<IGenericBound<Data>> it = getBoundIterator();
		IGenericBound<Data> currentLowerBound = it.next();
		IGenericBound<Data> currentUpperBound = it.next();
		Data next = currentLowerBound.getValue();
		ret.add(next);
		int currentChunckProgress = chunk;
		int dist = currentUpperBound.compareTo(currentLowerBound)+1;
		while(ret.size()<numberOfSample)
		{
			if(currentChunckProgress <= dist)
			{
				dist-= currentChunckProgress;
				next = _surroundingProvider.getSuccessor(next, currentChunckProgress);
				currentChunckProgress = chunk;
				ret.add(next);
			}
			else
			{
				currentChunckProgress -= dist;
				currentLowerBound = it.next();
				currentUpperBound = it.next();
				next = currentLowerBound.getValue();
				dist = currentUpperBound.compareTo(currentLowerBound)+1;
			}
		}
		return ret;
	}

	@Override
	public List<Data> sample()
	{
		// the formula of the number of samples
		// I wanted it to grow quickly for little sets and as they goes bigger and bigger the number of samples will be stabilized at MAX_SAMPLE
		// I've stated from the Sigmoïde formula and then stretched it as I wanted. --> http://fooplot.com/plot/gn0f2ulnmz
		//Math.min((1.f/(1.f+(Math.exp(-10.f/200.f)))*200.f*2.f-200.f+1.f), 200.f)
 		final int numberOfSample = (int) Math.min((1.f/(1.f+(Math.exp(-size()/MAX_SAMPLE)))*MAX_SAMPLE*2.f-MAX_SAMPLE+1.f), MAX_SAMPLE);
		return sample(numberOfSample);
	}
}