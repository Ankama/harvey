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
public class DefaultChainedValueSurroundingProvider<Data extends ChainedValue<Data>>
	implements SurroundingValuesProvider<Data>
{
	private static DefaultChainedValueSurroundingProvider<?> _instance = makeInstance();

	private static <Data extends ChainedValue<Data>> DefaultChainedValueSurroundingProvider<Data> makeInstance()
	{
		return new DefaultChainedValueSurroundingProvider<Data>();
	}

	@SuppressWarnings("unchecked")
	public static <Data extends ChainedValue<Data>> DefaultChainedValueSurroundingProvider<Data> getInstance()
	{
		return (DefaultChainedValueSurroundingProvider<Data>)_instance ;
	}

	private DefaultChainedValueSurroundingProvider()
	{}

	@Override
	public @Nullable Data getSuccessor(@Nullable final Data value)
	{
		if(value==null)
			return null;
		return value.getSuccessor();
	}

	@Override
	public @Nullable Data getPredecessor(@Nullable final Data value)
	{
		if(value==null)
			return null;
		return value.getPredecessor();
	}

	@Override
	public @Nullable Data getSuccessor(@Nullable final Data value, final int times)
	{
		if(value == null)
			return null;
		return value.getSuccessor(times);
	}
}