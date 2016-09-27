/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes;

import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class LongBound
extends AbstractLongBound<ILongBound>
implements ILongBound
{

	public static  LongBound makeBound(final long value)
	{
		return new LongBound(value);
	}

	protected LongBound(final long value)
	{
		super(value);
	}

	@Override
	public boolean isPreceding(final @Nullable ILongBound bound)
	{
		if(bound == null)
			return false;
		final long predecessor = (long) (bound.getValue()-1);
		return predecessor == getValue();
	}

	@Override
	public boolean isSucceeding(final @Nullable ILongBound bound)
	{
		if(bound == null)
			return false;
		final long succcessor = (long) (bound.getValue()+1);
		return succcessor == getValue();
	}

	@Override
	public String toString()
	{
		return ((Long)_value).toString();
	}
}