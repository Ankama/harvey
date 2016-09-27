/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.interfaces.IIIntegerBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public abstract class AbstractIntegerBound<Bound extends IBound<Bound>&IIIntegerBound>
implements IIIntegerBound, IBound<Bound>
{
	protected int _value;

	protected AbstractIntegerBound(final int value)
	{
		_value = value;
	}

	@Override
	public boolean equals(@Nullable final Object obj)
	{
		if(obj instanceof IIIntegerBound && obj instanceof IBound)
			return compareTo((Bound)obj) == 0;
		return false;
	}

	@Override
	public int hashCode()
	{
		return ((Integer)_value).hashCode();
	}

	@Override
	public int getValue()
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