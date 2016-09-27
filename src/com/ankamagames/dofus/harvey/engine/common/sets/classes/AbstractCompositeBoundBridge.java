/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class AbstractCompositeBoundBridge
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	CompositeSet extends ICompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ?>,
	ChildType extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Bridged extends ICompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType>
>
extends AbstractBoundBridge<Bound, Set, SimpleSet, ElementarySet, Bridged> {


	final boolean USE_CACHE = true;

	protected int _boundCountCache = -1;

	public ArrayList<Bound> _boundIteratorCache = new ArrayList<Bound>(_bridged.getChildrenCount()*2);

	public AbstractCompositeBoundBridge(final Bridged bridged)
	{
		super(bridged);
		if(!bridged.isEmpty())
			_boundIteratorCache.add(_bridged.getLowerBound());
	}

	boolean cacheIsComplete = false;

	@Override
	public Iterator<Bound> getBoundIterator()
	{

		final Bound lowerBound = _bridged.getLowerBound();
		final Bound upperBound = _bridged.getUpperBound();
		if(lowerBound == null || upperBound == null)
			return EmptyIterator.getInstance();

		if(USE_CACHE && cacheIsComplete)
			return _boundIteratorCache.iterator();
		return new Iterator<Bound>()
		{
			protected PriorityQueue<Bound> lowerBoundList = new PriorityQueue<Bound>();
			final HashMap<Bound, Iterator<Bound>> iteratorMap = new HashMap<Bound, Iterator<Bound>>(_bridged.getChildrenCount()*4);
			ArrayList<Bound> cache = new ArrayList<Bound>(_boundIteratorCache);
			boolean returnsLowerBound = true;
			boolean hasNext = true;
			final Iterator<? extends ChildType> currentChildIt = _bridged.getChildren().iterator();
			ChildType currentChild = currentChildIt.next();

			Bound nextBound = lowerBound;
			boolean useCache = false;
			boolean nextIsCurentChild = true;
			Iterator<Bound> startIt = _boundIteratorCache.iterator();

			public Iterator<Bound> initialize()
			{

				if(USE_CACHE && !_boundIteratorCache.isEmpty())
					useCache = true;

				return this;
			}

			@Override
			public boolean hasNext()
			{
				if(useCache)
					return startIt.hasNext();
				else
					return hasNext;
			}

			@Override
			public Bound next()
			{
				if(useCache)
				{
					final Bound tmp = startIt.next();
					if(!startIt.hasNext())
						_catchUpTheCache();
					return tmp;
				}
				if(returnsLowerBound)
				{
					returnsLowerBound = false;
					return nextBound;
				}
				hasNext = false;
				returnsLowerBound = true;
				final Bound ret = _getUpperBound();
				if(USE_CACHE)
					_addNewBound(ret);
				return ret;
			}

			protected synchronized void _addNewBound(final Bound newBound)
			{
				if(_boundIteratorCache.size() == cache.size())
				{
					_boundIteratorCache.add(newBound);
					if(hasNext)
						_boundIteratorCache.add(nextBound);
				}
				cache.add(newBound);
				if(hasNext)
					cache.add(nextBound);
			}

			protected void _catchUpTheCache()
			{
				final Iterator<Bound> it = cache.iterator();
				Bound lastBound = it.next();
				while(it.hasNext())
				{
					lastBound = it.next();
				}
				nextBound = lastBound;
				Bound currentBound;

				while(currentChildIt.hasNext())
				{
					if(cache.size()>1) // if cache size == 1, currentChild is already initialised
						currentChild = currentChildIt.next();
					else
						break;
					Bound currentLowerBound;
					if(!lowerBoundList.isEmpty())
						currentLowerBound = lowerBoundList.poll();
					final Iterator<Bound> currentBoundIt = currentChild.getBoundIterator();
					if(currentChild.isInterval())
					{
						final int compareTo = currentBoundIt.next().compareTo(lastBound);
						if(compareTo<0)
							continue;
						else
						{
							break;
						}
					}
					else
					{
						final Iterator<Bound> tmpIt = currentChild.getBoundIterator();
						while(tmpIt.hasNext())
						{
							currentBound = tmpIt.next();
							final int compareTo = currentBound.compareTo(lastBound);
							if(compareTo==0)
							{
								if(tmpIt.hasNext())
								{
									nextBound = tmpIt.next();
									lowerBoundList.add(nextBound);
									iteratorMap.put(nextBound, tmpIt);
									nextIsCurentChild = false;
								}
								break;
							}
							if(compareTo > 0)
							{
								lowerBoundList.add(currentBound);
								iteratorMap.put(currentBound, tmpIt);
								nextIsCurentChild = false;
							}
							tmpIt.next();
						}
					}
				}
				returnsLowerBound = false;
				useCache = false;
			}

			protected Bound _getUpperBound()
			{
				// Init
				Bound currentUpperBound;


				if(nextIsCurentChild)
				{
					final Iterator<Bound> boundIt = currentChild.getBoundIterator();
					boundIt.next();
					currentUpperBound = boundIt.next();
				}
				else
				{
					final Iterator<Bound> boundIt = iteratorMap.remove(lowerBoundList.poll());
					currentUpperBound = boundIt.next();
					if(boundIt.hasNext())
					{
						final Bound tmpNextBound = boundIt.next();
						lowerBoundList.add(tmpNextBound);
						iteratorMap.put(tmpNextBound, boundIt);
					}
				}
				// Now I got the naive UpperBound
				if(currentUpperBound == null)
					throw new NullPointerException();
				while(currentChildIt.hasNext())
				{
					if(!lowerBoundList.isEmpty() && (lowerBoundList.peek().compareTo(currentUpperBound)<=0 || lowerBoundList.peek().isSucceeding(currentUpperBound)))
					{
						final Iterator<Bound> partBoundIt = iteratorMap.remove(lowerBoundList.poll());
						final Bound tmpUpperBound = partBoundIt.next();
						if(tmpUpperBound.compareTo(currentUpperBound)>0)
						{
							currentUpperBound = tmpUpperBound;
						}
						if(partBoundIt.hasNext())
						{
							final Bound tmpNextBound = partBoundIt.next();
							lowerBoundList.add(tmpNextBound);
							iteratorMap.put(tmpNextBound, partBoundIt);
						}

					}
					else if(currentChildIt.hasNext())
					{
						if(nextIsCurentChild)
							currentChild = currentChildIt.next();
						if(currentChild.isEmpty())
							break;
						final Bound tmpLowerBound = currentChild.getLowerBound();
						if(tmpLowerBound==null)
							throw new NullPointerException();
						if(tmpLowerBound.compareTo(currentUpperBound)<=0 || tmpLowerBound.isSucceeding(currentUpperBound))
						{
							final Iterator<Bound> tmpBoundIt = currentChild.getBoundIterator();
							tmpBoundIt.next();
							final Bound tmpUpperBound = tmpBoundIt.next();
							if(tmpUpperBound.compareTo(currentUpperBound)> 0)
							{
								currentUpperBound = tmpUpperBound;
							}
							if(tmpBoundIt.hasNext())
							{
								final Bound tmpNextBound = tmpBoundIt.next();
								lowerBoundList.add(tmpNextBound);
								iteratorMap.put(tmpNextBound, tmpBoundIt);
							}
						}
						else
						{
							nextBound = tmpLowerBound;
							nextIsCurentChild = true;
							hasNext = true;
							break;
						}
					}
				}

				if(!lowerBoundList.isEmpty() && lowerBoundList.peek().compareTo(nextBound)<0)
				{
					nextBound = lowerBoundList.peek();
					nextIsCurentChild = false;
					hasNext = true;
				}
				if(!hasNext)
				{
					cacheIsComplete = true;
				}
				return currentUpperBound;


			}
		}.initialize();
	}

	@Override
	public int getBoundCount()
	{
		if(!USE_CACHE)
		{
			final Iterator<? extends ChildType> childIt = _bridged.getChildren().iterator();
			final TreeMap<Bound, Iterator<Bound>> compositeBoundMap = new TreeMap<Bound, Iterator<Bound>>();
			return _getBoundCount(childIt, compositeBoundMap);
		}
		if(_boundCountCache != -1)
			return _boundCountCache;
		if(cacheIsComplete)
			return _boundCountCache = _boundIteratorCache.size();

		final int ret = _boundIteratorCache.size();
		final Iterator<? extends ChildType> childIt = _bridged.getChildren().iterator();
		final TreeMap<Bound, Iterator<Bound>> compositeBoundMap = new TreeMap<Bound, Iterator<Bound>>();
		if(ret == 1)
			return _boundCountCache = _getBoundCount(childIt, compositeBoundMap);
		Bound lastBound = null;
		final Iterator<Bound> it = _boundIteratorCache.iterator();
		while(it.hasNext())
			lastBound = it.next();
		if(lastBound == null)
			return _boundCountCache = 0;

		while(childIt.hasNext())
		{
			final ChildType nextChild = childIt.next();
			final Iterator<Bound> boundIterator = nextChild.getBoundIterator();
			if(nextChild.getBoundCount()<=2)
			{
				boundIterator.next();
				if(boundIterator.next().compareTo(lastBound)==0)// upperBound
					break;
			}
			else
			{
				while(boundIterator.hasNext())
				{
					final Bound next = boundIterator.next();
					if(next.compareTo(lastBound)>0)
					{
						compositeBoundMap.put(next, boundIterator);
						break;
					}
				}
			}
		}
		return _boundCountCache = (ret+_getBoundCount(childIt, compositeBoundMap));

	};

	protected int _getBoundCount(final Iterator<? extends ChildType> childIt, final TreeMap<Bound, Iterator<Bound>> compositeBoundMap)
	{
		if(USE_CACHE && _boundCountCache >= 0)
			return _boundCountCache;

		ChildType nextChild;

		if(childIt.hasNext())
		{
			nextChild = childIt.next();
			if(nextChild.isEmpty())
				return 0;
		}
		else
			return 0;

		final AtomicInteger r = new AtomicInteger(2);

		Bound currentUpperBound;
		if(nextChild.getBoundCount()<=2)
			currentUpperBound = nextChild.getUpperBound();
		else
		{
			final Iterator<Bound> boundIterator = nextChild.getBoundIterator();
			boundIterator.next();
			currentUpperBound = boundIterator.next();
			if(boundIterator.hasNext())
				compositeBoundMap.put(boundIterator.next(), boundIterator);
		}

		if(currentUpperBound == null)
			throw new NullPointerException();

		while(childIt.hasNext())
		{
			nextChild = childIt.next();

			final Bound nextChildLowerBound = nextChild.getLowerBound();
			if(nextChildLowerBound == null)
			{
				break;
			}
			while(!compositeBoundMap.isEmpty() && nextChildLowerBound.compareTo(compositeBoundMap.firstKey()) > 0)
			{
				currentUpperBound = manageFirstEntry(compositeBoundMap, currentUpperBound, r);
			}

			final int nextBoundCount = nextChild.getBoundCount();
			Bound nextChildUpperBound;
			if(nextBoundCount <= 2)
			{
				nextChildUpperBound = nextChild.getUpperBound();
			}
			else
			{
				final Iterator<Bound> childBoundIterator = nextChild.getBoundIterator();
				childBoundIterator.next();
				nextChildUpperBound = childBoundIterator.next();
				if(childBoundIterator.hasNext())
					compositeBoundMap.put(childBoundIterator.next(), childBoundIterator);
			}

			if(nextChildUpperBound == null)
				throw new NullPointerException();

			if(nextChildLowerBound.compareTo(currentUpperBound)<=0 || currentUpperBound.isPreceding(nextChildLowerBound))
			{
				if(currentUpperBound.compareTo(nextChildUpperBound)<0)
					currentUpperBound = nextChildUpperBound;
			}
			else
			{
				r.addAndGet(2);
				currentUpperBound = nextChildUpperBound;
			}
		}
		return _boundCountCache = r.get();
	}

	@SuppressWarnings("null")
	protected Bound manageFirstEntry(final TreeMap<Bound, Iterator<Bound>> compositeBoundMap, Bound currentUpperBound, final AtomicInteger count)
	{
		final Entry<Bound, Iterator<Bound>> entry = compositeBoundMap.pollFirstEntry();
		final Bound lowerBound = entry.getKey();
		final Iterator<Bound> iterator = entry.getValue();
		final Bound upperBound = iterator.next();
		if(lowerBound.compareTo(currentUpperBound)>0 && !currentUpperBound.isPreceding(lowerBound))
		{
			count.addAndGet(2);
			currentUpperBound = upperBound;
		}
		else
		{
			if(currentUpperBound.compareTo(upperBound)<0)
				currentUpperBound = upperBound;
		}
		if(iterator.hasNext())
			compositeBoundMap.put(iterator.next(), iterator);
		return currentUpperBound;
	}
}