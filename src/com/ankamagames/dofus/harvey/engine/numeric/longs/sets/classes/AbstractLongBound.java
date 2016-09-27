/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.interfaces.IILongBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public abstract class AbstractLongBound<Bound extends IBound<Bound>&IILongBound>
implements IILongBound, IBound<Bound>
{
	protected long _value;

	protected AbstractLongBound(final long value)
	{
		_value = value;
	}

	@Override
	public boolean equals(@Nullable final Object obj)
	{
		if(obj instanceof IILongBound && obj instanceof IBound)
			return compareTo((Bound)obj) == 0;
		return false;
	}

	@Override
	public int hashCode()
	{
		return ((Long)_value).hashCode();
	}

	@Override
	public long getValue()
	{
		return _value;
	}

	@Override
	public int compareTo(@Nullable final Bound o)
	{
		if(o==null)
			return 1;
		return (int)(_value - o.getValue());
	}
}