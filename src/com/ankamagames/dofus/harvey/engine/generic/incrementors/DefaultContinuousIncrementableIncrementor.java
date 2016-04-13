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
public class DefaultContinuousIncrementableIncrementor<Data extends ContinuousIncrementable<Data>>
	implements ContinuousIncrementor<Data>
{
	private static DefaultContinuousIncrementableIncrementor<?> _instance = makeInstance();
	
	private static <Data extends ContinuousIncrementable<Data>> DefaultContinuousIncrementableIncrementor<Data> makeInstance()
	{
		return new DefaultContinuousIncrementableIncrementor<Data>();
	}

	@SuppressWarnings("unchecked")
	public static <Data extends ContinuousIncrementable<Data>> DefaultContinuousIncrementableIncrementor<Data> getInstance()
	{
		return (DefaultContinuousIncrementableIncrementor<Data>)_instance ;
	}
	
	private DefaultContinuousIncrementableIncrementor()
	{}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.generic.sets.interfaces.Incrementor#getNext(java.lang.Object, int)
	 */
	@Override
	public @Nullable Data getNext(@Nullable final Data value, final double steps)
	{
		if(value==null)
			return null;
		return value.getNext(steps);
	}
}