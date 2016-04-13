/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.comparators;

import java.util.Comparator;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class DefaultUncheckedComparableComparator<Data>
	implements Comparator<Data>
{
	@SuppressWarnings("rawtypes")
	static protected DefaultUncheckedComparableComparator _instance = new DefaultUncheckedComparableComparator();
	
	@SuppressWarnings("unchecked")
	static public <Data> DefaultUncheckedComparableComparator<Data> getInstance()
	{
		return _instance;
	}
	
	protected DefaultUncheckedComparableComparator()
	{
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int compare(@Nullable final Data o1, @Nullable final Data o2)
	{
		if(o1 != null)
			return ((Comparable<Data>)o1).compareTo(o2);
		if(o2 != null)
			return ((Comparable<Data>)o2).compareTo(o1);
		return 0;
	}
}
