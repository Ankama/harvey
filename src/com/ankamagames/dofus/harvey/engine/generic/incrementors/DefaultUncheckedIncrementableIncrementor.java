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
public class DefaultUncheckedIncrementableIncrementor<Data>
	implements Incrementor<Data>
{
	private static DefaultUncheckedIncrementableIncrementor<?> _instance = makeInstance();

	private static <Data> DefaultUncheckedIncrementableIncrementor<Data> makeInstance()
	{
		return new DefaultUncheckedIncrementableIncrementor<Data>();
	}

	@SuppressWarnings("unchecked")
	public static <Data> DefaultUncheckedIncrementableIncrementor<Data> getInstance()
	{
		return (DefaultUncheckedIncrementableIncrementor<Data>)_instance ;
	}

	private DefaultUncheckedIncrementableIncrementor()
	{}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.generic.sets.interfaces.Incrementor#getNext(java.lang.Object, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public @Nullable Data getNextValue(@Nullable final Data value)
	{
		if(value==null)
			return null;
		return ((Incrementable<Data>)value).getNextValue();
	}
}