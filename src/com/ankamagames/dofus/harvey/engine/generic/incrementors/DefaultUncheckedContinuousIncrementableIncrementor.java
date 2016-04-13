/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.incrementors;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class DefaultUncheckedContinuousIncrementableIncrementor<Data>
	implements ContinuousIncrementor<Data>
{
	private static DefaultUncheckedContinuousIncrementableIncrementor<?> _instance = makeInstance();
	
	private static <Data> DefaultUncheckedContinuousIncrementableIncrementor<Data> makeInstance()
	{
		return new DefaultUncheckedContinuousIncrementableIncrementor<Data>();
	}

	@SuppressWarnings("unchecked")
	public static <Data> DefaultUncheckedContinuousIncrementableIncrementor<Data> getInstance()
	{
		return (DefaultUncheckedContinuousIncrementableIncrementor<Data>)_instance ;
	}
	
	private DefaultUncheckedContinuousIncrementableIncrementor()
	{}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.generic.sets.interfaces.Incrementor#getNext(java.lang.Object, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public @Nullable Data getNext(@Nullable final Data value, final double steps)
	{
		if(value==null)
			return null;
		return ((ContinuousIncrementable<Data>)value).getNext(steps);
	}
}