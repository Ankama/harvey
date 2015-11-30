/**
 *
 */
package com.ankamagames.dofus.harvey.singlevalues;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.interfaces.singlevalues.ISingleValueRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class SingleValueRandomVariable<Data>
	implements ISingleValueRandomVariable<Data>
{
	protected @Nullable Data _value;

	public SingleValueRandomVariable(@Nullable final Data value)
	{
		_value = value;
	}

	public SingleValueRandomVariable(
			final SingleValueRandomVariable<Data> singleValueRandomVariable)
	{
		_value = singleValueRandomVariable._value;
	}

	@Override
	public @Nullable Data getValue()
	{
		return _value;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.interfaces.IRandomVariable#getProbabilityOf(java.lang.Object)
	 */
	@Override
	public int getProbabilityOf(final @Nullable Data value)
	{
		return contains(value)?RandomVariableUtils.ONE:0;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.interfaces.IRandomVariable#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(final @Nullable Data value)
	{
		if(value==null)
			return (_value==null);
		return value.equals(_value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.interfaces.IRandomVariable#isEmpty()
	 */
	@Override
	public boolean isEmpty()
	{
		return false;
	}
}
