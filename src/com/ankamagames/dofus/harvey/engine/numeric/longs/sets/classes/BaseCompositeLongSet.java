/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes;

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
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ICompositeLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongBound;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseCompositeLongSet<ChildType extends ILongSet>
extends	AbstractCompositeSortedSet
<
	ILongBound,
	ILongSet,                                          				// Set
	ISimpleLongSet,                                    				// SimpleSet
	IElementaryLongSet,                                				// ElementarySet
	ICompositeLongSet<?>,                                 			// CompositeSet
	ChildType,                                                         				// ChildType
	TreeSet<ChildType>                                                 				// InternalChildrenCollectionType
>
implements ICompositeLongSet<ChildType>
{
	private static final float MAX_SAMPLE = 200f;

	CompositeLongSetBridge<ChildType> _bridge;
	AbstractCompositeBoundBridge<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ICompositeLongSet<?>, ChildType, BaseCompositeLongSet<ChildType>> _boundBridge;

	CommonCompositeLongSetBridge<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ICompositeLongSet<?>, ChildType,  BaseCompositeLongSet<ChildType>> _compositeBridge;

	protected BaseCompositeLongSet()
	{
		_bridge = new CompositeLongSetBridge<ChildType>(this);
		_compositeBridge = new CommonCompositeLongSetBridge<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ICompositeLongSet<?>, ChildType, BaseCompositeLongSet<ChildType>>(
				this, _bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeBoundBridge<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ICompositeLongSet<?>, ChildType, BaseCompositeLongSet<ChildType>>(this);
	}

	@SuppressWarnings("unchecked")
	protected BaseCompositeLongSet(final ChildType element)
	{
		_bridge = new CompositeLongSetBridge<ChildType>(this, element);
		_compositeBridge = new CommonCompositeLongSetBridge<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ICompositeLongSet<?>, ChildType, BaseCompositeLongSet<ChildType>>(
				this, _bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeBoundBridge<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ICompositeLongSet<?>, ChildType, BaseCompositeLongSet<ChildType>>(this);
	}

	protected BaseCompositeLongSet(final Collection<ChildType> collection)
	{
		_bridge = new CompositeLongSetBridge<ChildType>(this, collection);
		_compositeBridge = new CommonCompositeLongSetBridge<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ICompositeLongSet<?>, ChildType, BaseCompositeLongSet<ChildType>>(
				this, _bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeBoundBridge<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ICompositeLongSet<?>, ChildType, BaseCompositeLongSet<ChildType>>(this);
	}

	@Override
	protected CompositeLongSetBridge<ChildType> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractCompositeBoundBridge<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ICompositeLongSet<?>, ChildType, BaseCompositeLongSet<ChildType>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public ILongSet getAsSet()
	{
		return this;
	}

	@Override
	public ICompositeLongSet<ChildType> getAsComposite()
	{
		return this;
	}

	@Override
	public @Nullable ILongBound getLowerBound()
	{
		return _compositeBridge.getLowerBound();
	}

	@Override
	public @Nullable ILongBound getUpperBound()
	{
		return _compositeBridge.getUpperBound();
	}

	@Override
	public boolean contains(final long value)
	{
		return _compositeBridge.contains(value);
	}

	@Override
	public List<? extends ILongSet> split(final long[] values, final boolean[] isIntervalStart)
	{
		return _compositeBridge.split(values, isIntervalStart);
	}

	@Override
	public List<? extends ILongSet> split(final long... values)
	{
		final boolean[] inNextInterval = new boolean[values.length];
		Arrays.fill(inNextInterval, true);
		return split(values, inNextInterval);
	}

	@Override
	public ISimpleLongSet getSimpleSet()
	{
		return super.getSimpleSet().getAsSimpleSet();
	}

	class IteratorValuePair
	{
		Iterator<Long> iterator;
		long value;

		public IteratorValuePair(final Iterator<Long> _iterator, final long _value)
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
			return (int) Math.signum(o1.value - o2.value);
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
			return (int) Math.signum(o2.value - o1.value);
		}
	};

	protected abstract class LongSetIterator
	implements Iterator<Long>
	{
		PriorityQueue<IteratorValuePair> iteratorQueue = initializeQueue();

		protected abstract PriorityQueue<IteratorValuePair> initializeQueue();

		@Override
		public boolean hasNext()
		{
			return !iteratorQueue.isEmpty();
		}

		@Override
		public Long next()
		{
			final IteratorValuePair nextItValuePair = iteratorQueue.poll();
			if(nextItValuePair.iterator.hasNext())
			{
				final long next = nextItValuePair.iterator.next();
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
	public Iterator<Long> getDataIterator()
	{
		return new LongSetIterator()
		{
			@Override
			protected PriorityQueue<IteratorValuePair> initializeQueue()
			{
				final PriorityQueue<IteratorValuePair> r = new PriorityQueue<IteratorValuePair>(Math.max(1, getChildrenCount()), ascendingComparator);
				for(final ChildType child:getChildren())
				{
					final Iterator<Long> dataIterator = child.getDataIterator();
					if(dataIterator.hasNext())
						r.add(new IteratorValuePair(dataIterator, dataIterator.next()));
				}
				return r;
			}
		};
	}

	@Override
	public Iterator<Long> getReverseDataIterator()
	{
		return new LongSetIterator()
		{
			@Override
			protected PriorityQueue<IteratorValuePair> initializeQueue()
			{
				final PriorityQueue<IteratorValuePair> r = new PriorityQueue<IteratorValuePair>(Math.max(1, getChildrenCount()), descendingComparator);
				for(final ChildType child:getChildrenDescending())
				{
					final Iterator<Long> dataIterator = child.getReverseDataIterator();
					if(dataIterator.hasNext())
						r.add(new IteratorValuePair(dataIterator, dataIterator.next()));
				}
				return r;
			}
		};
	}

	@Override
	public List<Long> sample(final int numberOfSample)
	{
		if(numberOfSample<=0 || numberOfSample > size())
			return sample();
		final long chunk = (long) (size()/(numberOfSample-1));
		final List<Long> ret = new ArrayList<Long>(numberOfSample);
		final Iterator<ILongBound> it = getBoundIterator();
		ILongBound currentLowerBound = it.next();
		ILongBound currentUpperBound = it.next();
		long next = currentLowerBound.getValue();
		ret.add(next);
		long currentChunckProgress = chunk;
		long dist = currentUpperBound.compareTo(currentLowerBound)+1;
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
	public List<Long> sample()
	{
		// the formula of the number of samples
		// I wanted it to grow quickly for little sets and as they goes bigger and bigger the number of samples will be stabilized at MAX_SAMPLE
		// I've stated from the Sigmoïde formula and then stretched it as I wanted. --> http://fooplot.com/plot/gn0f2ulnmz
		//Math.min((1.f/(1.f+(Math.exp(-10.f/200.f)))*200.f*2.f-200.f+1.f), 200.f)
 		final int numberOfSample = (int) Math.min((1.f/(1.f+(Math.exp(-size()/MAX_SAMPLE)))*MAX_SAMPLE*2.f-MAX_SAMPLE+1.f), MAX_SAMPLE);
		return sample(numberOfSample);
	}
}