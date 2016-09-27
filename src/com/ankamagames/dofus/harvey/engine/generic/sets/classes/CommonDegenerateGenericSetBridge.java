/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.SingleValueIterator;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class CommonDegenerateGenericSetBridge<Data>
{
	protected @Nullable Data _value;

	public CommonDegenerateGenericSetBridge(@Nullable final Data value)
	{
		_value = value;
	}

	@Nullable
	public Data getValue()
	{
		return _value;
	}

	public boolean contains(final @Nullable Data value)
	{
		final Data lValue = getValue();
		if(lValue!=null)
			return lValue.equals(value);
		return value==null;
	}

	public Iterator<Data> getDataIterator()
	{
		return new SingleValueIterator<Data>(getValue());
	}

	@Override
	public String toString()
	{
		final Data value = getValue();
		if(value==null)
			return "null";
		return value.toString();
	}
}