/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes;

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
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ICompositeIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerBound;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseCompositeIntegerSet<ChildType extends IIntegerSet>
extends	AbstractCompositeSortedSet
<
	IIntegerBound,
	IIntegerSet,                                          				// Set
	ISimpleIntegerSet,                                    				// SimpleSet
	IElementaryIntegerSet,                                				// ElementarySet
	ICompositeIntegerSet<?>,                                 			// CompositeSet
	ChildType,                                                         				// ChildType
	TreeSet<ChildType>                                                 				// InternalChildrenCollectionType
>
implements ICompositeIntegerSet<ChildType>
{
	private static final float MAX_SAMPLE = 200f;

	CompositeIntegerSetBridge<ChildType> _bridge;
	AbstractCompositeBoundBridge<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, ICompositeIntegerSet<?>, ChildType, BaseCompositeIntegerSet<ChildType>> _boundBridge;

	CommonCompositeIntegerSetBridge<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, ICompositeIntegerSet<?>, ChildType,  BaseCompositeIntegerSet<ChildType>> _compositeBridge;

	protected BaseCompositeIntegerSet()
	{
		_bridge = new CompositeIntegerSetBridge<ChildType>(this);
		_compositeBridge = new CommonCompositeIntegerSetBridge<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, ICompositeIntegerSet<?>, ChildType, BaseCompositeIntegerSet<ChildType>>(
				this, _bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeBoundBridge<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, ICompositeIntegerSet<?>, ChildType, BaseCompositeIntegerSet<ChildType>>(this);
	}

	@SuppressWarnings("unchecked")
	protected BaseCompositeIntegerSet(final ChildType element)
	{
		_bridge = new CompositeIntegerSetBridge<ChildType>(this, element);
		_compositeBridge = new CommonCompositeIntegerSetBridge<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, ICompositeIntegerSet<?>, ChildType, BaseCompositeIntegerSet<ChildType>>(
				this, _bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeBoundBridge<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, ICompositeIntegerSet<?>, ChildType, BaseCompositeIntegerSet<ChildType>>(this);
	}

	protected BaseCompositeIntegerSet(final Collection<ChildType> collection)
	{
		_bridge = new CompositeIntegerSetBridge<ChildType>(this, collection);
		_compositeBridge = new CommonCompositeIntegerSetBridge<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, ICompositeIntegerSet<?>, ChildType, BaseCompositeIntegerSet<ChildType>>(
				this, _bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeBoundBridge<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, ICompositeIntegerSet<?>, ChildType, BaseCompositeIntegerSet<ChildType>>(this);
	}

	@Override
	protected CompositeIntegerSetBridge<ChildType> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractCompositeBoundBridge<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, ICompositeIntegerSet<?>, ChildType, BaseCompositeIntegerSet<ChildType>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public IIntegerSet getAsSet()
	{
		return this;
	}

	@Override
	public ICompositeIntegerSet<ChildType> getAsComposite()
	{
		return this;
	}

	@Override
	public @Nullable IIntegerBound getLowerBound()
	{
		return _compositeBridge.getLowerBound();
	}

	@Override
	public @Nullable IIntegerBound getUpperBound()
	{
		return _compositeBridge.getUpperBound();
	}

	@Override
	public boolean contains(final int value)
	{
		return _compositeBridge.contains(value);
	}

	@Override
	public List<? extends IIntegerSet> split(final int[] values, final boolean[] isIntervalStart)
	{
		return _compositeBridge.split(values, isIntervalStart);
	}

	@Override
	public List<? extends IIntegerSet> split(final int... values)
	{
		final boolean[] inNextInterval = new boolean[values.length];
		Arrays.fill(inNextInterval, true);
		return split(values, inNextInterval);
	}

	@Override
	public ISimpleIntegerSet getSimpleSet()
	{
		return super.getSimpleSet().getAsSimpleSet();
	}

	class IteratorValuePair
	{
		Iterator<Integer> iterator;
		int value;

		public IteratorValuePair(final Iterator<Integer> _iterator, final int _value)
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
			return o1.value - o2.value;
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
			return (o2.value - o1.value);
		}
	};

	protected abstract class IntegerSetIterator
	implements Iterator<Integer>
	{
		PriorityQueue<IteratorValuePair> iteratorQueue = initializeQueue();

		protected abstract PriorityQueue<IteratorValuePair> initializeQueue();

		@Override
		public boolean hasNext()
		{
			return !iteratorQueue.isEmpty();
		}

		@Override
		public Integer next()
		{
			final IteratorValuePair nextItValuePair = iteratorQueue.poll();
			if(nextItValuePair.iterator.hasNext())
			{
				final int next = nextItValuePair.iterator.next();
				final Iterator<IteratorValuePair> it = iteratorQueue.iterator();
				final LinkedList<IteratorValuePair> toAdd = new LinkedList<IteratorValuePair>();
				while(it.hasNext())
				{
					final IteratorValuePair currentIt = it.next();
					if(currentIt.value == next)
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
	public Iterator<Integer> getDataIterator()
	{
		return new IntegerSetIterator()
		{
			@Override
			protected PriorityQueue<IteratorValuePair> initializeQueue()
			{
				final PriorityQueue<IteratorValuePair> r = new PriorityQueue<IteratorValuePair>(Math.max(1, getChildrenCount()), ascendingComparator);
				for(final ChildType child:getChildren())
				{
					final Iterator<Integer> dataIterator = child.getDataIterator();
					if(dataIterator.hasNext())
						r.add(new IteratorValuePair(dataIterator, dataIterator.next()));
				}
				return r;
			}
		};
	}

	@Override
	public Iterator<Integer> getReverseDataIterator()
	{
		return new IntegerSetIterator()
		{
			@Override
			protected PriorityQueue<IteratorValuePair> initializeQueue()
			{
				final PriorityQueue<IteratorValuePair> r = new PriorityQueue<IteratorValuePair>(Math.max(1, getChildrenCount()), descendingComparator);
				for(final ChildType child:getChildrenDescending())
				{
					final Iterator<Integer> dataIterator = child.getReverseDataIterator();
					if(dataIterator.hasNext())
						r.add(new IteratorValuePair(dataIterator, dataIterator.next()));
				}
				return r;
			}
		};
	}

	@Override
	public List<Integer> sample(final int numberOfSample)
	{
		if(numberOfSample<=0 || numberOfSample > size())
			return sample();
		final int chunk = (int) (size()/(numberOfSample-1));
		final List<Integer> ret = new ArrayList<Integer>(numberOfSample);
		final Iterator<IIntegerBound> it = getBoundIterator();
		IIntegerBound currentLowerBound = it.next();
		IIntegerBound currentUpperBound = it.next();
		int next = currentLowerBound.getValue();
		ret.add(next);
		int currentChunckProgress = chunk;
		int dist = currentUpperBound.compareTo(currentLowerBound)+1;
		while(ret.size()<numberOfSample)
		{
			if(currentChunckProgress <= dist)
			{
				dist-= currentChunckProgress;
				next = next+currentChunckProgress;
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
	public List<Integer> sample()
	{
		// the formula of the number of samples
		// I wanted it to grow quickly for little sets and as they goes bigger and bigger the number of samples will be stabilized at MAX_SAMPLE
		// I've stated from the Sigmoïde formula and then stretched it as I wanted. --> http://fooplot.com/plot/gn0f2ulnmz
		//Math.min((1.f/(1.f+(Math.exp(-10.f/200.f)))*200.f*2.f-200.f+1.f), 200.f)
 		final int numberOfSample = (int) Math.min((1.f/(1.f+(Math.exp(-size()/MAX_SAMPLE)))*MAX_SAMPLE*2.f-MAX_SAMPLE+1.f), MAX_SAMPLE);
		return sample(numberOfSample);
	}
}