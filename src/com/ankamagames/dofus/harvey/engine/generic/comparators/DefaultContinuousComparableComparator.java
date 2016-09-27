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
public class DefaultContinuousComparableComparator<Data extends ContinuousComparable<? super Data>>
//	extends DefaultUncheckedContinuousComparableComparator<Data>
implements ContinuousComparator<Data>
{
	@SuppressWarnings("rawtypes")
	static protected DefaultContinuousComparableComparator _instance = new DefaultContinuousComparableComparator();

	@SuppressWarnings("unchecked")
	static public <Data extends ContinuousComparable<? super Data>> DefaultContinuousComparableComparator<Data> getInstance()
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
	public int compare(final @Nullable Data o1, final @Nullable Data o2)
	{
		return (int) Math.signum(compareContinuous(o1,o2));
	}

	@Override
	public double compareContinuous(final @Nullable Data o1, final @Nullable Data o2)
	{
		if(o1 != null)
			return o1.compareToContinuous(o2);
		if(o2 != null)
			return o2.compareToContinuous(o1);
		return 0;
	}
}