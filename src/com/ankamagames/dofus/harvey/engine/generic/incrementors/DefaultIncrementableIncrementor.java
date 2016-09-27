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
public class DefaultIncrementableIncrementor<Data extends Incrementable<Data>>
	implements Incrementor<Data>
{
	private static DefaultIncrementableIncrementor<?> _instance = makeInstance();

	private static <Data extends Incrementable<Data>> DefaultIncrementableIncrementor<Data> makeInstance()
	{
		return new DefaultIncrementableIncrementor<Data>();
	}

	@SuppressWarnings("unchecked")
	public static <Data extends Incrementable<Data>> DefaultIncrementableIncrementor<Data> getInstance()
	{
		return (DefaultIncrementableIncrementor<Data>)_instance ;
	}

	private DefaultIncrementableIncrementor()
	{}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.generic.sets.interfaces.Incrementor#getNext(java.lang.Object, int)
	 */
	@Override
	public @Nullable Data getNextValue(@Nullable final Data value)
	{
		if(value==null)
			return null;
		return value.getNextValue();
	}
}