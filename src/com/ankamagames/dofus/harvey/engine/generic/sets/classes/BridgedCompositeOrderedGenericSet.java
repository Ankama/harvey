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

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractBridgedCompositeOrderedSet;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.Incrementor;
import com.ankamagames.dofus.harvey.generic.sets.classes.EmptyOrderedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ICompositeOrderedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IOrderedGenericSet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedCompositeOrderedGenericSet
<
	Data,
	ChildType extends IOrderedGenericSet<Data>,
	Bridged extends ICompositeOrderedGenericSet<Data, ChildType>
>
extends AbstractBridgedCompositeOrderedSet
<
	IOrderedGenericSet<Data>,
	ChildType,
	Bridged
>
{
	protected Bridged _bridged;
	
	protected Incrementor<Data> _incrementor;
	
	protected TreeSet<ChildType>			_children;
	protected TreeSet<ChildType>			_descChildren;
	
	public BridgedCompositeOrderedGenericSet(final Bridged bridged, final Incrementor<Data> incrementor)
	{
		_bridged = bridged;
		_incrementor = incrementor;
		_children = new TreeSet<ChildType>(createAscendingComparator());
		_descChildren = new TreeSet<ChildType>(createDescendingComparator());
	}
	
	public BridgedCompositeOrderedGenericSet(final Bridged bridged, final ChildType element, final Incrementor<Data> incrementor)
	{
		this(bridged, incrementor);
		_children.add(element);
		_descChildren.add(element);
	}
	
	public BridgedCompositeOrderedGenericSet(final Bridged bridged, final Collection<? extends ChildType> collection, final Incrementor<Data> incrementor)
	{
		this(bridged, incrementor);
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
				return _bridged.getComparator().compare(o1.getLowerBound(), o2.getLowerBound());
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
				return _bridged.getComparator().compare(o2.getUpperBound(), o1.getUpperBound());
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

	public boolean isInterval()
	{
		final BaseOrderedGenericSet<Data, ?> allBoundaries = getMergedSet();
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

	public boolean containsAllValuesInRange(final IOrderedGenericSet<Data> set)
	{
		return containsAllValuesFromAndUpTo(set.getLowerBound(), set.getUpperBound());
	}
	
	public boolean containsAllValuesFromAndUpTo(@Nullable final Data value, @Nullable final Data upTo)
	{
		final BaseOrderedGenericSet<Data, ?> mergedSet = getMergedSet();
		for(final IOrderedGenericSet<Data> child:mergedSet.getChildren())
		{
			if(child.contains(value))
				if(child.contains(upTo))
					return true;
				else
					return false;
		}
		return false;
	}

	@Override
	public boolean contains(final IOrderedGenericSet<Data> set)
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
		
		for(final IOrderedGenericSet<Data> element:set.getMergedSet().getChildren())
		{
			if(!contains(element))
				return false;
		}
		return true;
	}

	protected List<? extends IOrderedGenericSet<Data>> buildCompositeFromArrays(
		final ArrayList<ArrayList<IOrderedGenericSet<Data>>> arrays, final Comparator<? super Data> comparator)
	{
		final ArrayList<IOrderedGenericSet<Data>> r =
				new ArrayList<IOrderedGenericSet<Data>>(arrays.size());
		
		for(final ArrayList<IOrderedGenericSet<Data>> array:arrays)
		{
			if(array.size()==1)
				r.add(array.get(0));
			else
				r.add(BaseOrderedGenericSet.makeSet(array, comparator, _incrementor));
		}
		
		return r;
	}


	@SuppressWarnings("unchecked")
	public List<? extends IOrderedGenericSet<Data>> splitOnRange(final IOrderedGenericSet<Data> set)
	{
		return split((Data[])new Object[]{set.getLowerBound(), set.getUpperBound()}, new boolean[]{true, false});
	}
	
	public List<? extends IOrderedGenericSet<Data>> split(final Data[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IOrderedGenericSet<Data>> r =
				new ArrayList<IOrderedGenericSet<Data>>(values.length+1);

		final EmptyOrderedGenericSet<Data> empty = EmptyOrderedGenericSet.getInstance();
		int i;
		Data value = null;
		final Comparator<? super Data> comparator = _bridged.getComparator();
		for(i = 0 ; i < values.length ; i++)
		{
			value = values[i];
			final int compare = comparator.compare(getLowerBound(), value);
			if((compare<0)||((compare==0)&&(!isIntervalStart[i])))
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
			final int compare = comparator.compare(getUpperBound(), value);
			if((compare>0)||((compare==0)&&(isIntervalStart[j])))
				break;
		}
		if(j < i)
		{
			r.add(_bridged);
			for(j++ ; j < values.length ; j++)
				r.add(empty);
			return r;
		}
		
		final ArrayList<IOrderedGenericSet<Data>> emptyArray = new ArrayList<IOrderedGenericSet<Data>>(1);
		emptyArray.add(empty);

		final ArrayList<ArrayList<IOrderedGenericSet<Data>>> tmpBuffer =
				new ArrayList<ArrayList<IOrderedGenericSet<Data>>>((j+2)-i);		//j+2 = values.length - (values.length-1 - j) + 1
		Collections.fill(tmpBuffer, emptyArray);
		
		final Data[] lValues = Arrays.copyOfRange(values, i, j+1);
		final boolean[] lIsIntervalStart = Arrays.copyOfRange(isIntervalStart, i, j+1);
		for(final ChildType child : _bridged.getChildren())
		{
			final List<? extends IOrderedGenericSet<Data>> childSplit = child.split(values, isIntervalStart);
			int k = i;
			for(final IOrderedGenericSet<Data> elmt:childSplit)
			{
				if(!elmt.isEmpty())
				{
					final ArrayList<IOrderedGenericSet<Data>> arrayList = tmpBuffer.get(k);
					if((arrayList.size()==1)&&(arrayList.contains(emptyArray)))
						arrayList.clear();
					arrayList.add(elmt);
				}
					
				k++;
			}
		}
		
		final List<? extends IOrderedGenericSet<Data>> compositeArray = buildCompositeFromArrays(tmpBuffer, _bridged.getComparator());
		for(final IOrderedGenericSet<Data> composite:compositeArray)
		{
			r.add(i, composite);
			i++;
		}
		
		return r;
	}

	public BaseOrderedGenericSet<Data, ?> getMergedSet()
	{
		final ArrayList<IOrderedGenericSet<Data>> collection = new ArrayList<IOrderedGenericSet<Data>>(4);
		final TreeSet<IOrderedGenericSet<Data>> toMerge = new TreeSet<IOrderedGenericSet<Data>>(
				new Comparator<IOrderedGenericSet<Data>>()
					{
						@Override
						public int compare(@Nullable final IOrderedGenericSet<Data> o1, @Nullable final IOrderedGenericSet<Data> o2)
						{
							if(o1==null)
								if(o2==null)
									return 0;
								else
									return 1;
							if(o2==null)
								return -1;
							return _bridged.getComparator().compare(o1.getLowerBound(), o2.getLowerBound());
						}
					}
				);
		for(final ChildType child:getChildren())
		{
			final ICompositeOrderedGenericSet<Data, ?> mergedSet = child.getMergedSet();
			for(final IOrderedGenericSet<Data> lChild:mergedSet.getChildren())
			{
				final Data formerElmt = _incrementor.getNext(lChild.getLowerBound(), -1);
				final Data nextElmt = _incrementor.getNext(lChild.getUpperBound(), 1);
				toMerge.clear();
				for(final IOrderedGenericSet<Data> alreadyInSet:collection)
				{
					if((alreadyInSet.intersects(lChild))||(alreadyInSet.contains(formerElmt))||((alreadyInSet.contains(nextElmt))))
					{
						toMerge.add(alreadyInSet);
					}
				}
				if(toMerge.isEmpty())
					collection.add(lChild);
				else
				{
					final Data intervalStart = toMerge.first().getLowerBound();
					Data intervalEnd = lChild.getUpperBound();
					for(final IOrderedGenericSet<Data> mergeElement:toMerge)
					{
						if(_bridged.getComparator().compare(mergeElement.getUpperBound(), intervalEnd)>1)
							intervalEnd = mergeElement.getUpperBound();
						collection.remove(mergeElement);
					}
					if(_bridged.getComparator().compare(intervalStart,intervalEnd)==0)
						collection.add(lChild);
					else
						collection.add(BaseGenericInterval.makeInterval(intervalStart, intervalEnd, _bridged.getComparator(), _incrementor));
				}
			}
		}
		return BaseOrderedGenericSet.makeSet(collection, _bridged.getComparator(), _incrementor);
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
