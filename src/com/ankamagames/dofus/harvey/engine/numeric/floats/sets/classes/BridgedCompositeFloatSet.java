/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes;

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
import com.ankamagames.dofus.harvey.numeric.floats.sets.classes.EmptyFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.ICompositeFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatSet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedCompositeFloatSet<ChildType extends IFloatSet, Bridged extends ICompositeFloatSet<ChildType>>
	extends AbstractBridgedCompositeOrderedSet
	<
		IFloatSet,
		ChildType,
		Bridged
	>
{
	protected Bridged _bridged;

	protected TreeSet<ChildType>	_children;
	protected TreeSet<ChildType>	_descChildren;
	
	public BridgedCompositeFloatSet(final Bridged bridged)
	{
		_bridged = bridged;
		_children = new TreeSet<ChildType>(createAscendingComparator());
		_descChildren = new TreeSet<ChildType>(createDescendingComparator());
	}
	
	public BridgedCompositeFloatSet(final Bridged bridged, final ChildType element)
	{
		this(bridged);
		_children.add(element);
		_descChildren.add(element);
	}
	
	public BridgedCompositeFloatSet(final Bridged bridged, final Collection<? extends ChildType> collection)
	{
		this(bridged);
		for(final ChildType child:collection)
		{
			_children.add(child);
			_descChildren.add(child);
		}
	}

	private Comparator<? super IFloatSet> createAscendingComparator()
	{
		return new Comparator<IFloatSet>()
		{
			@Override
			public int compare(@Nullable final IFloatSet o1, @Nullable final IFloatSet o2)
			{
				if(o1==null)
					if(o2==null)
						return 0;
					else
						return 1;
				if(o2==null)
					return -1;
				final int compare = Float.compare(o1.getLowerBound(), o2.getLowerBound());
				if(compare!=0)
					return compare;
				
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

	private Comparator<? super IFloatSet> createDescendingComparator()
	{
		return new Comparator<IFloatSet>()
		{
			@Override
			public int compare(@Nullable final IFloatSet o1, @Nullable final IFloatSet o2)
			{
				if(o1==null)
					if(o2==null)
						return 0;
					else
						return -1;
				if(o2==null)
					return 1;
				final int compare = Float.compare(o2.getUpperBound(), o1.getUpperBound());
				if(compare!=0)
					return compare;
				
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

	public float getLowerBound()
	{
		return getChildren().first().getLowerBound();
	}

	public float getUpperBound()
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
		final BaseFloatSet<?> allBoundaries = getMergedSet();
		return allBoundaries.getChildren().size()<=1;
	}
	
	public boolean contains(final float value)
	{
		for(final IFloatSet child:getChildren())
		{
			if(child.contains(value))
				return true;
		}
		return false;
	}

	public boolean containsAllValuesInRange(final IFloatSet set)
	{
		return containsAllValuesFromAndUpTo(set.getLowerBound(), set.isLowerBoundClosed(), set.getUpperBound(), set.isUpperBoundClosed());
	}
	
	public boolean containsAllValuesFromAndUpTo(final float value, final float upTo)
	{
		return containsAllValuesFromAndUpTo(value, true, upTo, true);
	}
	
	public boolean containsAllValuesFromAndUpTo(final float value, final boolean valueIncluded, final float upTo, final boolean upToIncluded)
	{
		final BaseFloatSet<?> mergedSet = getMergedSet();
		for(final IFloatSet child:mergedSet.getChildren())
		{
			if((child.contains(value))||((!valueIncluded)&&((child.getLowerBound()==value)&&(!child.isLowerBoundClosed()))))
				if((child.contains(upTo))||((!upToIncluded)&&((child.getUpperBound()==upTo)&&(!child.isUpperBoundClosed()))))
					return true;
				else
					return false;
		}
		return false;
	}

	@Override
	public boolean contains(final IFloatSet set)
	{
		if(set.isEmpty())
			return true;
		
		if(isEmpty())
			return false;
		
		if(set.isDegenerate())
		{
			for(final IFloatSet child:getChildren())
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
		
		for(final IFloatSet element:set.getMergedSet().getChildren())
		{
			if(!contains(element))
				return false;
		}
		return true;
	}

	protected List<? extends IFloatSet> buildCompositeFromArrays(final ArrayList<ArrayList<IFloatSet>> arrays)
	{
		final ArrayList<IFloatSet> r =
				new ArrayList<IFloatSet>(arrays.size());
		
		for(final ArrayList<IFloatSet> array:arrays)
		{
			if(array.size()==1)
				r.add(array.get(0));
			else
				r.add(BaseFloatSet.makeSet(array));
		}
		
		return r;
	}

	public List<? extends IFloatSet> splitOnRange(final IFloatSet set)
	{
		return split(new float[]{set.getLowerBound(), set.getUpperBound()}, new boolean[]{set.isLowerBoundClosed(), !set.isLowerBoundClosed()});
	}
	
	public List<? extends IFloatSet> split(final float[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IFloatSet> r =
				new ArrayList<IFloatSet>(values.length+1);

		final EmptyFloatSet empty = EmptyFloatSet.getInstance();
		int i;
		float value = 0;
		for(i = 0 ; i < values.length ; i++)
		{
			value = values[i];
			if((getLowerBound()<value)||((getLowerBound()==value)&&(isLowerBoundClosed())&&(!isIntervalStart[i])))
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
			if((getUpperBound()>value)||((getUpperBound()==value)&&(isUpperBoundClosed())&&(isIntervalStart[j])))
				break;
		}
		if(j < i)
		{
			r.add(_bridged);
			for(j++ ; j < values.length ; j++)
				r.add(empty);
			return r;
		}
		
		final ArrayList<IFloatSet> emptyArray = new ArrayList<IFloatSet>(1);
		emptyArray.add(empty);

		final ArrayList<ArrayList<IFloatSet>> tmpBuffer =
				new ArrayList<ArrayList<IFloatSet>>((j+2)-i);		//j+2 = values.length - (values.length-1 - j) + 1
		Collections.fill(tmpBuffer, emptyArray);
		
		final float[] lValues = Arrays.copyOfRange(values, i, j+1);
		final boolean[] lIsIntervalStart = Arrays.copyOfRange(isIntervalStart, i, j+1);
		for(final IFloatSet child : _bridged.getChildren())
		{
			final List<? extends IFloatSet> childSplit = child.split(lValues, lIsIntervalStart);
			int k = i;
			for(final IFloatSet elmt:childSplit)
			{
				if(!elmt.isEmpty())
				{
					final ArrayList<IFloatSet> arrayList = tmpBuffer.get(k);
					if((arrayList.size()==1)&&(arrayList.contains(emptyArray)))
						arrayList.clear();
					arrayList.add(elmt);
				}
					
				k++;
			}
		}
		
		final List<? extends IFloatSet> compositeArray = buildCompositeFromArrays(tmpBuffer);
		for(final IFloatSet composite:compositeArray)
		{
			r.add(i, composite);
			i++;
		}
		
		return r;
	}

	public BaseFloatSet<?> getMergedSet()
	{
		final ArrayList<IFloatSet> collection = new ArrayList<IFloatSet>(4);
		final TreeSet<IFloatSet> toMerge = new TreeSet<IFloatSet>(
				new Comparator<IFloatSet>()
				{
					@Override
					public int compare(@Nullable final IFloatSet o1, @Nullable final IFloatSet o2)
					{
						if(o1==null)
							if(o2==null)
								return 0;
							else
								return 1;
						if(o2==null)
							return -1;
						if(o1.getLowerBound()!=o2.getLowerBound())
							return Float.compare(o1.getLowerBound(), o2.getLowerBound());
						
						if(o1.isLowerBoundClosed())
							if(o2.isLowerBoundClosed())
								return 0;
							else
								return 1;
	
						if(o2.isLowerBoundClosed())
							return -1;
						return 0;
					}
				});
		for(final IFloatSet child:getChildren())
		{
			final ICompositeFloatSet<? extends IFloatSet> mergedSet = child.getMergedSet();
			for(final IFloatSet lChild:mergedSet.getChildren())
			{
				toMerge.clear();
				for(final IFloatSet alreadyInSet:collection)
				{
					if((alreadyInSet.intersects(lChild))||
							((alreadyInSet.getUpperBound()==lChild.getLowerBound())&&(alreadyInSet.isUpperBoundClosed()||lChild.isLowerBoundClosed()))||
							((alreadyInSet.getLowerBound()==lChild.getUpperBound())&&(alreadyInSet.isLowerBoundClosed()||lChild.isUpperBoundClosed())))
					{
						toMerge.add(alreadyInSet);
					}
				}
				if(toMerge.isEmpty())
					collection.add(lChild);
				else
				{
					final float intervalStart = toMerge.first().getLowerBound();
					final boolean closedStart = toMerge.first().isLowerBoundClosed();
					float intervalEnd = lChild.getUpperBound();
					boolean closedEnd = lChild.isUpperBoundClosed();
					for(final IFloatSet mergeElement:toMerge)
					{
						if(mergeElement.getUpperBound() > intervalEnd)
						{
							intervalEnd = mergeElement.getUpperBound();
							closedEnd = mergeElement.isUpperBoundClosed();
						}
						else if((mergeElement.getUpperBound() == intervalEnd)&&(!closedEnd)&&(mergeElement.isUpperBoundClosed()))
							closedEnd = true;
						collection.remove(mergeElement);
					}
					if((intervalStart==intervalEnd)&&(lChild.isLowerBoundClosed()==closedStart)&&(lChild.isUpperBoundClosed()==closedEnd))
						collection.add(lChild);
					else
						collection.add(BaseFloatInterval.makeInterval(intervalStart, closedStart, intervalEnd, closedEnd));
				}
			}
		}
		return BaseFloatSet.makeSet(collection);
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
			for(final IFloatSet child:getChildren())
			{
				r +=  child.toString() + " ; ";
			}
			r = r.substring(0, r.length()-2);
			r+="}";
			return r;
		}
	}
}