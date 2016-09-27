/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.interfaces.IIByteBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public abstract class AbstractByteBound<Bound extends IBound<Bound>&IIByteBound>
implements IIByteBound, IBound<Bound>
{
	protected byte _value;

	protected AbstractByteBound(final byte value)
	{
		_value = value;
	}

	@Override
	public boolean equals(@Nullable final Object obj)
	{
		if(obj instanceof IIByteBound && obj instanceof IBound)
			return compareTo((Bound)obj) == 0;
		return false;
	}

	@Override
	public int hashCode()
	{
		return ((Byte)_value).hashCode();
	}

	@Override
	public byte getValue()
	{
		return _value;
	}

	@Override
	public int compareTo(@Nullable final Bound o)
	{
		if(o==null)
			return 1;
		return _value - o.getValue();
	}
}