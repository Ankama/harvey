/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.comparators;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class DefaultComparableComparator<Data extends Comparable<Data>>
	extends DefaultUncheckedComparableComparator<Data>
{
	@SuppressWarnings("rawtypes")
	static protected DefaultComparableComparator _instance = new DefaultComparableComparator();
	
	@SuppressWarnings("unchecked")
	static public <Data extends Comparable<Data>> DefaultComparableComparator<Data> getInstance()
	{
		return _instance;
	}
	
	protected DefaultComparableComparator()
	{
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(@Nullable Data o1, @Nullable Data o2)
	{
		if(o1 != null)
			return o1.compareTo(o2);
		if(o2 != null)
			return o2.compareTo(o1);
		return 0;
	}
}