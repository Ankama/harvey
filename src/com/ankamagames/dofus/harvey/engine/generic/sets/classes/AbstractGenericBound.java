/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.generic.sets.interfaces.IIGenericBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public abstract class AbstractGenericBound<Data, Bound extends IBound<Bound>&IIGenericBound<Data>>
implements IIGenericBound<Data>, IBound<Bound>
{
	protected @Nullable Data _value;

	protected AbstractGenericBound(@Nullable final Data value)
	{
		_value = value;
	}

	protected abstract Comparator<? super Data> getComparator();

	@Override
	public boolean equals(@Nullable final Object obj)
	{
		if(obj instanceof IIGenericBound && obj instanceof IBound)
			return compareTo((Bound)obj) == 0;
		return false;
	}

	@Override
	public int hashCode()
	{
		if(_value != null)
			return _value.hashCode();
		return 0;
	}

	@Override
	public @Nullable Data getValue()
	{
		return _value;
	}

	@Override
	public int compareTo(@Nullable final Bound o)
	{
		return getComparator().compare(_value, (o!=null)?o.getValue():null);
	}
}