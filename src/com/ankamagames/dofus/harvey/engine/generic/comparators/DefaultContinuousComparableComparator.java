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
public class DefaultContinuousComparableComparator<Data extends ContinuousComparable<Data>>
	extends DefaultUncheckedContinuousComparableComparator<Data>
{
	@SuppressWarnings("rawtypes")
	static protected DefaultContinuousComparableComparator _instance = new DefaultContinuousComparableComparator();
	
	@SuppressWarnings("unchecked")
	static public <Data extends ContinuousComparable<Data>> DefaultContinuousComparableComparator<Data> getInstance()
	{
		return _instance;
	}
	
	protected DefaultContinuousComparableComparator()
	{
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public double compare(@Nullable final Data o1, @Nullable final Data o2)
	{
		if(o1 != null)
			return o1.compareTo(o2);
		if(o2 != null)
			return o2.compareTo(o1);
		return 0;
	}
}