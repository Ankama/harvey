/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.interfaces.IIShortBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public abstract class AbstractShortBound<Bound extends IBound<Bound>&IIShortBound>
implements IIShortBound, IBound<Bound>
{
	protected short _value;

	protected AbstractShortBound(final short value)
	{
		_value = value;
	}

	@Override
	public boolean equals(@Nullable final Object obj)
	{
		if(obj instanceof IIShortBound && obj instanceof IBound)
			return compareTo((Bound)obj) == 0;
		return false;
	}

	@Override
	public int hashCode()
	{
		return ((Short)_value).hashCode();
	}

	@Override
	public short getValue()
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