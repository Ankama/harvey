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
public class DefaultUncheckedContinuousComparableComparator<Data>
	implements ContinuousComparator<Data>
{
	@SuppressWarnings("rawtypes")
	static protected DefaultUncheckedContinuousComparableComparator _instance = new DefaultUncheckedContinuousComparableComparator();
	
	@SuppressWarnings("unchecked")
	static public <Data> DefaultUncheckedContinuousComparableComparator<Data> getInstance()
	{
		return _instance;
	}
	
	protected DefaultUncheckedContinuousComparableComparator()
	{
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public double compare(@Nullable final Data o1, @Nullable final Data o2)
	{
		if(o1 != null)
			return ((ContinuousComparable<Data>)o1).compareTo(o2);
		if(o2 != null)
			return ((ContinuousComparable<Data>)o2).compareTo(o1);
		return 0;
	}
}
