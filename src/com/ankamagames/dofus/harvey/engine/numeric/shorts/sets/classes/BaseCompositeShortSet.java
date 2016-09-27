/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes;

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
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ICompositeShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseCompositeShortSet<ChildType extends IShortSet>
extends	AbstractCompositeSortedSet
<
	IShortBound,
	IShortSet,                                          				// Set
	ISimpleShortSet,                                    				// SimpleSet
	IElementaryShortSet,                                				// ElementarySet
	ICompositeShortSet<?>,                                 			// CompositeSet
	ChildType,                                                         				// ChildType
	TreeSet<ChildType>                                                 				// InternalChildrenCollectionType
>
implements ICompositeShortSet<ChildType>
{
	private static final float MAX_SAMPLE = 200f;

	CompositeShortSetBridge<ChildType> _bridge;
	AbstractCompositeBoundBridge<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, ICompositeShortSet<?>, ChildType, BaseCompositeShortSet<ChildType>> _boundBridge;

	CommonCompositeShortSetBridge<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, ICompositeShortSet<?>, ChildType,  BaseCompositeShortSet<ChildType>> _compositeBridge;

	protected BaseCompositeShortSet()
	{
		_bridge = new CompositeShortSetBridge<ChildType>(this);
		_compositeBridge = new CommonCompositeShortSetBridge<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, ICompositeShortSet<?>, ChildType, BaseCompositeShortSet<ChildType>>(
				this, _bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeBoundBridge<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, ICompositeShortSet<?>, ChildType, BaseCompositeShortSet<ChildType>>(this);
	}

	@SuppressWarnings("unchecked")
	protected BaseCompositeShortSet(final ChildType element)
	{
		_bridge = new CompositeShortSetBridge<ChildType>(this, element);
		_compositeBridge = new CommonCompositeShortSetBridge<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, ICompositeShortSet<?>, ChildType, BaseCompositeShortSet<ChildType>>(
				this, _bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeBoundBridge<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, ICompositeShortSet<?>, ChildType, BaseCompositeShortSet<ChildType>>(this);
	}

	protected BaseCompositeShortSet(final Collection<ChildType> collection)
	{
		_bridge = new CompositeShortSetBridge<ChildType>(this, collection);
		_compositeBridge = new CommonCompositeShortSetBridge<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, ICompositeShortSet<?>, ChildType, BaseCompositeShortSet<ChildType>>(
				this, _bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeBoundBridge<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, ICompositeShortSet<?>, ChildType, BaseCompositeShortSet<ChildType>>(this);
	}

	@Override
	protected CompositeShortSetBridge<ChildType> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractCompositeBoundBridge<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, ICompositeShortSet<?>, ChildType, BaseCompositeShortSet<ChildType>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public IShortSet getAsSet()
	{
		return this;
	}

	@Override
	public ICompositeShortSet<ChildType> getAsComposite()
	{
		return this;
	}

	@Override
	public @Nullable IShortBound getLowerBound()
	{
		return _compositeBridge.getLowerBound();
	}

	@Override
	public @Nullable IShortBound getUpperBound()
	{
		return _compositeBridge.getUpperBound();
	}

	@Override
	public boolean contains(final short value)
	{
		return _compositeBridge.contains(value);
	}

	@Override
	public List<? extends IShortSet> split(final short[] values, final boolean[] isIntervalStart)
	{
		return _compositeBridge.split(values, isIntervalStart);
	}

	@Override
	public List<? extends IShortSet> split(final short... values)
	{
		final boolean[] inNextInterval = new boolean[values.length];
		Arrays.fill(inNextInterval, true);
		return split(values, inNextInterval);
	}

	@Override
	public ISimpleShortSet getSimpleSet()
	{
		return super.getSimpleSet().getAsSimpleSet();
	}

	class IteratorValuePair
	{
		Iterator<Short> iterator;
		short value;

		public IteratorValuePair(final Iterator<Short> _iterator, final short _value)
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

	protected abstract class ShortSetIterator
	implements Iterator<Short>
	{
		PriorityQueue<IteratorValuePair> iteratorQueue = initializeQueue();

		protected abstract PriorityQueue<IteratorValuePair> initializeQueue();

		@Override
		public boolean hasNext()
		{
			return !iteratorQueue.isEmpty();
		}

		@Override
		public Short next()
		{
			final IteratorValuePair nextItValuePair = iteratorQueue.poll();
			if(nextItValuePair.iterator.hasNext())
			{
				final short next = nextItValuePair.iterator.next();
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
	public Iterator<Short> getDataIterator()
	{
		return new ShortSetIterator()
		{
			@Override
			protected PriorityQueue<IteratorValuePair> initializeQueue()
			{
				final PriorityQueue<IteratorValuePair> r = new PriorityQueue<IteratorValuePair>(Math.max(1, getChildrenCount()), ascendingComparator);
				for(final ChildType child:getChildren())
				{
					final Iterator<Short> dataIterator = child.getDataIterator();
					if(dataIterator.hasNext())
						r.add(new IteratorValuePair(dataIterator, dataIterator.next()));
				}
				return r;
			}
		};
	}

	@Override
	public Iterator<Short> getReverseDataIterator()
	{
		return new ShortSetIterator()
		{
			@Override
			protected PriorityQueue<IteratorValuePair> initializeQueue()
			{
				final PriorityQueue<IteratorValuePair> r = new PriorityQueue<IteratorValuePair>(Math.max(1, getChildrenCount()), descendingComparator);
				for(final ChildType child:getChildrenDescending())
				{
					final Iterator<Short> dataIterator = child.getReverseDataIterator();
					if(dataIterator.hasNext())
						r.add(new IteratorValuePair(dataIterator, dataIterator.next()));
				}
				return r;
			}
		};
	}

	@Override
	public List<Short> sample(final int numberOfSample)
	{
		if(numberOfSample<=0 || numberOfSample > size())
			return sample();
		final short chunk = (short) (size()/(numberOfSample-1));
		final List<Short> ret = new ArrayList<Short>(numberOfSample);
		final Iterator<IShortBound> it = getBoundIterator();
		IShortBound currentLowerBound = it.next();
		IShortBound currentUpperBound = it.next();
		short next = currentLowerBound.getValue();
		ret.add(next);
		short currentChunckProgress = chunk;
		short dist = (short) (currentUpperBound.compareTo(currentLowerBound)+1);
		while(ret.size()<numberOfSample)
		{
			if(currentChunckProgress <= dist)
			{
				dist-= currentChunckProgress;
				next = (short) (next+currentChunckProgress);
				currentChunckProgress = chunk;
				ret.add(next);
			}
			else
			{
				currentChunckProgress -= dist;
				currentLowerBound = it.next();
				currentUpperBound = it.next();
				next = currentLowerBound.getValue();
				dist = (short) (currentUpperBound.compareTo(currentLowerBound)+1);
			}
		}
		return ret;
	}

	@Override
	public List<Short> sample()
	{
		// the formula of the number of samples
		// I wanted it to grow quickly for little sets and as they goes bigger and bigger the number of samples will be stabilized at MAX_SAMPLE
		// I've stated from the Sigmoïde formula and then stretched it as I wanted. --> http://fooplot.com/plot/gn0f2ulnmz
		//Math.min((1.f/(1.f+(Math.exp(-10.f/200.f)))*200.f*2.f-200.f+1.f), 200.f)
 		final int numberOfSample = (int) Math.min((1.f/(1.f+(Math.exp(-size()/MAX_SAMPLE)))*MAX_SAMPLE*2.f-MAX_SAMPLE+1.f), MAX_SAMPLE);
		return sample(numberOfSample);
	}
}