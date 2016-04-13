/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractBridgedCompositeContinuousSet;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparator;
import com.ankamagames.dofus.harvey.generic.sets.classes.EmptyContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ICompositeContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericSet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedCompositeContinuousGenericSet
<
	Data,
	ChildType extends IContinuousGenericSet<Data>,
	Bridged extends ICompositeContinuousGenericSet<Data, ChildType>
>
extends
AbstractBridgedCompositeContinuousSet
<
	IContinuousGenericSet<Data>,
	ChildType,
	Bridged
>
{
	protected Bridged _bridged;
	
	protected TreeSet<ChildType>			_children;
	protected TreeSet<ChildType>			_descChildren;
	
	public BridgedCompositeContinuousGenericSet(final Bridged bridged)
	{
		_bridged = bridged;
		_children = new TreeSet<ChildType>(createAscendingComparator());
		_descChildren = new TreeSet<ChildType>(createDescendingComparator());
	}
	
	public BridgedCompositeContinuousGenericSet(final Bridged bridged, final ChildType element)
	{
		this(bridged);
		_children.add(element);
		_descChildren.add(element);
	}
	
	public BridgedCompositeContinuousGenericSet(final Bridged bridged, final Collection<? extends ChildType> collection)
	{
		this(bridged);
		for(final ChildType child:collection)
		{
			_children.add(child);
			_descChildren.add(child);
		}
	}

	private Comparator<? super ChildType> createAscendingComparator()
	{
		return new Comparator<ChildType>()
		{
			@Override
			public int compare(@Nullable final ChildType o1, @Nullable final ChildType o2)
			{
				if(o1==null)
					if(o2==null)
						return 0;
					else
						return 1;
				if(o2==null)
					return -1;
				final double compare = _bridged.getComparator().compare(o1.getLowerBound(), o2.getLowerBound());
				if(compare!=0)
					return (compare>0)?1:-1;
				
				if(o1.isLowerBoundClosed())
					if(o2.isLowerBoundClosed())
						return 0;
					else
						return 1;

				if(o2.isLowerBoundClosed())
					return -1;
				return 0;
			}
		};
	}

	private Comparator<? super ChildType> createDescendingComparator()
	{
		return new Comparator<ChildType>()
		{
			@Override
			public int compare(@Nullable final ChildType o1, @Nullable final ChildType o2)
			{
				if(o1==null)
					if(o2==null)
						return 0;
					else
						return -1;
				if(o2==null)
					return 1;
				final double compare = _bridged.getComparator().compare(o2.getUpperBound(), o1.getUpperBound());
				if(compare!=0)
					return (compare>0)?1:-1;
				
				if(o1.isUpperBoundClosed())
					if(o2.isUpperBoundClosed())
						return 0;
					else
						return 1;

				if(o2.isUpperBoundClosed())
					return -1;
				return 0;
			}
		};
	}

	@Override
	public TreeSet<ChildType> getChildren()
	{
		return _children;
	}
	
	@Override
	public TreeSet<ChildType> getChildrenDescending()
	{
		return _descChildren;
	}

	public @Nullable Data getLowerBound()
	{
		return getChildren().first().getLowerBound();
	}

	public @Nullable Data getUpperBound()
	{
		return getChildrenDescending().first().getUpperBound();
	}

	public boolean isLowerBoundClosed()
	{
		return getChildren().first().isLowerBoundClosed();
	}

	public boolean isUpperBoundClosed()
	{
		return getChildrenDescending().first().isUpperBoundClosed();
	}

	public boolean isInterval()
	{
		final BaseContinuousGenericSet<Data, ?> allBoundaries = getMergedSet();
		return allBoundaries.getChildren().size()<=1;
	}
	
	public boolean contains(@Nullable final Data value)
	{
		for(final ChildType child:getChildren())
		{
			if(child.contains(value))
				return true;
		}
		return false;
	}

	public boolean containsAllValuesInRange(final IContinuousGenericSet<Data> set)
	{
		return containsAllValuesFromAndUpTo(set.getLowerBound(), set.isLowerBoundClosed(), set.getUpperBound(), set.isUpperBoundClosed());
	}
	
	public boolean containsAllValuesFromAndUpTo(@Nullable final Data value, @Nullable final Data upTo)
	{
		return containsAllValuesFromAndUpTo(value, true, upTo, true);
	}
	
	public boolean containsAllValuesFromAndUpTo(@Nullable final Data value, final boolean valueIncluded, @Nullable final Data upTo, final boolean upToIncluded)
	{
		final BaseContinuousGenericSet<Data, ?> mergedSet = getMergedSet();
		for(final IContinuousGenericSet<Data> child:mergedSet.getChildren())
		{
			if((child.contains(value)) || ((!valueIncluded) && ((_bridged.getComparator().compare(child.getLowerBound(), value) == 0) && (!child.isLowerBoundClosed()))))
				if((child.contains(upTo)) || ((!upToIncluded) && ((_bridged.getComparator().compare(child.getUpperBound(), upTo) == 0) && (!child.isUpperBoundClosed()))))
					return true;
				else
					return false;
		}
		return false;
	}

	@Override
	public boolean contains(final IContinuousGenericSet<Data> set)
	{
		if(set.isEmpty())
			return true;
		
		if(isEmpty())
			return false;
		
		if(set.isDegenerate())
		{
			for(final ChildType child:getChildren())
			{
				if(child.contains(set))
					return true;
			}
			return false;
		}
		
		if(isDegenerate())
		{
			return false;
		}
		
		if(!hasValueInRange(set))
			return false;
		
		if(set.isInterval())
		{
			return containsAllValuesInRange(set);
		}
		
		for(final IContinuousGenericSet<Data> element:set.getMergedSet().getChildren())
		{
			if(!contains(element))
				return false;
		}
		return true;
	}

	protected List<? extends IContinuousGenericSet<Data>> buildCompositeFromArrays(
		final ArrayList<ArrayList<IContinuousGenericSet<Data>>> arrays, final ContinuousComparator<? super Data> comparator)
	{
		final ArrayList<IContinuousGenericSet<Data>> r =
				new ArrayList<IContinuousGenericSet<Data>>(arrays.size());
		
		for(final ArrayList<IContinuousGenericSet<Data>> array:arrays)
		{
			if(array.size()==1)
				r.add(array.get(0));
			else
				r.add(BaseContinuousGenericSet.makeSet(array, comparator));
		}
		
		return r;
	}


	@SuppressWarnings("unchecked")
	public List<? extends IContinuousGenericSet<Data>> splitOnRange(final IContinuousGenericSet<Data> set)
	{
		return split((Data[])new Object[]{set.getLowerBound(), set.getUpperBound()}, new boolean[]{set.isLowerBoundClosed(), set.isUpperBoundClosed()});
	}
	
	public List<? extends IContinuousGenericSet<Data>> split(final Data[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IContinuousGenericSet<Data>> r =
				new ArrayList<IContinuousGenericSet<Data>>(values.length+1);

		final EmptyContinuousGenericSet<Data> empty = EmptyContinuousGenericSet.getInstance();
		int i;
		Data value = null;
		final ContinuousComparator<? super Data> comparator = _bridged.getComparator();
		for(i = 0 ; i < values.length ; i++)
		{
			value = values[i];
			final double compare = comparator.compare(getLowerBound(), value);
			if((compare<0)||((compare==0)&&(isLowerBoundClosed())&&(!isIntervalStart[i])))
				break;
			r.add(empty);
		}
		if(i == values.length)
		{
			r.add(_bridged);
			return r;
		}
		
		int j;
		for(j = values.length-1 ; j >= i ; j--)	//attention au isInterval : on peut en avoir deux avec des isInterval différents
		{
			value = values[j];
			final double compare = comparator.compare(getUpperBound(), value);
			if((compare>0)||((compare==0)&&(isUpperBoundClosed())&&(isIntervalStart[j])))
				break;
		}
		if(j < i)
		{
			r.add(_bridged);
			for(j++ ; j < values.length ; j++)
				r.add(empty);
			return r;
		}
		
		final ArrayList<IContinuousGenericSet<Data>> emptyArray = new ArrayList<IContinuousGenericSet<Data>>(1);
		emptyArray.add(empty);

		final ArrayList<ArrayList<IContinuousGenericSet<Data>>> tmpBuffer =
				new ArrayList<ArrayList<IContinuousGenericSet<Data>>>((j+2)-i);		//j+2 = values.length - (values.length-1 - j) + 1
		Collections.fill(tmpBuffer, emptyArray);
		
		final Data[] lValues = Arrays.copyOfRange(values, i, j+1);
		final boolean[] lIsIntervalStart = Arrays.copyOfRange(isIntervalStart, i, j+1);
		for(final ChildType child : _bridged.getChildren())
		{
			final List<? extends IContinuousGenericSet<Data>> childSplit = child.split(lValues, lIsIntervalStart);
			int k = i;
			for(final IContinuousGenericSet<Data> elmt:childSplit)
			{
				if(!elmt.isEmpty())
				{
					final ArrayList<IContinuousGenericSet<Data>> arrayList = tmpBuffer.get(k);
					if((arrayList.size()==1)&&(arrayList.contains(emptyArray)))
						arrayList.clear();
					arrayList.add(elmt);
				}
					
				k++;
			}
		}
		
		final List<? extends IContinuousGenericSet<Data>> compositeArray = buildCompositeFromArrays(tmpBuffer, _bridged.getComparator());
		for(final IContinuousGenericSet<Data> composite:compositeArray)
		{
			r.add(i, composite);
			i++;
		}
		
		return r;
	}

	public BaseContinuousGenericSet<Data, ?> getMergedSet()
	{
		final ArrayList<IContinuousGenericSet<Data>> collection = new ArrayList<IContinuousGenericSet<Data>>(4);
		final TreeSet<IContinuousGenericSet<Data>> toMerge = new TreeSet<IContinuousGenericSet<Data>>(
				new Comparator<IContinuousGenericSet<Data>>()
					{
						@Override
						public int compare(@Nullable final IContinuousGenericSet<Data> o1, @Nullable final IContinuousGenericSet<Data> o2)
						{
							if(o1==null)
								if(o2==null)
									return 0;
								else
									return 1;
							if(o2==null)
								return -1;
							final double compare = _bridged.getComparator().compare(o1.getLowerBound(), o2.getLowerBound());
							if(compare!=0)
								return (compare>0)?1:-1;
							
							if(o1.isLowerBoundClosed())
								if(o2.isLowerBoundClosed())
									return 0;
								else
									return 1;

							if(o2.isLowerBoundClosed())
								return -1;
							return 0;
						}
					}
				);
		final ContinuousComparator<? super Data> comparator = _bridged.getComparator();
		for(final ChildType child:getChildren())
		{
			final ICompositeContinuousGenericSet<Data, ?> mergedSet = child.getMergedSet();
			for(final IContinuousGenericSet<Data> lChild:mergedSet.getChildren())
			{
				toMerge.clear();
				for(final IContinuousGenericSet<Data> alreadyInSet:collection)
				{
					if((alreadyInSet.intersects(lChild))
						|| ((comparator.compare(alreadyInSet.getUpperBound(), lChild.getLowerBound()) == 0) && (alreadyInSet.isUpperBoundClosed() || lChild.isLowerBoundClosed()))
						|| ((comparator.compare(alreadyInSet.getLowerBound(), lChild.getUpperBound()) == 0) && (alreadyInSet.isLowerBoundClosed() || lChild.isUpperBoundClosed())))
					{
						toMerge.add(alreadyInSet);
					}
				}
				if(toMerge.isEmpty())
					collection.add(lChild);
				else
				{
					final Data intervalStart = toMerge.first().getLowerBound();
					final boolean closedStart = toMerge.first().isLowerBoundClosed();
					Data intervalEnd = lChild.getUpperBound();
					boolean closedEnd = lChild.isUpperBoundClosed();
					for(final IContinuousGenericSet<Data> mergeElement:toMerge)
					{
						if(comparator.compare(mergeElement.getUpperBound(), intervalEnd)>1)
						{
							intervalEnd = mergeElement.getUpperBound();
							closedEnd = mergeElement.isUpperBoundClosed();
						}
						else if((comparator.compare(mergeElement.getUpperBound(), intervalEnd) == 0) && (!closedEnd) && (mergeElement.isUpperBoundClosed()))
							closedEnd = true;
						collection.remove(mergeElement);
					}
					if((comparator.compare(intervalStart,intervalEnd)==0)&&(lChild.isLowerBoundClosed()==closedStart)&&(lChild.isUpperBoundClosed()==closedEnd))
						collection.add(lChild);
					else
						collection.add(BaseContinuousGenericInterval.makeInterval(intervalStart, closedStart, intervalEnd, closedEnd, comparator));
				}
			}
		}
		return BaseContinuousGenericSet.makeSet(collection, comparator);
	}
	
	@Override
	public String toString()
	{
		switch(getChildren().size())
		{
		case 0:
			return "∅";
		case 1:
			return getChildren().first().toString();
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