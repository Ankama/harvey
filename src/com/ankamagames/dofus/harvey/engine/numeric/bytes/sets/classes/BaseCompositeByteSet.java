/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes;

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
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ICompositeByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseCompositeByteSet<ChildType extends IByteSet>
extends	AbstractCompositeSortedSet
<
	IByteBound,
	IByteSet,                                          				// Set
	ISimpleByteSet,                                    				// SimpleSet
	IElementaryByteSet,                                				// ElementarySet
	ICompositeByteSet<?>,                                 			// CompositeSet
	ChildType,                                                         				// ChildType
	TreeSet<ChildType>                                                 				// InternalChildrenCollectionType
>
implements ICompositeByteSet<ChildType>
{
	private static final float MAX_SAMPLE = 200f;

	CompositeByteSetBridge<ChildType> _bridge;
	AbstractCompositeBoundBridge<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, ICompositeByteSet<?>, ChildType, BaseCompositeByteSet<ChildType>> _boundBridge;

	CommonCompositeByteSetBridge<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, ICompositeByteSet<?>, ChildType,  BaseCompositeByteSet<ChildType>> _compositeBridge;

	protected BaseCompositeByteSet()
	{
		_bridge = new CompositeByteSetBridge<ChildType>(this);
		_compositeBridge = new CommonCompositeByteSetBridge<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, ICompositeByteSet<?>, ChildType, BaseCompositeByteSet<ChildType>>(
				this, _bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeBoundBridge<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, ICompositeByteSet<?>, ChildType, BaseCompositeByteSet<ChildType>>(this);
	}

	@SuppressWarnings("unchecked")
	protected BaseCompositeByteSet(final ChildType element)
	{
		_bridge = new CompositeByteSetBridge<ChildType>(this, element);
		_compositeBridge = new CommonCompositeByteSetBridge<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, ICompositeByteSet<?>, ChildType, BaseCompositeByteSet<ChildType>>(
				this, _bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeBoundBridge<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, ICompositeByteSet<?>, ChildType, BaseCompositeByteSet<ChildType>>(this);
	}

	protected BaseCompositeByteSet(final Collection<ChildType> collection)
	{
		_bridge = new CompositeByteSetBridge<ChildType>(this, collection);
		_compositeBridge = new CommonCompositeByteSetBridge<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, ICompositeByteSet<?>, ChildType, BaseCompositeByteSet<ChildType>>(
				this, _bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeBoundBridge<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, ICompositeByteSet<?>, ChildType, BaseCompositeByteSet<ChildType>>(this);
	}

	@Override
	protected CompositeByteSetBridge<ChildType> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractCompositeBoundBridge<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, ICompositeByteSet<?>, ChildType, BaseCompositeByteSet<ChildType>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public IByteSet getAsSet()
	{
		return this;
	}

	@Override
	public ICompositeByteSet<ChildType> getAsComposite()
	{
		return this;
	}

	@Override
	public @Nullable IByteBound getLowerBound()
	{
		return _compositeBridge.getLowerBound();
	}

	@Override
	public @Nullable IByteBound getUpperBound()
	{
		return _compositeBridge.getUpperBound();
	}

	@Override
	public boolean contains(final byte value)
	{
		return _compositeBridge.contains(value);
	}

	@Override
	public List<? extends IByteSet> split(final byte[] values, final boolean[] isIntervalStart)
	{
		return _compositeBridge.split(values, isIntervalStart);
	}

	@Override
	public List<? extends IByteSet> split(final byte... values)
	{
		final boolean[] inNextInterval = new boolean[values.length];
		Arrays.fill(inNextInterval, true);
		return split(values, inNextInterval);
	}

	@Override
	public ISimpleByteSet getSimpleSet()
	{
		return super.getSimpleSet().getAsSimpleSet();
	}

	class IteratorValuePair
	{
		Iterator<Byte> iterator;
		byte value;

		public IteratorValuePair(final Iterator<Byte> _iterator, final byte _value)
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

	protected abstract class ByteSetIterator
	implements Iterator<Byte>
	{
		PriorityQueue<IteratorValuePair> iteratorQueue = initializeQueue();

		protected abstract PriorityQueue<IteratorValuePair> initializeQueue();

		@Override
		public boolean hasNext()
		{
			return !iteratorQueue.isEmpty();
		}

		@Override
		public Byte next()
		{
			final IteratorValuePair nextItValuePair = iteratorQueue.poll();
			if(nextItValuePair.iterator.hasNext())
			{
				final byte next = nextItValuePair.iterator.next();
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
	public Iterator<Byte> getDataIterator()
	{
		return new ByteSetIterator()
		{
			@Override
			protected PriorityQueue<IteratorValuePair> initializeQueue()
			{
				final PriorityQueue<IteratorValuePair> r = new PriorityQueue<IteratorValuePair>(Math.max(1, getChildrenCount()), ascendingComparator);
				for(final ChildType child:getChildren())
				{
					final Iterator<Byte> dataIterator = child.getDataIterator();
					if(dataIterator.hasNext())
						r.add(new IteratorValuePair(dataIterator, dataIterator.next()));
				}
				return r;
			}
		};
	}

	@Override
	public Iterator<Byte> getReverseDataIterator()
	{
		return new ByteSetIterator()
		{
			@Override
			protected PriorityQueue<IteratorValuePair> initializeQueue()
			{
				final PriorityQueue<IteratorValuePair> r = new PriorityQueue<IteratorValuePair>(Math.max(1, getChildrenCount()), descendingComparator);
				for(final ChildType child:getChildrenDescending())
				{
					final Iterator<Byte> dataIterator = child.getReverseDataIterator();
					if(dataIterator.hasNext())
						r.add(new IteratorValuePair(dataIterator, dataIterator.next()));
				}
				return r;
			}
		};
	}

	@Override
	public List<Byte> sample(final int numberOfSample)
	{
		if(numberOfSample<=0 || numberOfSample > size())
			return sample();
		final byte chunk = (byte) (size()/(numberOfSample-1));
		final List<Byte> ret = new ArrayList<Byte>(numberOfSample);
		final Iterator<IByteBound> it = getBoundIterator();
		IByteBound currentLowerBound = it.next();
		IByteBound currentUpperBound = it.next();
		byte next = currentLowerBound.getValue();
		ret.add(next);
		byte currentChunckProgress = chunk;
		byte dist = (byte) (currentUpperBound.compareTo(currentLowerBound)+1);
		while(ret.size()<numberOfSample)
		{
			if(currentChunckProgress <= dist)
			{
				dist-= currentChunckProgress;
				next = (byte) (next+currentChunckProgress);
				currentChunckProgress = chunk;
				ret.add(next);
			}
			else
			{
				currentChunckProgress -= dist;
				currentLowerBound = it.next();
				currentUpperBound = it.next();
				next = currentLowerBound.getValue();
				dist = (byte) (currentUpperBound.compareTo(currentLowerBound)+1);
			}
		}
		return ret;
	}

	@Override
	public List<Byte> sample()
	{
		// the formula of the number of samples
		// I wanted it to grow quickly for little sets and as they goes bigger and bigger the number of samples will be stabilized at MAX_SAMPLE
		// I've stated from the Sigmoïde formula and then stretched it as I wanted. --> http://fooplot.com/plot/gn0f2ulnmz
		//Math.min((1.f/(1.f+(Math.exp(-10.f/200.f)))*200.f*2.f-200.f+1.f), 200.f)
 		final int numberOfSample = (int) Math.min((1.f/(1.f+(Math.exp(-size()/MAX_SAMPLE)))*MAX_SAMPLE*2.f-MAX_SAMPLE+1.f), MAX_SAMPLE);
		return sample(numberOfSample);
	}
}